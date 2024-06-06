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
      CommunityRolesRepository communityRolesRepository) {
    this.userCommunityRepository = userCommunityRepository;
    this.communityRepository = communityRepository;
    this.communityRolesRepository = communityRolesRepository;
  }

  @Override
  public UserCommunityDto joinCommunity(UserCommunityDto userCommunityDto) {
    UserCommunity userCommunity = DtoEntityConverter.dtoToEntity(userCommunityDto, UserCommunity.class);

    // Obtener y Establecer la comunidad a la que el usuario se está uniendo
    Community comunidad = communityRepository.findById(userCommunityDto.getComunidadId())
        .orElseThrow(() -> new ResourceNotFoundException("Ya estas unido en esta comunidad"));
    userCommunity.setComunidad(comunidad);

    // Obtener y Establecer el rol de comunidad a la que el usuario se está uniendo
    CommunityRoles communityRol = communityRolesRepository.findById(userCommunityDto.getRolesComunidadId())
        .orElseThrow(() -> new ResourceNotFoundException("No existe ese rol"));
    userCommunity.setRolesComunidad(communityRol);

    // Guardar la entidad UserCommunity
    UserCommunity insertedUserCommunity = userCommunityRepository.save(userCommunity);

    // Convertir la entidad guardada a un DTO y devolverlo
    return DtoEntityConverter.entityToDto(insertedUserCommunity, UserCommunityDto.class);
  }

  @Override
  public String leaveCommunity(Long userId, Long communityId) {
    // Buscar la relación UserCommunity correspondiente al usuario y la comunidad
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userId, communityId)
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró la relación del usuario con la comunidad"));

    // Cambiar el estado a "inactivo"
    userCommunity.setEstado("inactivo");

    // Guardar la entidad UserCommunity actualizada
    userCommunityRepository.save(userCommunity);

    // Convertir la entidad actualizada a un DTO y devolverlo
    return "Saliste del grupo exitosamente";
  }

  @Override
  public String banUserFromCommunity(Long userId, Long communityId) {
    // Buscar la relación UserCommunity correspondiente al usuario y la comunidad
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userId, communityId)
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró la relación del usuario con la comunidad"));

    // Cambiar el estado a "inactivo"
    userCommunity.setEstado("baneado");

    // Guardar la entidad UserCommunity actualizada
    userCommunityRepository.save(userCommunity);

    // Convertir la entidad actualizada a un DTO y devolverlo
    return "Se baneo al usuario exitosamente";
  }
}
