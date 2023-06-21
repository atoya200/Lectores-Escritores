/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cliente_v2;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

/**
 *
 * @author Agustín Toya
 */
public class VentanaEscritores extends javax.swing.JPanel {

    private ControladorCliente ctrl;

    /**
     * Creates new form VentanaEscritores
     */
    public VentanaEscritores(ControladorCliente controlador) {
        this.ctrl = controlador;
        initComponents();
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
        titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPain = new javax.swing.JTextPane();
        aceptar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        titulo.setText("Escribiendo en el archivo ");

        textPain.setCaretColor(new java.awt.Color(200, 200, 200));
        jScrollPane1.setViewportView(textPain);

        aceptar.setBackground(new java.awt.Color(80, 230, 82));
        aceptar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        aceptar.setForeground(new java.awt.Color(10, 0, 0));
        aceptar.setText("Aceptar");
        aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarMouseClicked(evt);
            }
        });
        aceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                aceptarKeyPressed(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(208, 80, 82));
        cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelar.setForeground(new java.awt.Color(0, 0, 0));
        cancelar.setText("Cancelar");
        cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelarMouseClicked(evt);
            }
        });
        cancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cancelarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelar)
                .addGap(38, 38, 38)
                .addComponent(aceptar)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(titulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(titulo)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseClicked
        // TODO add your handling code here:
        enviarDatos();
    }//GEN-LAST:event_aceptarMouseClicked

    private void aceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_aceptarKeyPressed
        // TODO add your handling code here:
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            enviarDatos();
        }
    }//GEN-LAST:event_aceptarKeyPressed

    private void cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseClicked
        ctrl.volverMenu();
    }//GEN-LAST:event_cancelarMouseClicked

    private void cancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ctrl.volverMenu();
        }

    }//GEN-LAST:event_cancelarKeyPressed

    public void setRutaArchivo(String rutaArchivo, boolean loCreo, String nombreArchivo) throws FileNotFoundException {
        if (!loCreo) {
            String texto = ctrl.recuperarArchivo(rutaArchivo);
            textPain.setText(texto);
            textoAMostrar = texto;
        } else {
            textPain.setText("");
            textoAMostrar = "";
        }
        titulo.setText(titulo.getText() + " " + nombreArchivo);
        rutaArchivoEditar = rutaArchivo;

    }

    public void enviarDatos() {
        if (!textoAMostrar.equals(textPain.getText())) {
            String[] lineas = textPain.getText().split("\n");
            ctrl.escribirArchivo(lineas, rutaArchivoEditar);
        }
    }
   
    
    private String textoAMostrar = "";
    private String rutaArchivoEditar = "";

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private javax.swing.JButton cancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPain;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
