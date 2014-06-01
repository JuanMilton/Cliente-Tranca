/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Synchronizer;
import firstone.serializable.IngresoSalida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class IngresoSalidaDAO {

    private static final Logger log = Logger.getLogger(IngresoSalidaDAO.class);

        public synchronized void insert(String tipo, Date fecha_hora, int id_tranca, String placa) {
        log.info("Guardar IngresoSalida de Vehiculo :: PLACA :" + placa + " :: TIPO :" + tipo + " :: FECHA_HORA :" + fecha_hora);
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO ingreso_salida(tipo,fecha_hora,ref_id_tranca,ref_placa_vehiculo) VALUES(?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, tipo);
                st.setTimestamp(2, new Timestamp(fecha_hora.getTime()));
                st.setInt(3, id_tranca);
                st.setString(4, placa);
                
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
        
        
    public List<IngresoSalida> obtenerIngresosSalidas()
    {
        log.info("Obteniendo todas los ingresos y salidas realizados");
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<IngresoSalida> ss = new ArrayList<>();

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM ingreso_salida";
            st = con.prepareStatement(sql);

            if (st != null) {
                rs = st.executeQuery();

                while (rs.next()) {
                    
                    IngresoSalida s = new IngresoSalida();
                    s.setFecha_hora(rs.getTimestamp("fecha_hora").getTime());
                    s.setId_tranca(rs.getInt("ref_id_tranca"));
                    s.setPlaca(rs.getString("ref_placa_vehiculo"));
                    s.setTipo(rs.getString("tipo"));
                    
                    ss.add(s);
                }
            }
            st.close();
            
            sql = "TRUNCATE TABLE ingreso_salida";
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
