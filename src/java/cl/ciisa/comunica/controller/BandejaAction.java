package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.model.Bandeja;
import cl.ciisa.comunica.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;


/**
 * TODO(Por hacer)
 * @author cristian
 */
public class BandejaAction extends ActionSupport {
    private User user;
    public List<Comunicacion> comunicaciones;
    public String execute(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session != null && !session.isEmpty()){
            this.user = new User((Integer)session.get("id"), (String)session.get("correo"), (String)session.get("typeUser"));
            Bandeja b = new Bandeja(this.user);
            comunicaciones = b.getComunicaciones();
            
            return SUCCESS;  
        }  
        return ERROR;
    }
    
}
