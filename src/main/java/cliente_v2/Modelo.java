/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_v2;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Agustín Toya
 */
public final class Modelo {

    private static final Modelo INSTANCE = new Modelo();
    
    private static String rutaCarpeta =  "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\archivosPrueba";

    private static HashMap<String, String> archivosHabilitados;

    private Modelo() {
        archivosHabilitados = new HashMap();
    }

    public static Modelo getInstance() {
        return INSTANCE;
    }

    public static LinkedList<String> getNombresArchivos() {
        return new LinkedList<>(archivosHabilitados.keySet());
    }

    public static String getRutaArchivo(String nombreArchivo) {
        if (archivosHabilitados.containsKey(nombreArchivo)) {
            return archivosHabilitados.get(nombreArchivo);
        }
        return "";
    }

    public static boolean existeArchivo(String nombreArchivo) {
        return archivosHabilitados.containsKey(nombreArchivo);
    }
    
    public static void agregarArchivoMap(String nombre, String ruta){
        archivosHabilitados.put(nombre, ruta);
    }
    
    /**
     * A partir de un nombre de archivo, registra la url del archivo a 
     * crearse en la ventana. 
     * @return 
     */
    public static String crearRutaArchivo(String nombre){
        String ruta = rutaCarpeta + "/" +  nombre + ".txt";
        return ruta;
    }
    
 

}
