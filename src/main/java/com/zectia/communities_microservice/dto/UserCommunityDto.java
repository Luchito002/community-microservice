package com.zectia.communities_microservice.dto;

public class UserCommunityDto {
    private Long id;
    private Long idUsuario;
    private Long comunidadId;
    private String estado;
    private Long rolesComunidadId;

    public UserCommunityDto() {}

    public UserCommunityDto(Long id, Long idUsuario, Long comunidadId, String estado, Long rolesComunidadId) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.comunidadId = comunidadId;
        this.estado = estado;
        this.rolesComunidadId = rolesComunidadId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getComunidadId() {
        return comunidadId;
    }

    public void setComunidadId(Long comunidadId) {
        this.comunidadId = comunidadId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getRolesComunidadId() {
        return rolesComunidadId;
    }

    public void setRolesComunidadId(Long rolesComunidadId) {
        this.rolesComunidadId = rolesComunidadId;
    }
}
