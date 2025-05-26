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

@RestController
@RequestMapping("api/v1/cursos")
public class ControladorCursos {
    @Autowired
    private ServicioCursos servicio;

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = servicio.verCurso();
        if (cursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cursos);
    }
    
    @PostMapping
    public ResponseEntity<Curso> guardar(@RequestBody ModeloRequest curso) {
        Curso cursonuevo = servicio.guardarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursonuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        try {
            Curso curso = servicio.buscarPorId(id);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

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
