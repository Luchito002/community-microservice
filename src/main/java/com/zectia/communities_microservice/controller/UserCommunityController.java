package com.zectia.communities_microservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public String joinCommunity(UserCommunityDto userCommunityDto) {
    return this.userCommunityService.joinCommunity(userCommunityDto);
  }

  @PutMapping("salir/{usuarioId}/{comunidadId}/{nuevoUsuarioAdminId}")
  public String leaveCommunity(@RequestParam Long userId, @RequestParam Long communityId, @RequestParam(required = false) Long newUserAdminId) {
    return this.userCommunityService.leaveCommunity(userId, communityId, newUserAdminId);
  }

  @PutMapping("banear")
  public String banUserFromCommunity(@RequestParam Long userId, @RequestParam Long communityId) {
    return this.userCommunityService.banUserFromCommunity(userId, communityId);
  }

  @PutMapping("cambiar-rol-usuario/{adminId}/{comunidadId}/{nuevoRolParaUsuarioId}/{rolId}")
  public String changeUserRolCommunity(Long userAdminId, Long communityId, Long newRolForUserId, Long rolId) {
    return this.userCommunityService.changeUserRolCommunity(userAdminId, communityId, newRolForUserId, rolId);
  }

  @PutMapping("cambiar-visibilidad-comunidad-privado/{usuarioAdminId}/{comunidadId}")
  public String makeCommunityPrivate(Long userAdminId, Long communityId) {
    return this.userCommunityService.makeCommunityPrivate(userAdminId, communityId);
  }

  @PutMapping("cambiar-visibilidad-comunidad-publico/{usuarioAdminId}/{comunidadId}")
  public String makeCommunityPublic(Long userAdminId, Long communityId) {
    return this.userCommunityService.makeCommunityPublic(userAdminId, communityId);
  }
}
