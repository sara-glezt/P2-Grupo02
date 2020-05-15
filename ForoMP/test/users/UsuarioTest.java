/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import Sistema.Sistema;
import Subforo_Entrada.Subforo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author alvarogonzalez
 */
public class UsuarioTest {
   
  
   //Usuario u = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
   /* public void sistema(){
   Sistema sist = Sistema.getInstance();
   sist.registrarse("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123", "Alumno");
   sist.logIn("peter@alumnos.urjc.es", "peter", "123");
   sistema = sist;
   }*/
   
    /**
     * Test of darDeBajaSubforo method, of class Usuario.
     */
    @Test
    public void testDarDeBajaSubforo() {
        Sistema sistema = Sistema.getInstance(); 
        sistema.registrarse("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123","Profesor");
        sistema.logIn("peter@urjc.es", "peter", "123");
        sistema.crearSubforo("Ciencia");
        sistema.logOut();
        
         sistema.registrarse("Juan","Jimenez", "Garcia", "juanxo", "juan@alumnos.urjc.es","123","Alumno");
         sistema.logIn("juan@alumnos.urjc.es", "juanxo", "123");
       // Usuario u = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        sistema.getConectado().darDeAltaSubforo(sistema.getSubforo().get(0));
        
        sistema.getConectado().darDeBajaSubforo("Ciencia");
        assertEquals(0,sistema.getConectado().getSubforos().size()); //vemos que efictavamente ya no esta suscrito al subforo
        sistema.logOut();
        sistema.deleteBD();
        
    }

    /**
     * Test of darDeAltaSubforo method, of class Usuario.
     */
    @Test
    public void testDarDeAltaSubforo() {
        Sistema sistema = Sistema.getInstance(); 
        sistema.registrarse("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123","Profesor");
          sistema.registrarse("Juan","Jimenez", "Garcia", "juanxo", "juan@alumnos.urjc.es","123","Alumno");
             sistema.logIn("peter@urjc.es", "peter", "123");
             sistema.crearSubforo("Noticias");
             sistema.logOut();
             sistema.logIn("juan@alumnos.urjc.es", "juanxo", "123");
             sistema.getConectado().darDeAltaSubforo(sistema.getSubforo().get(0));

        assertEquals(sistema.getConectado().getSubforos().size(),1);//miramos que el el subforo coincide
        sistema.deleteBD();
        
    }

    /**
     * Test of recibirNotificacion method, of class Usuario.
     */
    @Test
    public void testRecibirNotificacion() {
        
        /*Subforo s = new Subforo("Ciencia");
        //Usuario u = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        
        sistema.getConectado().darDeAltaSubforo(s);
        s.addSubscriptor(sistema.getConectado());
        //int i = u.getNotificaciones().size();
        
        
        Usuario u1 = new Profesor("Juan","Jimenez", "Garcia", "juanxo", "juan@urjc.es","123");
        s.crearEntrada(u1, "Prueba", "Texto 1");
        
        //assertTrue(u.getNotificaciones().size()==i+1);//miramos que se ha añadido una notificacion
        assertEquals(sistema.getConectado().getNotificaciones().size(),1);*/
    }

    /**
     * Test of mostrarNotificaciones method, of class Usuario.
     */
    @Test
    public void testMostrarNotificaciones() {
    }

    /**
     * Test of penalizar method, of class Usuario.
     */
    @Test
    public void testPenalizar() {
    }

    /**
     * Test of penalizado method, of class Usuario.
     */
    @Test
    public void testPenalizado() {
    }

    /**
     * Test of actualizarPenalizacion method, of class Usuario.
     */
    @Test
    public void testActualizarPenalizacion() {
    }

    public class UsuarioImpl extends Usuario {

        public UsuarioImpl() {
            super("", "", "", "", "", "");
        }
    }
    
}
