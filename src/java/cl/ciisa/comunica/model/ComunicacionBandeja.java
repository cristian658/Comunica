package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import cl.ciisa.comunica.util.SendEmail;
import static java.lang.Boolean.*;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author cristian
 */
public class ComunicacionBandeja {

    public List<Alumno> alumnos;
    public Profesor profesor;
    public Apoderado apoderado;

    public Apoderado getApoderado(Integer idApoderado) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select ap from Apoderado ap where ap.idApoderado = :idApoderado");
        q.setParameter("idApoderado", idApoderado);
        this.apoderado = (Apoderado) q.uniqueResult();
        s.close();
        return this.apoderado;
    }

    public Profesor getProfesorByCurso(Integer idCurso) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select p from Profesor p where p.curso.idCurso = :idCurso");
        q.setParameter("idCurso", idCurso);
        this.profesor = (Profesor) q.uniqueResult();
        s.close();
        return this.profesor;

    }

    public void addDetalleComunicacionDeApoderadoAProfesor(String asunto, String mensaje,
            Profesor profesor, Apoderado apoderado, String correoEmisor) {

        Comunicacion c = null;
        c = this.addComunicacionVinculo(apoderado, profesor);

        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        if (c != null) {
            try {
                Detallecomunicacion dc = null;
                dc = new Detallecomunicacion(c, asunto, mensaje,
                        new Date(), correoEmisor, profesor.getEmailProfesor(), FALSE);
                s.save(dc);
                t.commit();
            } catch (HibernateException ex) {
                t.rollback();
                ex.printStackTrace();
            } finally {
                s.close();
                SendEmail se = new SendEmail("Nueva Comunicacion", "", profesor.getEmailProfesor());
                se.setBody(se.getBodyNuevaComunicacion(asunto));
                se.send();
            }
        }

    }

    public Profesor getProfesor(Integer idProfesor) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select p from Profesor p where p.idProfesor = :idProfesor");
        q.setParameter("idProfesor", idProfesor);
        this.profesor = (Profesor) q.uniqueResult();
        s.close();
        return this.profesor;
    }

    public List<Alumno> getAlumnos(Curso curso) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select a from Alumno a where a.curso.idCurso = :idCurso");
        q.setParameter("idCurso", curso.getIdCurso());
        alumnos = (List<Alumno>) q.list();
        s.close();
        return alumnos;
    }

    public Apoderado getApoderadoByAlumno(Integer idAlumno) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select a from Apoderado a where a.alumno.idAlumno = :id");
        q.setParameter("id", idAlumno);
        this.apoderado = (Apoderado) q.uniqueResult();
        return this.apoderado;
    }

    public void addDetalleComunicacionDeProfesorAApoderado(String asunto, String mensaje,
            Integer idAlumno, Profesor profesor, String emailEmisor) {
        Comunicacion c = null;
        if (idAlumno != null) {
            getApoderadoByAlumno(idAlumno);
            c = this.addComunicacionVinculo(this.apoderado, profesor);
        } else {
            c = this.addComunicacionVinculo(null, profesor);
        }
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        if (c != null) {
            try {
                Detallecomunicacion dc = null;
                if (idAlumno != null) {
                    dc = new Detallecomunicacion(c, asunto, mensaje,
                            new Date(), emailEmisor, this.apoderado.getEmailApoderado(), FALSE);
                } else {
                    dc = new Detallecomunicacion(c, asunto, mensaje,
                            new Date(), emailEmisor, null, FALSE);
                }
                s.save(dc);
                t.commit();
            } catch (HibernateException ex) {
                t.rollback();
                ex.printStackTrace();
            } finally {
                s.close();
                if (idAlumno != null) {
                    SendEmail se = new SendEmail("Nueva Comunicacion", "", this.apoderado.getEmailApoderado());
                    se.setBody(se.getBodyNuevaComunicacion(asunto));
                    se.send();
                }
            }
        }

    }

    public Comunicacion addComunicacionVinculo(Apoderado apoderado, Profesor profesor) {
        Comunicacion c = new Comunicacion();
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            c.setApoderado(apoderado);
            c.setProfesor(profesor);
            s.save(c);
            t.commit();
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
            c = null;
        } finally {
            s.close();
        }

        return c;
    }

}
