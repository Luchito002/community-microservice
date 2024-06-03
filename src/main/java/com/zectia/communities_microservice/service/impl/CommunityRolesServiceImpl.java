package com.zectia.communities_microservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zectia.communities_microservice.dto.CommunityRolesDto;
//import com.zectia.communities_microservice.exception.ResourceNotFoundException;
import com.zectia.communities_microservice.model.CommunityRoles;
import com.zectia.communities_microservice.repository.CommunityRolesRepository;
import com.zectia.communities_microservice.service.CommunityRolesService;
import com.zectia.communities_microservice.utils.DtoEntityConverter;

@Service
public class CommunityRolesServiceImpl implements CommunityRolesService {

  private final CommunityRolesRepository communityRolesRepository;

  @Autowired
  public CommunityRolesServiceImpl(CommunityRolesRepository communityRolesRepository) {
    this.communityRolesRepository = communityRolesRepository;
  }

  @Override
  public CommunityRolesDto createCommunityRol(CommunityRolesDto communityRolesDto) {
    CommunityRoles communityRol = DtoEntityConverter.dtoToEntity(communityRolesDto, CommunityRoles.class);
    CommunityRoles insertedCommunityRol = this.communityRolesRepository.save(communityRol);
    return DtoEntityConverter.entityToDto(insertedCommunityRol, CommunityRolesDto.class);
  }
}
