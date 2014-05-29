/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito.proceso;

import firstone.cliente.circuito.EventListener;
import firstone.cliente.circuito.InterfazCircuito;
import firstone.cliente.datos.dao.GuardiaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton
 */
public class ProcesoRFID extends Thread {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ProcesoRFID.class);
    
    private EventListener eventListener;
    private InterfazCircuito interfazCircuito;
    private boolean run;
    private int lastReaded;
    
    
    public ProcesoRFID(EventListener eventListener)
    {
        this.eventListener = eventListener;
        this.interfazCircuito = new InterfazCircuito();
        
        run = true;
        lastReaded = 0;
    }
    
    @Override
    public void run()
    {
        while (run)
        {
            
            int rfid = this.interfazCircuito.leerRFID();
            if (rfid != lastReaded)
            {
                eventListener.llegoVehiculo(rfid);
                lastReaded = rfid;
            }
                
            try {    
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                log.error("Error al dormir el proceso en PROCESO_RFID",ex);
            }
        }
    }
    
    public void detenerProceso()
    {
        run = false;
    }
    
    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }
}
