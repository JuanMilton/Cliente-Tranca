/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.BitacoraDAO;
import firstone.cliente.datos.model.Bitacora;

/**
 *
 * @author Milton
 */
public class BitacoraNegocio {
    
    BitacoraDAO bitacoraDao;
    
    public BitacoraNegocio()
    {
        bitacoraDao = new BitacoraDAO();
    }
    
    public void registrarBitacora(Bitacora bitacora)
    {
        bitacoraDao.insert(bitacora);
    }
    
}
