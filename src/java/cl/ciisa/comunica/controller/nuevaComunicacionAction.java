/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.entity.Alumno;
import cl.ciisa.comunica.entity.Apoderado;
import cl.ciisa.comunica.entity.Profesor;
import cl.ciisa.comunica.model.ComunicacionBandeja;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cristian
 */
public class nuevaComunicacionAction extends ActionSupport {

    public String option;
    public List<Alumno> alumnos;
    public String asunto;
    public String mensaje;
    public Profesor profesor;
    public Apoderado apoderado;
    public Integer idAlumno;
    Map<String, Object> session;
    private ComunicacionBandeja comunBandeja;

    public String execute() {
        session = ActionContext.getContext().getSession();
        String emailSession = (String) session.get("correo");
        Integer idSession = (Integer) session.get("id");
        String typeUserSession = (String) session.get("typeUser");
        comunBandeja = new ComunicacionBandeja();
        if (/*this.option != null &&*/ session != null && !session.isEmpty() &&
           (typeUserSession.equals("Profesor") || typeUserSession.equals("Apoderado"))) {
            if (typeUserSession.equals("Profesor")) {
                if (idSession != null) {
                    profesor = comunBandeja.getProfesor(idSession);
                    alumnos = comunBandeja.getAlumnos(profesor.getCurso());
                    if (idAlumno != null && asunto != null && mensaje != null) {
                        comunBandeja.addComunicacionDetalle(asunto, mensaje, idAlumno, profesor, emailSession);
                        addActionMessage("Comunicacion Enviada Exitosamente!");
                        this.setIdAlumno(0);
                        this.setAsunto("");
                        this.setMensaje("");
                    } else if (asunto != null && mensaje != null) {
                        comunBandeja.addComunicacionDetalle(asunto, mensaje, null, profesor, emailSession);
                        addActionMessage("Comunicacion Enviada Exitosamente!");
                        this.setAsunto("");
                        this.setMensaje("");
                    }
                }
            }else{
                if(idSession != null)
                {
                    apoderado = comunBandeja.getApoderado(idSession);
                    profesor = comunBandeja.getProfesorByCurso(apoderado.getAlumno().getCurso().getIdCurso());
                    
                    if(asunto != null && mensaje != null)
                    {
                        comunBandeja.addComunicacionDetalle2(asunto, mensaje, profesor,apoderado, emailSession);
                        addActionMessage("Comunicacion Enviada Exitosamente!");
                        this.setAsunto("");
                        this.setMensaje("");
                    }
                }
            }
        } else {
            return ERROR;
        }
        return SUCCESS;
    }

    /*public void validate() {
        if (this.option != null) {
            if (asunto != null && asunto.equals("")) {
                addActionError("Ingrese asunto de la comunicación");
            }
            if (mensaje != null && mensaje.equals("")) {
                addActionError("Ingrese mensaje de la comunicación");
            }
        }
    }*/

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

}
