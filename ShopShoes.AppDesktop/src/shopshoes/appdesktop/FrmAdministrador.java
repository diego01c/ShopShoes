/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.AdministratorDAL;
import shopshoes.accesoadatos.PaymentMethodDAL;
import shopshoes.appdesktop.utils.TablaImagen;
import shopshoes.entidadesdenegocio.Administrator;
import shopshoes.entidadesdenegocio.PaymentMethod;

/**
 *
 * @author MINEDUCYT
 */
public class FrmAdministrador extends javax.swing.JFrame {
DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    /**
     * Creates new form FrmMetodosPago
     */
    public FrmAdministrador() {
        initComponents();
    }
    
    private void CargarTabla(){
         this.tblAdministradores = new JTable(modelo);
         tblAdministradores.setDefaultRenderer(Object.class, new TablaImagen());
         
         modelo.setRowCount(0);
        modelo.setColumnCount(0);
       modelo.addColumn("Usuario");
         modelo.addColumn("Correo");
          modelo.addColumn("Fecha de Registro");
           modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
         
        Limpiar();
        try {
            ArrayList<Administrator> administrator = AdministratorDAL.obtenerTodos();
            for(int i=0; i<administrator.size();i++){
               
                Object[] fila = new Object[5];
               
                fila[0] = administrator.get(i).getAdministratorName();
                
                this.modelo.addRow(fila);
               
                this.tblAdministradores.updateUI();
                this.jScrollPane1.setViewportView(tblAdministradores);
            }
        } catch (Exception ex) {
           
        }
    }
    
    private void Editar() {
        try {
            PaymentMethod payment = new PaymentMethod();
            payment.setId(2);
            payment.setPaymentMethodName(this.txtUser.getText());
            payment.setPaymentMethodDescription(this.txtDescripcion.getText());
            if (payment.getPaymentMethodName().trim().isEmpty() == false && payment.getPaymentMethodDescription().trim().isEmpty() == false) {
                int metodoEdit = PaymentMethodDAL.modificar(payment);
                if(metodoEdit != 0){
                    JOptionPane.showMessageDialog(this, "Actualizado Correctamente");
                   Limpiar();
                   CargarTabla();
                } else {
                    // Mostrar un mensaje al usuario que usa la pantalla  que login y password son incorrectos
                    JOptionPane.showMessageDialog(this, "No se pudo Modificar");

                }
            } else {
                // Mostrar un mensaje al usuario que usa la pantalla  que login y password son obligatorios
                JOptionPane.showMessageDialog(this, "Los datos son obligatorios");
            }
        } catch (Exception ex) {
            // Mostrar un mensaje al usuario que usa la pantalla  sucedio un error al momento de autentificar el usuario
            JOptionPane.showMessageDialog(this, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }
    
    private void Limpiar(){
        txtUser.setText("");
        txtDescripcion.setText("");
       
    }
    
    
    
    private void Guardar() {
        try {
            PaymentMethod payment = new PaymentMethod();
            payment.setPaymentMethodName(this.txtUser.getText());
            payment.setPaymentMethodDescription(this.txtDescripcion.getText());
            if (payment.getPaymentMethodName().trim().isEmpty() == false && payment.getPaymentMethodDescription().trim().isEmpty() == false) {
                int metodoCreate = PaymentMethodDAL.crear(payment);
                if(metodoCreate != 0){
                   JOptionPane.showMessageDialog(this, "Guardado Correctamente");
                   Limpiar();
                   CargarTabla();
                } else {
                    // Mostrar un mensaje al usuario que usa la pantalla  que login y password son incorrectos
                    JOptionPane.showMessageDialog(this, "No se pudo Guardar");

                }
            } else {
                // Mostrar un mensaje al usuario que usa la pantalla  que login y password son obligatorios
                JOptionPane.showMessageDialog(this, "Los datos son obligatorios");
            }
        } catch (Exception ex) {
            // Mostrar un mensaje al usuario que usa la pantalla  sucedio un error al momento de autentificar el usuario
            JOptionPane.showMessageDialog(this, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }
    
    private void Seleccionar() {
        PaymentMethod payment = new PaymentMethod();
        try {
            payment = PaymentMethodDAL.obtenerPorId(2);
             
            
            txtUser.setText(payment.getPaymentMethodName());
            txtDescripcion.setText(payment.getPaymentMethodDescription());
            
        } catch (Exception ex) {
            
        }
        
    }
    
    private void Buscar() {
         this.tblAdministradores = new JTable(modelo);
         tblAdministradores.setDefaultRenderer(Object.class, new TablaImagen());
         
         modelo.setRowCount(0);
        modelo.setColumnCount(0);
       modelo.addColumn("Nombre");
         modelo.addColumn("Descripcion");
         
        try {
            ArrayList<PaymentMethod> payments = PaymentMethodDAL.obtenerPorName(txtUser.getText());
            for(int i=0; i<payments.size();i++){
              
                Object[] fila = new Object[2];
               
                fila[0] = payments.get(i).getPaymentMethodName();
                fila[1] = payments.get(i).getPaymentMethodDescription();;
               
           
                this.modelo.addRow(fila);
               
                this.tblAdministradores.updateUI();
                this.jScrollPane1.setViewportView(tblAdministradores);
          
            }
        } catch (Exception ex) {
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdministradores = new javax.swing.JTable();
        txtUser = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActu = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        txtMail = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtLast = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblAdministradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblAdministradores);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActu.setText("ACTUALIZAR");
        btnActu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        LIMPIAR.setText("LIMPIAR");
        LIMPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LIMPIARActionPerformed(evt);
            }
        });

        btnCargar.setText("ACTUALIZAR TABLA");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtLast, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActu)
                        .addGap(48, 48, 48)
                        .addComponent(btnBuscar)
                        .addGap(36, 36, 36)
                        .addComponent(LIMPIAR))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActu)
                    .addComponent(btnBuscar)
                    .addComponent(LIMPIAR))
                .addGap(27, 27, 27)
                .addComponent(btnCargar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuActionPerformed
        // TODO add your handling code here:
        Editar();
    }//GEN-LAST:event_btnActuActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void LIMPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LIMPIARActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_LIMPIARActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        CargarTabla();
    }//GEN-LAST:event_btnCargarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LIMPIAR;
    private javax.swing.JButton btnActu;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAdministradores;
    private javax.swing.JTextField txtLast;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
