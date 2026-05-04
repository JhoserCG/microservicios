package com.peliculas.peliculas_service.repository;



import java.time.LocalDateTime; // ✅ AGREGAR
import java.util.List;          // ✅ AGREGAR

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.peliculas_service.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByNombreContainingIgnoreCase(String nombre);

    List<Pelicula> findByFechaAgregadoAfter(LocalDateTime fecha);
}