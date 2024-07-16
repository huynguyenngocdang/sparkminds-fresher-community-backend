package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.Post;
import com.example.sparkminds_community.entity.PostLike;
import com.example.sparkminds_community.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUserAndIsDeleteFalse(Post post, User user);
}
