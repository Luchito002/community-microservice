package com.zectia.communities_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.communities_microservice.dto.CommunityDto;
import com.zectia.communities_microservice.model.Community;
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
  public String createCommunity(@RequestBody CommunityDto communityDto) {
    return this.communityService.createCommunity(communityDto);
  }

  @GetMapping("obtener-comunidades")
  public List<Community> getCommunities() {
    return this.communityService.getCommunities();
  }

  @GetMapping("obtener-comunidades-por-usuario-id/{id}")
  public List<CommunityDto> getCommunitiesByUserId(@PathVariable Long id) {
    return this.communityService.getCommunitiesByUserId(id);
  }

  @PutMapping("desactivar-comunidad/{comunidadId}")
  public String disableCommunity(@PathVariable Long comunidadId) {
    return this.communityService.disableCommunity(comunidadId);
  }

  @PutMapping("activar-comunidad/{comunidadId}")
  public String enableCommunity(@PathVariable Long comunidadId) {
    return this.communityService.enableCommunity(comunidadId);
  }
}
