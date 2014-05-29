/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.IngresoSalidaDAO;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class IngresoSalidaNegocio {

    IngresoSalidaDAO ingresoSalidaDAO;
    
    public IngresoSalidaNegocio()
    {
        ingresoSalidaDAO = new IngresoSalidaDAO();
    }
    
    public void registrarIngresoSalida(String tipo, Date fecha_hora, int id_tranca, String placa)
    {
        ingresoSalidaDAO.insert(tipo, fecha_hora, id_tranca, placa);
    }
    
}
