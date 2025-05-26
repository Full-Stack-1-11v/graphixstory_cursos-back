package com.graphixstory.cursos.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    @Column (nullable = false)
    private String nombre;
    
    @Column (nullable = false)
    private String sigla;
    
    @Column (nullable = false)
    private int cantidadAlumnos;
    
    @Column (nullable = false)
    private String horario;

    @Column (nullable = false)
    private Integer profe_id;

    @Column (nullable = false)
    private String profe_nombre;

    @Column (nullable = false)
    private String profe_apellido;

    @Column (nullable = false)
    private String profe_correo;
}
