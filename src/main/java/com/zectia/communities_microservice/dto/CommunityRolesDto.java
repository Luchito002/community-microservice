package com.zectia.communities_microservice.dto;

public class CommunityRolesDto {
    private Long id;
    private String nombre;

    public CommunityRolesDto() {}

    public CommunityRolesDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
