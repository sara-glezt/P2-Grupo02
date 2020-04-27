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

    public Usuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nick = nick;
        this.email = email;
        this.contraseña = contraseña;
        this.subforos = null;
        this.notificaciones = null;
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
    
    public void darDeBajaSubforo(String nombre){
        subforos.forEach((subforo) -> {
            if (subforo.getNombre().equals(nombre)){
                subforo.deleteSubscriptor(this);
                subforos.remove(subforo);
            }
        });
    }
    
    public void darDeAltaSubforo(Subforo sub){
        subforos.add(sub);
        sub.addSubscriptor(this);
    }
    
    //
    // COSAS DE OBSERVER (down)
    //
    
    @Override
    public void recibirNotificacion(String noti){
        notificaciones.add(noti); 
    };

    public void actualizarPenalizacion(int dias) {}
}
