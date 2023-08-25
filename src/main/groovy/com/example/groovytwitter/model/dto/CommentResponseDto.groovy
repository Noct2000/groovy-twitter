package com.example.groovytwitter.model.dto

import java.time.LocalDateTime

class CommentResponseDto {
    String id
    UserResponseDto author
    String text
    LocalDateTime timestamp

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    UserResponseDto getAuthor() {
        return author
    }

    void setAuthor(UserResponseDto author) {
        this.author = author
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    LocalDateTime getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp
    }
}
