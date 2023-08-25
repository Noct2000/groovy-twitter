package com.example.groovytwitter.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("comment")
class Comment {
    @Id
    String id
    User author
    String text
    LocalDateTime timestamp

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    User getAuthor() {
        return author
    }

    void setAuthor(User author) {
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
