package com.example.sparkminds_community.service.impl;

import com.example.sparkminds_community.constant.PostConstant;
import com.example.sparkminds_community.dto.request.post.CreatePostRequest;
import com.example.sparkminds_community.dto.response.post.PostResponse;
import com.example.sparkminds_community.entity.Post;
import com.example.sparkminds_community.entity.User;
import com.example.sparkminds_community.exception.ResourceNotFoundException;
import com.example.sparkminds_community.mapper.PostMapper;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.repository.PostDislikeRepository;
import com.example.sparkminds_community.repository.PostLikeRepository;
import com.example.sparkminds_community.repository.PostRepository;
import com.example.sparkminds_community.service.AuthUserExtractor;
import com.example.sparkminds_community.service.PostService;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private final AuthUserExtractor authUserExtractor;
    private final PostMapper postMapper;
    private final PostLikeRepository postLikeRepository;
    private final PostDislikeRepository postDislikeRepository;
    @Override
    @Transactional
    public ResponsePayload createPost(CreatePostRequest request) {
     try {
         User currentUser = authUserExtractor.getCurrentUser();
         Post post = new Post();
         post.setTitle(request.getTitle());
         if(request.getContent() != null) {
             post.setContent(request.getContent());
             post.setPostType(PostConstant.POST_TYPE_TEXT);
         }
         if(request.getImageUrl() != null) {
             post.setImageUrl(request.getImageUrl());
             post.setPostType(PostConstant.POST_TYPE_IMAGE);
         }
         post.setUser(currentUser);
         post.setTotalLikes(0L);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm");
         String formattedDateTime = LocalDateTime.now().format(formatter);
         post.setCreatedDate(formattedDateTime);
         postRepository.save(post);
         return responsePayloadUtility.buildResponse(
                 PostConstant.POST_CREATE_SUCCESS,
                 HttpStatus.CREATED,
                 null,
                 null
         );
     } catch (Exception e) {
         return responsePayloadUtility.buildResponse(
                 PostConstant.POST_CREATE_FAILED,
                 HttpStatus.INTERNAL_SERVER_ERROR,
                 null,
                 e.getMessage()
         );
     }
    }

    @Override
    public ResponsePayload getAllPostByUser(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByIsDeleteFalse(pageable);
        List<PostResponse> postResponseList = mapToPostResponseWithUser(posts.getContent());
        Page<PostResponse> postResponses = new PageImpl<>(postResponseList, pageable, posts.getTotalElements());
        return responsePayloadUtility.buildResponse(
                PostConstant.POST_GET_SUCCESS,
                HttpStatus.OK,
                postResponses,
                null
        );
    }
    @Override
    public ResponsePayload getAllPost(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByIsDeleteFalse(pageable);
        List<PostResponse> postResponseList = mapToPostResponse(posts.getContent());
        Page<PostResponse> postResponses = new PageImpl<>(postResponseList, pageable, posts.getTotalElements());
        return responsePayloadUtility.buildResponse(
                PostConstant.POST_GET_SUCCESS,
                HttpStatus.OK,
                postResponses,
                null
        );
    }

    @Override
    public ResponsePayload getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(PostConstant.POST_NOT_FOUND + " postId: " + postId));
        PostResponse postResponse = mapPostToPostResponse(post);
        return responsePayloadUtility.buildResponse(
                PostConstant.POST_GET_SUCCESS,
                HttpStatus.OK,
                postResponse,
                null
        );
    }
    private List<PostResponse> mapToPostResponse(List<Post> posts) {
        return posts.stream()
                .map(this::mapPostToPostResponse)
                .toList();
    }
    private PostResponse mapPostToPostResponse(Post post) {
        PostResponse postResponse = postMapper.toPostResponseFromPost(post);
        postResponse.setAuthor(post.getUser().getUsername());
        return postResponse;
    }

    private List<PostResponse> mapToPostResponseWithUser(List<Post> posts) {
        return posts.stream()
                .map(this::mapPostToPostResponseWithUser)
                .toList();
    }
    private PostResponse mapPostToPostResponseWithUser(Post post) {
        PostResponse postResponse = postMapper.toPostResponseFromPost(post);
        User currentUser = authUserExtractor.getCurrentUser();
        boolean userLikedPost = postLikeRepository.existsByPostAndUserAndIsDeleteFalse(post, currentUser);
        postResponse.setLike(userLikedPost);
        boolean userDislikedPost = postDislikeRepository.existsByPostAndUserAndIsDeleteFalse(post, currentUser);
        postResponse.setDislike(userDislikedPost);
        postResponse.setAuthor(post.getUser().getUsername());
        return postResponse;
    }
}
