package cl.ciisa.comunica.model;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Curso;
import cl.ciisa.comunica.util.ComunicaHibernateUtil;
import cl.ciisa.comunica.util.Crypt;
import cl.ciisa.comunica.util.SendEmail;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        Session s = ComunicaHibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        Apoderado ap = new Apoderado();
        Boolean send = true;
        try {
            
            String passCod = Crypt.digest(passw);
            ap.setNombreApoderado(nombre);
            ap.setApellidoPatApoderado(ap_pat);
            ap.setApellidoMatApoderado(ap_mat);
            ap.setEmailApoderado(email);
            ap.setPasswordApoderado(passCod);
            ap.setFechaRegistroApoderado(new Date());
            ap.setAlumno(this.alumno);
            s.save(ap);
            t.commit();

        } catch (HibernateException ex) {
            ex.printStackTrace();
            t.rollback();
            send = false;
        }finally {
            if(send){
                sendEmail(nombre, ap_pat, email, passw, 
                        this.alumno.getNombreAlumno(), this.alumno.getApellidoPatAlumno()
                );
            }
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
        Query q = s.createQuery("Select a from Apoderado a where a.emailApoderado = :email");
        q.setParameter("email", email);
        List c = q.list();
        if (c.size() > 0) {
            return true;
        }
        return false;
    }
    
    public void sendEmail(String nombre, String apellido, String mail, String pass, String nombre_alumno, String ap_alumno){
          
          String body = "Hola "+nombre +" "+apellido+ "\n"+
                      " \n" +
                      "Apoderado de: "+ nombre_alumno+" "+ap_alumno+"\n"+
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
