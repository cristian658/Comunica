package cl.ciisa.comunica.controller.admin;

import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.model.Cursos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author cristian
 */
public class CursoAction extends ActionSupport {

    public Cursos cursos = new Cursos();;
    public List<Curso> cursosList;
    public String nombre;

    public String execute() {
        if (nombre != null) {
            System.out.println(nombre);
            cursos.addCurso(nombre);
        }
        cursosList = cursos.getCursos();
        return SUCCESS;
    }

    public void validate() {
        cursosList = cursos.getCursos();
        if (nombre != null) {
            if (cursos.validCurso(nombre)) {
                addActionError("Ya existe");
            }
        }

    }

}
