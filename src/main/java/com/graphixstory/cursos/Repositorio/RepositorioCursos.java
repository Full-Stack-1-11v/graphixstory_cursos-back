package com.graphixstory.cursos.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.graphixstory.cursos.Modelo.Curso;

@Repository
public interface RepositorioCursos extends JpaRepository<Curso, Long>{
    
}