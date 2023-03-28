package com.asjservicios.bootcamp.proyectofinal.repository;

import com.asjservicios.bootcamp.proyectofinal.datos.DatosDummy;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import lombok.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Email encontrado")
    void findByEmail() {
        repository.save(DatosDummy.getUsuarioNaim());
        Optional<Usuario> usuarioOptional= repository.findByEmail("naimjose@gmail.com");
        assertThat(usuarioOptional.isPresent()).isTrue();
        assertThat(usuarioOptional.get().getEmail()).isEqualTo("naimjose@gmail.com");
    }

    @Test
    @DisplayName("Nombre y Password encontrado")
    void findByNombreAndPassword() {
        repository.save(DatosDummy.getUsuarioNaim());
        Optional<Usuario> usuarioOptional= repository.findByNombreAndPassword("Naim", "Naim.12345");
        assertThat(usuarioOptional.isPresent()).isTrue();
        assertThat(usuarioOptional.get().getNombre()).isEqualTo("Naim");
        assertThat(usuarioOptional.get().getPassword()).isEqualTo("Naim.12345");
    }

    @Test
    @DisplayName("Nombre encontrado")
    void findByNombre() {
        repository.save(DatosDummy.getUsuarioNaim());
        Optional<Usuario> usuarioOptional= repository.findByNombre("Naim");
        assertThat(usuarioOptional.isPresent()).isTrue();
        assertThat(usuarioOptional.get().getNombre()).isEqualTo("Naim");
    }

}