/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.datos.model;

import java.util.Date;

/**
 *
 * @author Milton
 */
public class Bitacora {
    
    public static final String ACCION_REGISTRO_VISITA       = "Registro de Visita";
    public static final String ACCION_LANZAR_ALARMA         = "Lanzar Alarma";
    public static final String ACCION_LANZAR_AVISO          = "Lanzar Aviso";
    public static final String ACCION_DEJAR_PASAR_VEHICULO  = "Dejar pasar Vehiculo";
    
    
    private Date fecha_hora;
    private String accion;
    private String detalle;
    private String ci_guardia;

    /**
     * @return the fecha_hora
     */
    public Date getFecha_hora() {
        return fecha_hora;
    }

    /**
     * @param fecha_hora the fecha_hora to set
     */
    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @return the ci_guardia
     */
    public String getCi_guardia() {
        return ci_guardia;
    }

    /**
     * @param ci_guardia the ci_guardia to set
     */
    public void setCi_guardia(String ci_guardia) {
        this.ci_guardia = ci_guardia;
    }
    
    
    
}
