/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.backEnd;

/**
 *
 * @author Bruno
 */
public class Escritor extends Thread {

    int miId;
    Recurso recurso;
    String textoAEscribir = "";

    public Escritor(Recurso _recurso, int _miId, String texto) {
        miId = _miId;
        recurso = _recurso;
        textoAEscribir = texto;
    }

    public void run() {
        System.out.println("Escritor " + miId + " quiere escribir");
        recurso.escribir(textoAEscribir);
        System.out.println("Escritor " + miId + " ha terminado de escribir");
        System.out.println("-----------------------------------------------------E");

    }
}
