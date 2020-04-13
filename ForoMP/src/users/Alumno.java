/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author Sara
 */
public class Alumno extends Usuario{
    
    public Alumno(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        super(nombre, apellido1, apellido2, nick, email, contraseña);
    }
    
    public boolean penalizar(){
        
     return false;
}
    
    public boolean penalizado(){
        
     return false;
}
}
