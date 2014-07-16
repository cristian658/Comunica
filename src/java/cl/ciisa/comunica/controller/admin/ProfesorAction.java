package cl.ciisa.comunica.controller.admin;

import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.model.Cursos;
import cl.ciisa.comunica.model.Profesores;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author eduardo
 */
public class ProfesorAction extends ActionSupport {

    public int id_Curso;
    public String nombre_profesor;
    public String apellido_pat_profesor;
    public String apellido_mat_profesor;
    public String email_profesor;
    public String password_profesor;
    
    public List<Curso> cursosList;
    public List<Profesor> profesoresList;
    
    Cursos cursos = new Cursos();
    Profesores p = new Profesores();
    public String execute() {
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session != null && !session.isEmpty() && session.get("typeUser").equals("Administrador")){
            if(this.nombre_profesor != null){
                String code = RandomStringUtils.randomAlphanumeric(6);     
                p.agregarProfesor(nombre_profesor, apellido_pat_profesor, apellido_mat_profesor, email_profesor,code.toLowerCase(), id_Curso);
                this.setNombre_profesor("");
                this.setApellido_pat_profesor("");
                this.setApellido_mat_profesor("");
                this.setEmail_profesor("");
                this.setId_Curso(0);
                addActionMessage("Profesor Registrado Exitosamente!");
            }
            this.cursosList = this.cursos.getCursos();
            this.profesoresList=this.p.getProfesores();
            return SUCCESS;
        }else{
            return ERROR;
        }
    }
    
    public void validate() {
        this.cursosList = this.cursos.getCursos();
        this.profesoresList=this.p.getProfesores();
        if (this.getEmail_profesor() != null) {
            if (this.p.validEmailProfesor(this.getEmail_profesor())==true) {
                addActionError("Ya existe el profesor '"+this.getEmail_profesor()+"'");
            }       
        }
        if(this.cursosList.size()<=0){
                addActionError("Se requiere agregar cursos antes de registrar un profesor");
            }       
        
    }


    public Integer getId_Curso() {
        return id_Curso;
    }

    public void setId_Curso(Integer id_Curso) {
        this.id_Curso = id_Curso;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_pat_profesor() {
        return apellido_pat_profesor;
    }

    public void setApellido_pat_profesor(String apellido_pat_profesor) {
        this.apellido_pat_profesor = apellido_pat_profesor;
    }

    public String getApellido_mat_profesor() {
        return apellido_mat_profesor;
    }

    public void setApellido_mat_profesor(String apellido_mat_profesor) {
        this.apellido_mat_profesor = apellido_mat_profesor;
    }

    public String getEmail_profesor() {
        return email_profesor;
    }

    public void setEmail_profesor(String email_profesor) {
        this.email_profesor = email_profesor;
    }

    public String getPassword_profesor() {
        return password_profesor;
    }

    public void setPassword_profesor(String password_profesor) {
        this.password_profesor = password_profesor;
    }


 
}
