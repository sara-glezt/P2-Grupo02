/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Observer.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import users.*; //para importar el paquete users
import Observer.*;//para importar el paquete Observer
/**
 *
 * @author alvarogonzalez
 */
public class Subforo implements Serializable, Observable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Observer> usuarios;//arrayList de usuario para saber quien se ha susbcrito NECESARIO EN OBSERVABLE
    private ArrayList<Entrada> entrada; //arrayList de entrada para saber que entradas tiene el subforo

    public Subforo(String nombre) {  //constructor de Subforo
        this.nombre = nombre;
        usuarios = new ArrayList<Observer>();
        entrada = new ArrayList<Entrada>();
    }
    
    
    //
    // COSAS DE OBSERVER (down)
    //
    
    @Override
    public void addSubscriptor(Observer o) {

        usuarios.add(o);
        System.out.println("Nuevo usuario subscrito al subforo: <<" + this.nombre + " >>");
    }

    @Override
    public void notifySubscriptor(Entrada ent) {//no se que debe hacer exactemante
        // como en usuario debe haber un array de notificaciones, entiendo que cada vez que vez que se añade algo inmediatamente despues debe
        //de haber un notificar() para que a todos los usuario suscrito se les guarde en este array y cuanto haga recibirnotificacion se le carge en 
        //el array
        String s = "notificacion";
        //generarNotificacion(ent);
        usuarios.forEach((usuario) -> {
            usuario.recibirNotificacion(s);
        });
    }

    @Override
    public void deleteSubscriptor(Observer o) {
        usuarios.remove(o);
        System.out.println("Hasta luego.");
    }
    
    //
    // COSAS DE OBSERVER (up)
    //

    public void addEntrada(Entrada ent) {
        entrada.add(ent);
        
        notifySubscriptor(ent);

    }
}
