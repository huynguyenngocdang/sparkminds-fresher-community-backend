package com.example.sparkminds_community.mapper;

import com.example.sparkminds_community.dto.request.user.CreateUserRequest;
import com.example.sparkminds_community.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "isDelete", constant = "false")
    @Mapping(target = "user", ignore = true)
    UserProfile toUserProfileFromCreateRequest(CreateUserRequest request);
}
