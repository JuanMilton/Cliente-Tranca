/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstone.cliente.presentacion;

import com.firstonesoft.client.Client;
import com.firstonesoft.client.event.EventClient;
import com.firstonesoft.client.util.ObjectUtil;
import firstone.cliente.ccs.Accion;
import firstone.cliente.ccs.InterfazEnvioCliente;
import firstone.cliente.circuito.EventListener;
import firstone.cliente.circuito.negocio.InteraccionNegocio;
import firstone.cliente.circuito.proceso.ProcesoRFID;
import firstone.cliente.datos.model.Aviso;
import firstone.cliente.datos.model.Alarma;
import firstone.cliente.datos.model.Bitacora;
import firstone.cliente.datos.model.Guardia;
import firstone.cliente.negocio.IngresoSalidaVisitaNegocio;
import firstone.cliente.datos.model.Propietario;
import firstone.cliente.datos.model.Tranca;
import firstone.cliente.datos.model.Vehiculo;
import firstone.cliente.datos.model.VehiculoVisita;
import firstone.cliente.datos.model.Visita;
import firstone.cliente.negocio.AvisoNegocio;
import firstone.cliente.negocio.BitacoraNegocio;
import firstone.cliente.negocio.PropietarioNegocio;
import firstone.cliente.negocio.SynchronizerNegocio;
import firstone.cliente.negocio.TrancaNegocio;
import firstone.cliente.negocio.VehiculoNegocio;
import firstone.cliente.negocio.VehiculoVisitaNegocio;
import firstone.cliente.negocio.VisitaNegocio;
import firstone.cliente.negocio.VisitaVehiculoNegocio;
import firstone.cliente.util.Parametros;
import firstone.cliente.util.Sincronizacion;
import firstone.serializable.Contrato;
import firstone.serializable.EstructureB;
import firstone.serializable.Notificacion;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Milton
 */
