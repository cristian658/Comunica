/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
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

    public List<Detallecomunicacion> getDetalleComunicacion(Integer idComunicacion) {
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("Select dc from Detallecomunicacion dc where dc.comunicacion.idComunicacion = :id"
                + " order by dc.fechaRegistroComunicacion asc");
        q.setParameter("id", idComunicacion);
        List<Detallecomunicacion> detalleComunicaciones = (List<Detallecomunicacion>) q.list();
        session.close();
        return detalleComunicaciones;
    }

    public void addRespuesta(String mensaje, String destino, String correoEmisor, Integer idComunicacion,
            Detallecomunicacion dcPrimerResgistro) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            if (correoEmisor.equals(dcPrimerResgistro.getDestinatario())) {
                destino = dcPrimerResgistro.getEmisor();
            } else {
                destino = dcPrimerResgistro.getDestinatario();
            }
            Detallecomunicacion dc = new Detallecomunicacion(dcPrimerResgistro.getComunicacion(), dcPrimerResgistro.getAsunto(), mensaje,
                    new Date(), correoEmisor, destino, FALSE);
            s.save(dc);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }

}
