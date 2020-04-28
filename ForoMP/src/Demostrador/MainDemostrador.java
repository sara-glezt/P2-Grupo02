/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demostrador;

/**
 *
 * @author Equipo
 */

import Observer.*;
import Sistema.*;
import Subforo_Entrada.*;
import users.*;

public class MainDemostrador {

    public static void main(String[] args) {
        System.out.println("Empezamos la prueba del foro ");
        System.out.println("Creamos un sistema");
        Sistema s = Sistema.getInstance();
        
        if(s != null){
            System.out.println("Se ha creado el Sistema");
        }else{
            System.out.println("No se ha creado el Sistema");
        }
        
        //Pruebas registro Usuario
        System.out.println("Registramos un usuario alumno");
        s.registrarse("Ruben", "A1", "A2", "grillos", "ruben@alumnos.urjc.es", "1122", "alumno");
        //System.out.println("Nombre: Ruben", "A1", "A2", "grillos", "ruben@alumnno.urjc.es", "1122", "alumno");
        
        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        System.out.println("");
        
        System.out.println("Intentamos registrar el mismo usuario (mismo email)");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben@alumnos.urjc.es", "1122", "alumno");
        
        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        System.out.println("");
        
        //debug: no devuelve el mensaje de nick igual
        System.out.println("Intentamos registrar el mismo usuario (mismo nick)");
        s.registrarse("Ruben", "A1", "A2", "grillos", "ruben12@alumnos.urjc.es", "1122", "alumno");
        
        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        System.out.println("");
        
        System.out.println("Intentamos registrar un usuario con otro tipo (profesor) ");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben1@alumnos.urjc.es", "1122", "profesor");
        
        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        
        System.out.println("Intentamos registrar un usuario con otro tipo (limpiador) ");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben1@alumnos.urjc.es", "1122", "limpiador");
        
        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        
        //Metemos profe
        System.out.println("Intentamos registrar un Profesor ");
        
        //debug: se come @urjc.es con tipo alumno y lo mete en la lista
        s.registrarse("Antonio", "A1", "A2", "Tony", "antonio@urjc.es", "1122", "profesor");
        
        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        
        
        //Registramos un admin
        System.out.println("Intentamos registrar un Admin ");
        s.registrarse("Gustavo", "A1", "A2", "Gusyluz", "gustavo@urjc.es", "1122", "administrador");
        
        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        
        //FASE2 Intentar hacer LOGIN y LOGOUT
        System.out.println("LOGIN Y LOGOUT");
        System.out.println("");
        
        System.out.println("1. Hacemos login con un usuario NO registrado previamente");
        s.logIn("gustavo@urjc.com", "Gusyluz", "1122");
        System.out.println("");
        
        System.out.println("2. Hacemos login con un usuario registrado previamente");
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        System.out.println("");
        
        System.out.println("3. Hacemos login de otro usuario sin hacer logout");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("");
        
        System.out.println("4. Hacemos logout");
        s.logOut();
        System.out.println("");
        
              
        System.out.println("5. Hacemos otro logout con el sistema no iniciado");
        s.logOut();
        System.out.println("");
        
        //Empezamos a crear subforos y entradas
        System.out.println("CREAR ENTRADAS Y SUBFOROS");
        System.out.println("");
        
        System.out.println("1.Intentamos crear un subforo con ningun usuario conectado");
        s.crearSubforo("Deportes");
        System.out.println("");
        
        System.out.println("2.Intentamos crear un subforo con un alumno");
        s.logIn("ruben@alumnos.urjc.es", "grillos1", "1122");
        s.crearSubforo("Deportes");
        System.out.println("");
        
        
        System.out.println("3.Intentamos crear un subforo con un profesor");
        s.logOut();
        s.logIn("antonio@urjc.es", "Tony", "1122");
        s.crearSubforo("Deportes");
        System.out.println("");
        
        System.out.println("4. Creamos una entrada");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Nadal gana al Covid");
        System.out.println("");
        
        System.out.println("5. El mismo creador vota la entrada");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
        System.out.println("");
        
        System.out.println("6. Comprobamos que la valoracion no cambia");
        System.out.println(s.getSubforo().get(0).getEntrada().get(0).toString());
        System.out.println("");
        
        System.out.println("7. Hacemos logout, conectamos a otro usuario y la vota");
        s.logOut();        
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
        System.out.println("");
        
        System.out.println("8. Hacemos que el usuario vote lo mismo y vemos como varia");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
        System.out.println("");
        
        System.out.println("9. Hacemos que el usuario vote lo contrario");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), -1);
        System.out.println("");
        
        System.out.println("10. Hacemos que el usuario vote algo no valido");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 7);
        System.out.println("");
        
        System.out.println("11. Hacemos que vote otro usuario mas y comprobamos la suma");
        s.logOut();
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), -1);
        System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
        
        
        
        
        
       
        

        
        
        
        
        
        
        
        
        
    }
    
}
