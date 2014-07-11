package cl.ciisa.comunica.controller.admin;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.model.Cursos;
import cl.ciisa.comunica.model.Matriculas;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Esteban
 */
public class MatriculaAction extends ActionSupport {

    public String Nombre_Apoderado;
    public String Ap_Paterno_Apoderado;
    public String Ap_Materno_Apoderado;
    public String Email_Apoderado;
    public String Nombre_Alumno;
    public String Ap_Paterno_Alumno;
    public String Ap_Materno_Alumno;
    public int Id_Curso;

    public Matriculas matriculas = new Matriculas();
    public Cursos cursos = new Cursos();

    public List<Curso> cursosList;
    public List<Alumno> alumnosList;
    public List<Apoderado> apoderadosList;

    @Override
    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session != null && !session.isEmpty() && session.get("typeUser").equals("Administrador")){
            if (this.Nombre_Apoderado != null) {
                String code = RandomStringUtils.randomAlphanumeric(6);
                this.matriculas.addAlumno(this.getNombre_Alumno(),
                        this.getAp_Paterno_Alumno(),
                        this.getAp_Materno_Alumno(),
                        this.getId_Curso());

                /*List<Alumno> a = this.matriculas.getAlumnoLastId();
                 for(Alumno al: a ){                        
                 Integer.parseInt(al.getIdAlumno().toString()));
                 }*/
                this.matriculas.addApoderado(this.getNombre_Apoderado(),
                        this.getAp_Paterno_Apoderado(),
                        this.getAp_Materno_Apoderado(),
                        this.getEmail_Apoderado(),
                        code.toLowerCase());

                this.setNombre_Alumno("");
                this.setAp_Paterno_Alumno("");
                this.setAp_Materno_Alumno("");
                this.setNombre_Apoderado("");
                this.setAp_Paterno_Apoderado("");
                this.setAp_Materno_Apoderado("");
                this.setEmail_Apoderado("");
                this.setId_Curso(0);
                addActionMessage("Matricula Registrada Exitosamente!");
            }
            this.cursosList = this.cursos.getCursos();
            this.alumnosList = this.matriculas.getAlumnos();
            this.apoderadosList = this.matriculas.getApoderados();

            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public void validate() {
        this.cursosList = this.cursos.getCursos();
        this.alumnosList = this.matriculas.getAlumnos();
        this.apoderadosList = this.matriculas.getApoderados();
        if (this.getEmail_Apoderado() != null) {
            if (this.matriculas.validEmailApoderado(this.getEmail_Apoderado()) == true) {
                addActionError("Ya existe el apoderado '" + this.getEmail_Apoderado() + "'");
            }
        }
        if (this.cursosList.size() <= 0) {
            addActionError("Se requiere agregar cursos antes de registrar matriculas");
        }

    }

    public String getNombre_Apoderado() {
        return Nombre_Apoderado;
    }

    public void setNombre_Apoderado(String Nombre_Apoderado) {
        this.Nombre_Apoderado = Nombre_Apoderado;
    }

    public String getAp_Paterno_Apoderado() {
        return Ap_Paterno_Apoderado;
    }

    public void setAp_Paterno_Apoderado(String Ap_Paterno_Apoderado) {
        this.Ap_Paterno_Apoderado = Ap_Paterno_Apoderado;
    }

    public String getAp_Materno_Apoderado() {
        return Ap_Materno_Apoderado;
    }

    public void setAp_Materno_Apoderado(String Ap_Materno_Apoderado) {
        this.Ap_Materno_Apoderado = Ap_Materno_Apoderado;
    }

    public String getEmail_Apoderado() {
        return Email_Apoderado;
    }

    public void setEmail_Apoderado(String Email_Apoderado) {
        this.Email_Apoderado = Email_Apoderado;
    }

    public String getNombre_Alumno() {
        return Nombre_Alumno;
    }

    public void setNombre_Alumno(String Nombre_Alumno) {
        this.Nombre_Alumno = Nombre_Alumno;
    }

    public String getAp_Paterno_Alumno() {
        return Ap_Paterno_Alumno;
    }

    public void setAp_Paterno_Alumno(String Ap_Paterno_Alumno) {
        this.Ap_Paterno_Alumno = Ap_Paterno_Alumno;
    }

    public String getAp_Materno_Alumno() {
        return Ap_Materno_Alumno;
    }

    public void setAp_Materno_Alumno(String Ap_Materno_Alumno) {
        this.Ap_Materno_Alumno = Ap_Materno_Alumno;
    }

    public int getId_Curso() {
        return Id_Curso;
    }

    public void setId_Curso(int Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

}
