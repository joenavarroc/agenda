package com.agenda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}