package com.example.sparkminds_community.repository;

import com.example.sparkminds_community.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r JOIN r.userRoles ur JOIN ur.user u WHERE u.username =:username")
    List<Role> findAllRoleForRead(@Param("username") String username);
    @Query("SELECT r FROM Role r WHERE r.role =:role")
    Optional<Role> findRoleByRole(@Param("role") String role);
}
