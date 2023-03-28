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
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresas")
    private int idEmpresas;

    @Column(name = "empresa")
    private String empresa;

    @OneToMany(
            mappedBy = "empresa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Videojuego> videojuegos;

    public Empresa(int idEmpresas, String empresa) {
        this.idEmpresas = idEmpresas;
        this.empresa = empresa;
        this.videojuegos = new ArrayList<>();
    }

}
