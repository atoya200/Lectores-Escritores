/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Agustín Toya
 */
public class ControladorCliente {

    private Principal principal;
    private Menu menu;
    private VentanaEscritores vE;
    private VentanaLectores vL;
    private InicioSesion login;

    public ControladorCliente() {

        this.principal = new Principal(this);
        this.menu = new Menu(this);
        this.vE = new VentanaEscritores(this);
        this.vL = new VentanaLectores(this);
        this.login = new InicioSesion(this);
        principal.setVisible(true);
        principal.pack();

    }

    public Principal getPrincipal() {
        return this.principal;
    }

    public void iniciar() {
        principal.setContentPane(login);
        principal.pack();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    public void cerrar() {
        principal.dispose();

    }

    public void irMenu() {
        principal.remove(login);
        principal.setContentPane(menu);
        principal.pack();
    }

    public void irVentanaEscitores(String nombreArchivo) throws FileNotFoundException {
        String rutaArchivo = Modelo.getRutaArchivo(nombreArchivo);
        boolean loCreo = false;
        if (rutaArchivo.length() == 0){
            rutaArchivo = Modelo.crearRutaArchivo(nombreArchivo);
            loCreo = true;
        }
        vE.setRutaArchivo(rutaArchivo, loCreo, nombreArchivo);
        principal.remove(menu);
        principal.setContentPane(vE);
        principal.validate();
        principal.pack();
    }

    public void irVentanaLectores(String nombreArchivo) throws FileNotFoundException {
        principal.remove(menu);
        String ruta = Modelo.getRutaArchivo(nombreArchivo);
        String texto = recuperarArchivo(ruta);
        vL.setTexto(texto, nombreArchivo);
        principal.setContentPane(vL);
        principal.validate();
        principal.pack();

    }

    public void volverMenu() {
        menu.resetVentana();
        menu.setVisible(true);
        principal.setContentPane(menu);
        principal.validate();
        principal.pack();
    }

    public void volverInicioSesion() {
        login.resetVentana();
        principal.remove(menu);
        principal.setContentPane(login);
        principal.validate();
        principal.pack();
    }

    public LinkedList<String> getNombresArchivos() {
        return Modelo.getNombresArchivos();
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
        return false;
    }

    public static boolean validarDatos(String nombreUsuario, String contraseña) {
        String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\v2\\datos_inicio_sesion";
        FileReader fr;
        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                String[] datos = lineaActual.split(",");
                if (nombreUsuario.equals(datos[0])) {
                    return contraseña.equals(datos[1]);
                }
                lineaActual = br.readLine();
            }
            return false;
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        }
        return false;
    }

    public static void cargarArchivos(String rutaArchivo) {
        File directorio = new File(rutaArchivo);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    int punto = archivo.getName().indexOf(".");
                    String nombreArchivo = archivo.getName().substring(0, punto);
                    Modelo.agregarArchivoMap(nombreArchivo, archivo.getAbsolutePath());
                }

            }

        }
    }

    public String recuperarArchivo(String rutaArchivo) throws FileNotFoundException {
        FileReader fr;
        StringBuilder strBuild = new StringBuilder();
        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                strBuild.append(lineaActual);
                strBuild.append("\n");
                lineaActual = br.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + rutaArchivo);
            e.printStackTrace();
        }
        return strBuild.toString();
    }
    
    public void escribirArchivo(String [] listaLineasArchivo,String rutaArchivoEditar){
        FileWriter fw;
        
        try {
            fw = new FileWriter(rutaArchivoEditar,true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaLineasArchivo.length; i++){
                String lineaActual = listaLineasArchivo[i];
                bw.write(lineaActual);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo "+rutaArchivoEditar);
            e.printStackTrace();
        }
        
    }

}
