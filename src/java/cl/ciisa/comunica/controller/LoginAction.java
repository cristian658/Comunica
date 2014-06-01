package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.model.User;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author cristian
 */
public class LoginAction extends ActionSupport {
    
    
    private User user;
    public String typeUser;
    
    @Override
    public String execute() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
                                  .get(ServletActionContext.HTTP_REQUEST);
        typeUser = request.getParameter("typeUser");
        if(user != null)
            System.out.println("Email:"+this.getUser().getEmail());
            
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
