package com.graphixstory.cursos.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.graphixstory.cursos.Repositorio.RepositorioCursos;
import com.graphixstory.cursos.Modelo.Curso;
import com.graphixstory.cursos.ComunicacionAPI.ModeloAPI;
import com.graphixstory.cursos.ComunicacionAPI.ModeloRequest;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCursos {

    @Autowired
    private RepositorioCursos repositorio;

    @Autowired
    private RestTemplate restTemplate;

    public List<Curso> verCurso() {
        return repositorio.findAll();
    }

    public Curso buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    public Curso guardarCurso(Curso modeloapi) {
        // Llamada a la API externa para obtener los datos del profesor
        String url = "https://graphixstory-usuario-back.onrender.com/api/usuarios/" + modeloapi.getProfe_id();
        ModeloAPI profesor = restTemplate.getForObject(url, ModeloAPI.class);

        if (profesor == null) {
            throw new RuntimeException("No se encontró el profesor con ID: " + modeloapi.getProfe_id());
        }

        // Crear y guardar el curso
        Curso cursoguardado = new Curso();
        cursoguardado.setNombre(modeloapi.getNombre());
        cursoguardado.setSigla(modeloapi.getSigla());
        cursoguardado.setCantidadAlumnos(modeloapi.getCantidadAlumnos());
        cursoguardado.setHorario(modeloapi.getHorario());

        // Datos del profesor traídos desde la API
        cursoguardado.setProfe_id(profesor.getId());
        cursoguardado.setProfe_nombre(profesor.getNombre());
        cursoguardado.setProfe_apellido(profesor.getApellido());
        cursoguardado.setProfe_correo(profesor.getCorreo());

        Curso test = repositorio.save(cursoguardado);

        return test;
    }

    public void borrarCurso(Long id) {
        repositorio.deleteById(id);
    }
}

