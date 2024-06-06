package com.zectia.communities_microservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zectia.communities_microservice.dto.UserCommunityDto;
import com.zectia.communities_microservice.exception.ResourceNotFoundException;
import com.zectia.communities_microservice.model.UserCommunity;
import com.zectia.communities_microservice.model.Community;
import com.zectia.communities_microservice.model.CommunityRoles;
import com.zectia.communities_microservice.repository.UserCommunityRepository;
import com.zectia.communities_microservice.repository.CommunityRepository;
import com.zectia.communities_microservice.repository.CommunityRolesRepository;
import com.zectia.communities_microservice.service.UserCommunityService;
import com.zectia.communities_microservice.utils.DtoEntityConverter;

@Service
public class UserCommunityServiceImpl implements UserCommunityService {

  private final UserCommunityRepository userCommunityRepository;
  private final CommunityRepository communityRepository;
  private final CommunityRolesRepository communityRolesRepository;

  private final RestTemplate restTemplate;
  private static final String USER_SERVICE_URL = "https://user-microservice-jq4a.onrender.com/usuarios/";

  @Autowired
  public UserCommunityServiceImpl(
      UserCommunityRepository userCommunityRepository,
      CommunityRepository communityRepository,
      CommunityRolesRepository communityRolesRepository,
      RestTemplate restTemplate) {
    this.userCommunityRepository = userCommunityRepository;
    this.communityRepository = communityRepository;
    this.communityRolesRepository = communityRolesRepository;
    this.restTemplate = restTemplate;
  }

  public String joinCommunity(UserCommunityDto userCommunityDto) {
    UserCommunity userCommunity = DtoEntityConverter.dtoToEntity(userCommunityDto, UserCommunity.class);

    // Obtener la comunidad a la que el usuario se está uniendo
    Community comunidad = communityRepository.findById(userCommunityDto.getComunidadId()).orElse(null);
    if (comunidad == null)
      return "La comunidad especificada no existe";

    // Establecer la comunidad a la que el usuario se está uniendo
    userCommunity.setComunidad(comunidad);

    // Obtener el rol de la comunidad
    CommunityRoles communityRol = communityRolesRepository.findById(userCommunityDto.getRolesComunidadId())
        .orElse(null);
    if (communityRol == null)
      return "El rol de comunidad especificado no existe";

    // Establecer el rol de la comunidad en la entidad UserCommunity
    userCommunity.setRolesComunidad(communityRol);

    // Guardar la entidad UserCommunity
    if (!comunidad.getVisibilidad()) {
      userCommunity.setEstado("pendiente");
      return "Se ha mandado tu solicitud para unirte a esta secta";
    }

    userCommunityRepository.save(userCommunity);
    return "Te has unido exitosamente a la comunidad";
  }

  @Override
  public String leaveCommunity(Long userId, Long communityId, Long newUserAdminId) {
    // Buscar la relación UserCommunity correspondiente al usuario y la comunidad
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userId, communityId)
        .orElse(null);

    if (userCommunity == null) {
      return "El usuario no pertenece a esa comunidad";
    }

    if (userCommunity.getRolesComunidad().getNombre().equals("admin") && newUserAdminId == null) {
      return "Eres administrador de esta comunidad. Por lo tanto, debes asignar a otro usuario el rol de administrador antes de salir";
    }

    // Cambiar el estado a "inactivo"
    userCommunity.setEstado("inactivo");

    // Guardar la entidad UserCommunity actualizada
    userCommunityRepository.save(userCommunity);

    if (newUserAdminId != null) {
      // Verificar si el nuevo administrador está dentro de la comunidad
      UserCommunity newAdminUserCommunity = userCommunityRepository
          .findByIdUsuarioAndComunidadId(newUserAdminId, communityId)
          .orElse(null);

      if (newAdminUserCommunity == null) {
        return "El usuario que se quiere asignar como administrador no está dentro de la comunidad";
      }

      // Obtener el objeto CommunityRoles con ID 1 (suponiendo que el ID 1 corresponde
      // al rol que deseas asignar)
      CommunityRoles adminRole = communityRolesRepository.findById(1L)
          .orElseThrow(null);

      if (adminRole == null) {
        return "Hubo un problema interno con el servidor";
      }

      // Cambiar el ID de rol de newAdminUserCommunity a 1
      newAdminUserCommunity.setRolesComunidad(adminRole);

      // Guardar la entidad UserCommunity actualizada del nuevo administrador
      userCommunityRepository.save(newAdminUserCommunity);
    }

