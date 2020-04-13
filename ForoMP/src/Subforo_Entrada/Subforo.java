/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alvarogonzalez
 */
public class Subforo implements Serializable, Subject {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Usuario> usuario;//arrayList de usuario para saber quien se ha susbcrito
    //private ArrayList<Entrada> entrada; arrayList de entrada para saber que entradas tiene el subforo

    public Subforo(String nombre) {  //constructor de Subforo
        this.nombre = nombre;
        usuario = new ArrayList<Usuario>(); 
        //entrada = new ArrayList<Entrada>(); 
    }

    public void añadirSubscritor(Usuario subs) {
        usuario.add(subs);
    }

    public void eliminarSubscritor(Usuario subs) {
        usuario.remove(subs);
    }

    /* public void addEntrada(Entrada ent) {
    entrada.add(ent);
    
    }*/

    public void notificar() {//preguntar que hace exactamente notificar
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
