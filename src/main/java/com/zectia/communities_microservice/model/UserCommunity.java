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
@Table(name = "usuario_comunidad")
public class UserCommunity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "usuarios_id", nullable = false)
  private Long idUsuario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comunidades_id", nullable = false)
  private Community comunidad;

  @Column(name = "estado", nullable = false, length = 15)
  private String estado = "activo";

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "roles_comunidad_id", nullable = false)
  private CommunityRoles rolesComunidad;

  public UserCommunity() {
  }

  public UserCommunity(Long idUsuario, Community comunidad, String estado, CommunityRoles rolesComunidad) {
    this.idUsuario = idUsuario;
    this.comunidad = comunidad;
    this.estado = estado;
    this.rolesComunidad = rolesComunidad;
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

  public Community getComunidad() {
    return comunidad;
  }

  public void setComunidad(Community comunidad) {
    this.comunidad = comunidad;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public CommunityRoles getRolesComunidad() {
    return rolesComunidad;
  }

  public void setRolesComunidad(CommunityRoles rolesComunidad) {
    this.rolesComunidad = rolesComunidad;
  }
}
