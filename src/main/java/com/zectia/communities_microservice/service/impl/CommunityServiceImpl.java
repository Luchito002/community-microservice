package com.zectia.communities_microservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zectia.communities_microservice.dto.CommunityDto;
//import com.zectia.communities_microservice.exception.ResourceNotFoundException;
import com.zectia.communities_microservice.model.Community;
import com.zectia.communities_microservice.repository.CommunityRepository;
import com.zectia.communities_microservice.service.CommunityService;

@Service
public class CommunityServiceImpl implements CommunityService {

  private final CommunityRepository communityRepository;

  @Autowired
  public CommunityServiceImpl(CommunityRepository communityRepository) {
    this.communityRepository = communityRepository;
  }

  @Override
  public CommunityDto createCommunity(CommunityDto communityDto) {
    Community community = this.dtoToEntity(communityDto);
    Community insertedCommunity = this.communityRepository.save(community);
    return new CommunityDto(insertedCommunity);
  }

  private Community dtoToEntity(CommunityDto communityDto) {
    Community community = new Community();
    BeanUtils.copyProperties(communityDto, community);
    return community;
  }
}
