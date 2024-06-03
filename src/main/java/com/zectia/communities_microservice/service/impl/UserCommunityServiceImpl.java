package com.zectia.communities_microservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zectia.communities_microservice.dto.UserCommunityDto;
import com.zectia.communities_microservice.exception.ResourceNotFoundException;
import com.zectia.communities_microservice.model.UserCommunity;
import com.zectia.communities_microservice.model.Community;
import com.zectia.communities_microservice.model.CommunityRoles;
import com.zectia.communities_microservice.repository.UserCommunityRepository;
import com.zectia.communities_microservice.repository.CommunityRepository;
import com.zectia.communities_microservice.repository.CommunityRolesRepository;
import com.zectia.communities_microservice.service.UserCommunityService;
import com.zectia.communities_microservice.utils.DtoEntityConverter;

@Service
public class UserCommunityServiceImpl implements UserCommunityService {

  private final UserCommunityRepository userCommunityRepository;
  private final CommunityRepository communityRepository;
  private final CommunityRolesRepository communityRolesRepository;

  @Autowired
  public UserCommunityServiceImpl(
    UserCommunityRepository userCommunityRepository,
    CommunityRepository communityRepository,
    CommunityRolesRepository communityRolesRepository
  ) {
    this.userCommunityRepository = userCommunityRepository;
    this.communityRepository = communityRepository;
    this.communityRolesRepository = communityRolesRepository;
  }

  @Override
  public UserCommunityDto joinCommunity(UserCommunityDto userCommunityDto) {
    UserCommunity userCommunity = DtoEntityConverter.dtoToEntity(userCommunityDto, UserCommunity.class);

    // Obtener y Establecer la comunidad a la que el usuario se está uniendo
    Community comunidad = communityRepository.findById(userCommunityDto.getComunidadId())
        .orElseThrow(() -> new ResourceNotFoundException(userCommunityDto.getComunidadId()));
    userCommunity.setComunidad(comunidad);

    // Obtener y Establecer el rol de comunidad a la que el usuario se está uniendo
    CommunityRoles communityRol = communityRolesRepository.findById(userCommunityDto.getRolesComunidadId())
        .orElseThrow(() -> new ResourceNotFoundException(userCommunityDto.getRolesComunidadId()));
    userCommunity.setRolesComunidad(communityRol);

    // Guardar la entidad UserCommunity
    UserCommunity insertedUserCommunity = userCommunityRepository.save(userCommunity);

    // Convertir la entidad guardada a un DTO y devolverlo
    return DtoEntityConverter.entityToDto(insertedUserCommunity, UserCommunityDto.class);
  }
}
