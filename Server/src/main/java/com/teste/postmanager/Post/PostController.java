package com.teste.postmanager.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; 

@RestController()
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@PostMapping
	public ResponseEntity<String> newPost(@RequestParam String text) {
		postService.createPost(text);
		return ResponseEntity.status(HttpStatus.CREATED).body("Post Created");
	}
	
	@GetMapping
	public ResponseEntity<?> getPosts(@RequestParam int page, @RequestParam int size) {
		Page<Post> postList = postService.getPostList(page, size);
		
		if (!postList.hasContent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No post was found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(postList);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> upvote(@PathVariable String id) {
		String response = postService.updateUpvote(id);
		
		if (response == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post from given id not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
}
