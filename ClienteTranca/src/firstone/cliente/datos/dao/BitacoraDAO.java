/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Bitacora;
import firstone.serializable.IngresoSalida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class BitacoraDAO {

    private static final Logger log = Logger.getLogger(BitacoraDAO.class);

        public synchronized void insert(Bitacora bitacora) {
        log.info("Guardar Bitacora :: GUARDIA :" + bitacora.getCi_guardia() + " :: ACCION :" + bitacora.getAccion());
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO bitacora(fecha_hora,accion,detalle,ref_ci_guardia) VALUES(?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setTimestamp(1, new Timestamp(bitacora.getFecha_hora().getTime()));
                st.setString(2, bitacora.getAccion());
                st.setString(3, bitacora.getDetalle());
                st.setString(4, bitacora.getCi_guardia());
                
                st.execute();
            }
        } catch (SQLException e) {
            log.error("Error al realizar la insercion en la base de datos", e);
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar el Statement", e);
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar la conexion a la base de datos", e);
            }
        }
    }
        
    public List<firstone.serializable.Bitacora> obtenerBitacora()
    {
        log.info("Obteniendo todas las tareas realizadas por los guardias");
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<firstone.serializable.Bitacora> ss = new ArrayList<>();

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM bitacora";
            st = con.prepareStatement(sql);

            if (st != null) {
                rs = st.executeQuery();

                while (rs.next()) {
                    
                    firstone.serializable.Bitacora s = new firstone.serializable.Bitacora();
                    s.setFecha_hora(rs.getTimestamp("fecha_hora").getTime());
                    s.setAccion(rs.getString("accion"));
                    s.setDetalle(rs.getString("detalle"));
                    s.setRef_ci_guardia(rs.getString("ref_ci_guardia"));
                    
                    ss.add(s);
                }
            }
            st.close();
            
            sql = "TRUNCATE TABLE bitacora";
            st = con.prepareStatement(sql);
            if (st != null)
            {
                st.execute();
            }

        } catch (SQLException e) {
            log.error("Error al consultar a la base de datos", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar el ResultSet", e);
            }

            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar el Statement", e);
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Error al cerrar la conexion a la base de datos", e);
            }
        }
        return ss;
    }
}
