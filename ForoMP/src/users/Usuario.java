/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import Observer.Observer;
import java.io.Serializable;
import Subforo_Entrada.Subforo;
import java.util.ArrayList;

/**
 *
 * @author Sara
 */
public abstract class Usuario implements Serializable, Observer {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nick;
    private String email;
    private String contraseña;
    private ArrayList<Subforo> subforos;
    private ArrayList<String> notificaciones;
    private int penalizacion;

    public Usuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nick = nick;
        this.email = email;
        this.contraseña = contraseña;
        subforos = new ArrayList<>();
        notificaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
/**
 * Se usa para darse de baja de un subforo al que este suscrito
 * @param nombre 
 */
    public void darDeBajaSubforo(String nombre) {

        Subforo aux = null;
        boolean valid = false;
        for (Subforo s : subforos) {
            if (s.getNombre().equals(nombre)) {
                s.deleteSubscriptor(this);
                valid = true;
                aux = s;
            }
        }
        if (valid) {
            subforos.remove(aux);
        }
    }
/**
 * Se usa para suscribirse al subforo que se desee
 * @param sub 
 */
    public void darDeAltaSubforo(Subforo sub) { 

        //sol improvisada
      boolean aceptar = true;
        for (Subforo s : subforos) {
            if (s.getNombre().equals(sub.getNombre())) {
                aceptar = false;
            }
        }
        if (aceptar) {
            subforos.add(sub);
        } else {
            System.out.println("Este usuario ya esta subscrito al foro");
        

    }}
    
    

    //
    // COSAS DE OBSERVER (down)
    //
    /**
     * Me da las notificaciones pendientes
     * @param noti 
     */
    @Override
    public void recibirNotificacion(String noti) {
        notificaciones.add(noti);
    }
/**
 * Me muestaras las notificaciones que tengo
 */
    public void mostrarNotificaciones(){
        for (int i =0; i<notificaciones.size();i++)
            System.out.println(notificaciones.get(i));
    }

   /**
    * sirve para penalizar dos dias
    */ 
      public void penalizar(){
       penalizacion = 2;
      
      }
    /**
     * Me devuelve si esta penaliazao el usuario
     * @return 
     */
    public boolean penalizado(){
    if (penalizacion > 0)
    return true;
    else
    return false;
}
/**
 * Actualiza las penalizaciones
 * @param dias 
 */
    public void actualizarPenalizacion(int dias){
          int nuevaPena = penalizacion - dias;
        if (nuevaPena <= 0){
        this.penalizacion = 0;}
        else {
        this.penalizacion = nuevaPena;}
    
    
}

    public ArrayList<Subforo> getSubforos() {
        return subforos;
    }

    public ArrayList<String> getNotificaciones() {
        return notificaciones;
    }
    
    
    
}
