package com.zectia.communities_microservice.dto;

public class CommunityRolesDto {
    private Long id;
    private Long usuarioComunidadId;
    private String nombre;

    public CommunityRolesDto() {}

    public CommunityRolesDto(Long id, Long usuarioComunidadId, String nombre) {
        this.id = id;
        this.usuarioComunidadId = usuarioComunidadId;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioComunidadId() {
        return usuarioComunidadId;
    }

    public void setUsuarioComunidadId(Long usuarioComunidadId) {
        this.usuarioComunidadId = usuarioComunidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
