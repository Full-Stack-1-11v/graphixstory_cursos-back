package com.graphixstory.cursos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.graphixstory.cursos.ComunicacionAPI.ModeloRequest;
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
    public void testVerCurso() {
        when(repositorioCursos.findAll()).thenReturn(List.of(new Curso(1, "Ingeniería en funas", "CTM-069", 25, "11:00-13:00", 1, "Pedro", "Manfinfla", "picosricos@duocuc.cl")));

        // Llama al método findAll() del servicio.
        List<Curso> curso = serviciocurso.verCurso();

        // Verifica que la lista devuelta no sea nula y contenga exactamente una Carrera.
        assertNotNull(curso);
        assertEquals(1, curso.size());
    }

    @Test
    public void testBuscarPorId() {
        String identificador = "1";
        Curso curso = new Curso(Integer.parseInt(identificador), "Ingeniería en funas", "CTM-069", 25, "11:00-13:00", 1, "Pedro", "Manfinfla", "picosricos@duocuc.cl");

        when(repositorioCursos.findById(Long.parseLong(identificador))).thenReturn(Optional.of(curso));

        Curso found = serviciocurso.buscarPorId(Integer.parseInt(identificador));

        assertNotNull(found);
        assertEquals(identificador, String.valueOf(1L));
    }

    @Test
    public void testGuardarCurso() {
        Curso curso = new Curso(1, "Ingeniería en funas", "CTM-069", 25, "11:00-13:00", 1, "Pedro", "Manfinfla", "picosricos@duocuc.cl");
        when(repositorioCursos.save(any(Curso.class))).thenReturn(curso);

        Curso saved = serviciocurso.guardarCurso(curso);
        assertNotNull(saved);
        assertEquals("Ingeniería en funas", saved.getNombre());
    }

    @Test
    public void testDeleteByCodigo() {

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(repositorioCursos).deleteById(1L);

        // Llama al método deleteByCodigo() del servicio.
        serviciocurso.borrarCurso(1L);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(repositorioCursos, times(1)).deleteById(1L);
    }
}
