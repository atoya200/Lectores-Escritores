/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package cliente_con_sockets;

import cliente_v2.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agustín Toya
 */
public class ConfirmarCrearArchivo extends javax.swing.JDialog {

    private ControladorCliente ctrl;

    /**
     * Creates new form confirmarCrearArchivo
     */
    public ConfirmarCrearArchivo(java.awt.Frame parent, boolean modal, ControladorCliente controlador) {
        super(parent, modal);
        this.ctrl = controlador;
        initComponents();

        this.setTitle("Confirmar creación o selección");
        this.setLocationRelativeTo(parent);
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
        instrucciones = new javax.swing.JTextPane();
        selecionado = new javax.swing.JButton();
        crear = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        instrucciones.setEditable(false);
        jScrollPane1.setViewportView(instrucciones);

        selecionado.setBackground(new java.awt.Color(200, 200, 202));
        selecionado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selecionado.setForeground(new java.awt.Color(0, 0, 0));
        selecionado.setText("Abrir seleccionado");
        selecionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionadoMouseClicked(evt);
            }
        });
        selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selecionadoKeyPressed(evt);
            }
        });

        crear.setBackground(new java.awt.Color(20, 20, 20));
        crear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        crear.setForeground(new java.awt.Color(207, 207, 207));
        crear.setText("Crear archivo");
        crear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearMouseClicked(evt);
            }
        });
        crear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                crearKeyPressed(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(208, 80, 82));
        cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelar.setForeground(new java.awt.Color(20, 20, 20));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(crear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selecionado)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(cancelar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarMouseClicked

        this.setVisible(false);

    }//GEN-LAST:event_cancelarMouseClicked

    private void cancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {

        }
    }//GEN-LAST:event_cancelarKeyPressed

    private void selecionadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selecionadoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            try {
                this.setVisible(false);
                ctrl.irVentanaEscitores(nombreSeleccionado);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfirmarCrearArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_selecionadoKeyPressed

    private void selecionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMouseClicked
        try {
            this.setVisible(false);
            ctrl.irVentanaEscitores(nombreSeleccionado);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfirmarCrearArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_selecionadoMouseClicked

    private void crearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearMouseClicked
        try {
            this.setVisible(false);
            ctrl.irVentanaEscitores(nombreACrear);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfirmarCrearArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_crearMouseClicked

    private void crearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_crearKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            try {
                this.setVisible(false);
                ctrl.irVentanaEscitores(nombreACrear);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfirmarCrearArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_crearKeyPressed

    public void setInstrucciones(String nombreSelecionado, String nombreACrear) {
        f1 += nombreSelecionado;
        f2 += nombreACrear;
        this.nombreSeleccionado = nombreSelecionado;
        this.nombreACrear = nombreACrear;
        instrucciones.setText(f1 + f2 + f3);

    }

    private String nombreSeleccionado = "";
    private String nombreACrear = "";
    private String f1 = "Tiene selecionado un archivo para abrir y editar, pero al mismo tiempo el campo archivos tiene elmentos suficientes para crear un archivo. \n\nArchivo selecionado: ";
    private String f2 = "\nArchivo a crear: ";
    private String f3 = "\n\n¿Quiere crear el archivo nuevo o abrir el selecionado?";


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton crear;
    private javax.swing.JTextPane instrucciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton selecionado;
    // End of variables declaration//GEN-END:variables
}
