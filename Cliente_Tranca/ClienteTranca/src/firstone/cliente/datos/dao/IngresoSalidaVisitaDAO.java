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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Milton
 */
public class IngresoSalidaVisitaDAO {

    private static final Logger log = Logger.getLogger(IngresoSalidaVisitaDAO.class);

        public synchronized void insert(String tipo, Date fecha_hora, int id_tranca, String placa) {
        log.info("Guardar IngresoSalida de Visita :: PLACA :" + placa + " :: TIPO :" + tipo + " :: FECHA_HORA :" + fecha_hora + " :: TRANCA :" + id_tranca);
        Connection con = null;
        PreparedStatement st = null;

        try {
            con = ServiceProvider.openConnection();

            String sql = "INSERT INTO ingreso_salida_visita(tipo,fecha_hora,ref_id_tranca,ref_placa_vehiculo_visita) VALUES(?,?,?,?)";

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
}
