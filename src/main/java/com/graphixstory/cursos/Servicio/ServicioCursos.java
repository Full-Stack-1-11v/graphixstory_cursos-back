package com.graphixstory.cursos.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphixstory.cursos.Repositorio.RepositorioCursos;
import com.graphixstory.cursos.Modelo.Curso;
import com.graphixstory.cursos.ComunicacionAPI.ModeloRequest;

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

    public Curso guardarCurso(ModeloRequest modeloapi) {
        Curso cursoguardado = new Curso();
        cursoguardado.setNombre(modeloapi.getNombre());
        cursoguardado.setSigla(modeloapi.getSigla());
        cursoguardado.setCantidadAlumnos(modeloapi.getCantidadAlumnos());
        cursoguardado.setHorario(modeloapi.getHorario());
        cursoguardado.setProfe_id(modeloapi.getProfe_id());
        cursoguardado.setProfe_nombre(modeloapi.getProfe_nombre());
        cursoguardado.setProfe_apellido(modeloapi.getProfe_apellido());
        cursoguardado.setProfe_correo(modeloapi.getProfe_correo());
        return repositorio.save(cursoguardado);
    }

    public void borrarCurso(Long id) {
        repositorio.deleteById(id);
    }
}
