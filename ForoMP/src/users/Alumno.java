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
    private ArrayList<Penalizacion> penalizaciones = new ArrayList<>();
    
    public Alumno(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        super(nombre, apellido1, apellido2, nick, email, contraseña);
    }
    ///HE CAMBIADO EL METODO A VOID
    public void penalizar(){
        Date fecha = new Date();
        Penalizacion pena = new Penalizacion(fecha);
        penalizaciones.add(pena);
        
}
    
    public boolean penalizado(){
    if (penalizaciones.isEmpty())
     return false;
    else
        return true;
}
}
