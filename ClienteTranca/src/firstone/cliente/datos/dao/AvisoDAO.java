/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Aviso;
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
public class AvisoDAO {

    private static final Logger log = Logger.getLogger(AvisoDAO.class);

    public synchronized List<Aviso> getAllOrderDesc() {
//        log.info("obtener Vehiculo :: RFID :" + rfid);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Aviso> advertencias = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM aviso ORDER BY id DESC LIMIT 50";
            st = con.prepareStatement(sql);

            if (st != null) {
                rs = st.executeQuery();

                advertencias = new ArrayList<>();
                while (rs.next()) {
                    
                    Aviso advertencia = new Aviso();
                    
                    advertencia.setDescripcion(rs.getString("descripcion"));
                    advertencia.setFecha_hora(new Date(rs.getTimestamp("fecha_hora").getTime()));
                    advertencia.setId_tranca(rs.getInt("ref_id_tranca"));
                    advertencia.setDe(rs.getString("de"));
                    
                    advertencias.add(advertencia);
                }
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
        return advertencias;
    }

    public void registrar(Aviso aviso) {
        log.info("Guardar Aviso :: DESCRIPCION :" + aviso.getDescripcion());
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO aviso(fecha_hora,descripcion,ref_id_tranca,de) VALUES(?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setTimestamp(1, new Timestamp(aviso.getFecha_hora().getTime()));
                st.setString(2, aviso.getDescripcion());
                st.setInt(3, aviso.getId_tranca());
                st.setString(4, aviso.getDe());
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

}
