/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente_con_sockets;

import Sockets.Cliente;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Agustín Toya
 */
public class ControladorCliente {

    private final Principal principal;
    private final Menu menu;
    private final VentanaEscritores vE;
    private final VentanaLectores vL;
    private final InicioSesion login;
    private String opcionEscribir = "";

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
        recuperarArchivo(nombreArchivo, false);
        vE.setRutaArchivo(nombreArchivo);
        principal.remove(menu);
        principal.setContentPane(vE);
        principal.validate();
        principal.pack();
    }

    public void irVentanaLectores(String nombreArchivo) throws FileNotFoundException {
        principal.remove(menu);
        recuperarArchivo(nombreArchivo, true);
        vL.setTitulo(nombreArchivo);
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

    public String[] getNombresArchivos() {
        // Tiene que comunicarse con el server, luego 
        // reaarmar la lista en base a las strings que les llega. 
        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 8000;
        DataInputStream in;
        DataOutputStream out;

        String mensaje = "Nombres archivos";
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            //Recibo el mensaje del servidor
            respuesta = in.readUTF();
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String[] nombres = respuesta.split("|");

        return nombres;
    }

    public  boolean validarDatos(String nombreUsuario, String contraseña) {
        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 8001;
        DataInputStream in;
        DataOutputStream out;

        String mensaje = nombreUsuario + "|" + contraseña;
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            //Recibo el mensaje del servidor
            respuesta = in.readUTF();
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return procesarLogin(respuesta);
    }

    public boolean procesarLogin(String respuesta) {
        switch (respuesta) {
            case "1":
                login.setMensajesError("El usuario no existe en el sistema", "");
                break;
            case "2":
                login.setMensajesError("", "La contraseña no corresponde al usuario especificado");
                break;

            case "3":
                return true;

        }
        return false;
    }

//    public static void cargarArchivos(String rutaArchivo) {
//        File directorio = new File(rutaArchivo);
//        if (directorio.isDirectory()) {
//            File[] archivos = directorio.listFiles();
//
//            for (File archivo : archivos) {
//                if (archivo.isFile()) {
//                    int punto = archivo.getName().indexOf(".");
//                    String nombreArchivo = archivo.getName().substring(0, punto);
//                    Modelo.agregarArchivoMap(nombreArchivo, archivo.getAbsolutePath());
//                }
//
//            }
//
//        }
//    }
    public void recuperarArchivo(String nombreArchivo, boolean esLector) throws FileNotFoundException {
        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 8000;
        DataInputStream in;
        DataOutputStream out;
        String mensaje = nombreArchivo;
        String respuesta = "";
        if (!esLector) {
            mensaje = "1| |" + mensaje;
        }
        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            //Recibo el mensaje del servidor
            respuesta = in.readUTF();
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!esLector) {
            procesarEscritores(respuesta);
        } else {
            procesarLectores(respuesta);
        }
    }

    private void procesarEscritores(String respuesta) {
        String r1 = "El archivo ha sido accedido por un lector, debe esperar e intentar luego de un tiempo";
        String r2 = "El archivo ha sido accedido por otro escritor, debe esperar e intentar luego de un tiempo";
        String r3 = "Se ha creado el archivo satisfactoriamente";
        String r4 = "Se ha editado el archivo correctamente";
        switch (respuesta) {
            case "El archivo ha sido accedido por un lector, debe esperar e intentar luego de un tiempo":
                vE.abrirConfirmacion(r1);
                break;

            case "El archivo ha sido accedido por otro escritor, debe esperar e intentar luego de un tiempo":
                vE.abrirConfirmacion(r2);
                break;
            case "Se ha creado el archivo satisfactoriamente":
                menu.actualizarCombo();
                vE.abrirConfirmacion(r3);
                break;

            case "Se ha editado el archivo correctamente":
                vE.abrirConfirmacion(r4);
                break;

            case "":
                opcionEscribir = "2";
                break;

            default:
                opcionEscribir = "3";
                vE.setTextoMostrar(respuesta);
                break;
        }

    }

    private void procesarLectores(String respuesta) {
        String r1 = "El archivo ha sido accedido por un escritor, debe esperar e intentar luego de un tiempo";

        switch (respuesta) {
            case "El archivo ha sido accedido por un escritor, debe esperar e intentar luego de un tiempo":
                vL.abrirConfirmacion(r1);
                break;

            default:
                vL.setTextoMostrar(respuesta);
                break;
        }

    }

    public void escribirArchivo(String lineasArchivo, String nombreArchivoEditar) {
        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 8000;
        DataInputStream in;
        DataOutputStream out;

        String mensaje = opcionEscribir + "|" + lineasArchivo + "|" + nombreArchivoEditar;
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            //Recibo el mensaje del servidor
            respuesta = in.readUTF();
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        procesarEscritores(respuesta);
    }

}
