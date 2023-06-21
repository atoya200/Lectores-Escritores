/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package versionFinal.backEnd;


/**
 *
 * @author Bruno
 */
public class Lector extends Thread {

    int miId;
    Recurso recurso;
    StringBuilder strB = new StringBuilder();

    public String getTextoLeido() {
        return strB.toString();
    }

    public Lector(Recurso _recurso, int _miId) {
        miId = _miId;
        recurso = _recurso;
    }

    public void run() {

        System.out.println("Lector " + miId + " quiere leer");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("SOY EL LECTOR " + miId);
        for (String s : recurso.leer()) {

            System.out.println(s);
            strB.append(s + "\n");
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Lector " + miId + " ha terminado de leer");
        System.out.println("-----------------------------------------------------E");

    }
}
