/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.datos.dao;

import firstone.cliente.datos.conexion.ServiceProvider;
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
public class PropietarioDAO {

    private static final Logger log = Logger.getLogger(PropietarioDAO.class);

    public synchronized Propietario buscarPorNombreOApellido(String cad) {
//        log.info("obtener Visita :: CI :" + ci);
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        Propietario propietario = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "SELECT * FROM propietario WHERE (nombres||' '||apellidos) ilike ? OR (nombres||' '||apellidos) ilike ?";
            st = con.prepareStatement(sql);

            if (st != null) {
                st.setString(1, cad+"%");
                st.setString(2, "% "+cad+"%");
                rs = st.executeQuery();

                if (rs.next()) {
                    propietario = new Propietario();
                    
                    propietario.setApellidos(rs.getString("apellidos"));
                    propietario.setCi(rs.getString("ci"));
                    propietario.setFoto(rs.getBytes("foto"));
                    propietario.setNombres(rs.getString("nombres"));
                    propietario.setNro_licencia(rs.getString("nro_licencia"));
                    
                    
                    st.close();
                
                    sql = "SELECT * FROM telefono_propietario WHERE ci = ?";
                    st = con.prepareStatement(sql);

                    if (st != null)
                    {
                        st.setString(1, propietario.getCi());
                        rs = st.executeQuery();
                        
                        List<Integer> telefonos = new ArrayList<>();
                        while (rs.next())
                        {
                            telefonos.add(rs.getInt("telefono"));
                        }
                        propietario.setTelefonos(telefonos);
                    }
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
        return propietario;
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

    public synchronized void insert(String ci, String nombres, String apellidos, byte[] foto, String nro_licencia) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO propietario(ci,nombres,apellidos,foto,nro_licencia) VALUES(?,?,?,?,?)";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, ci);
                st.setString(2, nombres);
                st.setString(3, apellidos);
                st.setBytes(4, foto);
                st.setString(5, nro_licencia);
                
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
    
    public synchronized void update(String ci, String nombres, String apellidos, byte[] foto, String nro_licencia) {
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "UPDATE propietario SET nombres = ?, apellidos = ?, foto = ?, nro_licencia = ? WHERE ci = ?";

            st = con.prepareStatement(sql);
            if (st != null) {
                st.setString(1, nombres);
                st.setString(2, apellidos);
                st.setBytes(3, foto);
                st.setString(4, nro_licencia);
                st.setString(5, ci);
                
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

            String sql = "DELETE FROM propietario WHERE ci = ?";

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
