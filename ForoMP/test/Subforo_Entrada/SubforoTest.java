/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Alumno;
import users.Profesor;

/**
 *
 * @author Ruben
 */
public class SubforoTest {
    
    /**
     * Test of addSubscriptor method, of class Subforo.
     */
    @Test
    public void testAddSubscriptor() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        
        Sistema s = Sistema.getInstance();
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");
        int i = s.getSubforo().get(0).getUsuariosSuscritos().size();
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());
        s.logOut();
        assertEquals(i+1, s.getSubforo().get(0).getUsuariosSuscritos().size());
        
    }

    

    
    /**
     * Test of notifySubscriptor method, of class Subforo.
     */
    @Test
    public void testNotifySubscriptor() {
        
        Sistema s = Sistema.getInstance();
        /*s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");//creamos el subforo
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//añadimos el suscritor*/
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Entada prueba", "Esto es mi prueba");
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getConectado().getNotificaciones();
        assertEquals(s.getConectado().getNotificaciones(),1);
        
    }

     /**
     * Test of deleteSubscriptor method, of class Subforo.
     */
    @Test
    public void testDeleteSubscriptor() {
          File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        
        Sistema s = Sistema.getInstance();
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");
        
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//añadimos el suscritor
        int i = s.getSubforo().get(0).getUsuariosSuscritos().size();//nos quedamos con el tamaño del array
        s.getSubforo().get(0).deleteSubscriptor(s.getConectado());//borramos el suscritos
        s.logOut();
        assertEquals(i-1,s.getSubforo().get(0).getUsuariosSuscritos().size());//comprobamso que hay un suscritor menos
    }
   
    /**
     * Test of crearEntrada method, of class Subforo.
     */
    @Test
    public void testCrearEntrada() {
    }

    /**
     * Test of mostrarListaEntrada method, of class Subforo.
     */
    @Test
    public void testMostrarListaEntrada() {
    }
    
}
