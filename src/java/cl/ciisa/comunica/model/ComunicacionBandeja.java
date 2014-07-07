package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Comunicacion;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
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

    public Profesor getProfesor(Integer idProfesor) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select p from Profesor p where p.idProfesor = :idProfesor");
        q.setParameter("idProfesor", idProfesor);
        this.profesor = (Profesor) q.uniqueResult();
        s.close();
        return this.profesor;
    }

    public Curso getCursoByProfesor(Profesor profesor) {
        return profesor.getCurso();
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

    public void addComunicacionDetalle(String asunto, String mensaje,
            Integer idAlumno, Profesor profesor, Integer idEmisor) {
        Comunicacion c = null;
        if(idAlumno != null){
            getApoderadoByAlumno(idAlumno);
            c = this.addComunicacion(this.apoderado, profesor);
        }else{
            c = this.addComunicacion(null, profesor);
        }
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        if (c != null) {
            try {
                Detallecomunicacion dc = null;
                if(idAlumno != null){
                dc = new Detallecomunicacion(c, asunto, mensaje,
                        new Date(), idEmisor, this.apoderado.getIdApoderado(), FALSE);
                }else{
                    dc = new Detallecomunicacion(c, asunto, mensaje,
                        new Date(), idEmisor, null, FALSE);
                }
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

    public Comunicacion addComunicacion(Apoderado apoderado, Profesor profesor) {
        Comunicacion c = new Comunicacion();
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        try {
            c.setApoderado(this.apoderado);
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
