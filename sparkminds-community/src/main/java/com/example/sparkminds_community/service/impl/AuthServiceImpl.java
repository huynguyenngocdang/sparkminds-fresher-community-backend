package com.example.sparkminds_community.service.impl;

import com.example.sparkminds_community.constant.UserConstant;
import com.example.sparkminds_community.dto.request.user.LoginRequest;
import com.example.sparkminds_community.dto.response.user.LoginResponse;
import com.example.sparkminds_community.dto.response.user.RoleResponse;
import com.example.sparkminds_community.entity.Role;
import com.example.sparkminds_community.entity.User;
import com.example.sparkminds_community.exception.ResourceNotFoundException;
import com.example.sparkminds_community.mapper.RoleMapper;
import com.example.sparkminds_community.payload.ResponsePayload;
import com.example.sparkminds_community.repository.RoleRepository;
import com.example.sparkminds_community.repository.UserRepository;
import com.example.sparkminds_community.security.JwtService;
import com.example.sparkminds_community.service.AuthService;
import com.example.sparkminds_community.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ResponsePayloadUtility responsePayloadUtility;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public ResponsePayload login(LoginRequest request) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch (AuthenticationException e) {
            return responsePayloadUtility.buildResponse(
                    "Login fail",
                    HttpStatus.NOT_FOUND,
                    null,
                    e.getMessage()
            );
        }
        if (authentication.isAuthenticated() && isUserNotDelete(request.getUsername())) {
            List<Role> roles = roleRepository.findAllRoleForRead(request.getUsername());
            List<RoleResponse> roleResponses = mapRoles(roles);
            User user = userRepository.findUserByUsername(request.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
            String accessToken = jwtService.generateToken(request.getUsername());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(accessToken);
            loginResponse.setUsername(request.getUsername());
            loginResponse.setRoles(roleResponses);
            loginResponse.setUserId(user.getId());
            return responsePayloadUtility.buildResponse(
                    "Login successfully",
                    HttpStatus.OK,
                    loginResponse,
                    null
            );
        }
        return responsePayloadUtility.buildResponse(
                "Login fail",
                HttpStatus.NOT_FOUND,
                null,
                "Login fail"
        );
    }

    private List<RoleResponse> mapRoles(List<Role> roles) {
        return roles.stream()
                .map(this::mapRoleToRoleResponse)
                .collect(Collectors.toList());
    }
    private RoleResponse mapRoleToRoleResponse(Role role) {
        return roleMapper.toRoleResponseFromRole(role);
    }
    @Override
    public boolean isUserNotDelete(String username) {
        User user = userRepository.findFirstByUsername(username);
        return !user.isDelete();
    }
}
