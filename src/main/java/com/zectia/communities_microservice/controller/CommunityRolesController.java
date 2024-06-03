package com.zectia.communities_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.communities_microservice.dto.CommunityRolesDto;
import com.zectia.communities_microservice.service.CommunityRolesService;

@RestController
@RequestMapping("/roles-comunidad")
public class CommunityRolesController {
  private final CommunityRolesService communityRolesService;

  @Autowired
  public CommunityRolesController(CommunityRolesService communityRolesService) {
    this.communityRolesService = communityRolesService;
  }

  @PostMapping("crear-rolcomunidad")
  public CommunityRolesDto createCommunity(CommunityRolesDto communityDto) {
    return this.communityRolesService.createCommunityRol(communityDto);
  }

}
