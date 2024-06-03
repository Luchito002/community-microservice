package com.zectia.communities_microservice.repository;

import com.zectia.communities_microservice.model.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
  Optional<UserCommunity> findById(Long id);
}
