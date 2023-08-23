package com.example.groovytwitter.repository

import com.example.groovytwitter.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login)
}
