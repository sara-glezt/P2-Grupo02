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
    private Entrada masVotada[];
    
    public Subforo(String nombre) {  //constructor de Subforo
        this.nombre = nombre;
        usuarios = new ArrayList<Observer>();
        entrada = new ArrayList<Entrada>();
        masVotada = new Entrada[3];
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Entrada> getEntrada() {
        return entrada;
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

    public void addEntrada(Entrada ent) {
        entrada.add(ent);
        notifySubscriptor(ent);
        
        /*  int min=0; //Esto lo que haria seria conforme metemos entradas compara con las demas para ver si su puntuacion es mas alta
        if(masVotada[0]== null)
        masVotada[0] = ent;
        else if(masVotada[1]== null)
        masVotada[1] = ent;
        else if(masVotada[2]== null)
        masVotada[2] = ent;
        else{
        
        for (int i = 0; i<3 ; i++){
        if (masVotada[i].getValor()<= masVotada[min].getValor()) //faltaria el metodo que me devolviese el valor de cada enrtada
        min = i;                                                 // ese metodo deberia de ir en entrada
        
        
        }
        if(masVotada[min].getValor() < ent.getValor())
        masVotada[min] = ent.valor;
        
        }*/
    }

    public Entrada[] getMasVotada() {
        return masVotada;
    }
    
    
}
