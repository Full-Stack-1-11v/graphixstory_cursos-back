package com.graphixstory.cursos.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.graphixstory.cursos.Modelo.Curso;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la entidad
 * {@link Curso}.
 * Proporciona métodos para realizar consultas personalizadas además de las
 * operaciones CRUD básicas.
 */
@Repository
public interface RepositorioCursos extends JpaRepository<Curso, Long>{
    
}