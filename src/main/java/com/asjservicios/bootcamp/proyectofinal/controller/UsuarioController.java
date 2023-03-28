package com.asjservicios.bootcamp.proyectofinal.controller;

import com.asjservicios.bootcamp.proyectofinal.dto.UsuarioDTO;
import com.asjservicios.bootcamp.proyectofinal.dto.VideojuegoDTO;
import com.asjservicios.bootcamp.proyectofinal.dto.mapper.UsuarioMapper;
import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.entity.Videojuego;
import com.asjservicios.bootcamp.proyectofinal.excepciones.NotFoundException;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.UsuarioService;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.VideojuegoService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path="/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    private final VideojuegoService videojuegoService;

    public UsuarioController(UsuarioService service, VideojuegoService videojuegoService) {
        this.service = service;
        this.videojuegoService = videojuegoService;
    }

    /* ---------- CRUD Principal ----------------- */
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = UsuarioMapper.registerDtoToEntity(usuarioDTO);
        Usuario usuarioExistente = service.usuarioExiste(usuario);

        if (usuarioExistente == usuario) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/all")
    public List<Usuario> findAll(){
        return service.findAll();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")Integer id){

        try {
            Usuario usuarioEncontrado = service.findById(id);
            return ResponseEntity.ok(usuarioEncontrado);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/update/{nombre}")
    public ResponseEntity<?> update(@PathVariable String nombre, @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuarioOriginal = service.findByNombre(nombre);

        if (usuarioOriginal != null) {
            usuarioOriginal.setNombre(usuarioDTO.getNombre());
            usuarioOriginal.setEmail(usuarioDTO.getEmail());
            usuarioOriginal.setPassword(usuarioDTO.getPassword());
            try {
                service.add(usuarioOriginal);
                return ResponseEntity.ok().body(usuarioOriginal);
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre o email ya existen en la base de datos.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al actualizar el usuario.");
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{nombre}")
    public String delete(@PathVariable String nombre){

        Usuario usuarioEncontrado = service.findByNombre(nombre);

        if (usuarioEncontrado != null) {
            service.delete(usuarioEncontrado);
            return "Usuario " + nombre + " fue eliminado";
        } else {
            return "Usuario no encontrado";
        }


    }

    /* --------------- Otros comandos ------------------ */

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = UsuarioMapper.loginDtoToEntity(usuarioDTO);

        Usuario usuarioOptional = service.usuarioNombreExiste(usuario);

        if ( usuarioOptional != null) {
            UsuarioDTO usuarioDevuelto = UsuarioMapper.loginEntityToDto(usuarioOptional);
            return ResponseEntity.ok().body(usuarioDevuelto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{nombre}/videojuego")
    public ResponseEntity<?> agregarVideojuego(@PathVariable String nombre, @RequestBody VideojuegoDTO videojuegoDTO) {

        Usuario usuarioOptional = service.findByNombre(nombre);
        Videojuego videojuegoOptional = videojuegoService.findById(videojuegoDTO.getId());

        if ( videojuegoOptional == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if ( usuarioOptional == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Videojuego> videojuegos = usuarioOptional.getVideojuegoList();

        if (!videojuegos.contains(videojuegoOptional)) {
            videojuegos.add(videojuegoOptional);
            usuarioOptional.setVideojuegoList(videojuegos);
            service.add(usuarioOptional);
        }

            /*
            UsuarioVideojuegos usuarioVideojuego = new UsuarioVideojuegos();
            usuarioVideojuego.setUsuario(usuario);
            usuarioVideojuego.setVideojuego(videojuegoOptional.get());
            usuarioVideojuegoService.add(usuarioVideojuego);*/

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{nombre}/delete/{id}")
    public ResponseEntity<?> eliminarVideojuego(@PathVariable String nombre, @PathVariable Integer id) {

        Usuario usuarioOptional = service.findByNombre(nombre);
        Videojuego videojuegoOptional = videojuegoService.findById(id);

        if ( videojuegoOptional == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if ( usuarioOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Videojuego> videojuegos = usuarioOptional.getVideojuegoList();
        if (videojuegos.remove(videojuegoOptional)) {
            usuarioOptional.setVideojuegoList(videojuegos);
            service.add(usuarioOptional);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /*Optional<UsuarioVideojuegos> relacion = usuarioVideojuegoService.find(usuarioOptional.get().getIdUsuario(), id);
        usuarioVideojuegoService.delete(relacion);*/
    }

}
