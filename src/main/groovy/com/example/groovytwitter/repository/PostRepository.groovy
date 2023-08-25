package com.example.groovytwitter.repository

import com.example.groovytwitter.model.Post
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByAuthorId(String id)
}
