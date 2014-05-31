/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.SynchronizerDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.model.Synchronizer;
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
    SynchronizerDAO szr;
    
    public VisitaNegocio()
    {
        visitaDao = new VisitaDAO();
        szr = new SynchronizerDAO();
    }
    
    public void registrarVisita(Visita visita)
    {
        visitaDao.insert(visita);
        
        Synchronizer s = new Synchronizer();
        s.setRef_id(visita.getCi());
        s.setTabla(Synchronizer.TABLE_VISITA);
        s.setTransaccion("I");
        szr.insert(s);
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
