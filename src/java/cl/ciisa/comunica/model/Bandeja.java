package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cristian
 */
public class Bandeja {
    
    private List<Comunicacion> comunicaciones;
    private User user; 
    
    public Bandeja(User user){
        this.user = user;
    }
    public List<Comunicacion> getComunicaciones(){
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "";
        switch(user.getType()){
            case "Profesor":
                query = "Select c from Comunicacion c where c.profesor.idProfesor = :id";
                break;
            case "Apoderado":
                query = "Select c from Comunicacion c where c.apoderado.idApoderado = :id";
                break;
        }
        Query q = session.createQuery(query);
        q.setParameter("id", user.getId());
        comunicaciones = (List<Comunicacion>)q.list();
        return comunicaciones;
    }
    
    
}
