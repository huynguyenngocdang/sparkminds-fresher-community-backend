package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
