/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cliente_con_sockets;

import cliente_v2.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Agustín Toya
 */
public class InicioSesion extends javax.swing.JPanel {

    private ControladorCliente ctrl;

    /**
     * Creates new form InicioSesion
     */
    public InicioSesion(ControladorCliente controlador) {
        this.ctrl = controlador;
        initComponents();

        defaultPassBorder = passwordField.getBorder();
        defaultUserBorder = ingresarUsuario.getBorder();
        mensajeErrorUsuario.setText("");
        mensajeErrorContraseña.setText("");
        ingresarUsuario.select(0, 0);
        ingresarUsuario.setCaretPosition(ingresarUsuario.getText().length());
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
        jLabel1 = new javax.swing.JLabel();
        ingresar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        ingresarUsuario = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        verContraseña = new javax.swing.JButton();
        labelIngresarUsuario = new javax.swing.JLabel();
        labelContraseña = new javax.swing.JLabel();
        mensajeErrorUsuario = new javax.swing.JLabel();
        mensajeErrorContraseña = new javax.swing.JLabel();

        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Iniciar sesión");

        ingresar.setBackground(new java.awt.Color(78, 200, 82));
        ingresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ingresar.setForeground(new java.awt.Color(0, 0, 0));
        ingresar.setText("Ingresar");
        ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingresarMouseClicked(evt);
            }
        });
        ingresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingresarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ingresarKeyReleased(evt);
            }
        });

        cancelar.setBackground(new java.awt.Color(208, 80, 82));
        cancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelar.setForeground(new java.awt.Color(0, 0, 0));
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        ingresarUsuario.setForeground(new java.awt.Color(0, 0, 0));
        ingresarUsuario.setNextFocusableComponent(passwordField);
        ingresarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingresarUsuarioMouseClicked(evt);
            }
        });
        ingresarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ingresarUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ingresarUsuarioKeyReleased(evt);
            }
        });

        passwordField.setNextFocusableComponent(verContraseña);
        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldMouseClicked(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });

        verContraseña.setBackground(new java.awt.Color(220, 220, 220));
        verContraseña.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        verContraseña.setForeground(new java.awt.Color(0, 0, 0));
        verContraseña.setText("Mostrar");
        verContraseña.setInheritsPopupMenu(true);
        verContraseña.setNextFocusableComponent(cancelar);
        verContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verContraseñaMouseClicked(evt);
            }
        });
        verContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verContraseñaKeyPressed(evt);
            }
        });

        labelIngresarUsuario.setLabelFor(ingresarUsuario);
        labelIngresarUsuario.setText("Usuario");

        labelContraseña.setLabelFor(passwordField);
        labelContraseña.setText("Contraseña");

        mensajeErrorUsuario.setForeground(new java.awt.Color(200, 0, 0));
        mensajeErrorUsuario.setLabelFor(ingresarUsuario);
        mensajeErrorUsuario.setText("--");

        mensajeErrorContraseña.setForeground(new java.awt.Color(200, 0, 0));
        mensajeErrorContraseña.setLabelFor(passwordField);
        mensajeErrorContraseña.setText("--");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelar)
                .addGap(18, 18, 18)
                .addComponent(ingresar)
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1))
                    .addComponent(labelIngresarUsuario)
                    .addComponent(mensajeErrorUsuario)
                    .addComponent(labelContraseña)
                    .addComponent(mensajeErrorContraseña)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordField)
                            .addComponent(ingresarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(verContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addComponent(labelIngresarUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ingresarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mensajeErrorUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(labelContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(verContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mensajeErrorContraseña)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ingresar)
                    .addComponent(cancelar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void ingresar() {
        String contraseña = new String(passwordField.getPassword());
        String nombreUsuario = ingresarUsuario.getText();

        if (nombreUsuario.length() == 0) {
            mensajeErrorUsuario.setText("El campo usuario no puede estar vacío");
            ingresarUsuario.setBorder(errorBorder);
            yaIntentoEnviarUser = true;

        } else if (contraseña.length() == 0) {
            yaEscribioPass = true;
            mensajeErrorContraseña.setText("El campo contraseña no puede estar vacío");
            passwordField.setBorder(errorBorder);

        } else {
            yaIntentoEnviarUser = true;
            yaEscribioPass = true;
          
            boolean datosValidos = ctrl.validarDatos(nombreUsuario, contraseña);
            if (datosValidos){
                ctrl.irMenu();
            }
            
        }

    }

    // Este incluye el click sobre el componente y el enter si estas parado sobre el, me sirve más
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        ctrl.getPrincipal().dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void ingresarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarUsuarioMouseClicked
        ingresarUsuario.setBorder(defaultUserBorder);
    }//GEN-LAST:event_ingresarUsuarioMouseClicked

    private void verContraseñaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verContraseñaMouseClicked
        // TODO add your handling code here:
        ocultarMostrar();
    }//GEN-LAST:event_verContraseñaMouseClicked


    private void ingresarUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarUsuarioKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ingresar();
        }

        if (yaIntentoEnviarUser) {
            // ingresarUsuario.setText("");
            mensajeErrorUsuario.setText("");
            yaIntentoEnviarUser = false;
            ingresarUsuario.setBorder(null);
        }

    }//GEN-LAST:event_ingresarUsuarioKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER)
            ingresar();
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void ingresarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarUsuarioKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ingresar();
        }

        if (yaIntentoEnviarUser) {
            // ingresarUsuario.setText("");
            mensajeErrorUsuario.setText("");
            yaIntentoEnviarUser = false;
            ingresarUsuario.setBorder(null);
        }
    }//GEN-LAST:event_ingresarUsuarioKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ingresar();
        }

        if (yaIntentoEnviarContra) {
            passwordField.setText("");
            mensajeErrorUsuario.setText("");
            yaEscribioPass = false;
        }
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void passwordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldMouseClicked
        if (yaIntentoEnviarContra) {
            passwordField.setText("");
            passwordField.setBorder(defaultPassBorder);
            yaIntentoEnviarContra = false;
        }
    }//GEN-LAST:event_passwordFieldMouseClicked

    private void ingresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarMouseClicked
        ingresar();
    }//GEN-LAST:event_ingresarMouseClicked

    private void ingresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER)
            ingresar();
    }//GEN-LAST:event_ingresarKeyPressed

    private void ingresarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ingresarKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER)
            ingresar();
    }//GEN-LAST:event_ingresarKeyReleased

    private void verContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verContraseñaKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER)
            ocultarMostrar();
    }//GEN-LAST:event_verContraseñaKeyPressed

    private void ocultarMostrar() {
        if (!contraseñaExpuesta) {
            contraseñaExpuesta = true;
            passwordField.setEchoChar((char) 0);
            verContraseña.setText("Ocultar");
            verContraseña.setBackground(Color.black);
            verContraseña.setForeground(new Color(220, 220, 220));
            verContraseña.setFont(octularFont);

        } else {
            verContraseña.setText("Mostrar");
            contraseñaExpuesta = false;
            passwordField.setEchoChar('*');
            verContraseña.setBackground(new Color(220, 220, 220));
            verContraseña.setForeground(Color.black);
        }

    }

    public void restablecerVentana() {
        contraseñaExpuesta = false;
        yaEscribioUser = false;
        yaEscribioPass = false;
        ingresarUsuario.setText("");
        passwordField.setText("");
        mensajeErrorContraseña.setText("");
        mensajeErrorUsuario.setText("");
        ingresarUsuario.setBorder(defaultUserBorder);
        ingresarUsuario.setBorder(defaultPassBorder);
        verContraseña.setText("Mostrar");
        contraseñaExpuesta = false;
        passwordField.setEchoChar('*');
        verContraseña.setBackground(new Color(220, 220, 220));
        verContraseña.setForeground(Color.black);

    }

    public void resetVentana() {
        contraseñaExpuesta = false;
        yaEscribioUser = false;
        yaEscribioPass = false;
        yaIntentoEnviarContra = false;
        yaIntentoEnviarUser = false;
        mensajeErrorUsuario.setText("");
        mensajeErrorContraseña.setText("");
        ingresarUsuario.setText("");
        passwordField.setText("");
    }
    
    public void setMensajesError(String mensajeErrorUser, String mensajeErrorPass){
        mensajeErrorUsuario.setText(mensajeErrorUser);
        mensajeErrorContraseña.setText(mensajeErrorPass);
    }
    
    
    
    private boolean contraseñaExpuesta = false;
    private boolean yaEscribioUser = false;
    private boolean yaEscribioPass = false;
    private boolean yaIntentoEnviarContra = false;
    private boolean yaIntentoEnviarUser = false;

    private Font octularFont = new Font("Soge UI", Font.BOLD, 12);

    private Border defaultUserBorder;
    private Border defaultPassBorder;
    private Border errorBorder = new LineBorder(SystemColor.red);

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton ingresar;
    private javax.swing.JTextField ingresarUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelIngresarUsuario;
    private javax.swing.JLabel mensajeErrorContraseña;
    private javax.swing.JLabel mensajeErrorUsuario;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton verContraseña;
    // End of variables declaration//GEN-END:variables
}