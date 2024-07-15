package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
    Optional<User> findUserByUsername(String username);
}
