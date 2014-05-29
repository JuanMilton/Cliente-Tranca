/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.circuito.negocio;

import firstone.cliente.circuito.InterfazCircuito;
import firstone.cliente.circuito.model.Sensor;
import firstone.cliente.datos.dao.AvisoDAO;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class InteraccionNegocio {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AvisoDAO.class);
    
    InterfazCircuito interfazCircuito;

    public InteraccionNegocio()
    {
        interfazCircuito = new InterfazCircuito();
    }
    
    public void dejarPasarVehiculo()
    {
        log.info("[CIRCUITO] Dejar Pasar Vehiculo " + (new Date()));
        
        interfazCircuito.levantarTranca();
        while (interfazCircuito.leerSensor() == Sensor.NO_HAY_VEHICULO)
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                log.error("Error al dormir HILO",ex);
            }
        }
        
        while (interfazCircuito.leerSensor() == Sensor.HAY_VEHICULO)
        {
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                log.error("Error al dormir HILO",ex);
            }
        }
        interfazCircuito.bajarTranca();
    }
    
    public void activarAlarma()
    {
        log.info("[CIRCUITO] Activar Alarma " + (new Date()));
        
        interfazCircuito.activarAlarma();
    }
    
    public void apagarAlarma()
    {
        log.info("[CIRCUITO] Apagar Alarma " + (new Date()));
        
        interfazCircuito.apagarAlarma();
    }
    
}
