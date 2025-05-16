package com.agenda.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.List;

public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public String getPassword() {
        return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes agregar lógica adicional si lo deseas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes agregar lógica adicional si lo deseas
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes agregar lógica adicional si lo deseas
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes agregar lógica adicional si lo deseas
    }
}
