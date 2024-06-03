package com.zectia.communities_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.communities_microservice.dto.UserCommunityDto;
import com.zectia.communities_microservice.service.UserCommunityService;

@RestController
@RequestMapping("/usuario-comunidad")
public class UserCommunityController {
  private final UserCommunityService userCommunityService;

  @Autowired
  public UserCommunityController(UserCommunityService userCommunityService) {
    this.userCommunityService = userCommunityService;
  }

  @PostMapping("unir")
  public UserCommunityDto joinCommunity(UserCommunityDto userCommunityDto) {
    return this.userCommunityService.joinCommunity(userCommunityDto);
  }

}
