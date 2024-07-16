package com.example.sparkminds_community.mapper;

import com.example.sparkminds_community.dto.response.post.PostResponse;
import com.example.sparkminds_community.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "isLike", ignore = true)
    @Mapping(target = "isDislike", ignore = true)
    PostResponse toPostResponseFromPost(Post post);
}
