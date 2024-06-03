package com.zectia.communities_microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "roles_comunidad")
public class CommunityRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_comunidad_id", nullable = false)
    private UserCommunity usuarioComunidad;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    public CommunityRoles() {}

    public CommunityRoles(UserCommunity usuarioComunidad, String nombre) {
        this.usuarioComunidad = usuarioComunidad;
        this.nombre = nombre;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCommunity getUsuarioComunidad() {
        return usuarioComunidad;
    }

    public void setUsuarioComunidad(UserCommunity usuarioComunidad) {
        this.usuarioComunidad = usuarioComunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
