package com.asjservicios.bootcamp.proyectofinal.dto;

import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideojuegoDTO {
    private int id;
    private String nombre;
    private LocalDate anio_lanzamiento;
    private String imagen;
    private String video;
    private String descripcion;
    private String genero;
    private String empresa;
    private String consola;
    private List<Usuario> usuarioList;

}
