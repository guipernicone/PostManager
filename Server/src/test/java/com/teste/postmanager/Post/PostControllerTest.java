package com.teste.postmanager.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testNewPost() throws Exception {
		
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post")
		        .queryParam("text", "My First Test Post");
		URI uri = urlBuilder.build().toUri();
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, null, String.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testGetPosts() throws Exception {
		// Insert a post before the test
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post")
		        .queryParam("text", "My First Test Post");
		URI uri = urlBuilder.build().toUri();
		
		this.restTemplate.postForEntity(uri, null, String.class);
		
		// Execute the test
		urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post")
		        .queryParam("page", "1")
				.queryParam("size", "1");
		uri = urlBuilder.build().toUri();
		
		ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("\"empty\":false"));
	}
	
	@Test
	public void testUpvote() throws Exception {
		//restTemplate do not support PATCH
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		// Insert a post before the test
		UriComponentsBuilder urlBuilder;
		URI uri;
		
		urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post")
		        .queryParam("text", "My First Test Post");
		uri = urlBuilder.build().toUri();
		
		ResponseEntity<String> response = this.restTemplate.postForEntity(uri, null, String.class);
		
		String postId = response.getBody();
		
		// Execute the test
		urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post/" + postId)
		        .queryParam("page", "1")
				.queryParam("size", "1");
		uri = urlBuilder.build().toUri();
		
		String responseUpvote = this.restTemplate.patchForObject(uri, null, String.class);
	
		assertEquals("1", responseUpvote);
	}
	
	@Test
	public void testUpvoteInvalidId() throws Exception {
		//restTemplate do not support PATCH
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	
		// Execute the test
		UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port)
				.path("/post/ivalidId")
		        .queryParam("page", "1")
				.queryParam("size", "1");
		URI uri = urlBuilder.build().toUri();
		
		String responseUpvote = this.restTemplate.patchForObject(uri, null, String.class);
		
		assertEquals("Post from given id not found", responseUpvote);
	}
}
