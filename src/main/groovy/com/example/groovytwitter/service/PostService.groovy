package com.example.groovytwitter.service

import com.example.groovytwitter.model.Comment
import com.example.groovytwitter.model.Post

interface PostService {
    Post save(Post post)

    Post update(Post post, String login)

    void removeById(String id, String login)

    Post setLikeToPostById(String postId, String login)

    Post unsetLikeToPostById(String postId, String login)

    Post commentPostById(String postId, Comment comment)

    List<Post> getAllByUserLogin(String login)

    List<Post> getAll()

    Post getById(String id)
}
