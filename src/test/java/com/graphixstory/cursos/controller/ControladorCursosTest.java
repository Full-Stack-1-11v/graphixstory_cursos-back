package com.graphixstory.cursos.controller;

import com.graphixstory.cursos.Controlador.ControladorCursos;
import com.graphixstory.cursos.Modelo.Curso;
import com.graphixstory.cursos.Servicio.ServicioCursos;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

        mockMvc.perform(get("/api/v1/cursos"))
                .andExpect(status().is(204));
    }

    @Test
    void PostCurso_Creado() throws Exception {
        Curso curso = new Curso();
        curso.setIdcurso(1);
        curso.setNombre("TestName");
        curso.setSigla("TST-123");
        curso.setCantidadAlumnos(10);
        curso.setHorario("1-2");
        curso.setProfe_id(1);
        curso.setProfe_nombre("Armando");
        curso.setProfe_apellido("Casas");
        curso.setProfe_correo("pajabrava@tulon.cl");

        Mockito.when(servicioCursos.guardarCurso(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/v1/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"TestName\",\"sigla\":\"TST-123\",\"cantidadAlumnos\":10,\"horario\":\"1-2\",\"profe_id\":1,\"profe_nombre\":\"Armando\",\"profe_apellido\":\"Casas\",\"profe_correo\":\"pajabrava@tulon.cl\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void GetCursoPorID() throws Exception {

        Mockito.when(servicioCursos.buscarPorId(1L)).thenReturn(new Curso());

        mockMvc.perform(get("/api/v1/cursos/1") // Ejecuta la prueba
                        .contentType(MediaType.APPLICATION_JSON)) // El tipo de contenido que retorna
                .andExpect(status().isOk()); // Si retorna un 200
    }

    @Test
    void DeleteCurso() throws Exception {

        Curso curso = new Curso();
        curso.setIdcurso(1);
        curso.setNombre("TestName");
        curso.setSigla("TST-123");
        curso.setCantidadAlumnos(10);
        curso.setHorario("1-2");



        doNothing().when(servicioCursos).borrarCurso(1L);

        mockMvc.perform(delete("/api/v1/cursos/1") // Ejecuta la prueba
                        .contentType(MediaType.APPLICATION_JSON)) // El tipo de contenido que retorna
                .andExpect(status().is(204)); // Si retorna un 204
    }
}