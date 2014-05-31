/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.ccs;

import firstone.cliente.datos.model.Alarma;
import firstone.cliente.datos.model.Aviso;
import firstone.cliente.datos.model.Tranca;
import java.util.List;

/**
 *
 * @author Milton
 */
public interface InterfazRecepcionCliente {

    public void nuevosDatos();
    
    public void llegoAviso(Aviso aviso);
    
    public void llegoAlarma(Alarma alarma);

    public void trancasExistentes(List<Tranca> trancas);
    
    public void verificarAdministrador(boolean resultado);
}
