/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Agustín Toya
 */
public class Servidor_con_hilos {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // Crear el socket del servidor en el puerto 8000
            serverSocket = new ServerSocket(8000);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                // Esperar a que llegue una conexión entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");

                // Crear un nuevo hilo para manejar al cliente
                Thread clientThread = new Thread(new ClienteLoginHandler(clientSocket));
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
}

class ClienteLoginHandler implements Runnable {

    private Socket clientSocket;

    public ClienteLoginHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
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

            // Si son tres datos, entonces es escritor
            // Primer dato, si lo va a crear o no, si no lo va a crear es que 
            // va a editar un archivo, por ende lo recuperamos, y se lo mandamos, cerramos conexión
            // esperando que mande o no la data modificada en un futuro
            // Si es un dato, entonces es un lector
            if (datos.length == 3) {
                String rutaArchivo = datos[2];
                String contenido =  datos[1];
                // Escritor
                if (datos[0].equals("1")) {
                    // recupera el contenido del archivo
                    // le envía la data
                } else if (datos[0].equals("2")) {
                    // Tiene que guardar la nueva dirección del archivo 
                    // que se va a crear
                    // Se escribe el contenido
                    // Devuelve el mensaje de confirmación
                    mensajeServidor = "Se ha creado el archivo satisfactoriamente";
                } else {
                    String texto = datos[1];
                    // es para escribir directamente en este caso
                    // llama a la función de escribir
                    // si no puede, le devuelve el mensaje al usuario
                    // si pudo, le envía el mensaje de confirmación
                    mensajeServidor = "Se ha editado el archivo correctamente";
                }
            } else if (datos.length == 1) {
                if (datos.equals("Nombres archivos")) {
                    // Obtiene los nombres de los archivos 
                    // lo carga al mensaje y lo devuelve
                } else {
                    // Lector
                    String rutaArchivo = datos[0];
                    // busca el archivo
                    // si no puede leer, notifica
                    // si puede, lo envia

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
