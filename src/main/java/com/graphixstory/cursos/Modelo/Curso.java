package com.graphixstory.cursos.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad que representa a un curso en el sistema.
 * Contiene información como el nombre del curso, su sigla, la
 * cantidad de alumnos, el horario, la ID única del profesor, 
 * su nombre, apellido, y correo electrónico.
 */
@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    /**
     * Identificador único del curso.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    /**
     * Nombre del curso.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String nombre;
    
    /**
     * Sigla del curso.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String sigla;
    
    /**
     * Cantidad de alumnos inscritos al curso.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private int cantidadAlumnos;
    
    /**
     * Horario del curso.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String horario;

    /**
     * ID única del profesor.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private Integer profe_id;

    /**
     * Nombre del profesor.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String profe_nombre;

    /**
     * Apellido del profesor.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String profe_apellido;

    /**
     * Correo electrónico del profesor.
     * Este campo es obligatorio.
     */
    @Column (nullable = false)
    private String profe_correo;
}
