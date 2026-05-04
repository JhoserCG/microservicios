package com.peliculas.usuarios_service.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.peliculas.usuarios_service.model.UsuarioConectado;

public interface UsuarioConectadoRepository extends JpaRepository<UsuarioConectado, Long> {
    List<UsuarioConectado> findByPeliculaId(Long peliculaId);

    @Transactional
    void deleteByUsuarioIdAndPeliculaId(Long usuarioId, Long peliculaId);

    // ✅ Verificar si el usuario ya está en la sala
    boolean existsByUsuarioIdAndPeliculaId(Long usuarioId, Long peliculaId);
}