package com.example.groovytwitter.service

import com.example.groovytwitter.model.Comment

interface CommentService {
    Comment save(Comment comment)

    List<Comment> getAllByPostId(String postId)

    void deleteCommentById(String id)
}
