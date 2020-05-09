/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvarogonzalez
 */
public class SistemaTest {
Sistema sistema = Sistema.getInstance();
    /**
     * Test of getInstance method, of class Sistema.
     */
    @Test
    public void testGetInstance() {//es un get, no se prueba  
    }

    /**
     * Test of getConectado method, of class Sistema.
     */
    @Test
    public void testGetConectado() {
    }

    /**
     * Test of registrarse method, of class Sistema.
     */
    @Test
    public void testRegistrarse1() {
     assertTrue(sistema.registrarse("Javier", "Arcos", "Lopez", "xavi", "xavi@alumnos.urjc.es", "123", "Alumno"));
     
    }
    @Test
    public void testRegistrarse2() {
     assertTrue(sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor"));
     
    }
    @Test
    public void testRegistrarse3() {
     assertTrue(sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador"));
     
    }

    /**
     * Test of eliminarUsuario method, of class Sistema.
     */
    @Test
    public void testEliminarUsuario1() {
        sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador");//registamos a un usuario
        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("angel"); //borramos al usuario
        assertEquals(i-1,sistema.getUsuarios().size());//miramos que el tamaño de ahora es igual al de antes de borrar -1
    }
    
    @Test
        public void testEliminarUsuario2() {//intentamos borrar un usuario que no esta registrado

        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("perico"); //borramos al usuario
        assertEquals(i,sistema.getUsuarios().size());//miramos que el tamaño del array es el mismo antes y despues de "eliminar" el usuario
    }

    /**
     * Test of crearSubforo method, of class Sistema.
     */
    @Test
    public void testCrearSubforo1() {//ceamos subforos sin estar logeado
        int i =sistema.getSubforo().size();//obtemos el tamaño del array antes de crear un suboforo
      sistema.crearSubforo("Tecnologia"); //intentamos crear el subforo
      assertEquals(sistema.getSubforo().size(),i);//Como no podemos, el valor es el mismo despues de itentar crearlo
    }
    
    @Test
    public void testCrearSubforo2() {//ceamos subforos
        sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor");
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        int i =sistema.getSubforo().size();//obtemos el tamaño del array antes de crear un suboforo
      sistema.crearSubforo("Tecnologia"); //intentamos crear el subforo
      assertEquals(sistema.getSubforo().size(),i+1);//Como no podemos, el valor es el mismo despues de itentar crearlo
    }

    /**
     * Test of eliminarSubforo method, of class Sistema.
     */
    @Test
    public void testEliminarSubforo() {
    }

    /**
     * Test of logIn method, of class Sistema.
     */
    @Test
    public void testLogIn() {
    }

    /**
     * Test of logOut method, of class Sistema.
     */
    @Test
    public void testLogOut() {
    }

    /**
     * Test of getMasVotadas method, of class Sistema.
     */
    @Test
    public void testGetMasVotadas() {
    }

    /**
     * Test of guardarSistema method, of class Sistema.
     */
    @Test
    public void testGuardarSistema() {
    }

    /**
     * Test of cargarSistema method, of class Sistema.
     */
    @Test
    public void testCargarSistema() {
    }

    /**
     * Test of ListaSubforo method, of class Sistema.
     */
    @Test
    public void testListaSubforo() {
    }

    /**
     * Test of ListaUsuario method, of class Sistema.
     */
    @Test
    public void testListaUsuario() {
    }

    /**
     * Test of saltarDias method, of class Sistema.
     */
    @Test
    public void testSaltarDias() {
    }

    /**
     * Test of getSubforo method, of class Sistema.
     */
    @Test
    public void testGetSubforo() {
    }
    
}
