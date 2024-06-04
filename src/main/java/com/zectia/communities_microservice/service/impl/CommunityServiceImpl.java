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
  public CommunityServiceImpl(CommunityRepository communityRepository, UserCommunityRepository userCommunityRepository) {
    this.communityRepository = communityRepository;
    this.userCommunityRepository = userCommunityRepository;
  }

  @Override
  public CommunityDto createCommunity(CommunityDto communityDto) {
    Community community = DtoEntityConverter.dtoToEntity(communityDto, Community.class);
    Community insertedCommunity = this.communityRepository.save(community);
    return new CommunityDto(insertedCommunity);
  }

  @Override
  public List<CommunityDto> getCommunities(Long userId) {
    List<UserCommunity> userCommunities = userCommunityRepository.findByIdUsuario(userId);

    return userCommunities.stream()
        .map(UserCommunity::getComunidad)
        .map(CommunityDto::new)
        .collect(Collectors.toList());
  }
}
