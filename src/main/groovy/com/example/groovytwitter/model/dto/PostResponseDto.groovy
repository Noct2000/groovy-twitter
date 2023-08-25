package com.example.groovytwitter.model.dto

import com.example.groovytwitter.model.Comment
import com.example.groovytwitter.model.Like
import com.example.groovytwitter.model.User
import java.time.LocalDateTime

class PostResponseDto {
    String id
    String content
    User author
    List<Comment> comments
    Set<Like> likes
    LocalDateTime timestamp

    LocalDateTime getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp
    }

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getContent() {
        return content
    }

    void setContent(String content) {
        this.content = content
    }

    User getAuthor() {
        return author
    }

    void setAuthor(User author) {
        this.author = author
    }

    List<Comment> getComments() {
        return comments
    }

    void setComments(List<Comment> comments) {
        this.comments = comments
    }

    Set<Like> getLikes() {
        return likes
    }

    void setLikes(Set<Like> likes) {
        this.likes = likes
    }
}
