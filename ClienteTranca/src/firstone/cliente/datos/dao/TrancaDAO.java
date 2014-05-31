/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Tranca;
import firstone.cliente.datos.model.Visita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class TrancaDAO {

    private static final Logger log = Logger.getLogger(TrancaDAO.class);

    public synchronized Tranca get() {
//        log.info("obtener Visita :: CI :" + ci);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Tranca tranca = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM tranca";
            st = con.prepareStatement(sql);

            if (st != null) {

                rs = st.executeQuery();

                if (rs.next()) {
                    tranca = new Tranca();
                    tranca.setId(rs.getInt("id"));
                    tranca.setDescripcion(rs.getString("descripcion"));
                    tranca.setTipo(rs.getString("tipo"));
                    tranca.setId_entorno(rs.getInt("id_entorno"));
                }else
                    log.error("No se pudo obtener la informacion de la tranca");
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
        return tranca;
    }

    public synchronized void insert(Tranca tranca) {
        log.info("Guardar TRANCA :: ID :" + tranca.getId() + " :: DESCRIPCION :" + tranca.getDescripcion() + " :: TIPO:" + tranca.getTipo());
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO tranca(id,descripcion,tipo,id_entorno) VALUES(?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setInt(1, tranca.getId());
                st.setString(2, tranca.getDescripcion());
                st.setString(3, tranca.getTipo());
                st.setInt(4, tranca.getId_entorno());
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
