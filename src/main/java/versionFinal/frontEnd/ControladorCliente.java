/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.frontEnd;

import Sockets.Cliente;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
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

    //Host del servidor
    private final String HOST = "127.0.0.1";
    //Puerto del servidor de archivos
    private final int PUERTOFILESERVER = 8000;
    private final int PUERTOAUTENTICACION = 8002;

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

    public void irVentanaEscitores(String nombreArchivo, boolean vaACrear) throws FileNotFoundException {
        vE.resetVentana();
        if (!vaACrear) {
            recuperarArchivo(nombreArchivo, false);
            opcionEscribir = "2";

        } else {
            opcionEscribir = "1";
        }
        vE.setNombreArchivo(nombreArchivo);
        principal.remove(menu);
        principal.setContentPane(vE);
        principal.validate();
        principal.pack();
    }

    public void irVentanaLectores(String nombreArchivo) throws FileNotFoundException {
        recuperarArchivo(nombreArchivo, true);
        principal.remove(menu);
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

        DataInputStream in;
        DataOutputStream out;

        String mensaje = "Nombres archivos|";
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTOFILESERVER);

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

        String[] nombres = respuesta.split("\\|");
        return nombres;
    }

    public boolean validarDatos(String nombreUsuario, String contraseña) {

        DataInputStream in;
        DataOutputStream out;

        String mensaje = nombreUsuario + "|" + contraseña;
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTOAUTENTICACION);

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

    public void recuperarArchivo(String nombreArchivo, boolean esLector) throws FileNotFoundException {

        DataInputStream in;
        DataOutputStream out;
        String mensaje = nombreArchivo;
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTOFILESERVER);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente
            out.writeUTF(mensaje);

            //Recibo el mensaje del servidor
            respuesta = in.readUTF();

            /*
            En el caso del escritor, deberí gener un hilo donde mostar el contenido en pantalla

             */
            // out.writeUTF(mensaje); 
            //respuesta = in.readUTF();
            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (esLector) {
            vL.setTextoMostrar(respuesta);
        } else {
            procesarEscritores(respuesta);
        }
    }

    private void procesarEscritores(String respuesta) {
        String r3 = "Se ha creado el archivo satisfactoriamente";
        String r4 = "Se ha editado el archivo correctamente";
        switch (respuesta) {
            case "1":
                menu.actualizarCombo();
                vE.abrirConfirmacion(r3);
                break;
            case "2":
                vE.abrirConfirmacion(r4);
                break;
            default:
                vE.setTextoMostrar(respuesta);
                break;
        }

    }

    public void escribirArchivo(String lineasArchivo, String nombreArchivoEditar) {

        DataInputStream in;
        DataOutputStream out;

        String mensaje = opcionEscribir + "|" + lineasArchivo + "|" + nombreArchivoEditar;
        String respuesta = "";

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTOFILESERVER);

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
    
    public void ajustarVentana(){
        principal.validate();
        principal.pack();
    }

}
