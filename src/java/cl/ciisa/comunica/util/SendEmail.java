package cl.ciisa.comunica.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author cristian
 */
public class SendEmail {

    private String subject;
    private String body;
    private String to;
    private String from = "comunicaeecc@gmail.com";
    private String pass = "passwordEECC";

    public SendEmail(String subject, String body, String to) {
        this.subject = subject;
        this.body = body;
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public String getBodyNuevaComunicacion(String Asunto){
        String comunicacionNueva = "Estimado:\n "
                                    + "Se ha enviado una nueva comunicacion:\n"
                                    + "'{asunto}'\n"
                                    + "Favor ingrese al sistema Comunica para ver mas detalle";
        comunicacionNueva = comunicacionNueva.replace("{asunto}", Asunto);
        return comunicacionNueva;
    }
    
    public String getBodyRespuestaComunicacion(String Asunto){
        String comunicacionNueva = "Estimado:\n "
                                    + "Se ha respondido la comunicacion:\n"
                                    + "'{asunto}'\n"
                                    + "Favor ingrese al sistema Comunica para ver mas detalle";
        comunicacionNueva = comunicacionNueva.replace("{asunto}", Asunto);
        return comunicacionNueva;
    }

    public void send() {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.quitwait", "false");
            properties.put("mail.debug", "false");
            properties.put("mail.smtp.protocol", "smtps");
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication
                        getPasswordAuthentication() {
                            return new PasswordAuthentication(from, pass);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    

}
