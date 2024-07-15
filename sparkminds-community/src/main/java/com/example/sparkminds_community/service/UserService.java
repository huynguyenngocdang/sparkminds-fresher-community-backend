package com.example.sparkminds_community.service;

import com.example.sparkminds_community.dto.request.user.CreateUserRequest;
import com.example.sparkminds_community.dto.request.user.DeleteUserRequest;
import com.example.sparkminds_community.dto.request.user.RestoreUserRequest;
import com.example.sparkminds_community.dto.request.user.UpdateUserProfileRequest;
import com.example.sparkminds_community.dto.request.user.UpdateUserRequest;
import com.example.sparkminds_community.payload.ResponsePayload;

public interface UserService {
    ResponsePayload createUser(CreateUserRequest request);
    ResponsePayload updateUser(UpdateUserRequest request);
    ResponsePayload updateUserProfile(UpdateUserProfileRequest request);
    ResponsePayload hardDeleteUser(DeleteUserRequest request);
    ResponsePayload softDeleteUser(DeleteUserRequest request);
    ResponsePayload restoreUserService(RestoreUserRequest request);
}
