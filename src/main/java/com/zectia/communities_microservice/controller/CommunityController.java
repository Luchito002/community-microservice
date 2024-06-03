package com.zectia.communities_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.communities_microservice.dto.CommunityDto;
import com.zectia.communities_microservice.service.CommunityService;

@RestController
@RequestMapping("/comunidades")
public class CommunityController {
  private final CommunityService communityService;

  @Autowired
  public CommunityController(CommunityService communityService) {
    this.communityService = communityService;
  }

  @PostMapping("crearcomunidad")
  public CommunityDto createCommunity(CommunityDto communityDto) {
    return this.communityService.createCommunity(communityDto);
  }

}
