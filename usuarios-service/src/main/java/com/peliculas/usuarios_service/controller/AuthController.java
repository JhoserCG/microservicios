package com.peliculas.usuarios_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.usuarios_service.model.Usuario;
import com.peliculas.usuarios_service.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository repo;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario u) {
        if (repo.findByEmail(u.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body("Este correo ya está registrado.");
        }
        return ResponseEntity.ok(repo.save(u));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario u) {
        return repo.findByEmailAndPassword(u.getEmail(), u.getPassword())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(401).build());
    }

    // 🔥 ESTE ES EL QUE TE FALTA
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}