package com.asjservicios.bootcamp.proyectofinal.datos;

import com.asjservicios.bootcamp.proyectofinal.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatosDummy {

    public static Videojuego getVideojuegoKirby(){
        List<Usuario> usuariosList = new ArrayList<>();
        Videojuego videojuego = new Videojuego(1, "Kirby and the Forgotten Land", LocalDate.of(2022, 11, 15), "blablablablabla", "imagen.jpg", new Genero(1, "aventura"), new Consola(1, "Nintendo Switch"), new Empresa(1, "Nintendo etc"), usuariosList);
        return videojuego;
    }

    public static Videojuego getVideojuegoZelda(){
        List<Usuario> usuariosList = new ArrayList<>();
        Videojuego videojuego = new Videojuego(2, "Legend of Zelda: Tears of the Kingdom", LocalDate.of(2022, 8, 5), "blablablabla", "imagen2.jpg", new Genero(2, "accion"), new Consola(1, "Nintendo Switch"), new Empresa(1, "Nintendo etc"), usuariosList);
        return videojuego;
    }

    public static Usuario getUsuarioNaim(){
        List<Videojuego> videojuegos = new ArrayList<>();
        return new Usuario(1, "Naim", "Naim.12345", "naimjose@gmail.com", videojuegos);
    }

    public static Usuario getUsuarioAny(){
        List<Videojuego> videojuegos = new ArrayList<>();
        return new Usuario(2, "Any", "Any.123456", "any@gmail.com", videojuegos);
    }


}
