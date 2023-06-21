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

    private static String rutaCarpeta = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\"+
        "Lectores-Escritores\\src\\main\\java\\versionFinal\\archivos\\";

    public static String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public static HashMap<String, Recurso> getRecursos() {
        return recursos;
    }

    public static HashMap<Recurso, Integer> getIdsRecurso() {
        return idsPorRecurso;
    }

    /**
     * Carga los archivos que ya están en la carpeta que va a ser usada por el programa
     */
    private static void cargarArchivos() {
        File directorio = new File(rutaCarpeta);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    int punto = archivo.getName().indexOf(".");
                    
                    // Crea un objeto recurso por cada archivo que va detectando, 
                    // pasandole la ruta del archivo que va a representar. 
                    String nombreArchivo = archivo.getName().substring(0, punto);
                    Recurso r = new Recurso(archivo.getAbsolutePath());
                    recursos.put(nombreArchivo, r);
                    // Se crea un id para identificar de forma diferente a un objeto lector 
                    // de otro lector y a los escritores entre sí. 
                    idsPorRecurso.put(r, 0);
                }

            }

        }
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
                
                String mensajeCliente = in.readUTF();
                
                // Inicio el hilo
                System.out.println("Creada la conexion con el cliente ");
                //ServidorHilo hilo = new ServidorHilo(in, out, mensajeCliente);
                ServidorHilo hilo = new ServidorHilo(out, mensajeCliente);
                hilo.start();
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
}
