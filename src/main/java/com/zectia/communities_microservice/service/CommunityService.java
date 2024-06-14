package com.zectia.communities_microservice.service;

import java.util.List;

import com.zectia.communities_microservice.dto.CommunityDto;
import com.zectia.communities_microservice.model.Community;

public interface CommunityService {
  String createCommunity(CommunityDto communityDto);

  String disableCommunity(Long communityId);

  String enableCommunity(Long communityId);

  List<Community> getCommunities();

  List<CommunityDto> getCommunitiesByUserId(Long userId);
}
