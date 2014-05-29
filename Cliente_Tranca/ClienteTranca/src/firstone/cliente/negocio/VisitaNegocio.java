/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.model.Visita;
import firstone.cliente.util.Parametros;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class VisitaNegocio {
    
    private static final Logger log = Logger.getLogger(VisitaDAO.class);
    
    VisitaDAO visitaDao;
    
    public VisitaNegocio()
    {
        visitaDao = new VisitaDAO();
    }
    
    public void registrarVisita(Visita visita)
    {
        visitaDao.insert(visita);
    }
    
    public Visita buscarVisita(String ci)
    {
        return visitaDao.get(ci);
    }

    public String validarValores(Visita visita) {
        log.debug("Validando valores de Visita :: CI " + visita.getCi());
        if (! visita.getCi().matches(Parametros.ER_CI))
            return "El documento de identidad no tiene el formato correcto";
        if (! visita.getNombres().matches(Parametros.ER_NOMBRES))
            return "Los nombres no tienen el formato correcto";
        if (! visita.getApellidos().matches(Parametros.ER_APELLIDOS))
            return "Los apellidos no tienen el formato correcto";
        return null;
    }
    
}
