/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author Agustín Toya
 */
public class Servidor_autenticacion {

    private static HashMap<String, String> usersPass = new HashMap<>();

    public static void main(String[] args) {
        HashMap<String, String> usersContras = new HashMap<>();

        cargarUsuarios();
        
        ServerSocket serverSocket = null;

        try {
            // Crear el socket del servidor en el puerto 8001
            serverSocket = new ServerSocket(8001);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                // Esperar a que llegue una conexión entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");

                // Crear un nuevo hilo para manejar al cliente
                Thread clientThread = new Thread(new ClientHandler(clientSocket, usersContras));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el socket del servidor
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void cargarUsuarios() {
        String rutaArchivo = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\v2\\datos_inicio_sesion";
        FileReader fr;

        try {
            fr = new FileReader(rutaArchivo);
            BufferedReader br = new BufferedReader(fr);
            String lineaActual = br.readLine();
            while (lineaActual != null) {
                String[] datos = lineaActual.split(",");
                if (!usersPass.containsKey(datos[0])) {
                    usersPass.put(datos[0], datos[1]);
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
    }
}

class ClientHandler implements Runnable {

    private Socket clientSocket;

    private HashMap<String, String> userPass;

    public ClientHandler(Socket clientSocket, HashMap<String, String> usersPass) {
        this.clientSocket = clientSocket;
        this.userPass = usersPass;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Obtener los streams de entrada y salida del socket
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Obtenemos los datos que envió el usuario
            String mensaje = in.readLine();
            String[] datos = mensaje.split("|");
            String mensajeServidor = "Hola, cliente. ¡Aquí está tu mensaje!";

            // Se supone que se le pasa el usuario y la contraseña
            // Deben haber dos datos si o si
            if (datos.length == 2) {
                String user = datos[0];
                String pass = datos[1];
                if (!userPass.containsKey(user)) {
                    mensajeServidor = "1";
                } else if (userPass.containsKey(user) && !userPass.get(user).equals(pass)) {
                    mensajeServidor = "2";
                } else {
                    mensajeServidor = "3";
                }
            } else {
                System.out.println("Error en el sistema, deberían ser dos o un dato");
                System.exit(-1);
            }
            // Enviar un mensaje al cliente
            out.println(mensajeServidor);

            System.out.println("Mensaje enviado al cliente.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los streams y el socket del cliente
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
