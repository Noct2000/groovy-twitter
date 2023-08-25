package com.example.groovytwitter.controller

import com.example.groovytwitter.mapper.CommentMapper
import com.example.groovytwitter.mapper.PostMapper
import com.example.groovytwitter.model.Post
import com.example.groovytwitter.model.dto.CommentCreateRequestDto
import com.example.groovytwitter.model.dto.CommentResponseDto
import com.example.groovytwitter.model.dto.PostCreateRequestDto
import com.example.groovytwitter.model.dto.PostResponseDto
import com.example.groovytwitter.model.dto.PostUpdateRequestDto
import com.example.groovytwitter.service.PostService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/posts")
class PostController {
    PostService postService
    PostMapper postMapper
    CommentMapper commentMapper

    PostController(
            PostService postService,
            PostMapper postMapper,
            CommentMapper commentMapper
    ) {
        this.postService = postService
        this.postMapper = postMapper
        this.commentMapper = commentMapper
    }

    @PostMapping
    PostResponseDto addPost(
            @RequestBody PostCreateRequestDto postCreateRequestDto,
            Authentication authentication
    ) {
        Post post = postMapper.toNewPost(postCreateRequestDto, authentication.name)
        return postMapper.toPostResponseDto(postService.save(post))
    }

    @PutMapping
    PostResponseDto updatePost(
            @RequestBody PostUpdateRequestDto postUpdateRequestDto,
            Authentication authentication
    ) {
        Post post = postMapper.toModel(postUpdateRequestDto)
        return postMapper.toPostResponseDto(postService.update(post, authentication.name))
    }

    @DeleteMapping("/{id}")
    void removePost(
            @PathVariable String id,
            Authentication authentication
    ) {
        postService.removeById(id, authentication.name)
    }

    @PatchMapping("/{id}/like")
    PostResponseDto likePost(
            @PathVariable String id,
            Authentication authentication
    ) {
        return postMapper.toPostResponseDto(postService.setLikeToPostById(id, authentication.name))
    }

    @PatchMapping("/{id}/unlike")
    PostResponseDto unlikePost(
            @PathVariable String id,
            Authentication authentication
    ) {
        return postMapper.toPostResponseDto(postService.unsetLikeToPostById(id, authentication.name))
    }

    @PatchMapping("/{id}/comment")
    PostResponseDto leaveComment(
            @PathVariable String id,
            @RequestBody CommentCreateRequestDto commentCreateRequestDto,
            Authentication authentication
    ) {
        Post post = postService.commentPostById(
                id,
                commentMapper.toModel(commentCreateRequestDto, authentication.name)
        )
        return postMapper.toPostResponseDto(post)
    }

    @GetMapping("/users/{userLogin}")
    List<PostResponseDto> getAllByUserId(@PathVariable String userLogin) {
        return postService.getAllByUserLogin(userLogin)
                .stream()
                .map(postMapper::toPostResponseDto)
                .toList()
    }

    @GetMapping("/{id}/comments")
    List<CommentResponseDto> getAllCommentsByPost(@PathVariable String id) {
        return postService.getById(id).getComments()
                .stream()
                .map(commentMapper::toCommentResponseDto)
                .toList()
    }

    @GetMapping
    List<PostResponseDto> getPostsForCurrentUser(Authentication authentication) {
        return null
    }
}
