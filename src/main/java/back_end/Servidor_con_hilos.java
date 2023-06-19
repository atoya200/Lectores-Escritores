/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.io.BufferedReader;
import java.io.File;
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
public class Servidor_con_hilos {

    private static HashMap<String, Recurso> recursos = new HashMap<>();
    private static HashMap<String, String> archivosSistema = new HashMap<>();
    private static HashMap<Recurso, Integer> idsPorRecurso = new HashMap<>();

    private static String rutaCarpeta = "C:\\Users\\Agustín Toya\\Documents\\NetBeansProjects\\Lectores-Escritores\\src\\main\\java\\archivosPrueba\\";

    public static String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public static HashMap<String, Recurso> getRecursos() {
        return recursos;
    }

    public static HashMap<Recurso, Integer> getIdsRecurso() {
        return idsPorRecurso;
    }

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        cargarArchivos(rutaCarpeta);

        try {
            // Crear el socket del servidor en el puerto 8000
            serverSocket = new ServerSocket(8000);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                // Esperar a que llegue una conexión entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado.");

                // Crear un nuevo hilo para manejar al cliente
                Thread clientThread = new Thread(new ClienteHandler(clientSocket));
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

    private static void cargarArchivos(String rutaArchivo) {
        File directorio = new File(rutaArchivo);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    int punto = archivo.getName().indexOf(".");
                    String nombreArchivo = archivo.getName().substring(0, punto);
                    archivosSistema.put(nombreArchivo, archivo.getAbsolutePath());
                    Recurso r = new Recurso(archivo.getAbsolutePath());
                    recursos.put(nombreArchivo, r);
                    idsPorRecurso.put(r, 0);
                }

            }

        }
    }

}

class ClienteHandler implements Runnable {

    
    private Socket clientSocket;
    
    private PrintWriter out = null;
    
    private BufferedReader in = null;
    
    

    public ClienteHandler(Socket clientSocket, clientSocket.getOutputStream() o, BufferedReader br) {
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = br;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
//        PrintWriter out = null;
//        BufferedReader in = null;
        System.out.println("A correr desgraciado");

        try {
            // Obtener los streams de entrada y salida del socket
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Obtenemos los datos que envió el usuario
            String mensaje = in.readLine();
            System.out.println("hasta acá corrio ");
            String[] datos = mensaje.split("|");
            String mensajeServidor = "Hola, cliente. ¡Aquí está tu mensaje!";

            // Si son tres datos, entonces es escritor
            // Primer dato, si lo va a crear o no, si no lo va a crear es que 
            // va a editar un archivo, por ende lo recuperamos, y se lo mandamos, cerramos conexión
            // esperando que mande o no la data modificada en un futuro
            // Si es un dato, entonces es un lector
            if (datos.length == 3) {
                String nombreArchivo = datos[2];
                String contenido = datos[1];
                // Escritor
                if (datos[0].equals("1")) {
                    // Crea un lector provisonal
                    Recurso r = Servidor_con_hilos.getRecursos().get(nombreArchivo);
                    int id = Servidor_con_hilos.getIdsRecurso().get(r) + 1;
                    Servidor_con_hilos.getIdsRecurso().replace(r, id);
                    Lector lProvisonal = new Lector(r, id);
                    lProvisonal.start();
                    mensaje = lProvisonal.getTextoLeido();
                    // recupera el contenido del archivo
                    // le envía la data
                } else if (datos[0].equals("2")) {
                    String rutaArchivo = Servidor_con_hilos.getRutaCarpeta() + nombreArchivo;
                    Recurso nuevoR = new Recurso(rutaArchivo);
                    Servidor_con_hilos.getIdsRecurso().put(nuevoR, 0);
                    Servidor_con_hilos.getRecursos().put(nombreArchivo,nuevoR);
                    Escritor esc = new Escritor(nuevoR, 0, contenido);
                    esc.start();
                    mensajeServidor = "Se ha creado el archivo satisfactoriamente";

                    // Tiene que guardar la nueva dirección del archivo 
                    // que se va a crear
                    // Se escribe el contenido
                    // Devuelve el mensaje de confirmación
                } else {
                    // es para escribir directamente en este caso
                    // llama a la función de escribir
                    // si no puede, le devuelve el mensaje al usuario
                    // si pudo, le envía el mensaje de confirmación
                    
                    Recurso r = Servidor_con_hilos.getRecursos().get(nombreArchivo);
                    int id = Servidor_con_hilos.getIdsRecurso().get(r) + 1;
                    Servidor_con_hilos.getIdsRecurso().replace(r, id);
                    Escritor esc = new Escritor(r, id, contenido);
                    esc.start();
                    mensajeServidor = "Se ha editado el archivo correctamente";
                }
            } else if (datos.length == 1) {
                if (datos.equals("Nombres archivos")) {
                    // Obtiene los nombres de los archivos 
                    // lo carga al mensaje y lo devuelve
                    mensajeServidor = nombresArchivos();
                    System.out.println("Estamos acá");
                } else {
                    // Lector
                    String nombre = datos[0];
                    Recurso r = Servidor_con_hilos.getRecursos().get(nombre);
                    int id = Servidor_con_hilos.getIdsRecurso().get(r) + 1;
                    Servidor_con_hilos.getIdsRecurso().replace(r, id);
                    
                    Lector l = new Lector(r, id);
                    l.start();
                    mensaje = l.getTextoLeido();
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
    
    private static String nombresArchivos() {
        StringBuilder strB = new StringBuilder();
        for (String s : Servidor_con_hilos.getRecursos().keySet()) {
            strB.append(s + "|");
        }
        return strB.toString();
    }

}
