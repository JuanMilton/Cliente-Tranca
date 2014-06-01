/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.BitacoraDAO;
import firstone.cliente.datos.dao.IngresoSalidaDAO;
import firstone.cliente.datos.dao.IngresoSalidaVisitaDAO;
import firstone.cliente.datos.dao.SynchronizerDAO;
import firstone.cliente.datos.dao.VehiculoVisitaDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.dao.VisitaVehiculoDAO;
import firstone.serializable.EstructureA;
import firstone.serializable.EstructureB;
import firstone.serializable.Synchronizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milton
 */
public class SynchronizerNegocio {
    
    SynchronizerDAO synchronizerDao;
    VisitaDAO visitaDao;
    VehiculoVisitaDAO vehiculoVisitaDao;
    VisitaVehiculoDAO visitaVehiculoDao;
    BitacoraDAO bitacoraDao;
    IngresoSalidaDAO ingresoSalidaDao;
    IngresoSalidaVisitaDAO ingresoSalidaVisitaDao;
    
    public SynchronizerNegocio()
    {
        synchronizerDao = new SynchronizerDAO();
        visitaVehiculoDao = new VisitaVehiculoDAO();
        vehiculoVisitaDao = new VehiculoVisitaDAO();
        bitacoraDao = new BitacoraDAO();
        visitaDao = new VisitaDAO();
        ingresoSalidaVisitaDao = new IngresoSalidaVisitaDAO();
        ingresoSalidaDao = new IngresoSalidaDAO();
    }
    
    public List<Object> obtenerDatosASubir()
    {
        List<Object> resultado = new ArrayList<>();
        
        List<firstone.cliente.datos.model.Synchronizer> ss = synchronizerDao.obtenerTransacciones();
        for (firstone.cliente.datos.model.Synchronizer s : ss)
        {
            Synchronizer sz = new Synchronizer();
            sz.setRef_id(s.getRef_id());
            sz.setTabla(s.getTabla());
            sz.setTransaccion(s.getTransaccion());
            
            EstructureB estructureB = new EstructureB();
            estructureB.setTask(sz);
            
            if (s.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VISITA)) // TRANSACCION SOBRE LA TABLA VISITA
            {
                estructureB.setObjeto(visitaDao.getVisitaSerializable(s.getRef_id()));
            }else if (s.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VEHICULO_VISITA)) // TRANSACCION SOBRE LA TABLA VEHICULO_VISITA
            {
                estructureB.setObjeto(vehiculoVisitaDao.getVehiculoVisitaSerializable(s.getRef_id()));
            }else if (s.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VISITA_VEHICULO)) // TRANSACCION SOBRE LA TABLA VISITA_VEHICULO
            {
                estructureB.setObjeto(s.getRef_id());
            }
            resultado.add(estructureB);
        }
        
        List<firstone.serializable.Bitacora> bitacoras = bitacoraDao.obtenerBitacora();
        for (firstone.serializable.Bitacora b : bitacoras)
        {
            EstructureA estructureA = new EstructureA();
            estructureA.setObjeto(b);
            resultado.add(estructureA);
        }
        List<firstone.serializable.IngresoSalida> is = ingresoSalidaDao.obtenerIngresosSalidas();
        for (firstone.serializable.IngresoSalida i : is)
        {
            EstructureA estructureA = new EstructureA();
            estructureA.setObjeto(i);
            resultado.add(estructureA);
        }
        List<firstone.serializable.IngresoSalidaVisita> isv = ingresoSalidaVisitaDao.obtenerIngresosSalidasVisita();
        for (firstone.serializable.IngresoSalidaVisita iv : isv)
        {
            EstructureA estructureA = new EstructureA();
            estructureA.setObjeto(iv);
            resultado.add(estructureA);
        }
        
        return resultado;
    }
    
    
    public static void main(String[] args) {
        SynchronizerNegocio sn = new SynchronizerNegocio();
        System.out.println(sn.obtenerDatosASubir().size());
    }
    
}
