package com.graphixstory.cursos.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    @Column
    private String nombre;
    
    @Column
    private String sigla;
    
    @Column
    private int cantidadAlumnos;
    
    @Column
    private String horario;

}
