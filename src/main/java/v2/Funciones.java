/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package v2;

import com.lectores.escritores.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    
    public static Principal principal = new Principal();
    
    
    public static void volverMenu(){
        principal.volverMenu();
    }

   public static boolean validarUsuario(String usuario){
       String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\v2\\datos_inicio_sesion";
       FileReader fr;
        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null){
                String [] datos = lineaActual.split(",");
                if (usuario.equals(datos[0])){
                    return true;
                }
                lineaActual = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo "+rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo "+rutaArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");
        return false;
   }

    static boolean validarDatos(String nombreUsuario, String contraseña) {
        String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\v2\\datos_inicio_sesion";
       FileReader fr;
        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null){
                String [] datos = lineaActual.split(",");
                if (nombreUsuario.equals(datos[0])){
                    if (contraseña.equals(datos[1])){
                        
                        // Por si implementamos lo de rol admin y rol user
                        setRol(datos[2]);
                        return true;
                    } else {
                        return false;
                    }
                }
                lineaActual = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo "+rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo "+rutaArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");
        return false;
    }
    
    // Ncesitaremos tener como una cookie de usuario, donde guardar el rol si implementamos lo del hisorial y demás
    public static String getRol(){
        return Funciones.rol;
    }
    
    public static  void setRol(String rol){
        Funciones.rol = rol;
    }
    private  static String rol;

    static void irMenu() {
        principal.irMenu();
    }
   
   
}
