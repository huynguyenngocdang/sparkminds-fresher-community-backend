package com.example.sparkminds_community.service;

import com.example.sparkminds_community.dto.request.user.LoginRequest;
import com.example.sparkminds_community.payload.ResponsePayload;

public interface AuthService {
    ResponsePayload login(LoginRequest request);
    boolean isUserNotDelete(String username);
}
