package com.example.groovytwitter.exception

class UserValidationException extends RuntimeException {
    UserValidationException(String message) {
        super(message)
    }
}
