/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.ccs;

import com.firstonesoft.client.Client;
import com.firstonesoft.client.event.EventClient;
import com.firstonesoft.client.util.ObjectUtil;
import firstone.cliente.datos.dao.GuardiaDAO;
import firstone.serializable.Contrato;
import firstone.serializable.Propietario;
import firstone.serializable.Tranca;
import firstone.cliente.event.EventConfiguracion;
import firstone.cliente.util.Parametros;
import firstone.serializable.Guardia;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Milton
 */
public class InterfazConfiguracion implements EventClient {
    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(InterfazConfiguracion.class);
    
    
    private static final String IP_CORE = Parametros.CORE_IP;
    private static final int PORT_CORE = Parametros.CORE_PORT;
    
    private EventConfiguracion eventConfiguracion;
    
    private Client cliente;
    private Map<String,Object> keys;
    
    private GuardiaDAO guardiaDao;
    
    public InterfazConfiguracion(EventConfiguracion eventConfiguracion)
    {
        this.eventConfiguracion = eventConfiguracion;
        cliente = new Client(IP_CORE, PORT_CORE);
        cliente.setEventClient(this);
        guardiaDao= new GuardiaDAO();
    }
    
    public boolean iniciar()
    {
        Propietario configurador = new Propietario();
        configurador.setApellidos("FirstOneSoft");
        configurador.setCi("000000-0");
        configurador.setFoto(null);
        configurador.setNombres("IdentiFour");
        configurador.setNro_licencia("000000-C");
        try {
            cliente.connectOpened(configurador.getCi(), configurador);
            return true;
        } catch (IOException ex) {
            log.error("No se pudo conectar",ex);
            return false;
        }
    }
    
    public void finalizar()
    {
        try {
            cliente.disconect();
        } catch (IOException ex) {
            log.error("Error al desconectarse",ex);
        }
    }
    
    public void autenticarAdministrador(String email, String pass)
    {
        try {
            Contrato contrato = new Contrato();
            contrato.setAccion(Accion.AUTENTICAR_ADMINISTRADOR);
            contrato.setContenido((email+","+pass).getBytes("UTF-8"));
            contrato.setId_entorno(0);
            cliente.sendPackage(ObjectUtil.createBytes(contrato));
        } catch (UnsupportedEncodingException ex) {
            log.error("Codificacion no soportada",ex);
        } catch (IOException ex) {
            log.error("Error al enviar Paquete de bytes",ex);
        }
    }
    
    private void autenticacionAdministrador(byte[] contenido, int id_entorno)
    {
        Object o = ObjectUtil.createObject(contenido);
        if (o != null)
        {
            List<Tranca> trancas = (List<Tranca>) o;
            eventConfiguracion.autenticacionAdministrador(trancas,id_entorno);
        }else
            eventConfiguracion.autenticacionAdministrador(null,id_entorno);
    }

    private void guardiasExistentes(byte[] contenido, int id_entorno)
    {
        List<Guardia> guardias = (List<Guardia>)ObjectUtil.createObject(contenido);
        for (Guardia g : guardias)
        {
            guardiaDao.insert(g.getCi(), g.getNombre(), g.getApellido(), g.getPassword());
        }
    }
    
    @Override
    public void onNewPackage(long size) {
    }

    @Override
    public void onNewTrama(int bytesRead) {
    }

    @Override
    public void onNewPackageComplet(byte[] data) {
        Contrato contrato = (Contrato) ObjectUtil.createObject(data);
        switch (contrato.getAccion())
        {
            case Accion.AUTENTICAR_ADMINISTRADOR: autenticacionAdministrador(contrato.getContenido(), contrato.getId_entorno()); break;
            case Accion.GUARDIAS: guardiasExistentes(contrato.getContenido(), contrato.getId_entorno()); break;
        }
    }

    @Override
    public void onDisconnectCore(IOException e) {
    }
    
}
