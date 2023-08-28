/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop;
import java.awt.Image;
import java.net.URL;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.AdministratorDAL;
import shopshoes.entidadesdenegocio.Administrator;


/**
 *
 * @author MINEDUCYT
 */
public class AdministratorFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorFrame
     */
    public AdministratorFrame() {
        initComponents();
    }
private void GuardarAdministrator() {
        try {
            Administrator Administrator = new Administrator();
            Administrator.setAdministratortName(this.txtName.getText());
            Administrator.setAdministratorImage(this.txtImage.getText());
            if (Administrator.getAdministratorName().trim().isEmpty() == false && Administrator.getAdministratorImage().trim().isEmpty() == false) {
                int AdministratorCreate = AdministratorDAL.crear(Administrator);
                if(AdministratorCreate != 0){
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtUsers = new javax.swing.JTextField();
        TxtPaswword = new javax.swing.JTextField();
        TxtMail = new javax.swing.JTextField();
        TxtConfirmarPaswoord = new javax.swing.JTextField();
        TxtAdministratorName = new javax.swing.JTextField();
        TxtGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TxtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(TxtGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TxtAdministratorName, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(TxtConfirmarPaswoord)
                    .addComponent(TxtMail)
                    .addComponent(TxtPaswword)
                    .addComponent(TxtUsers))
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(TxtUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(TxtPaswword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(TxtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TxtConfirmarPaswoord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TxtAdministratorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(AdministratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministratorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministratorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtAdministratorName;
    private javax.swing.JTextField TxtConfirmarPaswoord;
    private javax.swing.JButton TxtGuardar;
    private javax.swing.JTextField TxtMail;
    private javax.swing.JTextField TxtPaswword;
    private javax.swing.JTextField TxtUsers;
    // End of variables declaration//GEN-END:variables
}
