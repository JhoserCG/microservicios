package com.peliculas.peliculas_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String obtenerUsuario(Long id) {
        String url = "http://localhost:8081/api/auth/" + id;
        return restTemplate.getForObject(url, String.class);
    }
}