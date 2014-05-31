/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.SynchronizerDAO;
import firstone.cliente.datos.dao.VisitaVehiculoDAO;
import firstone.cliente.datos.model.Synchronizer;

/**
 *
 * @author Milton
 */
public class VisitaVehiculoNegocio {
    
    VisitaVehiculoDAO visitaVehiculoDao;
    SynchronizerDAO szr;
    
    public VisitaVehiculoNegocio()
    {
        visitaVehiculoDao = new VisitaVehiculoDAO();
        szr = new SynchronizerDAO();
    }
    
    public boolean existeRelacion(String placa, String ci)
    {
        return visitaVehiculoDao.existRelation(ci, placa);
    }
    
    public void insertarRelacion(String placa, String ci)
    {
        visitaVehiculoDao.insertRelation(placa, ci);
        
        Synchronizer s = new Synchronizer();
        s.setRef_id(placa+","+ci);
        s.setTabla(Synchronizer.TABLE_VISITA_VEHICULO);
        s.setTransaccion("I");
        szr.insert(s);
    }
    
    
}
