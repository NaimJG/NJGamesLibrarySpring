package com.asjservicios.bootcamp.proyectofinal.service.implementaciones;

import com.asjservicios.bootcamp.proyectofinal.entity.Usuario;
import com.asjservicios.bootcamp.proyectofinal.excepciones.NotFoundException;
import com.asjservicios.bootcamp.proyectofinal.repository.UsuarioRepository;
import com.asjservicios.bootcamp.proyectofinal.service.interfaces.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con el id: " + id));
    }

    @Override
    public Usuario usuarioExiste(Usuario usuario) {
        Optional<Usuario> usuarioOptional = repository.findByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        } else {
            repository.save(usuario);
            return usuario;
        }
    }

    @Override
    public Usuario add(Usuario valor) {
        return repository.save(valor);
    }

    @Override
    public Usuario findByNombre(String nombre) {
        Optional<Usuario> usuarioOptional = repository.findByNombre(nombre);
        return usuarioOptional.orElse(null);
    }

    @Override
    public String delete(Usuario usuario) {
        repository.delete(usuario);
        return "Usuario " + usuario.getIdUsuario() + " eliminado";
    }

    @Override
    public Usuario usuarioNombreExiste(Usuario usuario) {
        Optional<Usuario> usuarioOptional = repository.findByNombreAndPassword(usuario.getNombre(), usuario.getPassword());
        return usuarioOptional.orElse(null);
    }

}
