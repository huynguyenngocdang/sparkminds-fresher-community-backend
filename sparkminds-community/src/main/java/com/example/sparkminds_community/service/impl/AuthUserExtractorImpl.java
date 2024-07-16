package com.example.sparkminds_community.service.impl;

import com.example.sparkminds_community.constant.UserConstant;
import com.example.sparkminds_community.entity.User;
import com.example.sparkminds_community.exception.ResourceNotFoundException;
import com.example.sparkminds_community.repository.UserRepository;
import com.example.sparkminds_community.service.AuthUserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUserExtractorImpl implements AuthUserExtractor {
    private final UserRepository userRepository;
    @Override
    public String getCurrentUsernameFromAuth() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUsernameFromAuth();
        return userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST));
    }
    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
