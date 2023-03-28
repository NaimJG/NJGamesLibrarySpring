/*

package com.asjservicios.bootcamp.proyectofinal.controller;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.entity.UsuarioVideojuegos;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.UsuarioVideojuegoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path="/usuariosvideojuegos")
public class UsuarioVideojuegoController {

    private UsuarioVideojuegoService usuarioVideojuegoService;

    public UsuarioVideojuegoController(UsuarioVideojuegoService usuarioVideojuegoService){
        this.usuarioVideojuegoService = usuarioVideojuegoService;
    }

    @GetMapping("/all")
    public List<UsuarioVideojuegos> find(){
        return usuarioVideojuegoService.find();
    }


}
*/
