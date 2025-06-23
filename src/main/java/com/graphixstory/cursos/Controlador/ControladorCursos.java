package com.graphixstory.cursos.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los
 * cursos.
 * Proporciona endpoints para listar, obtener, crear y eliminar
 * cursos.
 * 
 * <p>Este controlador utiliza el servicio {@link ServicioCursos} para realizar
 * las operaciones CRUD sobre los cursos.</p>
 */
@RestController
@RequestMapping("api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas a la gestión de cursos")
public class ControladorCursos {
    @Autowired
    private ServicioCursos servicio;

    /**
     * Obtiene una lista de todos los cursos.
     * @return Lista de objetos {@link Curso}.
     */
    @Operation(summary = "Mostrar cursos", description = "Muestra todos los cursos registrados") // Nombre y descripción en Swagger
    @GetMapping
    @ApiResponses(value = { // Respuestas listadas en Swagger
        @ApiResponse(responseCode = "200", description = "Los cursos se mostraron de manera correcta.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "No hay cursos para mostrar."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<List<Curso>> listar() { // Método para listar todos los cursos
        List<Curso> cursos = servicio.verCurso();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }
    
    /**
     * Guarda un nuevo curso en el sistema.
     * 
     * @param curso Objeto {@link Curso} con la información del paciente a
     *                 guardar.
     * @return Objeto {@link Curso} guardado.
     */
    @Operation(summary = "Subir curso", description = "Registra un curso a la base de datos") // Nombre y descripción en Swagger
    @PostMapping
    @ApiResponses(value = { // Respuestas listadas en Swagger
        @ApiResponse(responseCode = "200", description = "El curso se subió correctamente.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "403", description = "No se tienen los permisos para subir el curso."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso) { // Método para guardar un curso
        Curso cursonuevo = servicio.guardarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursonuevo);
    }

    /**
     * Obtiene un curso por su ID.
     * 
     * @param id ID del curso a buscar.
     * @return Objeto {@link Curso} correspondiente al ID proporcionado.
     */
    @Operation(summary = "Mostrar curso por ID", description = "Muestra un curso según la ID especificada en el link") // Nombre y descripción en Swagger
    @GetMapping("/{id}")
    @ApiResponses(value = { // Respuestas listadas en Swagger
        @ApiResponse(responseCode = "200", description = "El curso se mostró de manera correcta.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "No hay un curso correspondiente con la ID."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<Curso> buscar(@PathVariable Long id) { // Método para buscar un curso por su ID
        try {
            Curso curso = servicio.buscarPorId(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un curso del sistema por su ID.
     * 
     * @param id ID del curso a eliminar.
     * @return Respuesta con código de estado HTTP.
     */
    @Operation(summary = "Borrar curso", description = "Borra un curso según la ID especificada en el link") // Nombre y descripción en Swagger
    @DeleteMapping("/{id}")
    @ApiResponses(value = { // Respuestas listadas en Swagger
        @ApiResponse(responseCode = "204", description = "El curso se ha borrado de forma correcta"),
        @ApiResponse(responseCode = "403", description = "No se tienen los permisos para borrar el curso."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id) { // Método para borrar un curso por su ID
        try {
            servicio.borrarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
