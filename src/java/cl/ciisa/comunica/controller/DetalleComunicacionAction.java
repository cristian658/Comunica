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
    String mensajeDeForm;

    DetalleComunicaciones detalleComunicaciones = new DetalleComunicaciones();
    public List<Detallecomunicacion> dcByIdComunicacionPrincipalList;
    public Detallecomunicacion dcPrimerRegistroDeLaLista;

    public String execute() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session != null && !session.isEmpty()
                && (session.get("typeUser").toString().equals("Profesor") || session.get("typeUser").toString().equals("Apoderado"))) {

            if (idComunicacion != null) {
                this.dcPrimerRegistroDeLaLista = this.detalleComunicaciones.getDetalleComunicacionByIdComunicacionPrincipal(idComunicacion).get(0);            

                if (mensajeDeForm != null) {
                    this.detalleComunicaciones.addRespuesta(mensajeDeForm, null, session.get("correo").toString(), idComunicacion, dcPrimerRegistroDeLaLista);
                    this.setMensajeDeForm("");
                    return "DETALLE";
                }
                this.dcByIdComunicacionPrincipalList = this.detalleComunicaciones.getDetalleComunicacionByIdComunicacionPrincipal(idComunicacion);
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

    public String getMensajeDeForm() {
        return mensajeDeForm;
    }

    public void setMensajeDeForm(String mensajeDeForm) {
        this.mensajeDeForm = mensajeDeForm;
    }

}
