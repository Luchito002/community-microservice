package com.zectia.communities_microservice.service;

import java.util.List;

import com.zectia.communities_microservice.dto.CommunityDto;

public interface CommunityService {
  CommunityDto createCommunity(CommunityDto communityDto);

  List<CommunityDto> getCommunities(Long userId);
}
