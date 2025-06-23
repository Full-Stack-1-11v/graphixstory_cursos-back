package com.graphixstory.cursos.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphixstory.cursos.Repositorio.RepositorioCursos;
import com.graphixstory.cursos.Modelo.Curso;

import jakarta.transaction.Transactional;
import java.util.List;

/**
 * Servicio para gestionar las operaciones relacionadas con los cursos.
 * Proporciona métodos para listar, obtener, crear y eliminar cursos.
 */
@Service
@Transactional
public class ServicioCursos {

    @Autowired
    private RepositorioCursos repositorio;

    /**
     * Obtiene una lista de todos los cursos en la plataforma.
     * 
     * @return Lista de objetos {@link Curso}.
     */
    public List<Curso> verCurso() {
        return repositorio.findAll();
    }

    /**
     * Obtiene un curso por su ID.
     * 
     * @param id ID del curso a buscar.
     * @return Objeto {@link Curso} correspondiente al ID proporcionado o
     *         {@code null} si no se encuentra.
     */
    public Curso buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo curso en el sistema.
     * 
     * @param curso Objeto {@link Curso} con la información del curso a
     *                 guardar.
     * @return Objeto {@link Curso} guardado o existente.
     */
    public Curso guardarCurso(Curso modelo) {
        Curso cursoguardado = new Curso();
        cursoguardado.setNombre(modelo.getNombre());
        cursoguardado.setSigla(modelo.getSigla());
        cursoguardado.setCantidadAlumnos(modelo.getCantidadAlumnos());
        cursoguardado.setHorario(modelo.getHorario());
        cursoguardado.setProfe_id(modelo.getProfe_id());
        cursoguardado.setProfe_nombre(modelo.getProfe_nombre());
        cursoguardado.setProfe_apellido(modelo.getProfe_apellido());
        cursoguardado.setProfe_correo(modelo.getProfe_correo());

        Curso test = repositorio.save(cursoguardado);

        return test;
    }

    /**
     * Elimina un curso del sistema por su ID.
     * 
     * @param id ID del curso a eliminar.
     */
    public void borrarCurso(Long id) {
        repositorio.deleteById(id);
    }
}

