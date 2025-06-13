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
import com.graphixstory.cursos.ComunicacionAPI.ModeloRequest;
import com.graphixstory.cursos.Modelo.Curso;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas a la gestión de cursos")
public class ControladorCursos {
    @Autowired
    private ServicioCursos servicio;

    @Operation(summary = "Mostrar cursos", description = "Muestra todos los cursos registrados")
    @GetMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Los cursos se mostraron de manera correcta.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "404", description = "No hay cursos para mostrar."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = servicio.verCurso();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }
    
    @Operation(summary = "Subir curso", description = "Registra un curso a la base de datos")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "El curso se subió correctamente.",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Curso.class))),
        @ApiResponse(responseCode = "403", description = "No se tienen los permisos para subir el curso."),
        @ApiResponse(responseCode = "500", description = "El servicio no está disponible.")
    })
    public ResponseEntity<Curso> guardar(@RequestBody ModeloRequest curso) {
        Curso cursonuevo = servicio.guardarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursonuevo);
    }

    @Operation(summary = "Mostrar curso por ID", description = "Muestra un curso según la ID especificada en el link")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        try {
            Curso curso = servicio.buscarPorId(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Borrar curso", description = "Borra el curso indicado por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            servicio.borrarCurso(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
