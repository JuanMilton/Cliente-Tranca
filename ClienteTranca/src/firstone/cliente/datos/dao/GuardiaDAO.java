/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
import firstone.cliente.datos.model.Guardia;
import firstone.cliente.datos.model.Propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class GuardiaDAO {

    private static final Logger log = Logger.getLogger(GuardiaDAO.class);

    public synchronized Guardia get(String ci, String password) {
//        log.info("obtener Visita :: CI :" + ci);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Guardia guardia = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM guardia WHERE ci = ? AND password = ?";
            st = con.prepareStatement(sql);

            if (st != null) {
                st.setString(1, ci);
                st.setString(2, password);
                rs = st.executeQuery();

                if (rs.next()) {
                    guardia = new Guardia();
                    guardia.setApellido(rs.getString("apellido"));
                    guardia.setNombre(rs.getString("nombre"));
                    guardia.setPassword(password);
                    guardia.setCi(ci);
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
        return guardia;
    }
    
    public synchronized Guardia get(String ci) {
//        log.info("obtener Visita :: CI :" + ci);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Guardia guardia = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM guardia WHERE ci = ?";
            st = con.prepareStatement(sql);

            if (st != null) {
                st.setString(1, ci);
                rs = st.executeQuery();

                if (rs.next()) {
                    guardia = new Guardia();
                    guardia.setApellido(rs.getString("apellido"));
                    guardia.setNombre(rs.getString("nombre"));
                    guardia.setPassword(rs.getString("password"));
                    guardia.setCi(ci);
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
        return guardia;
    }

    public synchronized void insert(String ci, String nombres, String apellidos, String pass) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO guardia(ci,nombre,apellido,password) VALUES(?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, ci);
                st.setString(2, nombres);
                st.setString(3, apellidos);
                st.setString(4, pass);
                
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
    
    public synchronized void update(String ci, String nombres, String apellidos, String pass) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "UPDATE guardia SET nombre = ?, apellido = ?, password = ? WHERE ci = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, nombres);
                st.setString(2, apellidos);
                st.setString(3, pass);
                st.setString(4, ci);
                
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
    
    public synchronized void delete(String ci) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "DELETE FROM guardia WHERE ci = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, ci);
                
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
