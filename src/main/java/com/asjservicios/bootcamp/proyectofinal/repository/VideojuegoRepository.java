package com.asjservicios.bootcamp.proyectofinal.repository;

import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Integer> {


}
