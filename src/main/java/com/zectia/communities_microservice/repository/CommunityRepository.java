package com.zectia.communities_microservice.repository;


import com.zectia.communities_microservice.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
  Optional<Community> findById(Long id);
}
