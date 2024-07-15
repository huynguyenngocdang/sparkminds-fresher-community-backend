package com.example.sparkminds_community.controller;

import com.example.sparkminds_community.dto.request.user.CreateUserRequest;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.service.UserService;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponsePayload> createUser(@RequestBody CreateUserRequest request) {
        ResponsePayload responsePayload = userService.createUser(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
