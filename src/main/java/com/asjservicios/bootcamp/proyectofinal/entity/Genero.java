package com.asjservicios.bootcamp.proyectofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_generos")
    private int idGeneros;

    @Column(name = "genero")
    private String genero;

    @OneToMany(
            mappedBy = "genero",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Videojuego> videojuegos;

    public Genero(int idGeneros, String genero) {
        this.idGeneros = idGeneros;
        this.genero = genero;
        this.videojuegos = new ArrayList<>();
    }

}
