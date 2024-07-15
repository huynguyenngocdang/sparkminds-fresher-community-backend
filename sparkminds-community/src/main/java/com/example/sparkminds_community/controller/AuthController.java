package com.example.sparkminds_community.controller;

import com.example.sparkminds_community.dto.request.user.LoginRequest;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ResponsePayload> loginToApp(@RequestBody LoginRequest request) {
        ResponsePayload responsePayload = authService.login(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
