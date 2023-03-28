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
@Table(name = "consolas")
public class Consola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consolas")
    private int idConsolas;

    @Column(name = "consola")
    private String consola;

    @OneToMany(
            mappedBy = "empresa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Videojuego> videojuegos;

    public Consola(int idConsolas, String consola) {
        this.idConsolas = idConsolas;
        this.consola = consola;
        this.videojuegos = new ArrayList<>();
    }

}
