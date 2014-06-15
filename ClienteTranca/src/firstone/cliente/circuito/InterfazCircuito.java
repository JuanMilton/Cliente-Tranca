/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.circuito;

import firstone.cliente.util.Parametros;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Milton
 */
public class InterfazCircuito {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InterfazCircuito.class);
    
    private boolean debug_sensor;
    public static boolean sensor_reading;
    
    CircuitNativeInterface cni;
    
    public InterfazCircuito()
    {
        cni = new CircuitNativeInterface();
        debug_sensor = true;
        sensor_reading = false;
    }
    
    public void levantarTranca()
    {
        log.info("[CIRCUITO] : Levantar Tranca");
        if (Parametros.DEBUG_CIRCUITO)
            return;
        try {
            cni.callCircuit(CircuitNativeInterface.LEVANTAR_BRAZO);
        } catch (Exception ex) {
            log.error("Error al conectar con el componente del circuito");
        }
    }
    
    public void bajarTranca()
    {
        log.info("[CIRCUITO] : Bajar Tranca");
        if (Parametros.DEBUG_CIRCUITO)
            return;
        try {
            cni.callCircuit(CircuitNativeInterface.BAJAR_BRAZO);
        } catch (Exception ex) {
            log.error("Error al conectar con el componente del circuito");
        }
    }
    
    public void activarAlarma()
    {
        log.info("[CIRCUITO] : Activar Alarma");
        if (Parametros.DEBUG_CIRCUITO)
            return;
        try {
            cni.callCircuit(CircuitNativeInterface.ENCENDER_ALARMA);
        } catch (Exception ex) {
            log.error("Error al conectar con el componente del circuito");
        }
    }
    
    public void apagarAlarma()
    {
        log.info("[CIRCUITO] : Apagar Alarma");
        if (Parametros.DEBUG_CIRCUITO)
            return;
        try {
            cni.callCircuit(CircuitNativeInterface.APAGAR_ALARMA);
        } catch (Exception ex) {
            log.error("Error al conectar con el componente del circuito");
        }
    }
    
    public int leerRFID()
    {
        if (Parametros.DEBUG_CIRCUITO)
            return 10;
        if (! sensor_reading)
        {
            try {
                int t = Integer.parseInt(cni.callCircuit(CircuitNativeInterface.OBTENER_CODIGO_VEHICULO));
//                log.debug("RFID :::::: " + t);
                return t;
            } catch (Exception ex) {
                log.error("Error al conectar con el componente del circuito");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex1) {
                    log.error("Error al dormir el hilo");
                }
                
            }
        }
        return -1;
    }
    
    public boolean leerSensor()
    {
        if (Parametros.DEBUG_CIRCUITO)
        {
            debug_sensor = !debug_sensor;
            return debug_sensor;
        }
        try {
            return (Integer.parseInt(cni.callCircuit(CircuitNativeInterface.OBTENER_DATOS_SENSOR)) == 0);
        } catch (Exception ex) {
            log.error("Error al conectar con el componente del circuito");
        }
        return true;
    }
    
}
