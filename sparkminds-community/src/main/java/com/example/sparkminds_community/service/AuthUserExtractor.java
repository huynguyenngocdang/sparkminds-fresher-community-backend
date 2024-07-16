package com.example.sparkminds_community.service;

import com.example.sparkminds_community.entity.User;

public interface AuthUserExtractor {
    String getCurrentUsernameFromAuth();
    User getCurrentUser();
    boolean isAuthenticated();
}
