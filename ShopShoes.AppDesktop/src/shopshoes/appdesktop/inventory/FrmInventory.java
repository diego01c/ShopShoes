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
        tblProducts.setModel(modelo);
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
                this.jScrollPane2.setViewportView(tblProducts);
            }
        } catch (Exception ex) {

        }
    }

    private void Buscar() {
        tblProducts.setModel(modelo);
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
                this.jScrollPane2.setViewportView(tblProducts);
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
        lblImagen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        cmbxCategories = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnActu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

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

        cmbxCategories.setBackground(new java.awt.Color(153, 153, 153));
        cmbxCategories.setForeground(new java.awt.Color(255, 255, 255));
        cmbxCategories.setBorder(null);
        cmbxCategories.setFocusable(false);

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

        btnModificar.setBackground(new java.awt.Color(0, 0, 0));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorderPainted(false);
        btnModificar.setFocusPainted(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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

        btnActu.setBackground(new java.awt.Color(0, 0, 0));
        btnActu.setForeground(new java.awt.Color(255, 255, 255));
        btnActu.setText("ACTUALIZAR TABLA");
        btnActu.setBorderPainted(false);
        btnActu.setFocusPainted(false);
        btnActu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuActionPerformed(evt);
            }
        });

        tblProducts.setBackground(new java.awt.Color(153, 153, 153));
        tblProducts.setForeground(new java.awt.Color(255, 255, 255));
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
        tblProducts.setAltoHead(35);
        tblProducts.setColorBackgoundHead(new java.awt.Color(0, 0, 0));
        tblProducts.setColorBordeFilas(new java.awt.Color(153, 153, 153));
        tblProducts.setColorFilasBackgound1(new java.awt.Color(153, 153, 153));
        tblProducts.setColorFilasBackgound2(new java.awt.Color(153, 153, 153));
        tblProducts.setColorFilasForeground1(new java.awt.Color(255, 255, 255));
        tblProducts.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        tblProducts.setColorSelBackgound(new java.awt.Color(255, 255, 255));
        tblProducts.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblProducts.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblProducts.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblProducts.setGridColor(new java.awt.Color(153, 153, 153));
        tblProducts.setGrosorBordeFilas(0);
        tblProducts.setGrosorBordeHead(0);
        tblProducts.setRowHeight(30);
        tblProducts.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblProducts.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblProducts);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnActu, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cmbxCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(502, Short.MAX_VALUE)
                    .addComponent(lblImagen)
                    .addContainerGap(502, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(279, Short.MAX_VALUE)
                    .addComponent(lblImagen)
                    .addContainerGap(279, Short.MAX_VALUE)))
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImagen;
    private rojerusan.RSTableMetro tblProducts;
    // End of variables declaration//GEN-END:variables

}
