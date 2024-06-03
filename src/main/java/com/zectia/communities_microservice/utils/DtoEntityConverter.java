package com.zectia.communities_microservice.utils;

import org.springframework.beans.BeanUtils;

public class DtoEntityConverter {

    public static <D, E> E dtoToEntity(D dto, Class<E> entityClass) {
        E entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(dto, entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir DTO a entidad", e);
        }
        return entity;
    }

    public static <E, D> D entityToDto(E entity, Class<D> dtoClass) {
        D dto = null;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir entidad a DTO", e);
        }
        return dto;
    }
}
