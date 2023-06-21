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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorAutenticacion {
    
    private static HashMap<String, String> usersPass = new HashMap<>();
    
    public static HashMap<String, String> getUsersPass(){
        return usersPass;
    }

    public static void main(String[] args) {
        
        
        try {
            ServerSocket server = new ServerSocket(8002);
            Socket sc;
            
            cargarUsuarios();
            
            
            System.out.println("Servidor iniciado");
            while(true){
            
                // Espero la conexion del cliente
                sc = server.accept();
                
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                

                String mensajeCliente = in.readUTF();
                
                // Inicio el hilo
                System.out.println("Creada la conexion con el cliente ");
                ServidoAutenticacionrHilo hilo = new ServidoAutenticacionrHilo(out, mensajeCliente);
                hilo.start();
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorAutenticacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private static void cargarUsuarios() {
        String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\" + 
            "src\\main\\java\\versionFinal\\backEnd\\datos_inicio_sesion";
        FileReader fr;

        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                String[] datos = lineaActual.split(",");
                if (!usersPass.containsKey(datos[0])) {
                    usersPass.put(datos[0], datos[1]);
                }
                lineaActual = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        }
    }
    
}
