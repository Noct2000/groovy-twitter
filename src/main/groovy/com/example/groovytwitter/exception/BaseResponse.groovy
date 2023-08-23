package com.example.groovytwitter.exception

import java.time.LocalDateTime

class BaseResponse<T> {
    T value
    LocalDateTime timestamp

    T getValue() {
        return value
    }

    void setValue(T value) {
        this.value = value
    }

    LocalDateTime getTimestamp() {
        return timestamp
    }

    void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp
    }
}
