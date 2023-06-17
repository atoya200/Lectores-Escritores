/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_con_sockets;

import cliente_v2.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Agustín Toya
 */
public final class Modelo {

//    private static final Modelo INSTANCE = new Modelo();
//    
//    private static String rutaCarpeta =  "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\archivosPrueba";
//
//    private static HashMap<String, String> archivosHabilitados;
//
//    private Modelo() {
//        archivosHabilitados = new HashMap();
//    }
//
//    public static Modelo getInstance() {
//        return INSTANCE;
//    }
//
//    public static LinkedList<String> getNombresArchivos() {
//        return new LinkedList<>(archivosHabilitados.keySet());
//    }
//
//    public static String getRutaArchivo(String nombreArchivo) {
//        if (archivosHabilitados.containsKey(nombreArchivo)) {
//            return archivosHabilitados.get(nombreArchivo);
//        }
//        return "";
//    }
//
//    public static boolean existeArchivo(String nombreArchivo) {
//        return archivosHabilitados.containsKey(nombreArchivo);
//    }
//    
//    public static void agregarArchivoMap(String nombre, String ruta){
//        archivosHabilitados.put(nombre, ruta);
//    }
//    
//    /**
//     * A partir de un nombre de archivo, registra la url del archivo a 
//     * crearse en la ventana. 
//     * @return 
//     */
//    public static String crearRutaArchivo(String nombre){
//        String ruta = rutaCarpeta + "/" +  nombre + ".txt";
//        return ruta;
//    }
//    
//     public static void escribirArchivo(String [] listaLineasArchivo,String rutaArchivoEditar){
//        FileWriter fw;
//        
//        try {
//            fw = new FileWriter(rutaArchivoEditar,true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            for (int i = 0; i < listaLineasArchivo.length; i++){
//                String lineaActual = listaLineasArchivo[i];
//                bw.write(lineaActual);
//                bw.newLine();
//            }
//            bw.close();
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Error al escribir el archivo "+rutaArchivoEditar);
//            e.printStackTrace();
//        }
//        
//    }
//     
//       public static String recuperarArchivo(String rutaArchivo) throws FileNotFoundException {
//        FileReader fr;
//        StringBuilder strBuild = new StringBuilder();
//        try {
//            fr = new FileReader(rutaArchivo);
//            BufferedReader br = new BufferedReader(fr);
//            String lineaActual = br.readLine();
//            while (lineaActual != null) {
//                strBuild.append(lineaActual);
//                strBuild.append("\n");
//                lineaActual = br.readLine();
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Error al leer el archivo " + rutaArchivo);
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Error al leer el archivo " + rutaArchivo);
//            e.printStackTrace();
//        }
//        return strBuild.toString();
//    }
//    
 

}
