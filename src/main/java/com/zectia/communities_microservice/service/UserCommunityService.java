package com.zectia.communities_microservice.service;

import com.zectia.communities_microservice.dto.UserCommunityDto;

public interface UserCommunityService {
  UserCommunityDto joinCommunity(UserCommunityDto UserCommunityDto);

  String leaveCommunity(Long userId, Long communityId);

  String banUserFromCommunity(Long userId, Long communityId);
}
