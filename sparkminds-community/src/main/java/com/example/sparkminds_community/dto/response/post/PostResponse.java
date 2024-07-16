package com.example.sparkminds_community.dto.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String author;
    private Long totalLikes;
    private String createdDate;
    private boolean isDelete;
    private boolean isLike;
    private boolean isDislike;
}
