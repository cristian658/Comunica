package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import cl.ciisa.comunica.util.SendEmail;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author eduardo
 */
public class Profesores {
    
    Cursos cursos = new Cursos();

    public void agregarProfesor(String nombre_profesor, String apellido_profesor,
            String apellido2_profesor, String mail,String pass,
            int id_curso) {
        Curso c = cursos.getCursoById(id_curso);
        Session session = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Profesor p = new Profesor();
        try {
            p.setFechaRegistroProfesor(new Date());
            p.setNombreProfesor(nombre_profesor);
            p.setApellidoPatProfesor(apellido_profesor);
            p.setApellidoMatProfesor(apellido2_profesor);
            p.setEmailProfesor(mail);
            p.setPasswordProfesor(pass);
            p.setCurso(c);
            session.save(p);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
        } finally{
            sendEmail(nombre_profesor, apellido_profesor, mail, pass, c.getNombreCurso());
            session.close();
        }
    }
     public List<Profesor> getProfesores() {
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select p from Profesor p");
        List<Profesor> profes = (List<Profesor>) q.list();
        s.close();
        return profes;
    }
     
      public Boolean validEmailProfesor(String email) {   
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("Select p from Profesor p where p.emailProfesor = :email");
        q.setParameter("email", email);
        List c= q.list();         
        if (c.size()>0){
            return true;
        }
        return false;
 }
      public void sendEmail(String nombre, String apellido, String mail, String pass, String curso){
          
          String body = "Hola "+nombre +" "+apellido+ "\n"+
                      " \n" +
                      "Dueño del curso: "+curso+"\n"+
                      " \n" +
                      "Detalles de tu cuenta\n" +
                      " \n" +
                      "Email: "+mail+"\n" +
                      "Contraseña: "+pass+"\n" +
                      " \n" +
                      "Consejos de Seguridad:\n" +
                      "\n" +
                      "Mantén los datos de tu cuenta en lugar seguro.\n" +
                      "No des los detalles de tu cuenta a nadie.\n" +
                      "Si sospechas que alguien esta usando ilegalmente tu cuenta, avísanos inmediatamente.\n" +
                      " comunicaeecc@gmail.com";
          SendEmail email = new SendEmail("Cuenta Comunica", body, mail);
          email.send();
      }

}
