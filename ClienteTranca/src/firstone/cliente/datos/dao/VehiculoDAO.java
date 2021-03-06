/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Vehiculo;
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
public class VehiculoDAO {

    private static final Logger log = Logger.getLogger(VehiculoDAO.class);

    public synchronized Vehiculo get(int rfid) {
//        log.info("obtener Vehiculo :: RFID :" + rfid);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Vehiculo vehiculo = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM vehiculo WHERE rfid = ?";
            st = con.prepareStatement(sql);

            if (st != null) {

                st.setInt(1, rfid);
                rs = st.executeQuery();

                if (rs.next()) {
                    vehiculo = new Vehiculo();
                    vehiculo.setFoto(rs.getBytes("foto"));
                    vehiculo.setMarca(rs.getString("marca"));
                    vehiculo.setModelo(rs.getString("modelo"));
                    vehiculo.setPlaca(rs.getString("placa"));
                    vehiculo.setRfid(rfid);
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
        return vehiculo;
    }
    
    
    public synchronized void insert(String placa, String marca, String modelo, byte[] foto, int rfid) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO vehiculo(placa,marca,modelo,foto,rfid) VALUES(?,?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, placa);
                st.setString(2, marca);
                st.setString(3, modelo);
                st.setBytes(4, foto);
                st.setInt(5, rfid);
                
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
    
    public synchronized void update(String placa, String marca, String modelo, byte[] foto, int rfid) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "UPDATE vehiculo SET marca = ?, modelo = ?, foto = ?, rfid = ? WHERE placa = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, marca);
                st.setString(2, modelo);
                st.setBytes(3, foto);
                st.setInt(4, rfid);
                st.setString(5, placa);
                
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
    
    public synchronized void delete(String placa) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "DELETE FROM vehiculo WHERE placa = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, placa);
                
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
