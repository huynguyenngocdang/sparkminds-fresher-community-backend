package com.example.sparkminds_community.service.impl;

import com.example.sparkminds_community.constant.RoleConstant;
import com.example.sparkminds_community.constant.UserConstant;
import com.example.sparkminds_community.dto.request.user.CreateUserRequest;
import com.example.sparkminds_community.dto.request.user.DeleteUserRequest;
import com.example.sparkminds_community.dto.request.user.RestoreUserRequest;
import com.example.sparkminds_community.dto.request.user.UpdateUserProfileRequest;
import com.example.sparkminds_community.dto.request.user.UpdateUserRequest;
import com.example.sparkminds_community.entity.Role;
import com.example.sparkminds_community.entity.User;
import com.example.sparkminds_community.entity.UserProfile;
import com.example.sparkminds_community.entity.UserRole;
import com.example.sparkminds_community.mapper.UserProfileMapper;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.repository.RoleRepository;
import com.example.sparkminds_community.repository.UserRepository;
import com.example.sparkminds_community.repository.UserRoleRepository;
import com.example.sparkminds_community.security.JwtService;
import com.example.sparkminds_community.service.UserService;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public ResponsePayload createUser(CreateUserRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        Optional<User> existingUserOpt = userRepository.findUserByUsername(username);
        if(existingUserOpt.isPresent()) {
            return responsePayloadUtility.buildResponse(
                    UserConstant.INVALID_USER_ALREADY_EXIST,
                    HttpStatus.CONFLICT,
                    null,
                    UserConstant.INVALID_USER_ALREADY_EXIST
            );
        }
        Optional<Role> defaultRoleOpt = roleRepository.findRoleByRole(RoleConstant.ROLE_USER);
        if(defaultRoleOpt.isEmpty()) {
            return responsePayloadUtility.buildResponse(
                    RoleConstant.INVALID_ROLE_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    null,
                    RoleConstant.INVALID_ROLE_NOT_FOUND
            );
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        UserProfile userProfile = new UserProfile();
        userProfile.setDelete(false);
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        userRepository.save(user);

        Role defaultRole = defaultRoleOpt.get();
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(defaultRole)
                .isDelete(false)
                .build();
        userRoleRepository.save(userRole);


        return responsePayloadUtility.buildResponse(
                UserConstant.CREATE_USER_SUCCESSFUL,
                HttpStatus.CREATED,
                null,
                null
        );
    }

    @Override
    public ResponsePayload updateUser(UpdateUserRequest request) {
        return null;
    }

    @Override
    public ResponsePayload updateUserProfile(UpdateUserProfileRequest request) {
        return null;
    }

    @Override
    public ResponsePayload hardDeleteUser(DeleteUserRequest request) {
        return null;
    }

    @Override
    public ResponsePayload softDeleteUser(DeleteUserRequest request) {
        return null;
    }

    @Override
    public ResponsePayload restoreUserService(RestoreUserRequest request) {
        return null;
    }
}
