package cl.ciisa.comunica.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;


/**
 * TODO(Por hacer)
 * @author cristian
 */
public class BandejaAction extends ActionSupport {
    
    public String execute(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session != null && !session.isEmpty()){
            System.out.println("aka estoy "+session.get("correo"));
            return SUCCESS;  
        }  
        return ERROR;
    }
    
}
