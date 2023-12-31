/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop.paymentmethods;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.PaymentMethodDAL;
import shopshoes.appdesktop.utils.TablaImagen;
import shopshoes.entidadesdenegocio.PaymentMethod;

/**
 *
 * @author MINEDUCYT
 */
public class FrmPaymentMethods extends javax.swing.JFrame {

    int Id = 0;
    DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    /**
     * Creates new form FrmMetodosPago
     */
    public FrmPaymentMethods() {
        initComponents();
        CargarTabla();
    }

    private void CargarTabla() {
       tblMetodos.setModel(modelo);
        tblMetodos.setDefaultRenderer(Object.class, new TablaImagen());

        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("N°");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");

        
        Limpiar();
        try {
            ArrayList<PaymentMethod> payments = PaymentMethodDAL.obtenerTodos();
            for (int i = 0; i < payments.size(); i++) {

                Object[] fila = new Object[3];

                fila[0] = payments.get(i).getId();
                fila[1] = payments.get(i).getPaymentMethodName();
                fila[2] = payments.get(i).getPaymentMethodDescription();

                this.modelo.addRow(fila);

                this.tblMetodos.updateUI();
                this.jScrollPane2.setViewportView(tblMetodos);
            }
        } catch (Exception ex) {

        }
    }

    

    private void Limpiar() {
        txtNombre.setText("");
       
    }

    

    

    private void Buscar() {
       tblMetodos.setModel(modelo);
        tblMetodos.setDefaultRenderer(Object.class, new TablaImagen());

        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("N°");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");

        
        try {
            String id = txtNombre.getText();
            ArrayList<PaymentMethod> payments = PaymentMethodDAL.obtenerPorName(id);
            for (int i = 0; i < payments.size(); i++) {

                Object[] fila = new Object[3];

                fila[0] = payments.get(i).getId();
                fila[1] = payments.get(i).getPaymentMethodName();
                fila[2] = payments.get(i).getPaymentMethodDescription();

                this.modelo.addRow(fila);

                this.tblMetodos.updateUI();
                this.jScrollPane2.setViewportView(tblMetodos);
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

        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnActu = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMetodos = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        txtNombre.setBackground(new java.awt.Color(153, 153, 153));
        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setBorder(null);

        btnNuevo.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("NUEVO");
        btnNuevo.setBorderPainted(false);
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnActu.setBackground(new java.awt.Color(0, 0, 0));
        btnActu.setForeground(new java.awt.Color(255, 255, 255));
        btnActu.setText("MODIFICAR");
        btnActu.setBorderPainted(false);
        btnActu.setFocusPainted(false);
        btnActu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuActionPerformed(evt);
            }
        });

        btnVer.setBackground(new java.awt.Color(0, 0, 0));
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("VER");
        btnVer.setBorderPainted(false);
        btnVer.setFocusPainted(false);
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCargar.setBackground(new java.awt.Color(0, 0, 0));
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setText("ACTUALIZAR TABLA");
        btnCargar.setBorderPainted(false);
        btnCargar.setFocusPainted(false);
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        tblMetodos.setBackground(new java.awt.Color(153, 153, 153));
        tblMetodos.setForeground(new java.awt.Color(255, 255, 255));
        tblMetodos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMetodos.setAltoHead(35);
        tblMetodos.setColorBackgoundHead(new java.awt.Color(0, 0, 0));
        tblMetodos.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblMetodos.setColorFilasBackgound1(new java.awt.Color(153, 153, 153));
        tblMetodos.setColorFilasBackgound2(new java.awt.Color(153, 153, 153));
        tblMetodos.setColorFilasForeground1(new java.awt.Color(255, 255, 255));
        tblMetodos.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tblMetodos.setColorSelBackgound(new java.awt.Color(255, 255, 255));
        tblMetodos.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMetodos.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblMetodos.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblMetodos.setGridColor(new java.awt.Color(153, 153, 153));
        tblMetodos.setGrosorBordeFilas(0);
        tblMetodos.setGrosorBordeHead(0);
        tblMetodos.setRowHeight(30);
        tblMetodos.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblMetodos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblMetodos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActu)
                        .addGap(27, 27, 27)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146)
                        .addComponent(btnCargar)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnActu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       FrmCreateMethod metodo = new FrmCreateMethod();
       metodo.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuActionPerformed
        // TODO add your handling code here:
        if (tblMetodos.getSelectedRowCount() != 0) {
            int index = tblMetodos.getSelectedRow();
        int row = Integer.parseInt(modelo.getValueAt(index, 0).toString());
       FrmEditMethod metodo = new FrmEditMethod(row);
       metodo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        }
       
    }//GEN-LAST:event_btnActuActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
     if (tblMetodos.getSelectedRowCount() != 0) {
            int index = tblMetodos.getSelectedRow();
        int row = Integer.parseInt(modelo.getValueAt(index, 0).toString());
       FrmDetailsMethod metodo = new FrmDetailsMethod(row);
       metodo.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        }
       
    }//GEN-LAST:event_btnVerActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPaymentMethods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPaymentMethods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPaymentMethods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPaymentMethods.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPaymentMethods().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActu;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSTableMetro tblMetodos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
