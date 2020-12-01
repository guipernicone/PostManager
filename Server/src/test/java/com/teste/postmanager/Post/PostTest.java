package com.teste.postmanager.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostTest {
	
	private Post post;
	
	@BeforeEach
	public void setup() {
		post = new Post("Test post");
	}
	
	@Test
	public void testGetId() {
		String id = post.getId();
		
		assertNull(id);
	}
	
	@Test
	public void testGetText() {
		String text = post.getText();
		
		assertEquals(text, "Test post");
	}
	
	@Test
	public void testSetUpvote() {
		post.setUpvote(1);
		
		int upvote = post.getUpvote();
		
		assertEquals(upvote, 1);
	}
	
	@Test
	public void testGetUpvote() {
		int upvote = post.getUpvote();
		
		assertEquals(upvote, 0);
	}
}
