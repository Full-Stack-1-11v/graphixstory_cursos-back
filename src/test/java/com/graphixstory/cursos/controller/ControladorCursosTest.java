package com.graphixstory.cursos.controller;

import com.graphixstory.cursos.ComunicacionAPI.ModeloRequest;
import com.graphixstory.cursos.Controlador.ControladorCursos;
import com.graphixstory.cursos.Modelo.Curso;
import com.graphixstory.cursos.Servicio.ServicioCursos;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorCursos.class)
public class ControladorCursosTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServicioCursos servicioCursos;

    @Test
    void Cursos_empty() throws Exception {
        Mockito.when(servicioCursos.verCurso()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void PostCurso_Creado() throws Exception {
        Curso curso = new Curso();
        curso.setIdcurso(1);
        curso.setNombre("TestName");
        curso.setSigla("TST-123");
        curso.setCantidadAlumnos(10);
        curso.setHorario("1-2");

        Mockito.when(servicioCursos.guardarCurso(any(ModeloRequest.class))).thenReturn(curso);

        mockMvc.perform(post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"TestName\",\"sigla\":\"TST-123\",\"cantidadAlumnos\"10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
}