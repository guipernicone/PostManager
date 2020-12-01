package com.teste.postmanager.Post;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<Post, String>{

}
