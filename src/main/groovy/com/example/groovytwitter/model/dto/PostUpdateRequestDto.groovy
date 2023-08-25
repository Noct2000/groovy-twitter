package com.example.groovytwitter.model.dto

class PostUpdateRequestDto {
    String id
    String content

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
}
