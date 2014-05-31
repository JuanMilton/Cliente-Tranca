/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito;

import firstone.cliente.datos.dao.AvisoDAO;

/**
 *
 * @author Milton
 */
public class InterfazCircuito {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InterfazCircuito.class);
    
    public void levantarTranca()
    {
        log.info("[CIRCUITO] : Levantar Tranca");
    }
    
    public void bajarTranca()
    {
        log.info("[CIRCUITO] : Bajar Tranca");
    }
    
    public void activarAlarma()
    {
        log.info("[CIRCUITO] : Activar Alarma");
    }
    
    public void apagarAlarma()
    {
        log.info("[CIRCUITO] : Apagar Alarma");
    }
    
    public int leerRFID()
    {
        
        return 0;
    }
    
    public boolean leerSensor()
    {
        return false;
    }
    
}
