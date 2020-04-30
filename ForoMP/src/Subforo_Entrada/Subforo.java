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
import Sistema.Sistema;
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

    /**
     * Constructor de Subforo
     * @param nombre 
     */
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
    /**
     * añade un suscritos al subforo en el que estamos
     * @param o 
     */
    @Override
    public void addSubscriptor(Observer o) {
        if (!usuarios.contains(o)) {
            usuarios.add(o);
            Sistema s = Sistema.getInstance();
            s.getConectado().darDeAltaSubforo(this);
            System.out.println("Nuevo usuario suscrito al subforo: <<" + this.nombre + " >>");
        } else {
            System.out.println("Este usuario ya esta subscrito");
        }
    }

    /**
     * Añade las notificaciones a los usuarios que esta suscritos
     * @param ent 
     */
    @Override
    public void notifySubscriptor(EntradaGenerica ent) {
        String noti = "Se ha creado una nueva entrada " + ent.getTitulo() + " en el Subforo " + this.nombre;
        usuarios.forEach((usuario) -> {
            usuario.recibirNotificacion(noti);
        });
    }
/**
 * Elimna al suscritos del subforo
 * @param o 
 */
    @Override
    public void deleteSubscriptor(Observer o) {
        if (usuarios.contains(o)) {
            usuarios.remove(o);
            System.out.println("Se ha dado de baja.");

        }

    }

    //
    // COSAS DE OBSERVER (up)
    //
    /**
     * Crea una entrada
     * @param u
     * @param titulo
     * @param txt 
     */
    public void crearEntrada(Usuario u, String titulo, String txt) {
        Sistema s = Sistema.getInstance();
        if (s.getConectado() != null) {
            boolean encontrado = false;
            Iterator<Entrada> i = entradas.iterator();

            while (!encontrado && i.hasNext()) {
                Entrada ent = i.next();
                if (ent.getTitulo().equals(titulo)) {
                    encontrado = true;
                    System.out.println("El titulo para la entrada ya esta en uso, use otro");

                }

            }
            if (!encontrado) {
                Entrada e = new Entrada(u, titulo, txt);
                e.setPertenezco(this);
                entradas.add(e);
                System.out.println("Entrada creada: " + titulo);
                if (!u.getClass().getSimpleName().equals("Alumno")) {
                    notifySubscriptor(e);
                    e.setVerificado(true);
                    e.setPublicada(true);
                }
            }

        }
    }
/**
 * Me da una lista de entradas
 * @return 
 */
    public String mostrarListaEntrada() {
        Sistema s = Sistema.getInstance();
        String info = "";
        if (s.getConectado() != null) {
            if (s.getConectado() instanceof Alumno) {
                info = "\t" + "\t" + "\t" + "Entradas del subforo" + "\n";
                for (Entrada e : entradas) {
                    if (e.getVerificado()) {
                        info += "\t" + e.getTitulo() + "\n";
                    }
                }
            } else {
                info = "\t" + "\t" + "\t" + "Entradas del subforo" + "\n";
                for (Entrada e : entradas) {
                    info += "\t" + e.getTitulo() + " ";
                    if (e.getVerificado()) {
                        info += ":verificada" + "\n";
                    } else {
                        info += ":no verificada" + "\n";
                    }
                }
            }
        }
        return info;
    }
}
