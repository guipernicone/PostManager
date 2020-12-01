package com.teste.postmanager.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

	@Mock
	private PostRepository postRepository;
	
	@InjectMocks
	private PostService postService;
	
//	@BeforeEach
//	public void setup() {
//		Post post = new Post("First post test");
//	}
	
	@Test
	public void testCreatePost() {
		postService.createPost("First post test");
		
		ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
		verify(postRepository, times(1)).save(argument.capture());
		
		assertEquals("First post test", argument.getValue().getText());
	}
}
