/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando
 */
public class ServidorHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private String mensajeRecibido = "";

    public ServidorHilo(DataInputStream in, DataOutputStream out, String mensaje) {
        this.in = in;
        this.out = out;
        this.mensajeRecibido = mensaje;
    }

    @Override
    public void run() {

        boolean salir = false;
//        while (!salir) {

        try {
            String[] datos = mensajeRecibido.split("\\|");
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
                    Recurso r = Servidor.getRecursos().get(nombreArchivo);
                    int id = Servidor.getIdsRecurso().get(r) + 1;
                    Servidor.getIdsRecurso().replace(r, id);
                    Lector lProvisonal = new Lector(r, id);
                    lProvisonal.start();

                    try {
                        lProvisonal.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }

                    mensajeServidor = lProvisonal.getTextoLeido();
                    // recupera el contenido del archivo
                    // le envía la data
                } else if (datos[0].equals("2")) {
                    String rutaArchivo = Servidor.getRutaCarpeta() + nombreArchivo;
                    Recurso nuevoR = new Recurso(rutaArchivo);
                    Servidor.getIdsRecurso().put(nuevoR, 0);
                    Servidor.getRecursos().put(nombreArchivo, nuevoR);
                    Escritor esc = new Escritor(nuevoR, 0, contenido);
                    esc.start();
                    
                    try {
                        esc.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }
//                    while (esc.isAlive()) {
//                    }
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
                    mensajeServidor = "Se ha editado el archivo correctamente";
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
                 
                    //while (l.isAlive()) {}
                    try {
                        l.join();
                    } catch (InterruptedException e) {
                        // Manejar la excepción
                    }

                    mensajeServidor = l.getTextoLeido();
                    // busca el archivo
                    // si no puede leer, notifica
                    // si puede, lo envia
                }
            } else {
                System.out.println("Error en el sistema, deberían ser dos o un dato");
                System.exit(-1);
            }
            // Enviar un mensaje al cliente

            out.writeUTF(mensajeServidor);
            salir = true;
            System.out.println("Cliente desconectandose");

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
