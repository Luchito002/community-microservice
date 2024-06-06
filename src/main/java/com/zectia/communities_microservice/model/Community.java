package com.zectia.communities_microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.Set;

@Entity
@Table(name = "comunidades")
public class Community {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @Column(name = "descripcion", nullable = false, length = 250)
  private String descripcion;

  @Column(name = "url_imagen_comunidad")
  private String urlImagenComunidad;

  @Column(name = "url_imagen_portada")
  private String urlImagenPortada;

  @Column(name = "estado", nullable = false)
  private Boolean estado;

  @Column(name = "visibilidad", nullable = false)
  private Boolean visibilidad = true;

  @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserCommunity> usuarioComunidades;

  public Community() {
  }

  public Community(String nombre, String descripcion, String urlImagenComunidad, String urlImagenPortada,
      Boolean estado) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.urlImagenComunidad = urlImagenComunidad;
    this.urlImagenPortada = urlImagenPortada;
    this.estado = estado;
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

  public Boolean getVisibilidad() {
    return visibilidad;
  }

  public void setVisibilidad(Boolean visibilidad) {
    this.visibilidad = visibilidad;
  }

  public Set<UserCommunity> getUsuarioComunidades() {
    return usuarioComunidades;
  }

  public void setUsuarioComunidades(Set<UserCommunity> usuarioComunidades) {
    this.usuarioComunidades = usuarioComunidades;
  }
}
