/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito;

import firstone.cliente.util.Parametros;
import java.util.Random;


/**
 *
 * @author Milton
 */
public class InterfazCircuito {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InterfazCircuito.class);
    
    private boolean debug_sensor;
    
    public InterfazCircuito()
    {
        debug_sensor = true;
    }
    
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
        if (Parametros.DEBUG_CIRCUITO)
        {
            debug_sensor = !debug_sensor;
            return debug_sensor;
        }
        return false;
    }
    
}
