package com.example.groovytwitter.service.impl

import com.example.groovytwitter.model.Like
import com.example.groovytwitter.repository.LikeRepository
import com.example.groovytwitter.service.LikeService
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl implements LikeService {
    LikeRepository likeRepository

    LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository
    }

    @Override
    Like save(Like like) {
        return likeRepository.save(like)
    }

    @Override
    Like removeById(String id) {
        return likeRepository.deleteById(id)
    }
}
