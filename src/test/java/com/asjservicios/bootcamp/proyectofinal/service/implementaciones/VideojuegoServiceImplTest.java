package com.asjservicios.bootcamp.proyectofinal.service.implementaciones;

import com.asjservicios.bootcamp.proyectofinal.datos.DatosDummy;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;
import com.asjservicios.bootcamp.proyectofinal.excepciones.NotFoundException;
import com.asjservicios.bootcamp.proyectofinal.repository.VideojuegoRepository;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.VideojuegoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class VideojuegoServiceImplTest {

    @MockBean
    private VideojuegoRepository repository;
    @Autowired
    private VideojuegoService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        DatosDummy.getVideojuegoKirby(),
                        DatosDummy.getVideojuegoZelda()
                ));

        List<Videojuego> videojuegos = service.findAll();

        assertThat(videojuegos.size())
                .isEqualTo(2);

        assertThat(videojuegos.isEmpty())
                .isFalse();

        verify(repository, times(1)).findAll();
    }

    @Test
    void findById() {

        Integer idVideojuego = 1;

        when(repository.findById(idVideojuego))
                .thenReturn(Optional.of(
                        DatosDummy.getVideojuegoKirby()
                ));

        Videojuego videojuegoEncontrado = service.findById(idVideojuego);

        assertThat(videojuegoEncontrado.getIdVideojuego())
                .isEqualTo(idVideojuego);

        assertThat(videojuegoEncontrado.getNombre())
                .isEqualTo("Kirby and the Forgotten Land");

    }

    @Test
    @DisplayName("Videojuego no encontrado por Id")
    void findByIdNotFound() {
        Integer idVideojuego = 1;

        when(repository.findById(idVideojuego))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(idVideojuego))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Videojuego no encontrado con el id: " + idVideojuego);
    }

}