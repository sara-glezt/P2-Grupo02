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
import java.util.Iterator;

/**
 *
 * @author alvarogonzalez
 */
public class Subforo implements Serializable, Observable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Observer> usuarios;//arrayList de usuario para saber quien se ha susbcrito NECESARIO EN OBSERVABLE
    private ArrayList<Entrada> entradas; //arrayList de entrada para saber que entradas tiene el subforo

    public Subforo(String nombre) {  //constructor de Subforo
        this.nombre = nombre;
        usuarios = new ArrayList<Observer>();
        entradas = new ArrayList<Entrada>();

    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Entrada> getEntrada() {
        return entradas;
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
    public void notifySubscriptor(EntradaGenerica ent) {
        String noti = "Se ha creado una nueva entrada " + ent.getTitulo() + " en el Subforo " + this.nombre;
        usuarios.forEach((usuario) -> {
            usuario.recibirNotificacion(noti);
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
    public void crearEntrada(Usuario u, String titulo) {
        boolean encontrado = false;
        Iterator<Entrada> i = entradas.iterator();

        while (!encontrado && i.hasNext()) {
            Entrada ent = i.next();
            if (ent.getTitulo().equals(titulo)) {
                encontrado = true;
            }

        }
        if (!encontrado) {
            Entrada e = new Entrada(u, titulo);
            entradas.add(e);
            System.out.println("Entada creada: " + titulo);
            notifySubscriptor(e);

        }

    }

}
