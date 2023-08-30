/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package shopshoes.appdesktop;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shopshoes.accesoadatos.CategoryDAL;
import shopshoes.entidadesdenegocio.Category;
import shopshoes.appdesktop.utils.TablaImagen;

/**
 *
 * @author MINEDUCYT
 */
public class FrmCategorias extends javax.swing.JFrame {
    DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    
     int Id = 2;
    /**
     * Creates new form frmCategorias
     */
    public FrmCategorias() {
        initComponents();
        CargarTabla();
    }
    
    private void CargarTabla(){
         this.tblCategories = new JTable(modelo);
         tblCategories.setDefaultRenderer(Object.class, new TablaImagen());
         
         modelo.setRowCount(0);
        modelo.setColumnCount(0);
       modelo.addColumn("Categoria");
         modelo.addColumn("Imagen");
         modelo.addColumn("");
         
        Limpiar();
        try {
            ArrayList<Category> categories = CategoryDAL.obtenerTodos();
            for(int i=0; i<categories.size();i++){
                Image image = null;
                String url_ = categories.get(i).getCategoryImage();
                URL url = new URL(url_);
                image = ImageIO.read(url);
                
          
            ImageIcon img = new ImageIcon(image);
           
          
                Object[] fila = new Object[3];
                Image imgEscalada = img.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            Icon img_ = new ImageIcon(imgEscalada);
                JLabel label_ = new JLabel(img_);
                JButton btn1 = new JButton("Editar");
                fila[0] = categories.get(i).getCategoryName();
                fila[1] = label_;
                fila[2] = btn1;
           
                this.modelo.addRow(fila);
                this.tblCategories.setRowHeight(170);
      
                this.tblCategories.updateUI();
                this.jScrollPane3.setViewportView(tblCategories);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void EditarCategoria() {
        try {
            Category categoria = new Category();
            categoria.setId(Id);
            categoria.setCategoryName(this.txtName.getText());
            categoria.setCategoryImage(this.txtImage.getText());
            if (categoria.getCategoryName().trim().isEmpty() == false && categoria.getCategoryImage().trim().isEmpty() == false) {
                int productCreate = CategoryDAL.modificar(categoria);
                if(productCreate != 0){
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
        txtName.setText("");
        txtImage.setText("");
        lblNameP.setText("");
        lblName.setText("");
        lblImagen.setText("");
        lblImagen.setIcon(null);
    }
    
    
    
    private void GuardarCategoria() {
        try {
            Category categoria = new Category();
            categoria.setCategoryName(this.txtName.getText());
            categoria.setCategoryImage(this.txtImage.getText());
            if (categoria.getCategoryName().trim().isEmpty() == false && categoria.getCategoryImage().trim().isEmpty() == false) {
                int productCreate = CategoryDAL.crear(categoria);
                if(productCreate != 0){
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
    
    private void SeleccionarFila() {
        Category category = new Category();
        try {
            category = CategoryDAL.obtenerPorId(Id);
             Image image = null;
                String url_ = category.getCategoryImage();
                URL url = new URL(url_);
                image = ImageIO.read(url);
           
            ImageIcon img = new ImageIcon(image);
            Image imgEscalada = img.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            Icon img_ = new ImageIcon(imgEscalada);
            lblImagen.setIcon(img_);
            
            
            txtName.setText(category.getCategoryName());
            txtImage.setText(category.getCategoryImage());
            
        } catch (Exception ex) {
            Logger.getLogger(FrmCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void BuscarCategoria() {
         this.tblCategories = new JTable(modelo); 
         tblCategories.setDefaultRenderer(Object.class, new TablaImagen());
          modelo.setRowCount(0);
        modelo.setColumnCount(0);
         modelo.addColumn("Categoria");
         modelo.addColumn("Imagen");
       
        try {
            ArrayList<Category> categories = CategoryDAL.obtenerPorName(txtName.getText());
            for(int i=0; i<categories.size();i++){
                Image image = null;
                String url_ = categories.get(i).getCategoryImage();
                URL url = new URL(url_);
                image = ImageIO.read(url);
            JLabel label = new JLabel();
            this.jScrollPane3.add(label);
            ImageIcon img = new ImageIcon(image);
            
           Object[] fila = new Object[2];
                Image imgEscalada = img.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            Icon img_ = new ImageIcon(imgEscalada);
                JLabel label_ = new JLabel(img_);
                fila[0] = categories.get(i).getCategoryName();
                fila[1] = label_;
           
                this.modelo.addRow(fila);
                this.tblCategories.setRowHeight(170);
      
                this.tblCategories.updateUI();
                this.jScrollPane3.setViewportView(tblCategories);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmCategorias.class.getName()).log(Level.SEVERE, null, ex);
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
        tblCategorias = new javax.swing.JTable();
        JPanelCategorias = new javax.swing.JPanel();
        txtImage = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblNameP = new javax.swing.JLabel();
        btnActu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCategories = new javax.swing.JTable();

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCategorias);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnActu.setText("ACTUALIZAR TABLA");
        btnActu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActuActionPerformed(evt);
            }
        });

        tblCategories.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCategories.setRowHeight(100);
        tblCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCategories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCategories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCategories);

        javax.swing.GroupLayout JPanelCategoriasLayout = new javax.swing.GroupLayout(JPanelCategorias);
        JPanelCategorias.setLayout(JPanelCategoriasLayout);
        JPanelCategoriasLayout.setHorizontalGroup(
            JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                        .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnActu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNameP, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanelCategoriasLayout.setVerticalGroup(
            JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelCategoriasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                        .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(JPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnActualizar)
                            .addComponent(btnBuscar)
                            .addComponent(btnLimpiar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActu))
                    .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelCategoriasLayout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNameP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanelCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPanelCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        BuscarCategoria();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        GuardarCategoria();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        EditarCategoria();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActuActionPerformed
        // TODO add your handling code here:
        CargarTabla();
    }//GEN-LAST:event_btnActuActionPerformed

    private void tblCategoriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriesMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "");
    }//GEN-LAST:event_tblCategoriesMouseClicked

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
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCategorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCategorias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelCategorias;
    private javax.swing.JButton btnActu;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNameP;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblCategories;
    private javax.swing.JTextField txtImage;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    
}
