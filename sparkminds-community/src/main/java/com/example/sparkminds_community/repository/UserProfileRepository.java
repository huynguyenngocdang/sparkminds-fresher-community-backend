package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
