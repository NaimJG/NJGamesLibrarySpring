package com.asjservicios.bootcamp.proyectofinal.service.implementaciones;

import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;
import com.asjservicios.bootcamp.proyectofinal.excepciones.NotFoundException;
import com.asjservicios.bootcamp.proyectofinal.repository.VideojuegoRepository;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.VideojuegoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    private final VideojuegoRepository repository;

    public VideojuegoServiceImpl(VideojuegoRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Videojuego> findAll() {
        return repository.findAll();
    }

    @Override
    public Videojuego findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Videojuego no encontrado con el id: " + id));
    }

}
