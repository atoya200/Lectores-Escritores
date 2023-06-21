/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.backEnd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidoAutenticacionrHilo extends Thread {

    private DataOutputStream out;
    private String mensajeRecibido = "";

    public ServidoAutenticacionrHilo(DataOutputStream out, String mensaje) {
        this.out = out;
        this.mensajeRecibido = mensaje;
    }

    @Override
    public void run() {


            try {
                String[] datos = mensajeRecibido.split("\\|");
                String mensajeServidor = "";

                // Se supone que se le pasa el usuario y la contraseña
                // Deben haber dos datos si o si
                if (datos.length == 2) {
                    String user = datos[0];
                    String pass = datos[1];
                    if (!ServidorAutenticacion.getUsersPass().containsKey(user)) {
                        mensajeServidor = "1";
                    } else if (ServidorAutenticacion.getUsersPass().containsKey(user) 
                            && !ServidorAutenticacion.getUsersPass().get(user).equals(pass)) {
                        mensajeServidor = "2";
                    } else {
                        mensajeServidor = "3";
                    }
                } else {
                    mensajeServidor = "Error en el sistema, deberían ser dos o un dato";
                }
                
                // Enviar un mensaje al cliente
                out.writeUTF(mensajeServidor);

                // Enviar un mensaje al cliente
                System.out.println(mensajeServidor);
                out.writeUTF(mensajeServidor);
                System.out.println("Se corroboró la información del cliente. \nCliente desconectado");
               // salir = true;

            } catch (IOException ex) {
                Logger.getLogger(ServidoAutenticacionrHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

