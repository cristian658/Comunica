package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Administrador;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cristian
 */
public class User {
    
    public Integer id;
    public String email;
    public String clave;
    public String type;
    
    public User(){}
    
    public User(Integer id,String email,String type){
        this.id = id;
        this.email = email;
        this.type = type;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * se checkea el usuario de esta instancia en la base de datos pasando como
     * parametro el correo electronico (email) que ya viene seteado en el objeto
     * @return List<Object>
     */
    public List<Object> checkUser(){
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "";
        switch(getType()){
            case "Profesor":
                query = "select p from Profesor p where p.emailProfesor = :mail";
                break;
            case "Apoderado":
                query = "select a from Apoderado a where a.emailApoderado = :mail";
                break;
            case "Administrador":
                query = "select a from Administrador a where a.emailAdministrador = :mail";
                break;
        }
        Query q = session.createQuery(query);
        q.setParameter("mail", this.getEmail());
        List<Object> objects = (List<Object>)q.list();
        session.close();
        return objects;
    }
    /**
     * se valida que el usuario este en la base de datos con el metodo checkUser(),
     * y con ello validamos la contraseña.
     * @return Boolean
     */
    public Boolean validUser(){
        List<Object> objects = this.checkUser();
            if (objects != null && objects.size()>0) {
                if (objects.get(0) instanceof Profesor) {
                    Profesor p = (Profesor) objects.get(0);
                    id = p.getIdProfesor();
                    if (!p.getPasswordProfesor().equals(this.getClave())) {
                        return false;
                    }
                }
                if (objects.get(0) instanceof Apoderado) {
                    Apoderado a = (Apoderado) objects.get(0);
                    id = a.getIdApoderado();
                    if (!a.getPasswordApoderado().equals(this.getClave())) {
                        return false;
                    }
                }
                if (objects.get(0) instanceof Administrador) {
                    Administrador a = (Administrador) objects.get(0);
                    id = a.getIdAdministrador();
                    if (!a.getPasswordAdministrador().equals(this.getClave())) {
                        return false;
                    }
                }
                return true;
            }
        return false;
    }

    
}
