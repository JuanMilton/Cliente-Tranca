/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.datos.model;

/**
 *
 * @author Milton
 */
public class Synchronizer {
    
    public static final String TABLE_VISITA = "visita";
    public static final String TABLE_VISITA_VEHICULO = "visita_vehiculo";
    public static final String TABLE_VEHICULO_VISITA = "vehiculo_visita";
    
    public static final String TABLE_GUARDIA = "guardia";
    public static final String TABLE_PROPIETARIO = "propietario";
    public static final String TABLE_PROPIETARIO_VEHICULO = "propietario_vehiculo";
    public static final String TABLE_TELEFONO_PROPIETARIO = "telefono_propietario";
    public static final String TABLE_VEHICULO = "vehiculo";
    public static final String TABLE_TRANCA = "tranca";
    
    private String transaccion;
    private String ref_id;
    private String tabla;

    /**
     * @return the transaccion
     */
    public String getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the ref_id
     */
    public String getRef_id() {
        return ref_id;
    }

    /**
     * @param ref_id the ref_id to set
     */
    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    
}
