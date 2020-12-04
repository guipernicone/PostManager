package com.teste.postmanager.Post;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	/**
	 * Create a new post
	 * 
	 * @param text The post text
	 * 
	 * @return The post id
	 */
	public String createPost(String text) {
		Post post = new Post(text);
		Post postCreated = postRepository.save(post);
		
		return postCreated.getId();
	}
	
	/**
	 * Get a pageable list of all post
	 * 
	 * @param pageNumber The number of page to search
	 * @param size The size of itens per page
	 * 
	 * @return The page list
	 */
	public Page<Post> getPostList(int pageNumber, int size){
		Pageable page = PageRequest.of(pageNumber, size, Direction.DESC, "date");
		
		Page<Post> posts = postRepository.findAll(page);

		return posts;
	}
	
	/**
	 * Update a post, increasing the upvote
	 * 
	 * @param postId The post ID
	 * 
	 * @return The new upvote value, null if no post is found
	 */
	public synchronized String updateUpvote(String postId){
		
		Optional<Post> optionalPost = postRepository.findById(postId);
		
		if(optionalPost.isPresent()) {
			Post post = optionalPost.get();
	
			int upvote = post.getUpvote();
			
			upvote++;
			post.setUpvote(upvote);		
			postRepository.save(post);
			
			return Integer.toString(upvote);
		}
		
		return null;
	}
	
}
