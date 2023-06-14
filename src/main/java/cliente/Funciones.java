/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Agustín Toya
 */
public class Funciones {

    private static String rutaCarpeta = "./src/archivosPrueba/";
    
    public static Principal principal = new Principal();

    /**
     * Lista que contiene las rutas de los archivos que se pueden leer o
     * modificar
     */
    private static HashMap<String, String> archivosHabilitados = new HashMap();

    public static void cargarArchivos(String rutaArchivo) {
        File directorio = new File(rutaArchivo);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    int punto = archivo.getName().indexOf(".");
                    String nombreArchivo = archivo.getName().substring(0, punto);
                    archivosHabilitados.put(nombreArchivo, archivo.getAbsolutePath());
                    System.out.println(nombreArchivo);
                    System.out.println(archivosHabilitados.size());
                }
            }
        }
    }

    public static HashMap<String, String> getArchivos() {
        return archivosHabilitados;
    }

    public static String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public static void agregarArchivo(String nombreArchivo) {
//        String rutaNuevoArchivo = rutaCarpeta + nombreArchivo + ".txt";
//        
//        String nombreArchivo = archivo.getName().substring(0, punto);
//        archivosHabilitados.put(nombreArchivo, archivo.getAbsolutePath());
//        archivosHabilitados.add(nuevaRuta);
    }

    public static LinkedList<String> getNombresArchivos() {
        System.out.println("Debería haber algo " + archivosHabilitados.keySet().size());
        return new LinkedList<String>(archivosHabilitados.keySet());
    }

    public static String getNombreArchivo() {
        String[] directorios = rutaCarpeta.split("/");
        return directorios[directorios.length - 1];
    }

    public static void setRutaArchivo(String rutaArchivo) {
        Funciones.rutaCarpeta = rutaArchivo;
    }

    public static String getRutaArchivo() {
        return rutaCarpeta;
    }

    // Para probar
    public static String recuperarArchivo(String rutaArchivo) {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(rutaArchivo);
        String respuesta = "";
        for (String l : lineas) {
            respuesta += l + "\n";
        }
        return respuesta;
    }

    // Para probar
    public static void escribirArchivo(String texto, String ruta) {
        String[] lineas = texto.split("\n");
        ManejadorArchivosGenerico.escribirArchivo(ruta, lineas);

    }

    

    public static void volverMenu() {
        principal.volverMenu();
    }

    public static void irVentanaEscritores(String nombreArchivo) {
        String rutaArchivo = "";
        boolean necesitaCrearArchivo = false;
        if (archivosHabilitados.containsKey(nombreArchivo)){
            rutaArchivo = archivosHabilitados.get(nombreArchivo);
        } else {
            rutaArchivo = rutaCarpeta + nombreArchivo + ".txt";
            archivosHabilitados.put(nombreArchivo, rutaArchivo);
            necesitaCrearArchivo = true;
        }
        principal.irVentanaEscritores(rutaArchivo, necesitaCrearArchivo);
    }

    public void irVentanaLectores(String nombreArchivo) {
        String rutaArchivo = archivosHabilitados.get(nombreArchivo);
        principal.irVentanaLectores(rutaArchivo);
    }

    public static boolean validarUsuario(String usuario) {
        String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\v2\\datos_inicio_sesion";
        FileReader fr;
        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                String[] datos = lineaActual.split(",");
                if (usuario.equals(datos[0])) {
                    return true;
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
            while (lineaActual != null) {
                String[] datos = lineaActual.split(",");
                if (nombreUsuario.equals(datos[0])) {
                    if (contraseña.equals(datos[1])) {

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
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        }
        System.out.println("Archivo leido satisfactoriamente");
        return false;
    }

    // Ncesitaremos tener como una cookie de usuario, donde guardar el rol si implementamos lo del hisorial y demás
    public static String getRol() {
        return Funciones.rol;
    }

    public static void setRol(String rol) {
        Funciones.rol = rol;
    }
    private static String rol;

    static void irMenu() {
        principal.irMenu();
    }

}
