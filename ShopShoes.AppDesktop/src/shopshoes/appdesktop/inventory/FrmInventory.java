/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop.inventory;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.CategoryDAL;
import shopshoes.accesoadatos.InventoryDAL;
import shopshoes.appdesktop.utils.ItemsCombo;
import shopshoes.appdesktop.utils.TablaImagen;
import shopshoes.entidadesdenegocio.Category;
import shopshoes.entidadesdenegocio.Inventory;
import shopshoes.entidadesdenegocio.Products;

/**
 *
 * @author MINEDUCYT
 */
public class FrmInventory extends javax.swing.JFrame {

    DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Creates new form FrmProducts
     */
    public FrmInventory() {
        initComponents();
        CargarTabla();
        CargarCombo();
    }

    private void CargarCombo() {
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

        }

    }

    private void CargarTabla() {
        this.tblProducts = new JTable(modelo);
        tblProducts.setDefaultRenderer(Object.class, new TablaImagen());

        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("N°");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Marca");
        modelo.addColumn("Stock");
        modelo.addColumn("Entradas");
        modelo.addColumn("Salidas");
        modelo.addColumn("Ganancias");

        try {
            Inventory inventory = new Inventory();
            ArrayList<Inventory> inventories = InventoryDAL.buscarIncluirProduct(inventory);
            for (int i = 0; i < inventories.size(); i++) {

                Object[] fila = new Object[8];
                fila[0] = inventories.get(i).getId();
                fila[1] = inventories.get(i).getProduct().getProductName();
                fila[2] = inventories.get(i).getProduct().getCost();
                fila[3] = inventories.get(i).getProduct().getBrand();
                fila[4] = inventories.get(i).getStock();
                fila[5] = inventories.get(i).getTickets();
                fila[6] = inventories.get(i).getDepartures();
                fila[7] = inventories.get(i).getProfits();

                this.modelo.addRow(fila);

                this.tblProducts.updateUI();
                this.jScrollPane1.setViewportView(tblProducts);
            }
        } catch (Exception ex) {

        }
    }

    private void Buscar() {
        this.tblProducts = new JTable(modelo);
        tblProducts.setDefaultRenderer(Object.class, new TablaImagen());

        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("N°");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Marca");
        modelo.addColumn("Stock");
        modelo.addColumn("Entradas");
        modelo.addColumn("Salidas");
        modelo.addColumn("Ganancias");

        try {
            Inventory inventory = new Inventory();
            ItemsCombo categoria = new ItemsCombo();
            categoria = (ItemsCombo) cmbxCategories.getSelectedItem();
            Products product = new Products();

            product.setIdCategory(categoria.getId());

            inventory.setProduct(product);
            inventory.setStock(categoria.getId());

            ArrayList<Inventory> inventories = InventoryDAL.buscarIncluirProduct(inventory);
            for (int i = 0; i < inventories.size(); i++) {

                Object[] fila = new Object[8];
                fila[0] = inventories.get(i).getId();
                fila[1] = inventories.get(i).getProduct().getProductName();
                fila[2] = inventories.get(i).getProduct().getCost();
                fila[3] = inventories.get(i).getProduct().getBrand();
                fila[4] = inventories.get(i).getStock();
                fila[5] = inventories.get(i).getTickets();
                fila[6] = inventories.get(i).getDepartures();
                fila[7] = inventories.get(i).getProfits();

                this.modelo.addRow(fila);

                this.tblProducts.updateUI();
                this.jScrollPane1.setViewportView(tblProducts);
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        lblImagen = new javax.swing.JLabel();
        lblImagenP = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnActu = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        cmbxCategories = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProducts);

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnVer.setText("VER");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnActu.setText("ACTUALIZAR TABLA");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 848, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(50, 50, 50)
                                .addComponent(btnModificar)
                                .addGap(50, 50, 50)
                                .addComponent(btnVer)
                                .addGap(38, 38, 38)
                                .addComponent(btnActu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscar)))))
                .addGap(178, 178, 178)
                .addComponent(lblImagenP, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(602, Short.MAX_VALUE)
                    .addComponent(lblImagen)
                    .addContainerGap(602, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblImagenP, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnModificar)
                    .addComponent(btnVer)
                    .addComponent(btnActu)
                    .addComponent(btnBuscar))
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(306, Short.MAX_VALUE)
                    .addComponent(lblImagen)
                    .addContainerGap(307, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        FrmCreateProduct crear = new FrmCreateProduct();
        crear.setVisible(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnActuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuActionPerformed
        // TODO add your handling code here:
        CargarTabla();

    }//GEN-LAST:event_btnActuActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        if (tblProducts.getSelectedRowCount() != 0) {
            int index = tblProducts.getSelectedRow();
            int row = Integer.parseInt(modelo.getValueAt(index, 0).toString());
            FrmEditProduct edit = new FrmEditProduct(row);
            edit.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        if (tblProducts.getSelectedRowCount() != 0) {
            int index = tblProducts.getSelectedRow();
            int row = Integer.parseInt(modelo.getValueAt(index, 0).toString());
            FrmDetailsProduct edit = new FrmDetailsProduct(row);
            edit.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnVerActionPerformed

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
            java.util.logging.Logger.getLogger(FrmInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmInventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActu;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<ItemsCombo> cmbxCategories;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagenP;
    private javax.swing.JTable tblProducts;
    // End of variables declaration//GEN-END:variables

}
