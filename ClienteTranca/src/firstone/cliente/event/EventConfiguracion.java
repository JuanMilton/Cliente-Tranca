/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.event;

import firstone.serializable.Tranca;
import java.util.List;

/**
 *
 * @author Milton
 */
public interface EventConfiguracion {
    
    public void autenticacionAdministrador(List<Tranca> trancas, int id_entorno);
    
}
