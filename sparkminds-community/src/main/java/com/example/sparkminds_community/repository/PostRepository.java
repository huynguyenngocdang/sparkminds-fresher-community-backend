package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByIsDeleteFalse(Pageable pageable);
}
