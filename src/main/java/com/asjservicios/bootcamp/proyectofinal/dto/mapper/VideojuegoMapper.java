package com.asjservicios.bootcamp.proyectofinal.dto.mapper;

import com.asjservicios.bootcamp.proyectofinal.dto.VideojuegoDTO;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;


public class VideojuegoMapper {
    public static VideojuegoDTO entityToDto(Videojuego entity){
        return new VideojuegoDTO(
                entity.getIdVideojuego(),
                entity.getNombre(),
                entity.getAnio_lanzamiento(),
                entity.getImagen(),
                entity.getDescripcion(),
                entity.getGenero().getGenero(),
                entity.getEmpresa().getEmpresa(),
                entity.getConsola().getConsola(),
                entity.getUsuarios()
        );
    }

    /*public static Videojuego dtoToEntity(VideojuegoDTO dto){
        Videojuego entity = new Videojuego();
        entity.setDescripcion(dto.getDescripcion());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setAnio_lanzamiento(dto.getAnio_lanzamiento());
        entity.setIdVideojuego(dto.getId());
        return entity;
    }*/

}
