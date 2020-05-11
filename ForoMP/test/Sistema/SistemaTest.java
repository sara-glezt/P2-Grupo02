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
     * Test of registrarse method, of class Sistema.
     */
    @Test
    public void testRegistrarse1() {//registramos un aummno
     assertTrue(sistema.registrarse("Javier", "Arcos", "Lopez", "xavi", "xavi@alumnos.urjc.es", "123", "Alumno"));
     
    }
    @Test
    public void testRegistrarse2() {//registramos un profe
     assertTrue(sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor"));
     
    }
    @Test
    public void testRegistrarse3() {//registramos un admin
     assertTrue(sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador"));
     
    }
    
    /*   @Test
    public void testRegistrarse4() {//registramos un Usuario con un nick repetido
    assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel1@urjc.es", "123", "Administrador"));
    
    }*/
    
     @Test
    public void testRegistrarse5() {//registramos un usuario con un email no valido
     assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angel2", "angelperez@po.urjc.es", "123", "Administrador"));
     
    }
    
    @Test
    public void testRegistrarse6() {//registramos un usuario que no es del tipo adecuado
     assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angellus", "angel@urjc.es", "123", "Furbolista"));
     
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
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        int i =sistema.getSubforo().size();//obtemos el tamaño del array antes de crear un suboforo
      sistema.crearSubforo("Tecnologia"); //intentamos crear el subforo
      assertEquals(sistema.getSubforo().size(),i+1);//Como no podemos, el valor es el mismo despues de itentar crearlo
        sistema.logOut();
    }

    /**
     * Test of eliminarSubforo method, of class Sistema.
     */
    @Test
    public void testEliminarSubforo1() {
 sistema.logIn("pipe@urjc.es", "pipe", "123");
        int i =sistema.getSubforo().size();//obtemos el tamaño del array despues de crear un suboforo
        sistema.eliminarSubforo("Tecnologia");
        assertEquals(i-1,sistema.getSubforo().size());//comprobamos que ahora tenemos un subforo menos
         sistema.logOut();
    }
    
     @Test
    public void testEliminarSubforo2() {//un profe distitno al creador borra un subforo que no es suyo
           sistema.logIn("pipe@urjc.es", "pipe", "123");  
        sistema.crearSubforo("Comida"); //lo creamos con pipe
        sistema.logOut(); //cambiamos de usuario para borrarlo
        sistema.logIn("angel@urjc.es", "angel", "123"); //cambiamos de usuario para borrarlo
        int i =sistema.getSubforo().size(); // antes de intentar borrarlo cogemos el tamaño que tiene
        sistema.eliminarSubforo("Comida");//intentamos borrarlo con alguien que no es su creador
        assertEquals(i,sistema.getSubforo().size());//comprobamos que el tamaño no ha variado
        sistema.logOut();
    }

    /**
     * Test of logIn method, of class Sistema.
     */
    @Test
    public void testLogIn1() {//vamos a intentar hacer login
        assertTrue(sistema.logIn("angel@urjc.es", "angel", "123"));
        sistema.logOut();
        
    }
    
     @Test
    public void testLogIn2() {//vamos a intentar hacer login sin haber echo logOut
         sistema.logIn("angel@urjc.es", "angel", "123");//hacemos login con un usuario
        assertFalse(sistema.logIn("pipe@urjc.es", "pipe", "123"));//hacemos login sin hacer logOut
        sistema.logOut(); //cerramos sesion para dejarla libre para la proxima prueba
        
    }

    /**
     * Test of logOut method, of class Sistema.
     */
    @Test
    public void testLogOut1() { //hacemos logOut del usuairo conectado
         sistema.logIn("pipe@urjc.es", "pipe", "123");
        assertTrue(sistema.logOut());
    }
     @Test
    public void testLogOut2() { //hacemos logOut sin ningun usuario conectado
        assertFalse(sistema.logOut());
    }

    /**
     * Test of getMasVotadas method, of class Sistema.
     */
    @Test
    public void testGetMasVotadas() {//crear una entrada, votarla y llamar a mas votada y ver si su tamaño ha aumentado en 1
    }

    /**
     * Test of guardarSistema method, of class Sistema.
     */
   
    /**
     * Test of cargarSistema method, of class Sistema.
     */
    @Test
    public void testCargarSistema() {// devuelve un sistema, como lo hago?

    }

    /**
     * Test of ListaSubforo method, of class Sistema.
     */
    @Test
    public void testListaSubforo() {//este test devuelve un String, no se haria no?
        
    }

    /**
     * Test of ListaUsuario method, of class Sistema.
     */
    @Test
    public void testListaUsuario() {//este test devuelve un String, no se haria no?
        
    }

    /**
     * Test of saltarDias method, of class Sistema.
     */
    @Test
    public void testSaltarDias() {//este metodo se creo por no esperar lo dias, no conseidera que sea testeable

    }


    
    /**
     * Test of eliminarUsuario method, of class Sistema.
     */
    
    //en los de eliminar usuario, la primera vez que borrama daba falla, al ejecutar otra vez bien, por eso los creo y los borro, para que no de fallo
    @Test
    public void testEliminarUsuario1() {//borramos un alumno
        sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@alumnos.urjc.es", "123", "Alumno"); //lo creamaos
        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i-1,sistema.getUsuarios().size());//miramos que el tamaño de ahora es igual al de antes de borrar -1
    }
     @Test
    public void testEliminarUsuario2() {//borramos un profe
        sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@urjc.es", "123", "Profesor"); //lo creamaos
        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i-1,sistema.getUsuarios().size());//miramos que el tamaño de ahora es igual al de antes de borrar -1
    }
     @Test
    public void testEliminarUsuario3() {//borramos un admin
         sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@urjc.es", "123", "Administrador"); //lo creamaos
        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i-1,sistema.getUsuarios().size());//miramos que el tamaño de ahora es igual al de antes de borrar -1
    }
    
    @Test
        public void testEliminarUsuario4() {//intentamos borrar un usuario que no esta registrado
        int i=sistema.getUsuarios().size(); // cogemos el tamaño del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("perico"); //borramos al usuario
        assertEquals(i,sistema.getUsuarios().size());//miramos que el tamaño del array es el mismo antes y despues de "eliminar" el usuario
    }
        
         @Test
    public void testGuardarSistema() {//comprobamos que podemos guardar el sistema
        assertTrue(sistema.guardarSistema());
    }

}
