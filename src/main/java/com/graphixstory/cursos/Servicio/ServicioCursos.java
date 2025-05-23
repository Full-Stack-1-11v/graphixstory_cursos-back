package com.graphixstory.cursos.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphixstory.cursos.Repositorio.RepositorioCursos;
import com.graphixstory.cursos.Modelo.Curso;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioCursos {
    
    @Autowired
    private RepositorioCursos repositorio;

    public List<Curso> verCurso() {
        return repositorio.findAll();
    }

    public Curso buscarPorId(long id) {
        return repositorio.findById(id).get();
    }

    public Curso guardarCurso(Curso curso) {
        return repositorio.save(curso);
    }

    public void borrarCurso(Long id) {
        repositorio.deleteById(id);
    }
}
