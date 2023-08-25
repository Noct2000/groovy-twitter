package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Post
import com.example.groovytwitter.model.dto.PostCreateRequestDto
import com.example.groovytwitter.model.dto.PostResponseDto
import com.example.groovytwitter.model.dto.PostUpdateRequestDto
import com.example.groovytwitter.service.PostService
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostMapper {
    UserService userService

    PostService postService

    PostMapper(UserService userService, PostService postService) {
        this.userService = userService
        this.postService = postService
    }

    Post toNewPost(
            PostCreateRequestDto postCreateRequestDto,
            String login
    ) {
        Post post = new Post()
        post.setContent(postCreateRequestDto.getContent())
        post.setTimestamp(LocalDateTime.now())
        post.setAuthor(userService.getByLogin(login))
        return post
    }

    PostResponseDto toPostResponseDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto()
        postResponseDto.setId(post.getId())
        postResponseDto.setContent(post.getContent())
        postResponseDto.setAuthor(post.getAuthor())
        postResponseDto.setComments(post.getComments())
        postResponseDto.setLikes(post.getLikes())
        postResponseDto.setTimestamp(post.getTimestamp())
        return postResponseDto
    }

    Post toModel(PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postService.getById(postUpdateRequestDto.getId())
        post.setContent(postUpdateRequestDto.getContent())
        return post
    }
}
