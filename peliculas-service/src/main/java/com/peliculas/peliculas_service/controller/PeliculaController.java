package com.peliculas.peliculas_service.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.peliculas.peliculas_service.model.Pelicula;
import com.peliculas.peliculas_service.repository.PeliculaRepository;
import com.peliculas.peliculas_service.service.UsuarioClient;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200")
public class PeliculaController {

    @Autowired
    private PeliculaRepository repo;

    @Autowired
    private UsuarioClient usuarioClient;

    // 🔹 LISTAR TODAS
    @GetMapping
    public List<Pelicula> listar() {
        return repo.findAll();
    }

    // 🔹 BUSCAR POR NOMBRE
    @GetMapping("/buscar")
    public List<Pelicula> buscar(@RequestParam String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    // 🔹 OBTENER POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPorId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 CREAR PELÍCULA
    @PostMapping
    public ResponseEntity<Pelicula> agregar(@RequestBody Pelicula p) {
        p.setFechaAgregado(LocalDateTime.now());
        return ResponseEntity.ok(repo.save(p));
    }

    // 🔹 RECIENTES
    @GetMapping("/recientes")
    public List<Pelicula> recientes() {
        LocalDateTime hace7dias = LocalDateTime.now().minusDays(7);
        return repo.findByFechaAgregadoAfter(hace7dias);
    }

    // 🔹 EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> editar(@PathVariable Long id, @RequestBody Pelicula p) {
        p.setId(id);
        return ResponseEntity.ok(repo.save(p));
    }

    // 🔹 ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 🔥 🔥 🔥 ESTE ES EL IMPORTANTE (MICROSERVICIO)
    @GetMapping("/usuario/{id}")
    public String obtenerUsuarioDesdeOtroServicio(@PathVariable Long id) {
        return usuarioClient.obtenerUsuario(id);
    }
}