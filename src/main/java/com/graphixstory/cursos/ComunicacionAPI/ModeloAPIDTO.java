package com.graphixstory.cursos.ComunicacionAPI;
import com.graphixstory.cursos.Modelo.Curso;


public class ModeloAPIDTO {

    private Integer idcurso;
    private String nombre;
    private String sigla;
    private int cantidadAlumnos;
    private String horario;

    private Integer profe_id;
    private String profe_nombre;
    private String profe_apellido;
    private String profe_correo;
    public ModeloAPIDTO() {
    }
    
    public ModeloAPIDTO(Curso modelo, ModeloAPI modeloapi) {
        this.idcurso = modelo.getIdcurso();
        this.nombre = modelo.getNombre();
        this.sigla = modelo.getSigla();
        this.cantidadAlumnos = modelo.getCantidadAlumnos();
        this.horario = modelo.getHorario();

        if (modeloapi != null) {
            this.profe_id = modeloapi.getId();
            this.profe_nombre = modeloapi.getNombre(); 
            this.profe_apellido = modeloapi.getApellido();
            this.profe_correo = modeloapi.getCorreo();
        }
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getProfe_id() {
        return profe_id;
    }

    public void setProfe_id(Integer profe_id) {
        this.profe_id = profe_id;
    }

    public String getProfe_nombre() {
        return profe_nombre;
    }

    public void setProfe_nombre(String profe_nombre) {
        this.profe_nombre = profe_nombre;
    }

    public String getProfe_apellido() {
        return profe_apellido;
    }

    public void setProfe_apellido(String profe_apellido) {
        this.profe_apellido = profe_apellido;
    }

    public String getProfe_correo() {
        return profe_correo;
    }

    public void setProfe_correo(String profe_correo) {
        this.profe_correo = profe_correo;
    }

    

    
}
