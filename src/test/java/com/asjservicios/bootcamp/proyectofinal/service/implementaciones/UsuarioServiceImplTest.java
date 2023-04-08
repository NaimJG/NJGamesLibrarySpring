package com.asjservicios.bootcamp.proyectofinal.service.implementaciones;

import com.asjservicios.bootcamp.proyectofinal.datos.DatosDummy;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.excepciones.NotFoundException;
import com.asjservicios.bootcamp.proyectofinal.repository.UsuarioRepository;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.UsuarioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
class UsuarioServiceImplTest {

    @MockBean
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Usuarios encontrados")
    void findAll() {
        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        DatosDummy.getUsuarioNaim(),
                        DatosDummy.getUsuarioAny()
                ));

        List<Usuario> usuarios = service.findAll();

        assertThat(usuarios.size())
                .isEqualTo(2);

        assertThat(usuarios.isEmpty())
                .isFalse();

        verify(repository, times(1)).findAll();

    }

    @Test
    @DisplayName("Usuario encontrado por Id")
    void findById() {

        Integer idUsuario = 1;

        when(repository.findById(idUsuario))
                .thenReturn(Optional.of(
                        DatosDummy.getUsuarioNaim()
                ));

        Usuario usuarioEncontrado = service.findById(idUsuario);

        assertThat(usuarioEncontrado.getIdUsuario())
                .isEqualTo(idUsuario);

        assertThat(usuarioEncontrado.getNombre())
                .isEqualTo("Naim");

    }

    @Test
    @DisplayName("Usuario no encontrado por Id")
    void findByIdNotFound() {
        Integer idUsuario = 2;

        when(repository.findById(idUsuario))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(idUsuario))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Usuario no encontrado con el id: " + idUsuario);
    }

    // Caso en que encuentra el usuario ya existente en la BD cuando se usa método register
    @Test
    @DisplayName("Usuario Existe")
    void usuarioExiste() {

        String email = "naimjose@gmail.com";

        when(this.repository.findByEmail(email))
                .thenReturn(Optional.of(
                        DatosDummy.getUsuarioNaim()
                ));

        Usuario usuarioEncontrado = service.usuarioExiste(DatosDummy.getUsuarioNaim());

        assertThat(usuarioEncontrado.getIdUsuario())
                .isEqualTo(1);
        assertThat(usuarioEncontrado.getNombre())
                .isEqualTo("Naim");

    }

    // Caso en el que no encuentra al usuario en la DB cuando se usa el método register
    @Test
    @DisplayName("Usuario Guardado")
    void usuarioExiste2() {

        String email = "usuarioInexistente@gmail.com";

        when(this.repository.findByEmail(email))
                .thenReturn(Optional.empty());

        Usuario usuarioNuevo = DatosDummy.getUsuarioNaim();
        usuarioNuevo.setEmail(email);

        Usuario usuarioGuardado = service.usuarioExiste(usuarioNuevo);

        verify(repository, times(1)).findByEmail(email);
        verify(repository, times(1)).save(usuarioNuevo);

        assertThat(usuarioGuardado).isEqualTo(usuarioNuevo);

    }

    @Test
    @DisplayName("Registrar usuario")
    void add() {
        Usuario usuario = DatosDummy.getUsuarioAny();

        service.add(usuario);

        ArgumentCaptor<Usuario> usuarioArgumentCaptor
                = ArgumentCaptor.forClass(Usuario.class);

        verify(repository).save(usuarioArgumentCaptor.capture());

        Usuario usuarioGuardado = usuarioArgumentCaptor.getValue();

        assertThat(usuarioGuardado)
                .isEqualTo(usuario);

    }

    @Test
    @DisplayName("Usuario encontrado por Nombre")
    void findByNombre() {

        String nombre = "Naim";

        when(repository.findByNombre(nombre))
                .thenReturn(Optional.of(
                        DatosDummy.getUsuarioNaim()
                ));

        Usuario usuarioEncontrado = service.findByNombre(nombre);

        assertThat(usuarioEncontrado.getNombre())
                .isEqualTo(nombre);

    }

    @Test
    @DisplayName("Usuario no encontrado por Nombre")
    void findByNombreNoEncontrado() {

        String nombre = "NombreInexistente";
        when(repository.findByNombre(nombre)).thenReturn(Optional.empty());

        Usuario usuarioEncontrado = service.findByNombre(nombre);

        assertThat(usuarioEncontrado).isNull();
    }

    @Test
    void delete() {

        Usuario usuarioExistente = DatosDummy.getUsuarioNaim();

        String mensajeEliminarExistente = service.delete(usuarioExistente);
        verify(repository, times(1)).delete(usuarioExistente);
        assertThat(mensajeEliminarExistente).isEqualTo("Usuario 1 eliminado");

    }

    @Test
    void usuarioNombreExiste() {
        Usuario usuarioExistente = DatosDummy.getUsuarioNaim();
        Usuario usuarioNoExistente = new Usuario();
        usuarioNoExistente.setNombre("John");
        usuarioNoExistente.setPassword("password123");

        when(repository.findByNombreAndPassword(usuarioExistente.getNombre(), usuarioExistente.getPassword()))
                .thenReturn(Optional.of(usuarioExistente));
        when(repository.findByNombreAndPassword(usuarioNoExistente.getNombre(), usuarioNoExistente.getPassword()))
                .thenReturn(Optional.empty());

        Usuario usuarioOptionalExistente = service.usuarioNombreExiste(usuarioExistente);
        Usuario usuarioOptionalNoExistente = service.usuarioNombreExiste(usuarioNoExistente);

        assertThat(usuarioOptionalExistente).isEqualTo(usuarioExistente); // Verificar que el usuario retornado es el esperado para el usuario existente
        assertThat(usuarioOptionalNoExistente).isNull(); // Verificar que el objeto Optional está vacío para el usuario no existente

    }


}