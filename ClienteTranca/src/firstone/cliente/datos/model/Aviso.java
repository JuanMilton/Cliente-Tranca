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
public class Aviso {
    
    private Date fecha_hora;
    private String descripcion;
    private int id_tranca;
    private String de;

    
    public Object[] fieldsToObjects()
    {
        return new Object[]{getDe(),descripcion,fecha_hora};
    }
    

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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the id_tranca
     */
    public int getId_tranca() {
        return id_tranca;
    }

    /**
     * @param id_tranca the id_tranca to set
     */
    public void setId_tranca(int id_tranca) {
        this.id_tranca = id_tranca;
    }

    /**
     * @return the de
     */
    public String getDe() {
        return de;
    }

    /**
     * @param de the de to set
     */
    public void setDe(String de) {
        this.de = de;
    }
    
    
    
    
    
}
