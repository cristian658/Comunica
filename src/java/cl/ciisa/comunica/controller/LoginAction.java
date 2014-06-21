package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.model.User;
import static com.opensymphony.xwork2.Action.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author cristian
 */
public class LoginAction extends ActionSupport  {

    public User user;
    public static String typeUser;
    public Map session;
    /**
     * Método que se ejecuta cuando se inicia el controlador, en ella se captura
     * el valor de "typeUser" obtenido desde la url, asi como tambien obtenemos
     * los valores de la clase User(email,clave,type{profesor,apoderadoy administrador})
     * en ella se deja en session los valores correo,type{profesor,apoderadoy administrador}
     * y context(fecha en la que se conecto)
     * @return String
     */
    public String execute() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                .get(ServletActionContext.HTTP_REQUEST);
        typeUser = request.getParameter("typeUser");
        
        
        if (user != null) {
            typeUser = this.getUser().getType();
            session = ActionContext.getContext().getSession();
            session.put("id", this.getUser().getId());
            session.put("correo", this.getUser().getEmail());
            session.put("typeUser", this.getUser().getType());
            session.put("context", new Date());
            return SUCCESS;
        }
        return ERROR;
    }
    /**
     * Acá se hace validacion por parte del servidor los campos correo y el tipo
     * de usuario que esta ingresando {profesor,apoderado y administrador}, en ella
     * validamos si el usuario existe en la base de datos, asi como tambien validamos
     * si la contraseña fue ingresada correctamente
     */
    public void validate() {
        if (user != null) {
            if (user.getEmail().equals("")) {
                addFieldError("user.email", "El campo E-mail esta vacio");
            }
            if(!user.validUser()){
                addActionError("Usuario o clave mal ingresada");
            }
            if(user.getType().equals("")){
                addActionError("Falta Tipo de usuario");
            }
        }
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
    /**
     * Se elimina los valores de la session{correo,typeUser,context}
     * @return 
     */
    public String logout(){
        session = ActionContext.getContext().getSession();
        session.remove("correo");
        session.remove("typeUser");
        session.remove("context");
        return SUCCESS;
    }

}
