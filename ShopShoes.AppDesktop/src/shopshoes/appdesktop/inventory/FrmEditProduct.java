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
public class FrmEditProduct extends javax.swing.JFrame {
private static int Id;
int Stock;
int IdInventory;
int IdProduct;
int Tickets;

    /**
     * Creates new form JCrearProduct
     */
    public FrmEditProduct(int id) {
        initComponents();
        Id = id;
        CargarProduct(id);
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
            Logger.getLogger(FrmEditProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void CargarProduct(int id){
        ArrayList<Inventory> inventories = new ArrayList();
        Inventory inventory_ = new Inventory();
        try{
            inventory_.setId(Id);
            inventories = InventoryDAL.buscarIncluirProduct(inventory_);
            for(int i=0; i< inventories.size();i++){
                
                txtName.setText(inventories.get(i).getProduct().getProductName());
                double costo = inventories.get(i).getProduct().getCost();
        txtCost.setText(String.valueOf(costo));
        txtDescription.setText(inventories.get(i).getProduct().getProductDescription());
        txtProductImage.setText(inventories.get(i).getProduct().getProductImage());
        txtImageOne.setText(inventories.get(i).getProduct().getDetailImageOne());
        txtImageTwo.setText(inventories.get(i).getProduct().getDetailImageTwo());
        txtImageThree.setText(inventories.get(i).getProduct().getDetailImageThree());
        txtSpecifications.setText(inventories.get(i).getProduct().getSpecifications());
        txtBrand.setText(inventories.get(i).getProduct().getBrand());
Tickets = inventories.get(i).getTickets();
        Stock = inventories.get(i).getStock();
        IdProduct = inventories.get(i).getIdProduct();
        Category category = new Category();
        ItemsCombo categoria = new ItemsCombo();
        category = CategoryDAL.obtenerPorId(inventories.get(i).getProduct().getIdCategory());
                categoria.setId(category.getId());
                categoria.setLabel(category.getCategoryName());
        
        cmbxCategories.addItem(categoria);
            }
            
        }
        catch(Exception ex){
            
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

    private void EditarProducto() {
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
            product.setId(IdProduct);
            int TicketsTotal = 0;
            
            if (product.getProductName().trim().isEmpty() == false && product.getProductDescription().trim().isEmpty() == false
                    && product.getProductImage().trim().isEmpty() == false && product.getDetailImageOne().trim().isEmpty() == false
                    && product.getDetailImageTwo().trim().isEmpty() == false && product.getDetailImageThree().trim().isEmpty() == false
                    && product.getBrand().trim().isEmpty() == false && product.getSpecifications().trim().isEmpty() == false) {
                int productCreate = ProductsDAL.modificar(product);
                if (productCreate != 0) {
                    Inventory inventory = new Inventory();
                    inventory.setId(Id);
                    if(!"".equals(txtTickets.getText())){
                        TicketsTotal = Tickets + Integer.parseInt(txtTickets.getText());
                        inventory.setTickets(TicketsTotal);
                        
                    }
                    else{
                        inventory.setTickets(Tickets);
                    }
                    int StockTotal = Stock + TicketsTotal;
                    inventory.setStock(StockTotal);
                   
                    int inventoryCreate = InventoryDAL.modificar(inventory);
                    if (inventoryCreate != 0) {
                        JOptionPane.showMessageDialog(this, "Actualizado con exito");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSpecifications = new javax.swing.JTextArea();
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
        jScrollPane5 = new javax.swing.JScrollPane();
        txtProductImage = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtImageOne = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtImageTwo = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtImageThree = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        cmbxCategories.setBackground(new java.awt.Color(153, 153, 153));
        cmbxCategories.setForeground(new java.awt.Color(255, 255, 255));
        cmbxCategories.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Categoria:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Detalle 1:");

        txtName.setBackground(new java.awt.Color(153, 153, 153));
        txtName.setForeground(new java.awt.Color(255, 255, 255));
        txtName.setBorder(null);

        txtCost.setBackground(new java.awt.Color(153, 153, 153));
        txtCost.setForeground(new java.awt.Color(255, 255, 255));
        txtCost.setBorder(null);

        txtDescription.setBackground(new java.awt.Color(153, 153, 153));
        txtDescription.setColumns(20);
        txtDescription.setForeground(new java.awt.Color(255, 255, 255));
        txtDescription.setRows(5);
        txtDescription.setBorder(null);
        jScrollPane3.setViewportView(txtDescription);

        txtSpecifications.setBackground(new java.awt.Color(153, 153, 153));
        txtSpecifications.setColumns(20);
        txtSpecifications.setForeground(new java.awt.Color(255, 255, 255));
        txtSpecifications.setRows(5);
        txtSpecifications.setBorder(null);
        jScrollPane4.setViewportView(txtSpecifications);

        txtBrand.setBackground(new java.awt.Color(153, 153, 153));
        txtBrand.setForeground(new java.awt.Color(255, 255, 255));
        txtBrand.setBorder(null);

        txtTickets.setBackground(new java.awt.Color(153, 153, 153));
        txtTickets.setForeground(new java.awt.Color(255, 255, 255));
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

        txtProductImage.setBackground(new java.awt.Color(153, 153, 153));
        txtProductImage.setColumns(20);
        txtProductImage.setForeground(new java.awt.Color(255, 255, 255));
        txtProductImage.setRows(5);
        txtProductImage.setBorder(null);
        jScrollPane5.setViewportView(txtProductImage);

        txtImageOne.setBackground(new java.awt.Color(153, 153, 153));
        txtImageOne.setColumns(20);
        txtImageOne.setForeground(new java.awt.Color(255, 255, 255));
        txtImageOne.setRows(5);
        txtImageOne.setBorder(null);
        jScrollPane6.setViewportView(txtImageOne);

        txtImageTwo.setBackground(new java.awt.Color(153, 153, 153));
        txtImageTwo.setColumns(20);
        txtImageTwo.setForeground(new java.awt.Color(255, 255, 255));
        txtImageTwo.setRows(5);
        txtImageTwo.setBorder(null);
        jScrollPane7.setViewportView(txtImageTwo);

        txtImageThree.setBackground(new java.awt.Color(153, 153, 153));
        txtImageThree.setColumns(20);
        txtImageThree.setForeground(new java.awt.Color(255, 255, 255));
        txtImageThree.setRows(5);
        txtImageThree.setBorder(null);
        jScrollPane8.setViewportView(txtImageThree);

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtCost, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbxCategories, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTickets))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtBrand)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 56, Short.MAX_VALUE)
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
  EditarProducto();
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
            java.util.logging.Logger.getLogger(FrmEditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEditProduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmEditProduct(Id).setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextField txtBrand;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextArea txtImageOne;
    private javax.swing.JTextArea txtImageThree;
    private javax.swing.JTextArea txtImageTwo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextArea txtProductImage;
    private javax.swing.JTextArea txtSpecifications;
    private javax.swing.JTextField txtTickets;
    // End of variables declaration//GEN-END:variables
}
