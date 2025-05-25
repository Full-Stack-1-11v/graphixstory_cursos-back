package com.graphixstory.cursos.ComunicacionAPI;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ControladorAPI {
    private RestTemplate restTemplate;

        @Value("${url.usuarios}")
        private String url;

        public ControladorAPI(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public ModeloAPI getIdProfe(Long id) {
            return restTemplate.getForObject(url + "/usuarios/" + id, ModeloAPI.class);
        }
}