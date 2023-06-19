/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Bruno
 */
public class Escritor extends Thread {
    int miId;
    Recurso recurso;
    String textoAEscribir = "";
    public Escritor (Recurso _recurso, int _miId, String texto) {
        miId = _miId;
        recurso = _recurso;
        textoAEscribir = texto;
    }

    public void run () {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10) {
                    System.out.println ("Escritor "+ miId + " quiere escribir");
                    recurso.escribir ("Hola, soy el escritor " + miId);
                    System.out.println ("Escritor "+ miId + " ha terminado de escribir");
                    i++;
                }
            }
        };
        System.out.println("-----------------------------------------------------E");
        timer.schedule(task,20,2000);
    }
}
