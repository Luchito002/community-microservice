package com.zectia.communities_microservice.service;

import com.zectia.communities_microservice.dto.UserCommunityDto;

public interface UserCommunityService {
  String joinCommunity(UserCommunityDto UserCommunityDto);

  String leaveCommunity(Long userId, Long communityId, Long newUserAdminId);

  String changeUserRolCommunity(Long userAdminId, Long communityId, Long newRolForUserId, Long rolId);

  String banUserFromCommunity(Long userId, Long communityId);

  String makeCommunityPrivate(Long userAdminId, Long communityId);

  String makeCommunityPublic(Long userAdminId, Long communityId);
}
