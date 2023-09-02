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

        cmbxCategories = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        txtCost = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        txtProductImage = new javax.swing.JTextField();
        txtImageOne = new javax.swing.JTextField();
        txtImageTwo = new javax.swing.JTextField();
        txtImageThree = new javax.swing.JTextField();
        txtBrand = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSpecifications = new javax.swing.JTextArea();
        txtTickets = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtSpecifications.setColumns(20);
        txtSpecifications.setRows(5);
        jScrollPane2.setViewportView(txtSpecifications);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTickets)
                    .addComponent(txtProductImage)
                    .addComponent(jScrollPane1)
                    .addComponent(cmbxCategories, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtName)
                    .addComponent(txtCost))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtBrand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(txtTickets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
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
