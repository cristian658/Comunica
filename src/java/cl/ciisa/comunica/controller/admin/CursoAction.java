package cl.ciisa.comunica.controller.admin;

import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.model.Cursos;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cristian
 */
public class CursoAction extends ActionSupport {

    public Cursos cursos = new Cursos();;
    public List<Curso> cursosList;
    public String nombre;

    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session != null && !session.isEmpty() && session.get("typeUser").equals("Administrador")){
            if (nombre != null) {
                System.out.println(nombre);
                cursos.addCurso(nombre);
                addActionMessage("Curso Registrado Exitosamente!");
            }
            cursosList = cursos.getCursos();
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

    public void validate() {
        cursosList = cursos.getCursos();
        if (nombre != null) {
            if (cursos.validCurso(nombre)) {
                addActionError("Ya existe el curso '"+nombre+"'");
            }
        }

    }

}
