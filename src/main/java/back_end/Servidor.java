/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
    private static HashMap<String, Recurso> recursos = new HashMap<>();
    private static HashMap<Recurso, Integer> idsPorRecurso = new HashMap<>();

    private static String rutaCarpeta = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\archivosPrueba\\";

    public static String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public static HashMap<String, Recurso> getRecursos() {
        return recursos;
    }

    public static HashMap<Recurso, Integer> getIdsRecurso() {
        return idsPorRecurso;
    }

    public static void main(String[] args) {
        
        
        try {
            ServerSocket server = new ServerSocket(8000);
            Socket sc;
            
            cargarArchivos();
            
            
            System.out.println("Servidor iniciado");
            while(true){
            
                // Espero la conexion del cliente
                sc = server.accept();
                
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                
                // Pido al cliente el nombre al cliente
//                out.writeUTF("Indica tu nombre");
                String mensajeCliente = in.readUTF();
                
                // Inicio el hilo
                ServidorHilo hilo = new ServidorHilo(in, out, mensajeCliente);
                hilo.start();
                
                System.out.println("Creada la conexion con el cliente ");
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private static void cargarArchivos() {
        File directorio = new File(rutaCarpeta);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    int punto = archivo.getName().indexOf(".");
                    String nombreArchivo = archivo.getName().substring(0, punto);
                    Recurso r = new Recurso(archivo.getAbsolutePath());
                    recursos.put(nombreArchivo, r);
                    idsPorRecurso.put(r, 0);
                }

            }

        }
    }
    
}
