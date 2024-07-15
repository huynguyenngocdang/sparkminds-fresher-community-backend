package com.example.sparkminds_community.mapper;

import com.example.sparkminds_community.dto.response.user.RoleResponse;
import com.example.sparkminds_community.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponseFromRole(Role role);
}
