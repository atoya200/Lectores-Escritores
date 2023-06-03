/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lectores.escritores;

import java.util.LinkedList;

/**
 *
 * @author Agustín Toya
 */
public class Funciones {
    
    private static String rutaArchivo = "";
    
    public  static String getNombreArchivo (){
        String [] directorios = rutaArchivo.split("/");
        return directorios[directorios.length -1];
    }
    
    public static void setRutaArchivo(String rutaArchivo){
        Funciones.rutaArchivo = rutaArchivo;
    }
    
    public static String getRutaArchivo(){
        return rutaArchivo;
    }
    
    public static String recuperarArchivo(String rutaArchivo){
        String [] lineas = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);
        String respuesta = "";
        for (String l: lineas){
            respuesta += l + "\n";
        }
        return respuesta;
    }
    
    public static void escribirArchivo(String texto, String ruta){
       String [] lineas = texto.split("\n");
       ManejadorArchivosGenerico.escribirArchivo(ruta, lineas);
        
    }

    public static VentanaEscritor vE = new VentanaEscritor();
}
