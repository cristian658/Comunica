package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Esteban
 */
public class Matriculas {

    public Alumno alumno;
    Cursos cursos = new Cursos();

    public void addAlumno(String nombre, String ap_pat, String ap_mat, int id_curso) {
        Curso c = cursos.getCursoById(id_curso);
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        alumno = new Alumno();
        try {
            alumno.setNombreAlumno(nombre);
            alumno.setApellidoPatAlumno(ap_pat);
            alumno.setApellidoMatAlumno(ap_mat);
            alumno.setFechaRegistroAlumno(new Date());
            alumno.setCurso(c);
            s.save(alumno);
            t.commit();

        } catch (HibernateException ex) {
            t.rollback();

        } finally {
            s.close();
        }
    }

    public void addApoderado(String nombre, String ap_pat, String ap_mat, String email, String passw) {
        // Alumno a = this.getAlumnoLastId();
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Apoderado ap = new Apoderado();
        try {
            ap.setNombreApoderado(nombre);
            ap.setApellidoPatApoderado(ap_pat);
            ap.setApellidoMatApoderado(ap_mat);
            ap.setEmailApoderado(email);
            ap.setPasswordApoderado(passw);
            ap.setFechaRegistroApoderado(new Date());
            ap.setAlumno(this.alumno);
            s.save(ap);
            t.commit();

        } catch (HibernateException ex) {
            t.rollback();

        } finally {
            s.close();
        }
    }

    public List<Alumno> getAlumnos() {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select a from Alumno a");
        List<Alumno> alumnos = (List<Alumno>) q.list();
        s.close();
        return alumnos;
    }
    public Alumno getAlumnoById(Integer id){
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select a from Alumno a where a.idAlumno = :id");
        q.setParameter("id", id);
        Alumno a =  (Alumno)q.uniqueResult();
        s.close();
        return a;
    }

    public List<Apoderado> getApoderados() {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select a from Apoderado a");
        List<Apoderado> apoderados = (List<Apoderado>) q.list();
        s.close();
        return apoderados;
    }
    
    public Apoderado getApoderadoById(Integer id){
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select a from Apoderado a where a.idApoderado = :id");
        q.setParameter("id", id);
        Apoderado a =  (Apoderado)q.uniqueResult();
        s.close();
        return a;
    }

    public Boolean validEmailApoderado(String email) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        System.out.print("email3: " + email);
        Query q = s.createQuery("Select a from Apoderado a where a.emailApoderado = :email");
        q.setParameter("email", email);
        List c = q.list();
        System.out.print("tamaño: " + c.size());
        if (c.size() > 0) {
            return true;
        }
        return false;
    }

}
