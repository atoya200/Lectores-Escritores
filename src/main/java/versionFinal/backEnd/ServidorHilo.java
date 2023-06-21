/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.backEnd;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServidorHilo extends Thread {

    private DataOutputStream out;
    private String mensajeRecibido = "";

    public ServidorHilo(DataOutputStream out, String mensaje) {
        this.out = out;
        this.mensajeRecibido = mensaje;
    }

    @Override
    public void run() {


        try {
            String[] datos = mensajeRecibido.split("\\|");
            String mensajeServidor = "";

            // Si son tres datos, entonces es escritor
            // Primer dato, si lo va a crear o no, si no lo va a crear es que 
            // va a editar un archivo, por ende lo recuperamos, y se lo mandamos, cerramos conexión
            // esperando que mande o no la data modificada en un futuro
            // Si es un dato, entonces es un lector o quiere recuperar los nombres de los archivos 
            // que hay en el sistema
            
            if (datos.length == 3) {
                String nombreArchivo = datos[2];
                String contenido = datos[1];
                // Escritor
                if (datos[0].equals("1")) {
                    // Tiene que guardar la nueva dirección del archivo 
                    // que se va a crear
                    // Se escribe el contenido
                    // Guarda el mensaje de confirmación que va a devolver
                    
                    // Crea un nuevo archivo
                    String rutaArchivo = Servidor.getRutaCarpeta() + nombreArchivo;
                    
                    // Crea el nuevo recurso y lo carga
                    Recurso nuevoR = new Recurso(rutaArchivo);
                    Servidor.getIdsRecurso().put(nuevoR, 0);
                    Servidor.getRecursos().put(nombreArchivo, nuevoR);
                    
                    // Crea el escritor y comienda el proceso de lectura sobre el archivo nuevo
                    Escritor esc = new Escritor(nuevoR, 0, contenido);
                    esc.start();
                        
                    try {
                        esc.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }

                    mensajeServidor = "1";

                } else {
                    // es para escribir directamente en este caso
                    // llama a la función de escribir
                    // tras crear un escritor.

                    Recurso r = Servidor.getRecursos().get(nombreArchivo);
                    int id = Servidor.getIdsRecurso().get(r) + 1;
                    Servidor.getIdsRecurso().replace(r, id);
                    Escritor esc = new Escritor(r, id, contenido);
                    esc.start();

                    try {
                        esc.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }
                    mensajeServidor = "2";
                }
            } else if (datos.length == 1) {
                if (datos[0].equals("Nombres archivos")) {
                    // Obtiene los nombres de los archivos 
                    // lo carga al mensaje y lo devuelve
                    mensajeServidor = nombresArchivos();
                } else {
                    // Lector
                    String nombre = datos[0];
                    Recurso r = Servidor.getRecursos().get(nombre);
                    int id = Servidor.getIdsRecurso().get(r) + 1;

                    Servidor.getIdsRecurso().replace(r, id);

                    Lector l = new Lector(r, id);
                    l.start();
                 
                    try {
                        l.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }

                    mensajeServidor = l.getTextoLeido();
                }
            } else {
               mensajeServidor = "Error en el sistema, deberían ser dos o un dato";
            }
            
            // Enviar un mensaje al cliente
            out.writeUTF(mensajeServidor);
            System.out.println("El cliente se desconecto");

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String nombresArchivos() {
        StringBuilder strB = new StringBuilder();
        for (String s : Servidor.getRecursos().keySet()) {
            strB.append(s + "|");
        }
        return strB.toString();
    }

}
