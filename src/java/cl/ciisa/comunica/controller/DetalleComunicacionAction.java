/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ciisa.comunica.controller;

import cl.ciisa.comunica.entity.Detallecomunicacion;
import cl.ciisa.comunica.model.DetalleComunicaciones;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Esteban
 */
public class DetalleComunicacionAction extends ActionSupport {

    Integer idComunicacion;
    String mensajeForm;

    DetalleComunicaciones detalleComunicaciones = new DetalleComunicaciones();
    public List<Detallecomunicacion> detalleComunicacionList;
    public Detallecomunicacion dcPrimerResgistro;

    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session != null && !session.isEmpty()
                && (session.get("typeUser").toString().equals("Profesor") || session.get("typeUser").toString().equals("Apoderado"))) {

            if (idComunicacion != null) {
                this.dcPrimerResgistro = this.detalleComunicaciones.getDetalleComunicacion(idComunicacion).get(0);            

                if (mensajeForm != null) {
                    this.detalleComunicaciones.addRespuesta(mensajeForm, null, session.get("correo").toString(), idComunicacion, dcPrimerResgistro);
                    this.setMensajeForm("");
                    this.setIdComunicacion(0);
                    return "BANDEJA";
                }
                this.detalleComunicacionList = this.detalleComunicaciones.getDetalleComunicacion(idComunicacion);
            }
            return SUCCESS;
        }
        return ERROR;
    }

    public Integer getIdComunicacion() {
        return idComunicacion;
    }

    public void setIdComunicacion(Integer idComunicacion) {
        this.idComunicacion = idComunicacion;
    }

    public String getMensajeForm() {
        return mensajeForm;
    }

    public void setMensajeForm(String mensajeForm) {
        this.mensajeForm = mensajeForm;
    }

}
