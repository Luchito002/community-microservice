package com.zectia.communities_microservice.dto;

import com.zectia.communities_microservice.model.Community;

public class CommunityDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String urlImagenComunidad;
    private String urlImagenPortada;
    private Boolean estado;

    public CommunityDto() {}

    public CommunityDto(Community community) {
        this.id = community.getId();
        this.nombre = community.getNombre();
        this.descripcion = community.getDescripcion();
        this.urlImagenComunidad = community.getUrlImagenComunidad();
        this.urlImagenPortada = community.getUrlImagenPortada();
        this.estado = community.getEstado();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagenComunidad() {
        return urlImagenComunidad;
    }

    public void setUrlImagenComunidad(String urlImagenComunidad) {
        this.urlImagenComunidad = urlImagenComunidad;
    }

    public String getUrlImagenPortada() {
        return urlImagenPortada;
    }

    public void setUrlImagenPortada(String urlImagenPortada) {
        this.urlImagenPortada = urlImagenPortada;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
