/*
package com.asjservicios.bootcamp.proyectofinal.service.implementaciones;

import com.asjservicios.bootcamp.proyectofinal.entity.UsuarioVideojuegos;
import com.asjservicios.bootcamp.proyectofinal.repository.UsuarioVideojuegoRepository;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.UsuarioVideojuegoService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioVideojuegoImpl implements UsuarioVideojuegoService {

    private final UsuarioVideojuegoRepository repository;

    public UsuarioVideojuegoImpl(UsuarioVideojuegoRepository repository){
        this.repository = repository;
    }

    public UsuarioVideojuegos add(UsuarioVideojuegos usuarioVideojuegos){
        return repository.save(usuarioVideojuegos);
    }

    public List<UsuarioVideojuegos> find(){
        return repository.findAll();
    }

    public Optional<UsuarioVideojuegos> find(Integer id_usuario, Integer id_videojuego) {
        return repository.findByIdAndId(id_usuario, id_videojuego);
    }

    public void delete(Optional<UsuarioVideojuegos> usuarioVideojuegosOptional){
        repository.deleteById(usuarioVideojuegosOptional.get().getId());
    }


}
*/
