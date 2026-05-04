package com.peliculas.usuarios_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.peliculas.usuarios_service.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailAndPassword(String email, String password);

    // ✅ Verificar si el correo ya existe
    Optional<Usuario> findByEmail(String email);
}