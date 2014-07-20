package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import cl.ciisa.comunica.util.SendEmail;
import static java.lang.Boolean.FALSE;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Esteban
 */
public class DetalleComunicaciones {

    /**
     * Trae todas las respuesta de la comunicacion principal
     * @param idComunicacion
     * @param correoConectado
     * @return 
     */
    public List<Detallecomunicacion> getDetalleComunicacionByIdComunicacionPrincipal(Integer idComunicacion, String correoConectado) {
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("Select dc from Detallecomunicacion dc where dc.comunicacion.idComunicacion = :id"
                + " order by dc.fechaRegistroComunicacion asc");
        q.setParameter("id", idComunicacion);
        List<Detallecomunicacion> detalleComunicaciones = (List<Detallecomunicacion>) q.list();
        session.close();
        changeStatus(detalleComunicaciones, correoConectado);
        return detalleComunicaciones;
    }

    /**
     * Este metodo se encarga de agregar una respuesta a la comunicacion principal
     * @param mensaje
     * @param destinatario
     * @param correoSession
     * @param idComunicacion
     * @param dcPrimerRegistroDeLaLista 
     */
    public void addRespuesta(String mensaje, String destinatario, String correoSession, Integer idComunicacion,
            Detallecomunicacion dcPrimerRegistroDeLaLista) {
        
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            if (correoSession.equals(dcPrimerRegistroDeLaLista.getDestinatario())) {
                destinatario = dcPrimerRegistroDeLaLista.getEmisor();
            } else {
                destinatario = dcPrimerRegistroDeLaLista.getDestinatario();
            }
            Detallecomunicacion dc = new Detallecomunicacion(dcPrimerRegistroDeLaLista.getComunicacion(), dcPrimerRegistroDeLaLista.getAsunto(), mensaje,
                    new Date(), correoSession, destinatario, FALSE);
            s.save(dc);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
            if(!correoSession.equals(destinatario)){
                SendEmail se = new SendEmail("Se a respondido su comunicación", "", destinatario);
                se.setBody(se.getBodyRespuestaComunicacion(dcPrimerRegistroDeLaLista.getAsunto()));
                se.send();
            }
            
        }
    }
    
    /**
     * Cambia de estado las comunicaciones, esto con el objetivo de saber si leyó
     * la comunicacion
     * @param detallesComunicacion
     * @param correo 
     */
    public void changeStatus(List<Detallecomunicacion> detallesComunicacion, String correo){
        for(Detallecomunicacion dc : detallesComunicacion){
            if((dc.getEstado()!= null && dc.getDestinatario() != null) && dc.getEstado().equals(false) && dc.getDestinatario().equals(correo)){
                Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
                Transaction t = session.beginTransaction();
                try {
                    dc.setEstado(true);
                    session.merge(dc);
                }catch(HibernateException ex){
                    ex.printStackTrace();
                }finally{
                    t.commit();
                    session.close();
                    
                }
                
            }
        }
        
    }

}
