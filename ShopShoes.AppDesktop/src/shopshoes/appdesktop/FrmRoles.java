/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.PaymentMethodDAL;
import shopshoes.accesoadatos.RolesDAL;
import shopshoes.appdesktop.utils.TablaImagen;
import shopshoes.entidadesdenegocio.PaymentMethod;
import shopshoes.entidadesdenegocio.Roles;

/**
 *
 * @author MINEDUCYT
 */
public class FrmRoles extends javax.swing.JFrame {
DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    /**
     * Creates new form FrmMetodosPago
     */
    public FrmRoles() {
        initComponents();
    }
    
    private void CargarTabla(){
         this.tblRoles = new JTable(modelo);
         tblRoles.setDefaultRenderer(Object.class, new TablaImagen());
         
         modelo.setRowCount(0);
        modelo.setColumnCount(0);
       modelo.addColumn("Nombre");
        
        Limpiar();
        try {
            ArrayList<Roles> roles = RolesDAL.obtenerTodos();
            for(int i=0; i<roles.size();i++){
               
                Object[] fila = new Object[1];
               
                fila[0] = roles.get(i).getRolesName();
               
           
                this.modelo.addRow(fila);
               
                this.tblRoles.updateUI();
                this.jScrollPane1.setViewportView(tblRoles);
            }
        } catch (Exception ex) {
           
        }
    }
    
    private void Editar() {
        try {
            Roles roles = new Roles();
            roles.setId(2);
            roles.setRolesName(this.txtNombre.getText());
          
            if (roles.getRolesName().trim().isEmpty() == false) {
                int metodoEdit = RolesDAL.modificar(roles);
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
        txtNombre.setText("");
      
    }
    
    
    
    private void Guardar() {
        try {
            Roles roles = new Roles();
            roles.setRolesName(this.txtNombre.getText());
           
            if (roles.getRolesName().trim().isEmpty() == false) {
                int metodoCreate = RolesDAL.crear(roles);
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
        Roles roles = new Roles();
        try {
            roles = RolesDAL.obtenerPorId(2);
             
            
            txtNombre.setText(roles.getRolesName());
         
            
        } catch (Exception ex) {
            
        }
        
    }
    
    private void Buscar() {
         this.tblRoles = new JTable(modelo);
         tblRoles.setDefaultRenderer(Object.class, new TablaImagen());
         
         modelo.setRowCount(0);
        modelo.setColumnCount(0);
       modelo.addColumn("Nombre");
  
         
        try {
            ArrayList<Roles> roles = RolesDAL.obtenerPorName(txtNombre.getText());
            for(int i=0; i<roles.size();i++){
              
                Object[] fila = new Object[1];
               
                fila[0] = roles.get(i).getRolesName();
              
                this.modelo.addRow(fila);
               
                this.tblRoles.updateUI();
                this.jScrollPane1.setViewportView(tblRoles);
          
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
        tblRoles = new javax.swing.JTable();
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActu = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        LIMPIAR = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblRoles);

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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(FrmRoles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRoles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRoles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRoles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRoles().setVisible(true);
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
    private javax.swing.JTable tblRoles;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
