package com.zectia.communities_microservice.service;

import java.util.List;

import com.zectia.communities_microservice.dto.CommunityDto;
import com.zectia.communities_microservice.model.Community;

public interface CommunityService {
  CommunityDto createCommunity(CommunityDto communityDto);

  List<Community> getCommunities();

  List<CommunityDto> getCommunitiesByUserId(Long userId);

  String leftCommunity(Long userId, Long communityId);
}
