/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito.proceso;

import firstone.cliente.circuito.EventListener;
import firstone.cliente.circuito.InterfazCircuito;
import firstone.cliente.util.Parametros;

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
    public static boolean reconocido;
    
    public ProcesoRFID(EventListener eventListener)
    {
        this.eventListener = eventListener;
        this.interfazCircuito = new InterfazCircuito();
        
        run = true;
        reconocido = false;
    }
    
    @Override
    public void run()
    {
        if (Parametros.DEBUG_CIRCUITO)
            return;
        lastReaded = this.interfazCircuito.leerRFID();
        while (run)
        {
            try{
                synchronized (this)
                {
    //                if (!reconocido)
    //                {
                        int rfid = this.interfazCircuito.leerRFID(); /////////////////////////////////////// LEER RFID
                        if (rfid > -1 && rfid != lastReaded)
                        {
                            eventListener.llegoVehiculo(rfid);
                            lastReaded = rfid;
                            reconocido = true;
                        }

                        try {    
                            Thread.sleep(300);
                        } catch (InterruptedException ex) {
                            log.error("Error al dormir el proceso en PROCESO_RFID",ex);
                        }
    //                }
                }
            }catch(Exception e)
            {
                log.error("Error, sistema no proceso correctamente la niformacion recibida del circuito",e);
            }
        }
        log.debug("Finalizando el proceso Lector de RFID");
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
