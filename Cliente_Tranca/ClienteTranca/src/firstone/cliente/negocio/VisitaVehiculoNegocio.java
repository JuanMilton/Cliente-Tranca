/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.VisitaVehiculoDAO;

/**
 *
 * @author Milton
 */
public class VisitaVehiculoNegocio {
    
    VisitaVehiculoDAO visitaVehiculoDao;
    
    public VisitaVehiculoNegocio()
    {
        visitaVehiculoDao = new VisitaVehiculoDAO();
    }
    
    public boolean existeRelacion(String placa, String ci)
    {
        return visitaVehiculoDao.existRelation(ci, placa);
    }
    
    public void insertarRelacion(String placa, String ci)
    {
        visitaVehiculoDao.insertRelation(placa, ci);
    }
    
    
}
