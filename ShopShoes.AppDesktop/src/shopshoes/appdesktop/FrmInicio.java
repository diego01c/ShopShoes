/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop;

import java.awt.Button;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import shopshoes.appdesktop.categories.*;
import shopshoes.appdesktop.roles.*;
import shopshoes.appdesktop.paymentmethods.*;
import shopshoes.appdesktop.administrator.*;
import shopshoes.appdesktop.inventory.*;

/**
 *
 * @author victo
 */
public class FrmInicio extends javax.swing.JFrame {
private JFrame currentChildForm;
private JButton currentBtn;
    /**
     * Creates new form FrmInicio
     */
    public FrmInicio() {
        initComponents();
        this.setExtendedState(FrmInicio.MAXIMIZED_BOTH);
         disableButton();
        currentChildForm = new Home();
       OpenChildForm(new Home());
       lblPantalla.setText("INICIO");
      
    }
    
     private void OpenChildForm(JFrame childForm)
        {
            //open only form
            if (currentChildForm != null)
            {
                contenedor.remove(currentChildForm);
                contenedor.revalidate();
    contenedor.repaint();
    contenedor.removeAll();
            }
            
            currentChildForm = childForm;
            //End
            childForm.setSize(contenedor.getSize());
           
            contenedor.add(childForm.getContentPane());
            
            contenedor.revalidate();
    contenedor.repaint();
             
   
    
   
        }
     
     
     public class RGBColors {
    public static Color color1 = new Color(172, 126, 241);
    public static Color color2 = new Color(192, 128, 192);
    public static Color color3 = new Color(253, 138, 114);
    public static Color color4 = new Color(95, 77, 221);
    public static Color color5 = new Color(249, 88, 155);
    public static Color color6 = new Color(24, 161, 251);
}
     
