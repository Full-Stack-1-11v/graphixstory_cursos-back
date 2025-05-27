package com.graphixstory.cursos.ComunicacionAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ModeloRequest {
    private String nombre;
    private String sigla;
    private int cantidadAlumnos;
    private String horario;
    
    private Integer profe_id;
}
