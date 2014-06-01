/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.ccs;

import com.firstonesoft.client.Client;
import com.firstonesoft.client.event.EventClient;
import com.firstonesoft.client.util.ObjectUtil;
import firstone.serializable.Aviso;
import firstone.cliente.datos.model.Alarma;
import firstone.cliente.negocio.SynchronizerNegocio;
import firstone.cliente.util.Sincronizacion;
import firstone.serializable.Contrato;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Milton
 */
public class InterfazEnvioCliente implements EventClient {
    
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InterfazEnvioCliente.class);
    
    private Client cliente;
    private Map<String,Object> keys;
    
    private String ci_guardia;
    private SynchronizerNegocio synchronizerNegocio;
    
    public InterfazEnvioCliente(Client client, String ci_guardia)
    {
        this.synchronizerNegocio = new SynchronizerNegocio();
        
        this.ci_guardia = ci_guardia;
        this.cliente = client;
    }
    
    public boolean lanzarAviso(Aviso aviso, int id_entorno)
    {
        Contrato contrato = new Contrato();
        contrato.setAccion(Accion.AVISO);
        contrato.setContenido(ObjectUtil.createBytes(aviso));
        contrato.setId_entorno(id_entorno);
        try {
            if (!cliente.isConnected())   
                cliente.connectClosed(ci_guardia);
            if (cliente.isConnected())
            {
                cliente.sendPackage(ObjectUtil.createBytes(contrato));
                return true;
            }else
                log.warn("No se ha podido conectar para enviar la advertencia");
        } catch (IOException ex) {
            log.error("Error al conectarse al Servidor",ex);
        }
        return false;
    }
    
    public boolean lanzarAlarma(Alarma alarma, int id_entorno)
    {
        firstone.serializable.Alarma al = new firstone.serializable.Alarma();
        al.setEmisor(alarma.getEmisor());
        al.setPrioridad(alarma.getPrioridad());

        Contrato contrato = new Contrato();
        contrato.setAccion(Accion.ALARMA);
        contrato.setContenido(ObjectUtil.createBytes(al));
        contrato.setId_entorno(id_entorno);
        try {
            cliente.sendPackage(ObjectUtil.createBytes(contrato));
            return true;
        } catch (IOException ex) {
            log.error("Error al enviar la Alarma",ex);
        }
        return false;
    }
    
    public void actualizar(int id_entorno)
    {
        List<Object> paquete = synchronizerNegocio.obtenerDatosASubir();
        Contrato contrato = new Contrato();
        contrato.setAccion(Accion.UPDATE);
        contrato.setContenido(ObjectUtil.createBytes(paquete));
        contrato.setId_entorno(id_entorno);
        try {
            if (cliente.isConnected())
                cliente.sendPackage(ObjectUtil.createBytes(contrato));
        } catch (IOException ex) {
            log.error("No se pudo subir la informacion al Core ",ex);
        }
    }
    
    public void solicitarSincronizacion(int id_entorno)
    {
        Contrato contrato = new Contrato();
        contrato.setAccion(Accion.DOWNLOAD);
        
        contrato.setId_entorno(id_entorno);
        try {
            contrato.setContenido((Sincronizacion.ULTIMO_ID_SINCRONIZADO+","+id_entorno).getBytes("UTF-8"));
            if (cliente.isConnected())
                cliente.sendPackage(ObjectUtil.createBytes(contrato));
        } catch (IOException ex) {
            log.error("No se pudo subir la informacion al Core ",ex);
        }
    }
    
    public void solicitarTrancas()
    {
        
    }
    
    public void verificarAdministrador(String email, String password)
    {
        
    }

    @Override
    public void onNewPackage(long size) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNewTrama(int bytesRead) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNewPackageComplet(byte[] data) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onDisconnectCore(IOException e) {
        log.warn("Se ha desconectado el Servidor",e);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