     private void activateButton(Object senderBtn, Color color) {
        if (senderBtn != null) {
            disableButton();
            // Botón0
            currentBtn = null;
            currentBtn = (JButton) senderBtn;
            //currentBtn.setBackground(new Color(100, 100, 100, 128));
            currentBtn.setBackground(new Color(50, 50, 50));
            currentBtn.setForeground(color);
           Border bordeIzquierdo = BorderFactory.createMatteBorder(0, 5, 0, 0, color); // Ajusta el color y el ancho del borde izquierdo
        
        // Crea un borde compuesto con el borde izquierdo y el borde predeterminado del botón
        Border bordeCompuesto = BorderFactory.createCompoundBorder(bordeIzquierdo, currentBtn.getBorder());

        currentBtn.setBorder(bordeCompuesto);
           // currentBtn.setHorizontalAlignment(SwingConstants.CENTER);
           // currentBtn.setVerticalTextPosition(SwingConstants.CENTER);
           // currentBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
            // Borde izquierdo del botón

            // Icono del formulario actual
        }
    }

     
    private void disableButton() {
        if (currentBtn != null) {
            currentBtn.setBackground(Color.BLACK);
            currentBtn.setForeground(Color.WHITE);
            currentBtn.setBorder(null);
            Border bordeIzquierdo = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK); // Ajusta el color y el ancho del borde izquierdo
        
        // Crea un borde compuesto con el borde izquierdo y el borde predeterminado del botón
        Border bordeCompuesto = BorderFactory.createCompoundBorder(bordeIzquierdo, currentBtn.getBorder());

        currentBtn.setBorder(bordeCompuesto);
            //currentBtn.setHorizontalAlignment(SwingConstants.LEFT);
            //currentBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
           // currentBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
            currentBtn = null;
            // Restablece el borde izquierdo y el icono del botón actual
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
        contenedor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCategories = new javax.swing.JButton();
        btnMetodos = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnCerrarSesion1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblPantalla = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        contenedor.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 806, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        btnCategories.setBackground(new java.awt.Color(0, 0, 0));
        btnCategories.setForeground(new java.awt.Color(255, 255, 255));
        btnCategories.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\categorias_.png")); // NOI18N
        btnCategories.setBorder(new javax.swing.border.MatteBorder(null));
        btnCategories.setFocusPainted(false);
        btnCategories.setLabel("                  CATEGORIAS");
        btnCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriesActionPerformed(evt);
            }
        });

        btnMetodos.setBackground(new java.awt.Color(0, 0, 0));
        btnMetodos.setForeground(new java.awt.Color(255, 255, 255));
        btnMetodos.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\pago.png")); // NOI18N
        btnMetodos.setText("      METODOS DE PAGO");
        btnMetodos.setBorder(new javax.swing.border.MatteBorder(null));
        btnMetodos.setFocusPainted(false);
        btnMetodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetodosActionPerformed(evt);
            }
        });

        btnRoles.setBackground(new java.awt.Color(0, 0, 0));
        btnRoles.setForeground(new java.awt.Color(255, 255, 255));
        btnRoles.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\roles_.png")); // NOI18N
        btnRoles.setBorder(new javax.swing.border.MatteBorder(null));
        btnRoles.setFocusPainted(false);
        btnRoles.setLabel("                             ROLES");
        btnRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRolesActionPerformed(evt);
            }
        });

        btnInventario.setBackground(new java.awt.Color(0, 0, 0));
        btnInventario.setForeground(new java.awt.Color(255, 255, 255));
        btnInventario.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\inventario_.png")); // NOI18N
        btnInventario.setBorder(new javax.swing.border.MatteBorder(null));
        btnInventario.setFocusPainted(false);
        btnInventario.setLabel("                    INVENTARIO");
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });

        btnAdmin.setBackground(new java.awt.Color(0, 0, 0));
        btnAdmin.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\admin_.png")); // NOI18N
        btnAdmin.setText("           ADMINISTRADOR");
        btnAdmin.setBorder(new javax.swing.border.MatteBorder(null));
        btnAdmin.setFocusPainted(false);
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("CERRAR SESION");
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setDoubleBuffered(true);
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnCerrarSesion1.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrarSesion1.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion1.setText("INICIO");
        btnCerrarSesion1.setBorderPainted(false);
        btnCerrarSesion1.setDoubleBuffered(true);
        btnCerrarSesion1.setFocusPainted(false);
        btnCerrarSesion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesion1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\MINEDUCYT\\Downloads\\logohop1.png")); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMetodos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRoles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMetodos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setAlignmentX(0.0F);
        jPanel3.setAlignmentY(0.0F);

        lblPantalla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPantalla.setForeground(new java.awt.Color(255, 255, 255));
        lblPantalla.setText("INICIO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblPantalla, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(496, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblPantalla)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        // TODO add your handling code here:
        activateButton(evt.getSource(), new Color(192, 128, 192));
        currentChildForm = new FrmAdministrator();
       OpenChildForm(new FrmAdministrator());
       lblPantalla.setText("ADMINISTRADOR");
       
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriesActionPerformed
        // TODO add your handling code here:
        activateButton(evt.getSource(), RGBColors.color2);
        currentChildForm = new FrmCategories();
       OpenChildForm(new FrmCategories());
       lblPantalla.setText("CATEGORIAS");
    }//GEN-LAST:event_btnCategoriesActionPerformed

    private void btnMetodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetodosActionPerformed
        // TODO add your handling code here:
        activateButton(evt.getSource(), RGBColors.color2);
        currentChildForm = new FrmPaymentMethods();
       OpenChildForm(new FrmPaymentMethods());
       lblPantalla.setText("METODOS DE PAGO");
    }//GEN-LAST:event_btnMetodosActionPerformed

    private void btnRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRolesActionPerformed
        // TODO add your handling code here:
        activateButton(evt.getSource(), RGBColors.color2);
        currentChildForm = new FrmRoles();
       OpenChildForm(new FrmRoles());
       lblPantalla.setText("ROLES");
    }//GEN-LAST:event_btnRolesActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
        activateButton(evt.getSource(), RGBColors.color2);
        currentChildForm = new FrmInventory();
       OpenChildForm(new FrmInventory());
       lblPantalla.setText("INVENTARIO");
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        FrmLogin login = new FrmLogin();
        login.setVisible(true);
              this.setVisible(false);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnCerrarSesion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesion1ActionPerformed
        // TODO add your handling code here:
         disableButton();
        currentChildForm = new Home();
       OpenChildForm(new Home());
       lblPantalla.setText("INICIO");
    }//GEN-LAST:event_btnCerrarSesion1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnCategories;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCerrarSesion1;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnMetodos;
    private javax.swing.JButton btnRoles;
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblPantalla;
    // End of variables declaration//GEN-END:variables
}
