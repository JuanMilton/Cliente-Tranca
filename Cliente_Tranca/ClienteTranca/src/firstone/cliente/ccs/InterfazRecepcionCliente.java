/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.ccs;

import firstone.cliente.datos.model.Alarma;
import firstone.cliente.datos.model.Aviso;

/**
 *
 * @author Milton
 */
public interface InterfazRecepcionCliente {

    public void nuevosDatos();
    
    public Aviso llegoAviso();
    
    public Alarma llegoAlarma();

}
