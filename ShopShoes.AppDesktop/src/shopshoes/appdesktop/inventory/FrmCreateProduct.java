/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop.inventory;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import shopshoes.accesoadatos.*;
import shopshoes.appdesktop.utils.ItemsCombo;
import shopshoes.entidadesdenegocio.*;

/**
 *
 * @author MINEDUCYT
 */
public class FrmCreateProduct extends javax.swing.JFrame {

    /**
     * Creates new form JCrearProduct
     */
    public FrmCreateProduct() {
        initComponents();
        CargarCombo();
    }
    
    private void CargarCombo(){
        ArrayList<Category> listaCategorias = new ArrayList<Category>();
        try {
            listaCategorias = CategoryDAL.obtenerTodos();
            for (int i = 0; i < listaCategorias.size(); i++) {
                ItemsCombo categoria = new ItemsCombo();
                categoria.setId(listaCategorias.get(i).getId());
                categoria.setLabel(listaCategorias.get(i).getCategoryName());
                cmbxCategories.addItem(categoria);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmCreateProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void Limpiar(){
        txtName.setText("");
        txtCost.setText("");
        txtDescription.setText("");
        txtProductImage.setText("");
        txtImageOne.setText("");
        txtImageTwo.setText("");
        txtImageThree.setText("");
        txtSpecifications.setText("");
        txtBrand.setText("");
        txtTickets.setText("");
    }

    private void GuardarProducto() {
        try {
            Products product = new Products();
            // Obtener el valor de la caja de texto Login de la pantalla y llenar la propiedad Login de la clase Usuario
            ItemsCombo categoria = new ItemsCombo();
            categoria = (ItemsCombo) cmbxCategories.getSelectedItem();
            product.setIdCategory(categoria.getId());
            product.setProductName(this.txtName.getText());
            product.setCost(Double.parseDouble(this.txtCost.getText()));
            product.setProductDescription(this.txtDescription.getText());
            product.setProductImage(this.txtProductImage.getText());
            product.setDetailImageOne(this.txtImageOne.getText());
            product.setDetailImageTwo(this.txtImageTwo.getText());
            product.setDetailImageThree(this.txtImageThree.getText());
            product.setBrand(this.txtBrand.getText());
            product.setSpecifications(this.txtSpecifications.getText());
            product.setStatus("Disponible");
            if (product.getProductName().trim().isEmpty() == false && product.getProductDescription().trim().isEmpty() == false
                    && product.getProductImage().trim().isEmpty() == false && product.getDetailImageOne().trim().isEmpty() == false
                    && product.getDetailImageTwo().trim().isEmpty() == false && product.getDetailImageThree().trim().isEmpty() == false
                    && product.getBrand().trim().isEmpty() == false && product.getSpecifications().trim().isEmpty() == false) {
                int productCreate = ProductsDAL.crear(product);
                if (productCreate != 0) {
                    Inventory inventory = new Inventory();
                    inventory.setTickets(Integer.parseInt(txtTickets.getText()));
                    inventory.setStock(Integer.parseInt(txtTickets.getText()));
                    inventory.setDepartures(0);
                    inventory.setProfits(0.00);
                    Products product_ = ProductsDAL.obtenerPorData(product);
                    inventory.setIdProduct(product_.getId());
                    int inventoryCreate = InventoryDAL.crear(inventory);
                    if (inventoryCreate != 0) {
                        JOptionPane.showMessageDialog(this, "Guardado con exito");
                        Limpiar();
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo Guardar");
                    }

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

        jPanel1 = new javax.swing.JPanel();
        cmbxCategories = new javax.swing.JComboBox<>();
        txtImageOne = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        txtImageTwo = new javax.swing.JTextField();
        txtImageThree = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSpecifications = new javax.swing.JTextArea();
        txtProductImage = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        txtTickets = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        cmbxCategories.setBackground(new java.awt.Color(153, 153, 153));
        cmbxCategories.setForeground(new java.awt.Color(255, 255, 255));
        cmbxCategories.setBorder(null);

        txtImageOne.setBackground(new java.awt.Color(153, 153, 153));
        txtImageOne.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Categoria:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle 1:");

        txtName.setBackground(new java.awt.Color(153, 153, 153));
        txtName.setBorder(null);

        txtCost.setBackground(new java.awt.Color(153, 153, 153));
        txtCost.setBorder(null);

        txtImageTwo.setBackground(new java.awt.Color(153, 153, 153));
        txtImageTwo.setBorder(null);

        txtImageThree.setBackground(new java.awt.Color(153, 153, 153));
        txtImageThree.setBorder(null);

        txtDescription.setBackground(new java.awt.Color(153, 153, 153));
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setBorder(null);
        jScrollPane1.setViewportView(txtDescription);

        txtSpecifications.setBackground(new java.awt.Color(153, 153, 153));
        txtSpecifications.setColumns(20);
        txtSpecifications.setRows(5);
        txtSpecifications.setBorder(null);
        jScrollPane2.setViewportView(txtSpecifications);

        txtProductImage.setBackground(new java.awt.Color(153, 153, 153));
        txtProductImage.setBorder(null);

        txtBrand.setBackground(new java.awt.Color(153, 153, 153));
        txtBrand.setBorder(null);

        txtTickets.setBackground(new java.awt.Color(153, 153, 153));
        txtTickets.setBorder(null);

        btnGuardar.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Descripcion:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nueva Entrada:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Imagen:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Detalle 2:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Detalle 3:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Especificaciones:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Marca:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTickets)
                    .addComponent(txtProductImage)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(txtCost)
                    .addComponent(txtName)
                    .addComponent(cmbxCategories, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBrand, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtImageThree, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtImageTwo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtImageOne, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        this.GuardarProducto();
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCreateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCreateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCreateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCreateProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCreateProduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<ItemsCombo> cmbxCategories;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtImageOne;
    private javax.swing.JTextField txtImageThree;
    private javax.swing.JTextField txtImageTwo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtProductImage;
    private javax.swing.JTextArea txtSpecifications;
    private javax.swing.JTextField txtTickets;
    // End of variables declaration//GEN-END:variables
}
