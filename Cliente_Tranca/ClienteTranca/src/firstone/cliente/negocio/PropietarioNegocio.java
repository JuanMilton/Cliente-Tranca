/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.PropietarioDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.model.Propietario;
import firstone.cliente.datos.model.Visita;
import firstone.cliente.util.Parametros;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class PropietarioNegocio {
    
    private static final Logger log = Logger.getLogger(VisitaDAO.class);
    
    PropietarioDAO propietarioDAO;
    
    public PropietarioNegocio()
    {
        propietarioDAO = new PropietarioDAO();
    }
    
    public Propietario buscarPropietarioPorNombreOApellido(String cad)
    {
        return propietarioDAO.buscarPorNombreOApellido(cad);
    }

    public List<Propietario> obtenerPropietarios(String placa) {
        return propietarioDAO.obtenerPropietarios(placa);
    }
    
}
