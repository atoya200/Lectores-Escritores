/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.backEnd;


/**
 *
 * @author Bruno
 */
public class Recurso {

    int numLectores = 0;
    boolean hayEscritor = false;

    private String rutaRecurso = "";

    public Recurso(String rutaRecurso) {
        this.rutaRecurso = rutaRecurso;
    }

    public String[] leer() {
        //protocolo de entrada
        synchronized (this) {
            while (hayEscritor)
            try {
                wait();
            } catch (Exception e) {
            }
            numLectores++;
        }

        // Lo hacemos esperar a proposito, para así demora un poco más en la lectura
        try {
            Thread.sleep(5000); // Detener hilo durante un segundo
        } catch (InterruptedException e) {
            // Manejar la excepción
        }
        
        // leyendo. Sin sincronizar para permitir concurrencia.
        String[] lectura = ManejadorArchivosGenerico.leerArchivo(this.rutaRecurso, false);

        // protocolo de salida
        synchronized (this) {
            numLectores--;
            if (numLectores == 0) {
                notifyAll();
            }
        }
        return lectura;
    }

    synchronized public void escribir(String escritura) {
        // protocolo de entrada
        synchronized (this) {
            while (hayEscritor || (numLectores > 0))
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            hayEscritor = true;
        }

        try {
            Thread.sleep(15000); // Detener hilo durante un segundo
        } catch (InterruptedException e) {
            // Manejar la excepción
        }

        // escribiendo. Sin sincronizar, pero sólo habrá un escritor.
        ManejadorArchivosGenerico.escribirArchivo(this.rutaRecurso, new String[]{escritura});
        // protocolo de salida
        synchronized (this) {
            hayEscritor = false;
            notifyAll();
        }
    }
}
