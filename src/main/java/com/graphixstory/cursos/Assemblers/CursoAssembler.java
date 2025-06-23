package com.graphixstory.cursos.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.graphixstory.cursos.Controlador.ControladorCursosV2;
import com.graphixstory.cursos.Modelo.Curso;

@Component
public class CursoAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        Link selfLink = linkTo(methodOn(ControladorCursosV2.class).buscarPorId(Long.valueOf(curso.getIdcurso())))
                .withSelfRel();
        Link allCursosLink = linkTo(methodOn(ControladorCursosV2.class).listarCursos()).withRel("cursos");

        return EntityModel.of(curso, selfLink, allCursosLink);
    }
}