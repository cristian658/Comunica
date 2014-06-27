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
            System.out.println("aka estoy "+session.get("correo"));
            this.user = new User((Integer)session.get("id"), (String)session.get("correo"), (String)session.get("typeUser"));
            Bandeja b = new Bandeja(this.user);
            comunicaciones = b.getComunicaciones();
            /*for(Comunicacion comunicacion: comunicaciones ){
                System.out.println("Nombre Apoderado "+comunicacion.getApoderado().getNombreApoderado());
                System.out.println("Nombre Profesor "+comunicacion.getProfesor().getNombreProfesor());
                 Iterator <Detallecomunicacion> iterador = comunicacion.getDetallecomunicacions().iterator();
                 while (iterador.hasNext()) {  
                     Detallecomunicacion detallecomunicacion = iterador.next();
                     System.out.println("Asunto: "+detallecomunicacion.getAsunto());
                     System.out.println("Fecha: "+detallecomunicacion.getFechaRegistroComunicacion());
                 }
            }*/
            return SUCCESS;  
        }  
        return ERROR;
    }
    
}
