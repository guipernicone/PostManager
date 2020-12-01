package com.teste.postmanager.Post;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Post")
public class Post {

	@Id
	private String id;
	private String text;
	private int upvote;
	
	
	public Post(String text) {
		this.text = text;
		this.upvote = 0;
	}

	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public int getUpvote() {
		return upvote;
	}
	
	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}
	
	
}
