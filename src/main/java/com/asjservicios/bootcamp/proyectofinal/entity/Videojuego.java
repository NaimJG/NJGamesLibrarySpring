package com.asjservicios.bootcamp.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="videojuegos")
public class Videojuego {
    @Id
    @NotNull
    @Unsigned
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_videojuego", unique = true)
    private int idVideojuego;
    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "anio_lanzamiento")
    private LocalDate anio_lanzamiento;
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Column(name = "imagen")
    private String imagen;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "generos_id_generos")
    private Genero genero;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "consolas_id_consolas")
    private Consola consola;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "empresas_id_empresas")
    private Empresa empresa;


    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "videojuegoList"
    )
    @JsonIgnore
    private List<Usuario> usuarios;


}
