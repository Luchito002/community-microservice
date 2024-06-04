package com.zectia.communities_microservice.repository;

import com.zectia.communities_microservice.model.Community;
import com.zectia.communities_microservice.model.UserCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
  Optional<Community> findById(Long id);

  List<UserCommunity> findAllById(Long id);
}
