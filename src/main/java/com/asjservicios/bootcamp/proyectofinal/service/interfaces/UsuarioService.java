package com.asjservicios.bootcamp.proyectofinal.service.interfaces;

import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(Integer id);

    Usuario findByNombre(String nombre);

    Usuario usuarioExiste(Usuario usuario);

    Usuario add(Usuario valor);

    String delete(Usuario usuario);

    Usuario usuarioNombreExiste(Usuario usuario);

}
