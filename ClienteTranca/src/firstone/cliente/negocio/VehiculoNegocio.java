/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.VehiculoDAO;
import firstone.cliente.datos.model.Vehiculo;

/**
 *
 * @author Milton
 */
public class VehiculoNegocio {

    VehiculoDAO vehiculoDao;
    
    public VehiculoNegocio()
    {
       vehiculoDao = new VehiculoDAO();
    }
    
    public Vehiculo obtenerVehiculoRFID(int rfid)
    {
        return vehiculoDao.get(rfid);
    }
    
}
