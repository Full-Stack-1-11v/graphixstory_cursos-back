package com.graphixstory.cursos.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphixstory.cursos.Servicio.ServicioCursos;
import com.graphixstory.cursos.Modelo.Curso;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.graphixstory.cursos.Assemblers.CursoAssembler;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * cursos.
 * Proporciona endpoints para listar, obtener, crear y eliminar
 * cursos.
 * 
 * Esta es una copia del controlador principal modificada para implementar
 * HATEOAS, utilizando un link distinto al del controlador principal
 * (/v2/ en lugar de /v1/), además de no tener @Operation ni @ApiResponses,
 * ya que no son necesarios.
 * 
 * <p>Este controlador utiliza el servicio {@link ServicioCursos} para realizar
 * las operaciones CRUD sobre los cursos.</p>
 */
@RestController
@RequestMapping("api/v2/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas a la gestión de cursos")
public class ControladorCursosV2 {
    @Autowired
    private ServicioCursos servicio;

    @Autowired
    private CursoAssembler assembler;

    /**
     * Obtiene una lista de todos los cursos en el sistema.
     * @return Lista de objetos {@link Curso}.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<Curso>>> listarCursos() {
        List<EntityModel<Curso>> pacientes = servicio.verCurso().stream().map(assembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientes);
    }
    
    /**
     * Guarda un curso nuevo en el sistema.
     * 
     * @param paciente Objeto {@link Curso} con la información del curso a
     *                 guardar.
     * @return Objeto {@link Curso} guardado.
     */
    @PostMapping
    public ResponseEntity<EntityModel<Curso>> guardarCursos(@RequestBody Curso curso) {
        Curso newCurso = servicio.guardarCurso(curso);

        EntityModel<Curso> cursoModel = assembler.toModel(newCurso);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoModel);
    }

    /**
     * Obtiene un curso por su ID.
     * 
     * @param id ID del curso a buscar.
     * @return Objeto {@link Curso} correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Curso>> buscarPorId(@PathVariable Long id) {
        Curso curso = servicio.buscarPorId(id);
        if (curso == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        EntityModel<Curso> pacienteModel = assembler.toModel(curso);

        return ResponseEntity.ok(pacienteModel);
    }

    /**
     * Elimina un curso del sistema por su ID.
     * 
     * @param id ID del curso a eliminar.
     * @return Respuesta con código de estado HTTP.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        servicio.borrarCurso(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
