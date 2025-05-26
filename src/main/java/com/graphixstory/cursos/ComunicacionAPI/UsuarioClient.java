package com.graphixstory.cursos.ComunicacionAPI;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import java.util.Optional;

@Component
public class UsuarioClient {
    private final RestTemplate restTemplate;

    @Value("${usuarios.api.url}")
    private String baseUrl;

    public UsuarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ModeloAPI> getUsuarioById(Integer id) {
    try {
        ModeloAPI usuario = restTemplate.getForObject(baseUrl + "/usuarios/" + id, ModeloAPI.class);
        return Optional.ofNullable(usuario);
    } catch (Exception e) {
        return Optional.empty();
        }
    }
}