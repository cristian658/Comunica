package cl.ciisa.comunica.entity;
// Generated 02-jun-2014 22:16:48 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Curso generated by hbm2java
 */
public class Curso  implements java.io.Serializable {


     private Integer idCurso;
     private String nombreCurso;
     private Date fechaRegistroCurso;
     private Set alumnos = new HashSet(0);
     private Set profesors = new HashSet(0);

    public Curso() {
    }

    public Curso(String nombreCurso, Date fechaRegistroCurso, Set alumnos, Set profesors) {
       this.nombreCurso = nombreCurso;
       this.fechaRegistroCurso = fechaRegistroCurso;
       this.alumnos = alumnos;
       this.profesors = profesors;
    }
   
    public Integer getIdCurso() {
        return this.idCurso;
    }
    
    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }
    public String getNombreCurso() {
        return this.nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    public Date getFechaRegistroCurso() {
        return this.fechaRegistroCurso;
    }
    
    public void setFechaRegistroCurso(Date fechaRegistroCurso) {
        this.fechaRegistroCurso = fechaRegistroCurso;
    }
    public Set getAlumnos() {
        return this.alumnos;
    }
    
    public void setAlumnos(Set alumnos) {
        this.alumnos = alumnos;
    }
    public Set getProfesors() {
        return this.profesors;
    }
    
    public void setProfesors(Set profesors) {
        this.profesors = profesors;
    }




}


