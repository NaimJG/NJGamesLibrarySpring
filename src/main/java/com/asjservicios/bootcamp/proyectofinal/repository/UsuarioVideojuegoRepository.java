/*
package com.asjservicios.bootcamp.proyectofinal.repository;

import com.asjservicios.bootcamp.proyectofinal.entity.UsuarioVideojuegos;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioVideojuegoRepository extends JpaRepository<UsuarioVideojuegos, Integer> {

    @Modifying
    @Transactional
    @Query("SELECT FROM UsuarioVideojuegos uv WHERE uv.usuario.id = ?1 AND uv.videojuego.id = ?2")
    Optional<UsuarioVideojuegos> findByIdAndId(@Param("id_usuario") Integer idUsuario, @Param("id_videojuego") Integer idVideojuego);

}

*/
