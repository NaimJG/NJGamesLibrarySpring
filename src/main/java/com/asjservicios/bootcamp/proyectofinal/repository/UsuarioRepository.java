package com.asjservicios.bootcamp.proyectofinal.repository;

import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombreAndPassword(String nombre, String password);
    Optional<Usuario> findByNombre(String nombre);

}
