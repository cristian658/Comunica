package cl.ciisa.comunica.model;

import cl.ciisa.comunica.controller.admin.CursoAction;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
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
public class Cursos {
    
    /**
     * Este metodo sirve para guardar el curso en la base de datos
     * @param nombre
     * @return Boolean
     */
    public Boolean addCurso(String nombre) {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Curso c = new Curso();
        try {
            c.setNombreCurso(nombre);
            c.setFechaRegistroCurso(new Date());
            s.save(c);
            t.commit();
            return true;
        } catch (HibernateException ex) {
            t.rollback();
            return false;
        }finally{
            s.close();
        }
    }
    /**
     * Este metodo devuelve todos los cursos ingresados en la base de datos.
     * @return List<Curso>
     */
    public List<Curso> getCursos() {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select c from Curso c");
        List<Curso> cursos = (List<Curso>) q.list();
        s.close();
        return cursos;
    }
    /**
     * Este metodo valido si el curso existe o no
     * en caso de que devuelva un true(verdadero) significa que el curso existe,
     * caso contrario no existe.
     * @param curso
     * @return Boolean
     */
    public Boolean validCurso(String curso) {   
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
         Query q = s.createQuery("Select c from Curso c where c.nombreCurso = :curso");
         q.setParameter("curso", curso);
         if(q.list().size()>0){
             return true;
         }
         return false;
    }
    public Curso getCursoById(int idCUrso){
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("select c from Curso c where c.idCurso = :id");
        q.setParameter("id", idCUrso);
        List<Curso> cursos = (List<Curso>)q.list();
        s.close();
        if(cursos.size()>0){
            return     (Curso)cursos.get(0);
        }
        return null;
 }

}
