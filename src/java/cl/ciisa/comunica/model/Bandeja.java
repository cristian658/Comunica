package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.entity.Detallecomunicacion;
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
    public List<Comunicacion> getComunicacionesPrincipales(){
        String query = "";
        Apoderado a = null;
        Alumno al = null;
        switch(user.getType()){
            case "Profesor":
                query = "Select c from Comunicacion c where c.profesor.idProfesor = :id";
                        //+ " group by dc.asunto"
                        //+ " order by c.detallecomunicacions asc";
                break;
            case "Apoderado":
                Matriculas m = new Matriculas();
                a = m.getApoderadoById(user.getId());
                query = "Select dc from Detallecomunicacion dc where"
                        + " (dc.comunicacion.apoderado.idApoderado= :id or dc.comunicacion.apoderado.idApoderado = null)"
                        + " and dc.comunicacion.profesor.curso.idCurso = :idCurso"
                        + " group by dc.asunto"
                        + " order by dc.fechaRegistroComunicacion asc";
                
                query = "Select c from Comunicacion c where"
                        + " (c.apoderado.idApoderado= :id or c.apoderado.idApoderado = null)"
                        + " and c.profesor.curso.idCurso = :idCurso";
                        //+ " group by dc.asunto"
                        //+ " order by dc.fechaRegistroComunicacion asc";
                break;
        }
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery(query);
        q.setParameter("id", user.getId());
        if(user.getType().equals("Apoderado"))
            q.setParameter("idCurso", a.getAlumno().getCurso().getIdCurso());
        comunicaciones = (List<Comunicacion>)q.list();
        return comunicaciones;
    }
    
    
}
