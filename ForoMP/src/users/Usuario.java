/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.Serializable;

/**
 *
 * @author Sara
 */
public class Usuario implements Serializable {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nick;
    private String email;
    private String contraseña;

    public Usuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nick = nick;
        this.email = email;
        this.contraseña = contraseña;
    }
    
    public void recibirNotificacion(String not){
    };
}
