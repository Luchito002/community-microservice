package com.zectia.communities_microservice.repository;

import com.zectia.communities_microservice.model.CommunityRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRolesRepository extends JpaRepository<CommunityRoles, Long> {
  Optional<CommunityRoles> findById(Long id);
}
