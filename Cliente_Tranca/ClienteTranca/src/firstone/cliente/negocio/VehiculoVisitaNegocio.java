/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.VehiculoVisitaDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.model.VehiculoVisita;
import firstone.cliente.datos.model.Visita;
import firstone.cliente.util.Parametros;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class VehiculoVisitaNegocio {
    
    private static final Logger log = Logger.getLogger(VisitaDAO.class);
    
    VehiculoVisitaDAO vehiculoVisitaDao;
    
    public VehiculoVisitaNegocio()
    {
        vehiculoVisitaDao = new VehiculoVisitaDAO();
    }
    
    public void registrarVehiculoVisita(VehiculoVisita vehiculoVisita)
    {
        vehiculoVisitaDao.insert(vehiculoVisita);
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
