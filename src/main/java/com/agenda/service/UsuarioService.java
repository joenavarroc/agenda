package com.agenda.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agenda.model.Rol;
import com.agenda.model.Usuario;
import com.agenda.repository.RolRepository;
import com.agenda.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder) {  // Recibí la interfaz
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(String username, String passwordSinCifrar) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(passwordSinCifrar));
        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Rol ROLE_USER no encontrado"));
        usuario.setRoles(Set.of(rolUser));
        return usuarioRepository.save(usuario);
    }

}
