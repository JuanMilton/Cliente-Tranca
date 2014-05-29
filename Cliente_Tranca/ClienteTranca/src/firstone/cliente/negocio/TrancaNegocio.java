/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.TrancaDAO;
import firstone.cliente.datos.model.Tranca;

/**
 *
 * @author Milton
 */
public class TrancaNegocio {
    
    TrancaDAO trancaDao;
    
    public TrancaNegocio()
    {
        trancaDao = new TrancaDAO();
    }
    
    public Tranca obtenerTranca()
    {
        return trancaDao.get();
    }
    
}
