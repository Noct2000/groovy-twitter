package com.example.groovytwitter.model.dto

import java.time.LocalDateTime

class PostResponseDto {
    String id
    String content
    UserResponseDto author
    List<CommentResponseDto> comments
    Set<LikeResponseDto> likes
    LocalDateTime timestamp

    UserResponseDto getAuthor() {
        return author
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

    void setAuthor(UserResponseDto author) {
        this.author = author
    }

    List<CommentResponseDto> getComments() {
        return comments
    }

    void setComments(List<CommentResponseDto> comments) {
        this.comments = comments
    }

    Set<LikeResponseDto> getLikes() {
        return likes
    }

    void setLikes(Set<LikeResponseDto> likes) {
        this.likes = likes
    }

    LocalDateTime getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp
    }
}
