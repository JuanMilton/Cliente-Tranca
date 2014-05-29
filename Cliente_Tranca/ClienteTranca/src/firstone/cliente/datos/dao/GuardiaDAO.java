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
    
    public synchronized List<Propietario> obtenerPropietarios(String placa) {
        log.info("obtener Propietarios :: PLACA :" + placa);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        List<Propietario> propietarios = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM propietario WHERE ci in (SELECT ci FROM propietario_vehiculo WHERE placa = ?)";
            st = con.prepareStatement(sql);

            if (st != null) {
                st.setString(1, placa);
                rs = st.executeQuery();
                
                propietarios = new ArrayList<>();
                
                while (rs.next()) {
                    
                    Propietario propietario = new Propietario();
                    
                    propietario.setApellidos(rs.getString("apellidos"));
                    propietario.setCi(rs.getString("ci"));
                    propietario.setFoto(rs.getBytes("foto"));
                    propietario.setNombres(rs.getString("nombres"));
                    propietario.setNro_licencia(rs.getString("nro_licencia"));
                    
                    
//                    st.close();
                
                    sql = "SELECT * FROM telefono_propietario WHERE ci = ?";
                    st = con.prepareStatement(sql);

                    if (st != null)
                    {
                        st.setString(1, propietario.getCi());
                        rs2 = st.executeQuery();
                        
                        List<Integer> telefonos = new ArrayList<>();
                        while (rs2.next())
                        {
                            telefonos.add(rs2.getInt("telefono"));
                        }
                        propietario.setTelefonos(telefonos);
                    }
                    
                    propietarios.add(propietario);
                    
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
        return propietarios;
    }

//    public synchronized void insert(Visita visita) {
//        log.info("Guardar visita :: CI " + visita.getCi());
//        Connection con = null;
//        PreparedStatement st = null;
//
//        try {
//            con = ServiceProvider.openConnection();
//
//            String sql = "INSERT INTO visita(ci,nombres,apellidos) VALUES(?,?,?)";
//
//            st = con.prepareStatement(sql);
//            if (st != null) {
//                st.setString(1, visita.getCi());
//                st.setString(2, visita.getNombres());
//                st.setString(3, visita.getApellidos());
//                
//                st.execute();
//            }
//        } catch (SQLException e) {
//            log.error("Error al realizar la insercion en la base de datos", e);
//        } finally {
//            try {
//                if (st != null) {
//                    st.close();
//                }
//            } catch (SQLException e) {
//                log.error("Error al cerrar el Statement", e);
//            }
//
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (SQLException e) {
//                log.error("Error al cerrar la conexion a la base de datos", e);
//            }
//        }
//    }
}
