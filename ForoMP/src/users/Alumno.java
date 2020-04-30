/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Sara
 */
public class Alumno extends Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    private int penalizacion;
    /**
     * Constructor de Alumno
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param nick
     * @param email
     * @param contraseña 
     */
    public Alumno(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        super(nombre, apellido1, apellido2, nick, email, contraseña);
        penalizacion = 0;
    }
/**
 * Metodo que sirve para penalizar al alumnos cuando no se verifica su entrada
 */
    public void penalizar(){
    penalizacion = 2;
    
    }
   /**
    * Me dice si el usuario esta penalizado
    * @return 
    */ 
    public boolean penalizado(){
    if (penalizacion > 0)
    return true;
    else
    return false;
    }
    
    /**
     * Actualiza la cantidad de dias que tiene que estar penalizado un alumno
     * @param dias 
     */
    public void actualizarPenalizacion(int dias){
    int nuevaPena = penalizacion - dias;
    if (nuevaPena <= 0){
    this.penalizacion = 0;}
    else {
    this.penalizacion = nuevaPena;}
    }
}
