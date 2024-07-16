package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.Post;
import com.example.sparkminds_community.entity.PostDislike;
import com.example.sparkminds_community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDislikeRepository extends JpaRepository<PostDislike, Long> {
    boolean existsByPostAndUserAndIsDeleteFalse(Post post, User user);
}
