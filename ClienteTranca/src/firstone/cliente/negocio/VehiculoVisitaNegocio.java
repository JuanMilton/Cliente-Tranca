/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.SynchronizerDAO;
import firstone.cliente.datos.dao.VehiculoVisitaDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.model.Synchronizer;
import firstone.cliente.datos.model.VehiculoVisita;
import firstone.cliente.util.Parametros;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class VehiculoVisitaNegocio {
    
    private static final Logger log = Logger.getLogger(VisitaDAO.class);
    
    VehiculoVisitaDAO vehiculoVisitaDao;
    SynchronizerDAO szr;
    
    public VehiculoVisitaNegocio()
    {
        vehiculoVisitaDao = new VehiculoVisitaDAO();
        szr = new SynchronizerDAO();
    }
    
    public void registrarVehiculoVisita(VehiculoVisita vehiculoVisita)
    {
        vehiculoVisitaDao.insert(vehiculoVisita);
        
        Synchronizer s = new Synchronizer();
        s.setRef_id(vehiculoVisita.getPlaca());
        s.setTabla(Synchronizer.TABLE_VEHICULO_VISITA);
        s.setTransaccion("I");
        szr.insert(s);
    }
    
    public VehiculoVisita buscarVehiculoVisita(String placa)
    {
        return vehiculoVisitaDao.get(placa);
    }

    public String validarValores(VehiculoVisita vehiculoVisita) {
        log.debug("Validando valores de VehiculoVisita :: PLACA " + vehiculoVisita.getPlaca());
        if (! vehiculoVisita.getPlaca().matches(Parametros.ER_PLACA))
            return "La placa no tiene el formato correcto";
        if (! vehiculoVisita.getMarca().matches(Parametros.ER_MARCA))
            return "La marca no tienen el formato correcto";
        return null;
    }
    
}