    return "Saliste del grupo exitosamente";
  }

  @Override
  public String changeUserRolCommunity(Long userAdminId, Long communityId, Long newRolForUserId, Long rolId) {
    // Buscar la relación UserCommunity correspondiente al usuario y la comunidad
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userAdminId, communityId)
        .orElse(null);

    if (userCommunity == null) {
      return "El usuario no pertenece a esa comunidad";
    }

    if (!userCommunity.getRolesComunidad().getNombre().equals("admin")) {
      return "No eres administrador de la comunidad, no puedes cambiar el rol de otros usuario de esta comunidad";
    }

    UserCommunity newRolForUserCommunity = userCommunityRepository
        .findByIdUsuarioAndComunidadId(newRolForUserId, communityId)
        .orElse(null);

    if (newRolForUserCommunity == null) {
      return "El usuario al que quieres modificar su rol, no pertenece a esta comunidad";
    }

    CommunityRoles newRol = communityRolesRepository.findById(rolId)
        .orElseThrow(null);

    newRolForUserCommunity.setRolesComunidad(newRol);

    userCommunityRepository.save(newRolForUserCommunity);

    return "Se actualizo el rol del usuario exitosamente";
  }

  @Override
  public String banUserFromCommunity(Long userId, Long communityId) {
    // Buscar la relación UserCommunity correspondiente al usuario y la comunidad
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userId, communityId)
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró la relación del usuario con la comunidad"));

    // Cambiar el estado a "inactivo"
    userCommunity.setEstado("baneado");

    // Guardar la entidad UserCommunity actualizada
    userCommunityRepository.save(userCommunity);

    return "Se baneo al usuario exitosamente";
  }

  @Override
  public String makeCommunityPrivate(Long userAdminId, Long communityId) {
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userAdminId, communityId)
        .orElse(null);

    if (userCommunity == null) {
      return "El administrador no pertenece a esa comunidad";
    }

    if (!userCommunity.getRolesComunidad().getNombre().equals("admin")) {
      return "No eres administrador para cambiar la visibilidad de la comunidad";
    }

    Community community = communityRepository.findById(communityId)
        .orElse(null);

    if (community == null) {
      return "No existe esa comunidad";
    }

    community.setVisibilidad(false);
    communityRepository.save(community);

    return "La visibilidad de la comunidad se actualizó a 'Privado' exitosamente";
  }

  @Override
  public String makeCommunityPublic(Long userAdminId, Long communityId) {
    UserCommunity userCommunity = userCommunityRepository.findByIdUsuarioAndComunidadId(userAdminId, communityId)
        .orElse(null);

    if (userCommunity == null) {
      return "El administrador no pertenece a esa comunidad";
    }

    if (!userCommunity.getRolesComunidad().getNombre().equals("admin")) {
      return "No eres administrador para cambiar la visibilidad de la comunidad";
    }

    Community community = communityRepository.findById(communityId)
        .orElse(null);

    if (community == null) {
      return "No existe esa comunidad";
    }

    community.setVisibilidad(true);
    communityRepository.save(community);

    return "La visibilidad de la comunidad se actualizó a 'Pública' exitosamente";
  }

  @Override
  public List<?> getUsersFromCommunity(Long communityId) {
    List<UserCommunity> userCommunities = userCommunityRepository.findByComunidadId(communityId);

    List<Long> usersIds = userCommunities.stream()
        .map(UserCommunity::getIdUsuario)
        .collect(Collectors.toList());


    String url = USER_SERVICE_URL + "obtener-usuarios-por-ids";

    // Configurar los encabezados de la solicitud
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Crear el cuerpo de la solicitud con la lista de IDs de usuarios
    HttpEntity<List<Long>> requestEntity = new HttpEntity<>(usersIds, headers);

    // Realizar la solicitud POST y obtener la respuesta
    ResponseEntity<Object[]> response = restTemplate.postForEntity(url, requestEntity, Object[].class);

    Object[] users = response.getBody();

    return Arrays.asList(users);
  }
}
