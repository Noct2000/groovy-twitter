package com.example.groovytwitter.service.impl

import com.example.groovytwitter.model.Comment
import com.example.groovytwitter.repository.CommentRepository
import com.example.groovytwitter.repository.PostRepository
import com.example.groovytwitter.service.CommentService
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository

    PostRepository postRepository

    CommentServiceImpl(
            CommentRepository commentRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository
        this.postRepository = postRepository
    }

    @Override
    Comment save(Comment comment) {
        return commentRepository.save(comment)
    }

    @Override
    List<Comment> getAllByPostId(String postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("No post with id: " + postId))
                .getComments()
    }

    @Override
    void deleteCommentById(String id) {
        commentRepository.deleteById(id)
    }
}
