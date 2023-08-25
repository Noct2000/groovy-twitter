package com.example.groovytwitter.model.dto

class CommentCreateRequestDto {
    String text

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }
}
