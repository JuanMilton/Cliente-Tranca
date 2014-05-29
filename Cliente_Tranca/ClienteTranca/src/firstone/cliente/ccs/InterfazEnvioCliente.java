/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.ccs;

import firstone.cliente.ccs.model.Aviso;
import firstone.cliente.datos.model.Alarma;

/**
 *
 * @author Milton
 */
public class InterfazEnvioCliente {
    
    public boolean lanzarAviso(Aviso aviso)
    {
        return true;
    }
    
    public boolean lanzarAlarma(Alarma alarma)
    {
        return true;
    }
    
    public void actualizar()
    {
        
    }
    
}
