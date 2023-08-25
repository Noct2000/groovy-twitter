package com.example.groovytwitter.repository

import com.example.groovytwitter.model.Like
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository extends MongoRepository<Like, String> {

}
