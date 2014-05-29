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

}
