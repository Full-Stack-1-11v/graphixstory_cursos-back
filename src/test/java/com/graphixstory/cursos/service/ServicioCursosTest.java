package com.graphixstory.cursos.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.graphixstory.cursos.Modelo.Curso;
import com.graphixstory.cursos.Repositorio.RepositorioCursos;
import com.graphixstory.cursos.Servicio.ServicioCursos;

@SpringBootTest
@ActiveProfiles("test")
public class ServicioCursosTest {
    @Autowired
    private ServicioCursos serviciocurso;

    @MockBean
    private RepositorioCursos repositorioCursos;

    @Test
    public void testGetCursos() {
        List<Curso> cursos = new ArrayList<>();

        when(repositorioCursos.findAll()).thenReturn(cursos);

    }
}
