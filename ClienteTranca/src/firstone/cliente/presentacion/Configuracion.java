/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package firstone.cliente.presentacion;

import firstone.cliente.ccs.InterfazConfiguracion;
import firstone.cliente.datos.model.Tranca;
import firstone.cliente.event.EventConfiguracion;
import firstone.cliente.negocio.TrancaNegocio;
import firstone.cliente.util.Sincronizacion;
import java.awt.Component;
import java.awt.Container;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author Milton
 */
public class Configuracion extends javax.swing.JFrame implements EventConfiguracion {

    InterfazConfiguracion interfazConfiguracion;
    TrancaNegocio trancaNegocio;
    
    /**
     * Creates new form Configuracion
     */
    public Configuracion() {
        initComponents();
        
        initializeComponentes();
        
        interfazConfiguracion = new InterfazConfiguracion(this);
        trancaNegocio = new TrancaNegocio();
        
        if (!interfazConfiguracion.iniciar())
        {
            JOptionPane.showMessageDialog(rootPane, "El proveedor FirstOneSoft se encuentra mejorando el producto, por favor vuelva a intentar mas tarde o comuníquese con Soporte", "Servidor no disponible", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        ImageIcon img = new ImageIcon("etc/logo.png");
        this.setIconImage(img.getImage());
    }
    
    private void initializeComponentes()
    {
        this.setSize(350, 165);
//        setUndecorated(true);  
//        getRootPane().setWindowDecorationStyle(JRootPane.NONE);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlabel_tranca = new javax.swing.JLabel();
        jcombo_trancas = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtext_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jboton_tranca = new javax.swing.JButton();
        jtext_pass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                onWindowsClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                onWindowOpened(evt);
            }
        });

        jlabel_tranca.setText("Tranca");

        jLabel4.setText("Correo Electronico");

        jLabel5.setText("Contraseña");

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jboton_tranca.setText("Aceptar");
        jboton_tranca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jboton_trancaActionPerformed(evt);
            }
        });

        jLabel1.setText("Por favor ingrese sus credenciales");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Bienvenido a IdentiFour");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jlabel_tranca))
                                .addGap(108, 108, 108)
                                .addComponent(jcombo_trancas, 0, 251, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 321, Short.MAX_VALUE)
                                .addComponent(jboton_tranca))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtext_pass)
                                    .addComponent(jtext_email)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtext_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtext_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcombo_trancas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabel_tranca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jboton_tranca)
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        interfazConfiguracion.autenticarAdministrador(jtext_email.getText(), jtext_pass.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jboton_trancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jboton_trancaActionPerformed
        Tranca tranca = (Tranca)jcombo_trancas.getSelectedItem();
        trancaNegocio.guardarTranca(tranca);
        Sincronizacion.prop.setProperty("ULTIMO_ID_SINCRONIZADO", "0");
        Sincronizacion.save();
        this.dispose();
    }//GEN-LAST:event_jboton_trancaActionPerformed

    private void onWindowsClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowsClosing
        interfazConfiguracion.finalizar();
    }//GEN-LAST:event_onWindowsClosing

    private void onWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onWindowOpened
        this.setSize(350, 190);
    }//GEN-LAST:event_onWindowOpened

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
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton jboton_tranca;
    private javax.swing.JComboBox jcombo_trancas;
    private javax.swing.JLabel jlabel_tranca;
    private javax.swing.JTextField jtext_email;
    private javax.swing.JPasswordField jtext_pass;
    // End of variables declaration//GEN-END:variables

    @Override
    public void autenticacionAdministrador(List<firstone.serializable.Tranca> trancas, int id_entorno) {
        if (trancas != null)
        {
            this.setSize(350, 265);
            jtext_email.setFocusable(false);
            jtext_pass.setFocusable(false);
            jButton1.setEnabled(false);
            DefaultComboBoxModel model = (DefaultComboBoxModel) jcombo_trancas.getModel();
            model.removeAllElements();
            for(firstone.serializable.Tranca t : trancas)
            {
                Tranca tr = new Tranca();
                tr.setDescripcion(t.getDescripcion());
                tr.setId(t.getId());
                tr.setId_entorno(t.getId_entorno());
                tr.setTipo(t.getTipo());
                model.addElement(tr);
            }
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Correo Electrónico o Contraseña, o tal vez tenga que gestionar Guardias y Trancas desde la Web de Administracion", "Configurando a IdentiFour", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void licenciaInactiva() {
        JOptionPane.showMessageDialog(rootPane, "Licencia Inactiva", "La licencia de acceso a nuestros servidores esta desactivado, por favor cualquier duda comunicarse con Soporte       www.identifour.com", JOptionPane.ERROR_MESSAGE);
    }
}
