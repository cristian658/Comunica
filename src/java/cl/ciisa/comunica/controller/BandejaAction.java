package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.model.Bandeja;
import cl.ciisa.comunica.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO(Por hacer)
 *
 * @author cristian
 */
public class BandejaAction extends ActionSupport {

    private User user;
    public List<Comunicacion> comunicaciones;

    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session != null && !session.isEmpty()
                && (session.get("typeUser").equals("Profesor") || session.get("typeUser").equals("Apoderado"))) {
            this.user = new User((Integer) session.get("id"), (String) session.get("correo"), (String) session.get("typeUser"));
            Bandeja b = new Bandeja(this.user);
            comunicaciones = b.getComunicacionesPrincipales();
            return SUCCESS;
        }
        return ERROR;
    }

    public <T> List<T> fromSetToList(Set<T> set) {
        List<T> list = new ArrayList<T>();
        for (T o : set) {
            list.add(o);
        }
        return list;
    }

}
