package com.zectia.communities_microservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zectia.communities_microservice.dto.CommunityDto;
//import com.zectia.communities_microservice.exception.ResourceNotFoundException;
import com.zectia.communities_microservice.model.Community;
import com.zectia.communities_microservice.model.UserCommunity;
import com.zectia.communities_microservice.repository.CommunityRepository;
import com.zectia.communities_microservice.repository.UserCommunityRepository;
import com.zectia.communities_microservice.service.CommunityService;
import com.zectia.communities_microservice.utils.DtoEntityConverter;

@Service
public class CommunityServiceImpl implements CommunityService {

  private final CommunityRepository communityRepository;
  private final UserCommunityRepository userCommunityRepository;

  @Autowired
  public CommunityServiceImpl(CommunityRepository communityRepository,
      UserCommunityRepository userCommunityRepository) {
    this.communityRepository = communityRepository;
    this.userCommunityRepository = userCommunityRepository;
  }

  @Override
  public String createCommunity(CommunityDto communityDto) {
    Community community = DtoEntityConverter.dtoToEntity(communityDto, Community.class);
    if (communityRepository.existsByNombre(community.getNombre())) {
      return "Ya existe una comunidad con ese nombre";
    }
    this.communityRepository.save(community);
    return "Comunidad creada con exito";
  }

  @Override
  public List<Community> getCommunities() {
    List<Community> comunidadesSinUsuarioComunidades = communityRepository.findAll();

    // Recorrer cada comunidad y eliminar el campo usuarioComunidades
    for (Community comunidad : comunidadesSinUsuarioComunidades) {
      comunidad.setUsuarioComunidades(null);
    }

    // Devolver la lista de comunidades modificada
    return comunidadesSinUsuarioComunidades;
  }

  @Override
  public List<CommunityDto> getCommunitiesByUserId(Long userId) {
    List<UserCommunity> userCommunities = userCommunityRepository.findByIdUsuario(userId);

    return userCommunities.stream()
        .map(UserCommunity::getComunidad)
        .map(CommunityDto::new)
        .collect(Collectors.toList());
  }
}
