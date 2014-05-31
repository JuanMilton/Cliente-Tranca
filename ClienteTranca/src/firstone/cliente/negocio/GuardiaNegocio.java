/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.GuardiaDAO;
import firstone.cliente.datos.model.Guardia;

/**
 *
 * @author Milton
 */
public class GuardiaNegocio {
    
    GuardiaDAO guardiaDao;
    
    public GuardiaNegocio()
    {
        guardiaDao = new GuardiaDAO();
    }
    
    public Guardia autenticarGuardia(String ci, String password)
    {
        return guardiaDao.get(ci, password);
    }
    
}
