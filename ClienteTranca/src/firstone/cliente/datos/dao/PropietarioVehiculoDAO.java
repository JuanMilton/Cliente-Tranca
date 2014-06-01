/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class PropietarioVehiculoDAO {

    private static final Logger log = Logger.getLogger(PropietarioVehiculoDAO.class);

    public synchronized boolean existRelation(String ci, String placa) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        boolean result = false;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM propietario_vehiculo WHERE ci = ? AND placa = ?";
            st = con.prepareStatement(sql);

            if (st != null) {

                st.setString(1, ci);
                st.setString(2, placa);
                rs = st.executeQuery();

                if (rs.next()) {
                    result = true;
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
        return result;
    }

    public synchronized void insertRelation(String placa, String ci) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO propietario_vehiculo(ci,placa) VALUES(?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, ci);
                st.setString(2, placa);
                
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
    
    public synchronized void deleteRelation(String placa, String ci) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "DELETE FROM propietario_vehiculo WHERE ci = ? AND placa = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, ci);
                st.setString(2, placa);
                
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
