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
public class Administrador extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
   /**
    * Constructor de Administrador
    * @param nombre
    * @param apellido1
    * @param apellido2
    * @param nick
    * @param email
    * @param contraseña 
    */ 
    public Administrador(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        super(nombre, apellido1, apellido2, nick, email, contraseña);
    }
    
}