public class Main extends javax.swing.JFrame implements EventClient {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Main.class);
    
    
    VisitaNegocio visitaNegocio;
    VehiculoVisitaNegocio vehiculoVisitaNegocio;
    VisitaVehiculoNegocio visitaVehiculoNegocio;

    PropietarioNegocio propietarioNegocio;
    VehiculoNegocio vehiculoNegocio;
    IngresoSalidaVisitaNegocio ingresoSalidaVisitaNegocio;
    TrancaNegocio trancaNegocio;
    BitacoraNegocio bitacoraNegocio;
    AvisoNegocio avisoNegocio;
    InteraccionNegocio circuitoNegocio;
    InterfazEnvioCliente interfazEnvioCliente;

    List<Alarma> alarmas;
    Tranca tranca;
    Guardia guardia;
    ProcesoRFID procesoRFID;

    Client cliente;
    boolean respondido;
    /**
     * Creates new form Main
     */
    public Main(Guardia guardia) {
        initComponents();

        DOMConfigurator.configure("etc" + File.separator + "log4j.xml");
        alarmas = new ArrayList<>();

//        Alarma a1 = new Alarma();
//        a1.setEmisor("Tranca Norte");
//        a1.setPrioridad("Rojo");
//        Alarma a2 = new Alarma();
//        a2.setEmisor("Juan Pablo Dias Rojas");
//        a2.setPrioridad("Amarillo");
//        alarmas.add(a1);
//        alarmas.add(a2);

        initializeValues();
        
        this.guardia = guardia;
        visitaNegocio = new VisitaNegocio();
        vehiculoVisitaNegocio = new VehiculoVisitaNegocio();
        visitaVehiculoNegocio = new VisitaVehiculoNegocio();

        propietarioNegocio = new PropietarioNegocio();
        vehiculoNegocio = new VehiculoNegocio();
        ingresoSalidaVisitaNegocio = new IngresoSalidaVisitaNegocio();
        trancaNegocio = new TrancaNegocio();
        circuitoNegocio = new InteraccionNegocio();
        bitacoraNegocio = new BitacoraNegocio();
        avisoNegocio = new AvisoNegocio();
        
        cliente = new Client(Parametros.CORE_IP, Parametros.CORE_PORT);
        cliente.setEventClient(this);
        try {
            cliente.connectClosed(guardia.getCi());
        } catch (IOException ex) {
            log.error("Error, no se pudo conectar con el servidor",ex);
        }
        interfazEnvioCliente = new InterfazEnvioCliente(cliente,guardia.getCi());

        tranca = trancaNegocio.obtenerTranca();
    }

    private void initializeValues() {
        cargarAdvertencias();
        cargarAlarmas();
        
        procesoRFID = new ProcesoRFID(new EventListener() {

            @Override
            public void llegoVehiculo(int rfid) {
                registrarIngresoSalidaVehiculo(rfid);
            }
        });
        procesoRFID.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtext_placa_vehiculo = new javax.swing.JTextField();
        jtext_marca_vehiculo = new javax.swing.JTextField();
        jtext_modelo_vehiculo = new javax.swing.JTextField();
        jpanel_foto_vehiculo = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtext_ci_propietario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlist_telefonos_propietario = new javax.swing.JList();
        jtext_nombre_propietario = new javax.swing.JTextField();
        jtext_apellido_propietario = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jtext_licencia_propietario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        text_ci_propietario = new javax.swing.JTextField();
        text_nombre_apellido_propietario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        list_telefonos_propietarios = new javax.swing.JList();
        panel_foto_propietario = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        text_ci_visita = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        text_nombres_visita = new javax.swing.JTextField();
        text_apellidos_visita = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        text_placa_visita = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        text_marca_visita = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jboton_alarma_roja = new javax.swing.JButton();
        jboton_apagar_alarma = new javax.swing.JButton();
        jboton_alarma_verde = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtable_alarmas = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_advertencias = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("PROPIETARIOS");

        jButton3.setText("Alarma Roja");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowsClosing(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DATOS DEL VEHÍCULO");

        jLabel3.setText("Placa");

        jLabel4.setText("Marca");

        jLabel5.setText("Modelo");

        jtext_placa_vehiculo.setEnabled(false);

        jtext_marca_vehiculo.setEnabled(false);

        jtext_modelo_vehiculo.setEnabled(false);

        jpanel_foto_vehiculo.setBackground(new java.awt.Color(51, 204, 255));

        jButton7.setText("Emular");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpanel_foto_vehiculoLayout = new javax.swing.GroupLayout(jpanel_foto_vehiculo);
        jpanel_foto_vehiculo.setLayout(jpanel_foto_vehiculoLayout);
        jpanel_foto_vehiculoLayout.setHorizontalGroup(
            jpanel_foto_vehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_foto_vehiculoLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(jpanel_foto_vehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel_foto_vehiculoLayout.setVerticalGroup(
            jpanel_foto_vehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_foto_vehiculoLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 172, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel_foto_vehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtext_modelo_vehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jtext_marca_vehiculo)
                            .addComponent(jtext_placa_vehiculo))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtext_placa_vehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtext_marca_vehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtext_modelo_vehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpanel_foto_vehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("PROPIETARIOS");

        jtext_ci_propietario.setEnabled(false);

        jLabel6.setText("Documento de Identidad");

        jLabel7.setText("Nombre(s)");

        jLabel8.setText("Apellido(s)");

        jLabel10.setText("Teléfono(s)");

        jlist_telefonos_propietario.setValueIsAdjusting(true);
        jScrollPane1.setViewportView(jlist_telefonos_propietario);

        jtext_nombre_propietario.setEnabled(false);

        jtext_apellido_propietario.setEnabled(false);

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 139, Short.MAX_VALUE)
        );

        jLabel14.setText("Número de Licencia");

        jtext_licencia_propietario.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtext_licencia_propietario)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jtext_nombre_propietario)
                            .addComponent(jtext_ci_propietario)
                            .addComponent(jtext_apellido_propietario))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtext_ci_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtext_nombre_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jtext_apellido_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtext_licencia_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel10)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ingreso y Salida de Vehículos", jPanel1);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("PROPIETARIO A VISITAR");

        jLabel26.setText("Nombre o Apellido");

        jLabel27.setText("Documento de Identidad");

        text_nombre_apellido_propietario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onKeyReleasedPropietarioVisitas(evt);
            }
        });

        jLabel13.setText("Teléfono(s)");

        list_telefonos_propietarios.setValueIsAdjusting(true);
        jScrollPane4.setViewportView(list_telefonos_propietarios);

        panel_foto_propietario.setBackground(new java.awt.Color(51, 204, 255));

        javax.swing.GroupLayout panel_foto_propietarioLayout = new javax.swing.GroupLayout(panel_foto_propietario);
        panel_foto_propietario.setLayout(panel_foto_propietarioLayout);
        panel_foto_propietarioLayout.setHorizontalGroup(
            panel_foto_propietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel_foto_propietarioLayout.setVerticalGroup(
            panel_foto_propietarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel28.setText("Foto");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_nombre_apellido_propietario)
                            .addComponent(text_ci_propietario)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel28))
                        .addGap(73, 73, 73)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_foto_propietario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(text_nombre_apellido_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_ci_propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(panel_foto_propietario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jLabel28)
                        .addGap(94, 94, 94))))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("DATOS DE LA VISITA");

        text_ci_visita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onKeyReleased(evt);
            }
        });

        jLabel21.setText("Documento de Identidad");

        jLabel22.setText("Nombre(s)");

        jLabel23.setText("Apellido(s)");

        jLabel17.setText("Placa");

        text_placa_visita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onKeyReleasedPlaca(evt);
            }
        });

        jLabel18.setText("Marca");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("DATOS DEL VEHÍCULO DE LA VISITA");

        jButton6.setText("Registrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addGap(0, 152, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_apellidos_visita)
                            .addComponent(text_nombres_visita)
                            .addComponent(text_ci_visita)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_placa_visita)
                            .addComponent(text_marca_visita)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_ci_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(text_nombres_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(text_apellidos_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jLabel19)
                .addGap(4, 4, 4)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_placa_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(text_marca_visita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Visitas", jPanel2);

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jboton_alarma_roja.setText("Alarma Roja");
        jboton_alarma_roja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jboton_alarma_rojaActionPerformed(evt);
            }
        });

        jboton_apagar_alarma.setText("Alarma Amarilla");
        jboton_apagar_alarma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jboton_apagar_alarmaActionPerformed(evt);
            }
        });

        jboton_alarma_verde.setText("Alarma Verde");
        jboton_alarma_verde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jboton_alarma_verdeActionPerformed(evt);
            }
        });

        jButton1.setText("Dejar Pasar Vehículo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton9.setText("Actualizar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setText("Avisar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jboton_alarma_roja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jboton_apagar_alarma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jboton_alarma_verde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jboton_alarma_roja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jboton_apagar_alarma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jboton_alarma_verde)
                .addContainerGap())
        );

        jtable_alarmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emisor", "Prioridad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtable_alarmas);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("ALARMAS");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 197, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jtable_advertencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "De", "Descripción", "Fecha y Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_advertencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtable_advertencias);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("AVISOS");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Ingreso y Salida de Vehículos");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Visita visita = new Visita();

        visita.setCi(text_ci_visita.getText());
        visita.setNombres(text_nombres_visita.getText());
        visita.setApellidos(text_apellidos_visita.getText());

        String msg = visitaNegocio.validarValores(visita);
        if (msg == null) {
            VehiculoVisita vv = new VehiculoVisita();
            vv.setPlaca(text_placa_visita.getText());
            vv.setMarca(text_marca_visita.getText());

            msg = vehiculoVisitaNegocio.validarValores(vv);

            if (msg == null) {
                if (vehiculoVisitaNegocio.buscarVehiculoVisita(text_placa_visita.getText()) == null) {
                    vehiculoVisitaNegocio.registrarVehiculoVisita(vv);
                }
                if (visitaNegocio.buscarVisita(text_ci_visita.getText()) == null) {
                    visitaNegocio.registrarVisita(visita);
                }
                if (!visitaVehiculoNegocio.existeRelacion(vv.getPlaca(), visita.getCi())) {
                    visitaVehiculoNegocio.insertarRelacion(vv.getPlaca(), visita.getCi());
                }

                ingresoSalidaVisitaNegocio.registrarIngresoSalida(tranca.getTipo(), new Date(), tranca.getId(), text_placa_visita.getText()); ////// SE REALIZA EL REGISTRO DEL INGRESO DE LA VISITA
                
                registrarBitacoraGuardia(Bitacora.ACCION_REGISTRO_VISITA,"Ingreso la visita con CI : " + visita.getCi()+" con el vehiculo de PLACA : " + vv.getPlaca());
                
                JOptionPane.showMessageDialog(rootPane, "Registro realizado correctamente", "Registro de Visita", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Por favor, corrija los valores ingresados del vehículo de la VISITA\n" + msg, "Registro de Vehiculo de Visita", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Por favor, corrija los valores ingresados de la VISITA\n" + msg, "Registro de Visita", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void onKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyReleased
        Visita visita = visitaNegocio.buscarVisita(text_ci_visita.getText());
        if (visita != null) {
            text_nombres_visita.setText(visita.getNombres());
            text_apellidos_visita.setText(visita.getApellidos());
        } else {
            text_nombres_visita.setText("");
            text_apellidos_visita.setText("");
        }
    }//GEN-LAST:event_onKeyReleased

    private void onKeyReleasedPlaca(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyReleasedPlaca
        VehiculoVisita vehiculoVisita = vehiculoVisitaNegocio.buscarVehiculoVisita(text_placa_visita.getText());
        if (vehiculoVisita != null) {
            text_marca_visita.setText(vehiculoVisita.getMarca());
        } else {
            text_marca_visita.setText("");
        }
    }//GEN-LAST:event_onKeyReleasedPlaca

    private void onKeyReleasedPropietarioVisitas(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyReleasedPropietarioVisitas
        if (((evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90) || evt.getKeyCode() == 0) && text_nombre_apellido_propietario.getText().trim().length() > 0) {
            Propietario propietario = propietarioNegocio.buscarPropietarioPorNombreOApellido(text_nombre_apellido_propietario.getText().trim());
            if (propietario != null) {
                String aux = text_nombre_apellido_propietario.getText();
                int index = text_nombre_apellido_propietario.getText().length();
                text_nombre_apellido_propietario.setText(propietario.getNombres() + " " + propietario.getApellidos());
                text_ci_propietario.setText(propietario.getCi());

                DefaultListModel<Integer> telefonos = new DefaultListModel<>();
                for (Integer telf : propietario.getTelefonos()) {
                    telefonos.addElement(telf);
                }
                list_telefonos_propietarios.setModel(telefonos);

                int index2 = text_nombre_apellido_propietario.getText().toUpperCase().indexOf(" " + aux.toUpperCase());
                if (index2 > 0) {
                    index = index2 + 2;
                }
                text_nombre_apellido_propietario.setSelectionStart(index);
                text_nombre_apellido_propietario.setSelectionEnd(text_nombre_apellido_propietario.getText().length());
                //////// FALTA MOSTRAR LA FOTO DEL PROPIETARIO

            }
        }
    }//GEN-LAST:event_onKeyReleasedPropietarioVisitas

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        registrarIngresoSalidaVehiculo(Integer.parseInt(jTextField7.getText()));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        LanzarAviso frame = new LanzarAviso(tranca,guardia,cliente);
        frame.setTitle("Avisar");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        interfazEnvioCliente.actualizar(this.tranca.getId_entorno());
        interfazEnvioCliente.solicitarSincronizacion(tranca.getId_entorno());
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        circuitoNegocio.dejarPasarVehiculo();
        
        registrarBitacoraGuardia(Bitacora.ACCION_DEJAR_PASAR_VEHICULO, "Se dejo pasar un vehiculo de manera directa sin registro, fecha y hora :"+(new Date()) );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jboton_alarma_rojaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jboton_alarma_rojaActionPerformed
        circuitoNegocio.activarAlarma();

        Alarma alarma = new Alarma();
        alarma.setEmisor(tranca.getDescripcion());
        alarma.setPrioridad(firstone.cliente.circuito.model.Alarma.ROJO);

        if (interfazEnvioCliente.lanzarAlarma(alarma,tranca.getId_entorno()))
            registrarBitacoraGuardia(Bitacora.ACCION_LANZAR_ALARMA, "Activar una alarma ROJA a fecha y hora :"+(new Date()) );
        else
            JOptionPane.showMessageDialog(rootPane, "Problemas al lanzar la alarma, no se pudo alertar", "Lanzar Alarma", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jboton_alarma_rojaActionPerformed

    private void jboton_apagar_alarmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jboton_apagar_alarmaActionPerformed
        if (jboton_apagar_alarma.getText().equalsIgnoreCase("Alarma Amarilla")) {
            
            circuitoNegocio.activarAlarma();

            Alarma alarma = new Alarma();
            alarma.setEmisor(tranca.getDescripcion());
            alarma.setPrioridad(firstone.cliente.circuito.model.Alarma.AMARILLO);

            if (interfazEnvioCliente.lanzarAlarma(alarma,tranca.getId_entorno()))
                registrarBitacoraGuardia(Bitacora.ACCION_LANZAR_ALARMA, "Activar una alarma AMARILLA a fecha y hora :"+(new Date()) );
            else
                JOptionPane.showMessageDialog(rootPane, "Problemas al lanzar la alarma, no se pudo alertar", "Lanzar Alarma", JOptionPane.WARNING_MESSAGE);
        } else {
            circuitoNegocio.apagarAlarma();
            jboton_alarma_roja.setVisible(true);
            jboton_alarma_verde.setVisible(true);
            jboton_apagar_alarma.setText("Alarma Amarilla");
        }
    }//GEN-LAST:event_jboton_apagar_alarmaActionPerformed

    private void jboton_alarma_verdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jboton_alarma_verdeActionPerformed

        circuitoNegocio.activarAlarma();

        Alarma alarma = new Alarma();
        alarma.setEmisor(tranca.getDescripcion());
        alarma.setPrioridad(firstone.cliente.circuito.model.Alarma.VERDE);

        if (interfazEnvioCliente.lanzarAlarma(alarma,tranca.getId_entorno()))
            registrarBitacoraGuardia(Bitacora.ACCION_LANZAR_ALARMA, "Activar una alarma VERDE a fecha y hora :"+(new Date()) );
        else
            JOptionPane.showMessageDialog(rootPane, "Problemas al lanzar la alarma, no se pudo alertar", "Lanzar Alarma", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_jboton_alarma_verdeActionPerformed

    private void onWindowsClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowsClosing
        procesoRFID.detenerProceso();
        JFrame.setDefaultLookAndFeelDecorated(true);
        LogIn frame = new LogIn();
        frame.setTitle("Ingreso - Sistema de Control de Acceso Vehicular");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        try {
            if (cliente.isConnected())
                cliente.disconect();
        } catch (IOException ex) {
            log.error("Error al cerrar la conexion con el Server",ex);
        }
    }//GEN-LAST:event_onWindowsClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(new Guardia()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JButton jboton_alarma_roja;
    private javax.swing.JButton jboton_alarma_verde;
    private javax.swing.JButton jboton_apagar_alarma;
    private javax.swing.JList jlist_telefonos_propietario;
    private javax.swing.JPanel jpanel_foto_vehiculo;
    private javax.swing.JTable jtable_advertencias;
    private javax.swing.JTable jtable_alarmas;
    private javax.swing.JTextField jtext_apellido_propietario;
    private javax.swing.JTextField jtext_ci_propietario;
    private javax.swing.JTextField jtext_licencia_propietario;
    private javax.swing.JTextField jtext_marca_vehiculo;
    private javax.swing.JTextField jtext_modelo_vehiculo;
    private javax.swing.JTextField jtext_nombre_propietario;
    private javax.swing.JTextField jtext_placa_vehiculo;
    private javax.swing.JList list_telefonos_propietarios;
    private javax.swing.JPanel panel_foto_propietario;
    private javax.swing.JTextField text_apellidos_visita;
    private javax.swing.JTextField text_ci_propietario;
    private javax.swing.JTextField text_ci_visita;
    private javax.swing.JTextField text_marca_visita;
    private javax.swing.JTextField text_nombre_apellido_propietario;
    private javax.swing.JTextField text_nombres_visita;
    private javax.swing.JTextField text_placa_visita;
    // End of variables declaration//GEN-END:variables

    private void registrarIngresoSalidaVehiculo(int rfid) {
        Vehiculo vehiculo = vehiculoNegocio.obtenerVehiculoRFID(rfid);
        if (vehiculo != null) {
            List<Propietario> propietarios = propietarioNegocio.obtenerPropietarios(vehiculo.getPlaca());

            enviarNotificaciones(propietarios, vehiculo);
            
            jtext_placa_vehiculo.setText(vehiculo.getPlaca());
            jtext_marca_vehiculo.setText(vehiculo.getMarca());
            jtext_modelo_vehiculo.setText(vehiculo.getModelo());
            //FALTA FOTO DEL VEHICULO

            for (Propietario propietario : propietarios) {
                jtext_ci_propietario.setText(propietario.getCi());
                jtext_nombre_propietario.setText(propietario.getNombres());
                jtext_apellido_propietario.setText(propietario.getApellidos());
                jtext_licencia_propietario.setText(propietario.getNro_licencia());

                DefaultListModel<Integer> telefonos = new DefaultListModel<>();
                for (Integer telf : propietario.getTelefonos()) {
                    telefonos.addElement(telf);
                }
                jlist_telefonos_propietario.setModel(telefonos);
                
                ///FALTA LA FOTO DE LOS PROPIETARIOS

            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Se reconocio vehiculo con la Etiqueta de FIRSTONE que no esta registrado en este lugar", "Vehiculo reconocido", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void enviarNotificaciones(List<Propietario> propietarios, Vehiculo veh) {
        respondido = false;
        
        Notificacion notifi = new Notificacion();
        notifi.setMarca(veh.getMarca());
        notifi.setPlaca(veh.getPlaca());
        notifi.setAccion(tranca.getTipo());
        notifi.setCi_guardia(guardia.getCi());
        for (Propietario p : propietarios)
        {
            notifi.setCi(p.getCi());
            if (! interfazEnvioCliente.lanzarNotificacion(notifi))
            {
                log.error("No se pudo enviar la notificacion al propietario CI: " + notifi.getCi() + " del vehiculo PLACA :" + notifi.getPlaca());
                circuitoNegocio.dejarPasarVehiculo();
                respondido = true;
            }
            else
                log.info("Se envio la notificacion al propietario CI: " + notifi.getCi() + " del vehiculo PLACA :" + notifi.getPlaca());
        }
        
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    Thread.sleep(11000);
                }catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                
                if (! respondido)
                    circuitoNegocio.dejarPasarVehiculo();
            }
        });
        t.start();
    }

    private void cargarAdvertencias() {
        AvisoNegocio advertenciaNegocio = new AvisoNegocio();
        List<Aviso> advertencias = advertenciaNegocio.obtenerTodasAdvertencias();

        DefaultTableModel defaultModel = (DefaultTableModel) jtable_advertencias.getModel();
        defaultModel.setNumRows(0);
        for (Aviso advertencia : advertencias) {
            defaultModel.addRow(advertencia.fieldsToObjects());
        }
        jtable_advertencias.setModel(defaultModel);

    }

    private void cargarAlarmas() {
        DefaultTableModel defaultModel = (DefaultTableModel) jtable_alarmas.getModel();
        defaultModel.setNumRows(0);
        for (Alarma alarma : alarmas) {
            defaultModel.addRow(alarma.fieldsToObjects());
        }
        jtable_alarmas.setModel(defaultModel);

    }
    
    private void llegoUnAviso(firstone.serializable.Aviso aviso)
    {
        Aviso a = new Aviso();
        a.setDescripcion(aviso.getMensaje());
        a.setFecha_hora(new Date(aviso.getFecha_hora()));
        a.setId_tranca(tranca.getId());
        a.setDe(aviso.getFrom());
        avisoNegocio.registrarAviso(a);
        cargarAdvertencias();
    }
    
    private void llegoUnaAlarma(firstone.serializable.Alarma alarma)
    {
        jboton_alarma_roja.setVisible(false);
        jboton_alarma_verde.setVisible(false);
        jboton_apagar_alarma.setText("Apagar Alarma");

        Alarma al = new Alarma();
        al.setEmisor(alarma.getEmisor());
        al.setPrioridad(alarma.getPrioridad());
        alarmas.add(al);
        cargarAlarmas();
        
        if (alarma.getPrioridad().equalsIgnoreCase(firstone.cliente.circuito.model.Alarma.ROJO))
            JOptionPane.showMessageDialog(rootPane, "ALARMA", "ALARMA", JOptionPane.ERROR_MESSAGE);
        else if (alarma.getPrioridad().equalsIgnoreCase(firstone.cliente.circuito.model.Alarma.AMARILLO))
            JOptionPane.showMessageDialog(rootPane, "ALARMA", "ALARMA", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(rootPane, "ALARMA", "ALARMA", JOptionPane.INFORMATION_MESSAGE);
    }

    private void llegoPaqueteSincronizacion(List<Object> obs)
    {
        SynchronizerNegocio synchronizerNegocio = new SynchronizerNegocio();
        for (Object o : obs)
        {
            if (o instanceof EstructureB)
            {
                synchronizerNegocio.procesarEstructureB((EstructureB)o,tranca);
            }
        }
    }
    
    private void registrarBitacoraGuardia(String accion, String detalle) {
        Bitacora bitacora = new Bitacora();
        bitacora.setAccion(accion);
        bitacora.setCi_guardia(guardia.getCi());
        bitacora.setDetalle(detalle);
        bitacora.setFecha_hora(new Date());
        bitacoraNegocio.registrarBitacora(bitacora);
    }
    
    private void maximoId(Long last_id) {
        Sincronizacion.prop.setProperty("ULTIMO_ID_SINCRONIZADO", ""+last_id);
        Sincronizacion.save();
    }
    
    private synchronized void denegarIngresoSalida(Contrato contrato)
    {
        try {
            if (! respondido)
            {
                String cad = new String(contrato.getContenido(),"UTF-8");
                Boolean response = new Boolean(cad.split(",")[1]);
                if (response)
                {
                    circuitoNegocio.dejarPasarVehiculo();
                }else
                    JOptionPane.showMessageDialog(rootPane, "Vehiculo Bloqueado", "Vehiculo Bloqueado", JOptionPane.WARNING_MESSAGE);
                
                respondido = true;
            }
        } catch (UnsupportedEncodingException ex) {
            log.error("Error al deserealizar",ex);
        }
    }
    
    private void licenciaInactiva()
    {
        JOptionPane.showMessageDialog(rootPane, "Licencia Inactiva", "La licencia de acceso a nuestros servidores esta desactivado, por favor cualquier duda comunicarse con Soporte       www.identifour.com", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onNewPackage(long size) {
        
    }

    @Override
    public void onNewTrama(int bytesRead) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onNewPackageComplet(byte[] data) {
        log.info("Un nuevo paquete se ha recibido del servidor");
        Contrato contrato = (Contrato)ObjectUtil.createObject(data);
        switch(contrato.getAccion())
        {
            case Accion.AVISO : llegoUnAviso((firstone.serializable.Aviso)ObjectUtil.createObject(contrato.getContenido())); break;
            case Accion.ALARMA : llegoUnaAlarma((firstone.serializable.Alarma)ObjectUtil.createObject(contrato.getContenido())); break;
            case Accion.DOWNLOAD : llegoPaqueteSincronizacion((List<Object>)ObjectUtil.createObject(contrato.getContenido())); break;
            case Accion.MAX_ID : maximoId((Long)ObjectUtil.createObject(contrato.getContenido()));
            case Accion.DENEGAR_INGRESO_SALIDA : denegarIngresoSalida(contrato);
            case Accion.LICENCIA_INACTIVA   : licenciaInactiva();
                
        }
    }

    @Override
    public void onDisconnectCore(IOException e) {
        log.warn("El core se ha desconectado");
    }

    
}
