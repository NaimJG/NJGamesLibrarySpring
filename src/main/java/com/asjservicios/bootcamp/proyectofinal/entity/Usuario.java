package com.asjservicios.bootcamp.proyectofinal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @NotNull
    @Unsigned
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true)
    private int idUsuario;
    @NotNull
    @Column(name = "nombre", unique = true)
    private String nombre;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "usuario_has_videojuegos",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_videojuego")
    )
    @JsonIgnore
    private List<Videojuego> videojuegoList;

}
