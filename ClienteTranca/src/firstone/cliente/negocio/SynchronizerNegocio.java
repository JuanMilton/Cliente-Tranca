/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.negocio;

import firstone.cliente.datos.dao.BitacoraDAO;
import firstone.cliente.datos.dao.GuardiaDAO;
import firstone.cliente.datos.dao.IngresoSalidaDAO;
import firstone.cliente.datos.dao.IngresoSalidaVisitaDAO;
import firstone.cliente.datos.dao.PropietarioDAO;
import firstone.cliente.datos.dao.PropietarioVehiculoDAO;
import firstone.cliente.datos.dao.SynchronizerDAO;
import firstone.cliente.datos.dao.TelefonoDAO;
import firstone.cliente.datos.dao.TrancaDAO;
import firstone.cliente.datos.dao.VehiculoDAO;
import firstone.cliente.datos.dao.VehiculoVisitaDAO;
import firstone.cliente.datos.dao.VisitaDAO;
import firstone.cliente.datos.dao.VisitaVehiculoDAO;
import firstone.cliente.datos.model.Tranca;
import firstone.serializable.EstructureA;
import firstone.serializable.EstructureB;
import firstone.serializable.Guardia;
import firstone.serializable.Propietario;
import firstone.serializable.Synchronizer;
import firstone.serializable.Vehiculo;
import firstone.serializable.VehiculoVisita;
import firstone.serializable.Visita;
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
    TelefonoDAO telefonoDao;
    PropietarioDAO propietarioDao;
    VehiculoDAO vehiculoDao;
    PropietarioVehiculoDAO propietarioVehiculoDao;
    GuardiaDAO guardiaDao;
    TrancaDAO trancaDao;
    
    public SynchronizerNegocio()
    {
        synchronizerDao = new SynchronizerDAO();
        visitaVehiculoDao = new VisitaVehiculoDAO();
        vehiculoVisitaDao = new VehiculoVisitaDAO();
        bitacoraDao = new BitacoraDAO();
        visitaDao = new VisitaDAO();
        ingresoSalidaVisitaDao = new IngresoSalidaVisitaDAO();
        ingresoSalidaDao = new IngresoSalidaDAO();
        telefonoDao = new TelefonoDAO();
        propietarioDao = new PropietarioDAO();
        vehiculoDao = new VehiculoDAO();
        propietarioVehiculoDao = new PropietarioVehiculoDAO();
        guardiaDao = new GuardiaDAO();
        trancaDao = new TrancaDAO();
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

    public void procesarEstructureB(EstructureB trama, Tranca tranca) {
        Synchronizer sss = trama.getTask();
        if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_TELEFONO_PROPIETARIO))
        {
            if (sss.getTransaccion().equals("I"))
                telefonoDao.insert((String)trama.getObjeto(), Integer.parseInt(sss.getRef_id()));
            else
                telefonoDao.delete((String)trama.getObjeto(), Integer.parseInt(sss.getRef_id()));
        }else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_PROPIETARIO))
        {
            if (sss.getTransaccion().equals("I"))
            {
                Propietario pro = (Propietario)trama.getObjeto();
                propietarioDao.insert(pro.getCi(), pro.getNombres(), pro.getApellidos(), pro.getFoto(), pro.getNro_licencia());
            }else if (sss.getTransaccion().equals("M"))
            {
                Propietario pro = (Propietario)trama.getObjeto();
                propietarioDao.update(pro.getCi(), pro.getNombres(), pro.getApellidos(), pro.getFoto(), pro.getNro_licencia());
            }else //Eliminacion
            {
                propietarioDao.delete(sss.getRef_id());
            }
        } else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VEHICULO))
        {
            if (sss.getTransaccion().equals("I"))
            {
                Vehiculo pro = (Vehiculo)trama.getObjeto();
                vehiculoDao.insert(pro.getPlaca(),pro.getMarca(),pro.getModelo(),pro.getFoto(),pro.getRfid());
            }else if (sss.getTransaccion().equals("M"))
            {
                Vehiculo pro = (Vehiculo)trama.getObjeto();
                vehiculoDao.update(pro.getPlaca(),pro.getMarca(),pro.getModelo(),pro.getFoto(),pro.getRfid());
            }else //Eliminacion
            {
                vehiculoDao.delete(sss.getRef_id());
            }
        } else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_PROPIETARIO_VEHICULO))
        {
            String cads[] = sss.getRef_id().split(",");
            String placa = cads[0];
            String ci = cads[1];
            if (sss.getTransaccion().equals("I"))
            {
                if (! propietarioVehiculoDao.existRelation(ci, placa))
                    propietarioVehiculoDao.insertRelation(placa, ci);
            }else if (sss.getTransaccion().equals("E"))
            {
                if (propietarioVehiculoDao.existRelation(ci, placa))
                    propietarioVehiculoDao.deleteRelation(placa, ci);
            }
        } else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_GUARDIA))
        {
            if (sss.getTransaccion().equals("I"))
            {
                Guardia pro = (Guardia)trama.getObjeto();
                if (guardiaDao.get(pro.getCi()) == null)
                    guardiaDao.insert(pro.getCi(),pro.getNombre(),pro.getApellido(),pro.getPassword());
            }else if (sss.getTransaccion().equals("M"))
            {
                Guardia pro = (Guardia)trama.getObjeto();
                if (guardiaDao.get(pro.getCi()) != null)
                    guardiaDao.update(pro.getCi(),pro.getNombre(),pro.getApellido(),pro.getPassword());
            }else //Eliminacion
            {
                guardiaDao.delete(sss.getRef_id());
            }
        } else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VISITA))
        {
            if (sss.getTransaccion().equals("I"))
            {
                Visita pro = (Visita)trama.getObjeto();
                if (visitaDao.get(pro.getCi()) == null)
                    visitaDao.insert(pro.getCi(),pro.getNombres(),pro.getApellidos());
//                else
//                    System.out.println("VISITA EXISTENTE");
            }
        }  else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VEHICULO_VISITA))
        {
            if (sss.getTransaccion().equals("I"))
            {
                VehiculoVisita pro = (VehiculoVisita)trama.getObjeto();
                if (vehiculoVisitaDao.get(pro.getPlaca()) == null)
                    vehiculoVisitaDao.insert(pro.getPlaca(), pro.getMarca());
//                else
//                    System.out.println("VEHICULO VISITA EXISTENTE");
            }
        }  else if (sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_VISITA_VEHICULO))
        {
            if (sss.getTransaccion().equals("I"))
            {
                String cads[] = sss.getRef_id().split(",");
                String placa = cads[0];
                String ci = cads[1];
                if (! visitaVehiculoDao.existRelation(ci, placa))
                    visitaVehiculoDao.insertRelation(placa, ci);
//                else
//                    System.out.println("VISITA VEHICULO EXISTENTE");
            }
        } else if(sss.getTabla().equalsIgnoreCase(firstone.cliente.datos.model.Synchronizer.TABLE_TRANCA))
        {
            if (sss.getTransaccion().equals("M") && tranca.getId() == Integer.parseInt(sss.getRef_id()))
            {
                firstone.serializable.Tranca tran = (firstone.serializable.Tranca)trama.getObjeto();
                trancaDao.update(tran.getDescripcion(),tran.getTipo(),tran.getId());
            }
        }
    }
    
}
