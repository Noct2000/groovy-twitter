package com.example.groovytwitter.service

import com.example.groovytwitter.model.Like

interface LikeService {
    Like save(Like like)

    Like removeById(String id)
}
