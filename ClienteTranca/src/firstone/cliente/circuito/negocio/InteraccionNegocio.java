/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.circuito.negocio;

import firstone.cliente.circuito.InterfazCircuito;
import firstone.cliente.circuito.model.Sensor;
import firstone.cliente.datos.dao.AvisoDAO;
import firstone.cliente.util.Parametros;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton
 */
public class InteraccionNegocio {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AvisoDAO.class);
    
    InterfazCircuito interfazCircuito;
    boolean alarma;
    
    
    public InteraccionNegocio()
    {
        interfazCircuito = new InterfazCircuito();
        alarma = false;
    }
    
    public synchronized void dejarPasarVehiculo()
    {
        log.info("[CIRCUITO] Dejar Pasar Vehiculo " + (new Date()));
//        if (Parametros.DEBUG_CIRCUITO)
//            return;
        interfazCircuito.levantarTranca(); ///////////////////////////////////////////////////////////// LEVANTAR BARRERA
        while (interfazCircuito.leerSensor() == Sensor.NO_HAY_VEHICULO) ///////////////////////////////////////////////////////////// LEER SENSOR
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                log.error("Error al dormir HILO",ex);
            }
        }
        
        while (interfazCircuito.leerSensor() == Sensor.HAY_VEHICULO) ///////////////////////////////////////////////////////////// LEER SENSOR
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                log.error("Error al dormir HILO",ex);
            }
        }
        try {
            Thread.sleep(700);
        } catch (InterruptedException ex) {
            log.error("Error al dormir HILO",ex);
        }
        
        interfazCircuito.bajarTranca(); ///////////////////////////////////////////////////////////// BAJAR BARRERA
    }
    
    public synchronized void activarAlarma()
    {        
        if (! alarma)
        {
            interfazCircuito.activarAlarma();
            alarma = !alarma;
        }
    }
    
    public synchronized void apagarAlarma()
    {       
        if (alarma)
        {
            interfazCircuito.apagarAlarma();
            alarma = !alarma;
        }
    }
    
}
