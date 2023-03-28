package com.asjservicios.bootcamp.proyectofinal.service.interfaces;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;

import java.util.List;
import java.util.Optional;

public interface VideojuegoService {

    List<Videojuego> findAll();

    Videojuego findById(Integer id);


}
