package com.teste.postmanager.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

@SpringBootTest
public class PostServiceTest {

	@Mock
	private PostRepository postRepository;
	
	@InjectMocks
	private PostService postService;
	
	@Test
	public void testCreatePost() {
		Post post = new Post("teste");
		post.setId("id");
		when(postRepository.save(any())).thenReturn(post);
		String id = postService.createPost("First post test");
		
		ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
		verify(postRepository, times(1)).save(argument.capture());
		
		assertEquals("First post test", argument.getValue().getText());
		assertEquals("id", id);
	}
	
	@Test
	public void testGetPostList() {
		Pageable page = PageRequest.of(0, 1, Direction.DESC, "date");
		List<Post> postList = new ArrayList<Post>();
		Post post = new Post("First post test");
		postList.add(post);
		Page<Post> pagePost = new PageImpl<Post>(postList);
		
		when(postRepository.findAll(page)).thenReturn(pagePost);
	
		Page<Post> res = postService.getPostList(0,1);
		
		List<Post> resultList = res.getContent();
		
		assertTrue(resultList.contains(post));
		assertFalse(res.isEmpty());
	}
	
	@Test
	public void testUpdateUpvoteWhenPresent() {
		Post post = new Post("First post test");
		Optional<Post> optionalPost = Optional.of(post);
		when(postRepository.findById("1")).thenReturn(optionalPost);
		
		String res = postService.updateUpvote("1");
		
		ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
		verify(postRepository, times(1)).save(argument.capture());
		assertEquals("1", res);
	}
	
	@Test
	public void testUpdateUpvoteWhenEmpty() {
		Optional<Post> optionalPost = Optional.empty();
		when(postRepository.findById("1")).thenReturn(optionalPost);
		
		String res = postService.updateUpvote("1");
		
		ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
		verify(postRepository, times(0)).save(argument.capture());
		assertNull(res);
	}
}
