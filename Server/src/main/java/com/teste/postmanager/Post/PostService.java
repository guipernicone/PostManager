package com.teste.postmanager.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	
	
}
