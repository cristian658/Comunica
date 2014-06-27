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
    public String execute(){
        session = ActionContext.getContext().getSession();
        comunBandeja = new ComunicacionBandeja();
        if(this.option != null && session != null){
            if(((String)session.get("typeUser")).equals("Profesor")){
                Integer idProfesor = (Integer)session.get("id");
                if(idProfesor != null){
                    profesor = comunBandeja.getProfesor(idProfesor);
                    alumnos = comunBandeja.getAlumnos(profesor.getCurso());
                    if(idAlumno != null){
                       comunBandeja.addComunicacionDetalle(asunto, mensaje, idAlumno, profesor, idProfesor);
                    }
                }
            }
        }else{
            return ERROR;
        }
        return SUCCESS;
    }
    
    public void validate() {
        if (this.option != null) {
            if (asunto != null && asunto.equals("")) {
                addActionError("Ingrese asunto de la comunicación");
            }
            if(mensaje != null && mensaje.equals("")){
                addActionError("Ingrese mensaje de la comunicación");
            }
        }
    }
    
}
