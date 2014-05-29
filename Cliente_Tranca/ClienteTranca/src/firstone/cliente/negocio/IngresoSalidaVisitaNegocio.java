/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.IngresoSalidaVisitaDAO;
import java.util.Date;

/**
 *
 * @author Milton
 */
public class IngresoSalidaVisitaNegocio {

    IngresoSalidaVisitaDAO ingresoSalidaVisitaDAO;
    
    public IngresoSalidaVisitaNegocio()
    {
        ingresoSalidaVisitaDAO = new IngresoSalidaVisitaDAO();
    }
    
    public void registrarIngresoSalida(String tipo, Date fecha_hora, int id_tranca, String placa)
    {
        ingresoSalidaVisitaDAO.insert(tipo, fecha_hora, id_tranca, placa);
    }
    
}
