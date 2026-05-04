package com.peliculas.usuarios_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.usuarios_service.model.Usuario;
import com.peliculas.usuarios_service.model.UsuarioConectado;
import com.peliculas.usuarios_service.repository.UsuarioConectadoRepository;
import com.peliculas.usuarios_service.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/sala")
@CrossOrigin(origins = "*")
public class UsuarioConectadoController {

    @Autowired
    private UsuarioConectadoRepository salaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Entrar a ver una película
    @PostMapping("/entrar")
    public ResponseEntity<?> entrar(@RequestBody UsuarioConectado uc) {
        if (salaRepo.existsByUsuarioIdAndPeliculaId(
                uc.getUsuario().getId(), uc.getPeliculaId())) {
            return ResponseEntity.ok("Ya está en la sala");
        }
        Usuario u = usuarioRepo.findById(uc.getUsuario().getId()).orElse(null);
        if (u == null) return ResponseEntity.notFound().build();
        uc.setUsuario(u);
        return ResponseEntity.ok(salaRepo.save(uc));
    }

    // Salir de ver una película
    @DeleteMapping("/salir/{usuarioId}/{peliculaId}")
    public ResponseEntity<?> salir(@PathVariable Long usuarioId,
                                    @PathVariable Long peliculaId) {
        salaRepo.deleteByUsuarioIdAndPeliculaId(usuarioId, peliculaId);
        return ResponseEntity.noContent().build();
    }

    // Ver quién está viendo una película
    @GetMapping("/pelicula/{peliculaId}")
    public List<UsuarioConectado> verSala(@PathVariable Long peliculaId) {
        return salaRepo.findByPeliculaId(peliculaId);
    }

    // Contar cuántos están viendo una película
    @GetMapping("/pelicula/{peliculaId}/count")
    public ResponseEntity<?> contarSala(@PathVariable Long peliculaId) {
        return ResponseEntity.ok(salaRepo.findByPeliculaId(peliculaId).size());
    }
}