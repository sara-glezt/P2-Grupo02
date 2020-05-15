/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvarogonzalez
 */
public class SistemaTest {
//Sistema sistema = Sistema.getInstance();

    /**
     * Test of registrarse method, of class Sistema.
     */
    @Test
    public void testRegistrarse1() {//registramos un aummno
        
        Sistema sistema = Sistema.getInstance();
        
        assertTrue(sistema.registrarse("Javier", "Arcos", "Lopez", "xavi", "xavi@alumnos.urjc.es", "123", "Alumno"));
        sistema.deleteBD();
    }

    @Test
    public void testRegistrarse2() {//registramos un profe
        
        Sistema sistema = Sistema.getInstance();
        assertTrue(sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor"));
sistema.deleteBD();
    }

    @Test
    public void testRegistrarse3() {//registramos un admin
        
        Sistema sistema = Sistema.getInstance();
        assertTrue(sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador"));
sistema.deleteBD();
    }

    /*   @Test
    public void testRegistrarse4() {//registramos un Usuario con un nick repetido
    assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel1@urjc.es", "123", "Administrador"));
    
    }*/
    @Test
    public void testRegistrarse5() {//registramos un usuario con un email no valido
       
        Sistema sistema = Sistema.getInstance();
        assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angel2", "angelperez@pojzbxvckz.urjc.es", "123", "Administrador"));
        sistema.deleteBD();
    }

    @Test
    public void testRegistrarse6() {//registramos un usuario que no es del tipo adecuado
        
        Sistema sistema = Sistema.getInstance();
        assertFalse(sistema.registrarse("Angel", "Garcia", "Lopez", "angellus", "angel@urjc.es", "123", "Furbolista"));
sistema.deleteBD();
    }
    
      /**
     * Test of logIn method, of class Sistema.
     */
    @Test
    public void testLogIn1() {//vamos a intentar hacer login
         
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador");
        assertTrue(sistema.logIn("angel@urjc.es", "angel", "123"));
        sistema.logOut();
        sistema.deleteBD();

    }

    @Test
    public void testLogIn2() {//vamos a intentar hacer login sin haber echo logOut
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador");
        sistema.registrarse("Felipe", "Garcia", "Lopez", "pipe", "pipe@urjc.es", "123", "Administrador");
        sistema.logIn("angel@urjc.es", "angel", "123");//hacemos login con un usuario
        assertFalse(sistema.logIn("pipe@urjc.es", "pipe", "123"));//hacemos login sin hacer logOut
        sistema.logOut(); //cerramos sesion para dejarla libre para la proxima prueba
        sistema.deleteBD();
    }

    /**
     * Test of crearSubforo method, of class Sistema.
     */
    @Test
    public void testCrearSubforo1() {//ceamos subforos sin estar logeado
       
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Angel", "Garcia", "Lopez", "angel", "angel@urjc.es", "123", "Administrador");
         sistema.logIn("angel@urjc.es", "angel", "123");//hacemos login con un usuario
        int i = sistema.getSubforo().size();//obtemos el v del array antes de crear un suboforo
        sistema.crearSubforo("Tecnologia"); //intentamos crear el subforo
        assertEquals(i,sistema.getSubforo().size());//Como no podemos, el valor es el mismo despues de itentar crearlo
        sistema.logOut();
        sistema.deleteBD();
    }

    @Test
    public void testCrearSubforo2() {//ceamos subforos
      
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor");
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        int i = sistema.getSubforo().size();//obtemos el tamanios del array antes de crear un suboforo
        sistema.crearSubforo("Tecnologia"); //intentamos crear el subforo
        assertEquals(i + 1, sistema.getSubforo().size());//Como no podemos, el valor es el mismo despues de itentar crearlo
        sistema.logOut();
        sistema.deleteBD();
    }

    /**
     * Test of eliminarSubforo method, of class Sistema.
     */
    @Test
    public void testEliminarSubforo1() {
       
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor");
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        sistema.crearSubforo("Tecnologia");
        int i = sistema.getSubforo().size();//obtemos el tamanios del array despues de crear un suboforo
        sistema.eliminarSubforo("Tecnologia");
        assertEquals(i-1 , sistema.getSubforo().size());//comprobamos que ahora tenemos un subforo menos
        sistema.logOut();
        sistema.deleteBD();
    }

    @Test
    public void testEliminarSubforo2() {//un profe distitno al creador borra un subforo que no es suyo
        
       
        
        Sistema sistema = Sistema.getInstance();
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        sistema.crearSubforo("Comida"); //lo creamos con pipe
        sistema.logOut(); //cambiamos de usuario para borrarlo
        sistema.logIn("angel@urjc.es", "angel", "123"); //cambiamos de usuario para borrarlo
        int i = sistema.getSubforo().size(); // antes de intentar borrarlo cogemos el tamanios que tiene
        sistema.eliminarSubforo("Comida");//intentamos borrarlo con alguien que no es su creador
        assertEquals(i, sistema.getSubforo().size());//comprobamos que el tamanios no ha variado
        sistema.logOut();
        sistema.deleteBD();
    }

  

    /**
     * Test of logOut method, of class Sistema.
     */
    @Test
    public void testLogOut1() { //hacemos logOut del usuairo conectado
       
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Felipe", "badillo", "Lopez", "pipe", "pipe@urjc.es", "123", "Profesor");
        
        sistema.logIn("pipe@urjc.es", "pipe", "123");
        assertTrue(sistema.logOut());
        sistema.deleteBD();
    }

    @Test
    public void testLogOut2() { //hacemos logOut sin ningun usuario conectado
        
        Sistema sistema = Sistema.getInstance();
        assertFalse(sistema.logOut());
        sistema.deleteBD();
    }

    /**
     * Test of getMasVotadas method, of class Sistema.
     */
    @Test
    public void testGetMasVotadas1() {//como solo hay una entrada, me cogerá la que hay, aunque tenga un valor negativo
        
        Sistema sistema = Sistema.getInstance();
    sistema.registrarse("Antonio", "a1", "a2", "anton", "anton@urjc.es", "123", "Profesor");
    sistema.registrarse("xavi", "a1", "a2", "xavi22", "xavi22@alumnos.urjc.es", "123", "Alumno");
    sistema.logIn("anton@urjc.es", "anton", "123");
    sistema.crearSubforo("Nutricion");//cremos el subofor para agregarle las entrada y votarlas
    sistema.getSubforo().get(0).crearEntrada(sistema.getConectado(), "Mi prueba", "El test no da error");
    sistema.logOut();
    sistema.logIn("xavi22@alumnos.urjc.es", "xavi22", "123");//votamos todas la entradas menos una, que sera la que tenga menos valoracion(0)
    sistema.getSubforo().get(0).getEntrada().get(0).votar(sistema.getConectado(), -1);
    assertEquals(1,sistema.getMasVotadas().size());
    sistema.logOut();
    sistema.deleteBD();
    }
    
    @Test
    public void testGetMasVotadas2() {//Creamos 4 entradas, pero solo tenemos que obtener las tres mas votadas
         
        
    Sistema sistema = Sistema.getInstance();
    sistema.registrarse("Antonio", "a1", "a2", "anton", "anton@urjc.es", "123", "Profesor");
    sistema.registrarse("xavi", "a1", "a2", "xavi22", "xavi22@alumnos.urjc.es", "123", "Alumno");
    sistema.logIn("anton@urjc.es", "anton", "123");
    sistema.crearSubforo("Nutricion");//cremos el subofor para agregarle las entrada y votarlas
    sistema.getSubforo().get(0).crearEntrada(sistema.getConectado(), "Mi prueba", "El test no da error");
    sistema.getSubforo().get(0).crearEntrada(sistema.getConectado(), "Mi prueba1", "El test no da error");
    sistema.getSubforo().get(0).crearEntrada(sistema.getConectado(), "Mi prueba2", "El test no da error");
    //creamos otra ya que me va a mostrar solo las 3 mas votadas
    sistema.getSubforo().get(0).crearEntrada(sistema.getConectado(), "Mi prueba3", "El test no da error");
    sistema.logOut();
    sistema.logIn("xavi22@alumnos.urjc.es", "xavi22", "123");//votamos todas la entradas menos una, que sera la que tenga menos valoracion(0)
    sistema.getSubforo().get(0).getEntrada().get(0).votar(sistema.getConectado(), 1);
    sistema.getSubforo().get(0).getEntrada().get(2).votar(sistema.getConectado(), 1);
    sistema.getSubforo().get(0).getEntrada().get(3).votar(sistema.getConectado(), 1);
    assertEquals(3,sistema.getMasVotadas().size());//como solo cojo las 3 mas votadas, tiene que haber 3 y no 4 que es el total
    sistema.logOut();
    sistema.deleteBD();
    }

    
    /**
     * Test of cargarSistema method, of class Sistema.
     */
    @Test
    public void testCargarSistema() {// devuelve un sistema, como lo hago?

    }

    /**
     * Test of eliminarUsuario method, of class Sistema.
     */
    //en los de eliminar usuario, la primera vez que borrama daba falla, al ejecutar otra vez bien, por eso los creo y los borro, para que no de fallo
    @Test
    public void testEliminarUsuario1() {//borramos un alumno
       
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@alumnos.urjc.es", "123", "Alumno"); //lo creamaos
        int i = sistema.getUsuarios().size(); // cogemos el tamanios del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i - 1, sistema.getUsuarios().size());//miramos que el tamanios de ahora es igual al de antes de borrar -1
        sistema.deleteBD();
    }

    @Test
    public void testEliminarUsuario2() {//borramos un profe
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@urjc.es", "123", "Profesor"); //lo creamaos
        int i = sistema.getUsuarios().size(); // cogemos el tamanios del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i - 1, sistema.getUsuarios().size());//miramos que el tamanios de ahora es igual al de antes de borrar -1
        sistema.deleteBD();
    }

    @Test
    public void testEliminarUsuario3() {//borramos un admin
        
        Sistema sistema = Sistema.getInstance();
        sistema.registrarse("Miguel", "Lopez", "Arriba", "mikel", "mikel@urjc.es", "123", "Administrador"); //lo creamaos
        int i = sistema.getUsuarios().size(); // cogemos el tamanios del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("mikel"); //borramos al usuario
        assertEquals(i - 1, sistema.getUsuarios().size());//miramos que el tamanios de ahora es igual al de antes de borrar -1
        sistema.deleteBD();
    }

    @Test
    public void testEliminarUsuario4() {//intentamos borrar un usuario que no esta registrado
        
        Sistema sistema = Sistema.getInstance();
        int i = sistema.getUsuarios().size(); // cogemos el tamanios del array de usuarios antes de borrar uno
        sistema.eliminarUsuario("perico"); //borramos al usuario
        assertEquals(i, sistema.getUsuarios().size());//miramos que el tamanios del array es el mismo antes y despues de "eliminar" el usuario
    sistema.deleteBD();
    }

    /**
     * Test of guardarSistema method, of class Sistema.
     */
    @Test
    public void testGuardarSistema() {//comprobamos que podemos guardar el sistema
        
        Sistema sistema = Sistema.getInstance();
        assertTrue(sistema.guardarSistema());
        sistema.deleteBD();
    }

}
