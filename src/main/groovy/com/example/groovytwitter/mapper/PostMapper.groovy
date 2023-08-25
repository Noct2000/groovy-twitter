package com.example.groovytwitter.mapper

import com.example.groovytwitter.model.Post
import com.example.groovytwitter.model.dto.PostCreateRequestDto
import com.example.groovytwitter.model.dto.PostResponseDto
import com.example.groovytwitter.model.dto.PostUpdateRequestDto
import com.example.groovytwitter.service.PostService
import com.example.groovytwitter.service.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.stream.Collectors

@Component
class PostMapper {
    UserService userService
    PostService postService
    UserMapper userMapper
    CommentMapper commentMapper
    LikeMapper likeMapper

    PostMapper(
            UserService userService,
            PostService postService,
            UserMapper userMapper,
            CommentMapper commentMapper,
            LikeMapper likeMapper
    ) {
        this.userService = userService
        this.postService = postService
        this.userMapper = userMapper
        this.commentMapper = commentMapper
        this.likeMapper = likeMapper
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
        postResponseDto.setAuthor(userMapper.toResponseDto(post.getAuthor()))
        postResponseDto.setComments(
                post.getComments()
                        .stream()
                        .map(commentMapper::toCommentResponseDto)
                        .toList()
        )
        postResponseDto.setLikes(
                post.getLikes()
                        .stream()
                        .map(likeMapper::toLikeResponseDto)
                        .collect(Collectors.toSet())
        )
        postResponseDto.setTimestamp(post.getTimestamp())
        return postResponseDto
    }

    Post toModel(PostUpdateRequestDto postUpdateRequestDto) {
        Post post = postService.getById(postUpdateRequestDto.getId())
        post.setContent(postUpdateRequestDto.getContent())
        return post
    }
}
