package com.asjservicios.bootcamp.proyectofinal.dto.mapper;

import com.asjservicios.bootcamp.proyectofinal.dto.UsuarioDTO;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;


public class UsuarioMapper {

    public static UsuarioDTO loginEntityToDto(Usuario entity){
        return new UsuarioDTO(
                entity.getNombre(),
                entity.getPassword(),
                entity.getEmail()
        );
    }

    public static Usuario loginDtoToEntity(UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setNombre(dto.getNombre());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static Usuario registerDtoToEntity(UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }


}
