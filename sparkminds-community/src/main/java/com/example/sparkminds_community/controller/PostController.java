package com.example.sparkminds_community.controller;

import com.example.sparkminds_community.dto.request.post.CreatePostRequest;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.service.AuthUserExtractor;
import com.example.sparkminds_community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    private final AuthUserExtractor authUserExtractor;
    @PostMapping()
    public ResponseEntity<ResponsePayload> createPost(@RequestBody CreatePostRequest request) {
        ResponsePayload responsePayload = postService.createPost(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @GetMapping()
    public ResponseEntity<ResponsePayload> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page,size);
        if (authUserExtractor.isAuthenticated()) {
            ResponsePayload responsePayload = postService.getAllPostByUser(pageable);
            return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
        } else {
            ResponsePayload responsePayload = postService.getAllPost(pageable);
            return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload> getPostById(@PathVariable Long id) {
        ResponsePayload responsePayload = postService.getPostById(id);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }



    @GetMapping("/all")
    public ResponseEntity<ResponsePayload> getAllPostsLogin(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page,size);
        ResponsePayload responsePayload = postService.getAllPostByUser(pageable);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
