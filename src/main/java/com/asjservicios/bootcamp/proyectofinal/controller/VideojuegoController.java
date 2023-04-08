package com.asjservicios.bootcamp.proyectofinal.controller;

import com.asjservicios.bootcamp.proyectofinal.dto.VideojuegoDTO;
import com.asjservicios.bootcamp.proyectofinal.dto.mapper.VideojuegoMapper;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.VideojuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path="/videojuegos")
public class VideojuegoController {

    private final VideojuegoService service;

    public VideojuegoController(VideojuegoService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VideojuegoDTO>> find(){
        List<Videojuego> videojuegos = this.service.findAll();
        List<VideojuegoDTO> videojuegoDTOS = videojuegos.stream()
                .map(VideojuegoMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(videojuegoDTOS);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<VideojuegoDTO> find(@PathVariable("id") Integer id){
        Videojuego videojuegoEncontrado = this.service.findById(id);
        VideojuegoDTO videojuegoDTO = new VideojuegoDTO(
                videojuegoEncontrado.getIdVideojuego(),
                videojuegoEncontrado.getNombre(),
                videojuegoEncontrado.getAnio_lanzamiento(),
                videojuegoEncontrado.getImagen(),
                videojuegoEncontrado.getVideo(),
                videojuegoEncontrado.getDescripcion(),
                videojuegoEncontrado.getGenero().getGenero(),
                videojuegoEncontrado.getEmpresa().getEmpresa(),
                videojuegoEncontrado.getConsola().getConsola(),
                videojuegoEncontrado.getUsuarios()
        );

        return ResponseEntity.ok(videojuegoDTO);
    }

}
