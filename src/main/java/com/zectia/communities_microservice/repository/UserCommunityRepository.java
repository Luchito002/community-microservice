package com.zectia.communities_microservice.repository;

import com.zectia.communities_microservice.model.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
  Optional<UserCommunity> findById(Long id);

  List<UserCommunity> findByIdUsuario(Long id);

  Optional<UserCommunity> findByIdUsuarioAndComunidadId(Long idUsuario, Long comunidadId);

  List<UserCommunity> findByComunidadId(Long communityId);
}
