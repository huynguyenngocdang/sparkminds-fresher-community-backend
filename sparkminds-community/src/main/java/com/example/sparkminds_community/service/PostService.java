package com.example.sparkminds_community.service;

import com.example.sparkminds_community.dto.request.post.CreatePostRequest;
import com.example.sparkminds_community.payload.ResponsePayload;
import org.springframework.data.domain.Pageable;

public interface PostService {
    ResponsePayload createPost(CreatePostRequest request);
    ResponsePayload getAllPostByUser(Pageable pageable);
    ResponsePayload getAllPost(Pageable pageable);
    ResponsePayload getPostById(Long id);
}
