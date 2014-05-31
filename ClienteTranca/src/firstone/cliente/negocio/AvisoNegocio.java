/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.AvisoDAO;
import firstone.cliente.datos.model.Aviso;
import java.util.List;

/**
 *
 * @author Milton
 */
public class AvisoNegocio {
    
    AvisoDAO advertenciaDao;
    
    public AvisoNegocio()
    {
        advertenciaDao = new AvisoDAO();
    }
    
    public List<Aviso> obtenerTodasAdvertencias()
    {
        return advertenciaDao.getAllOrderDesc();
    }
    
    public void registrarAviso(Aviso aviso)
    {
        advertenciaDao.registrar(aviso);
    }
    
}
