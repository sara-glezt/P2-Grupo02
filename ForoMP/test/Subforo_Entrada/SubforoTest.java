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
        s.crearSubforo("Alimentacion");//creamos el subforo
        int i = s.getSubforo().get(0).getUsuariosSuscritos().size(); //obtenemos el tamaño del array de suscritos
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123"); //cambiamos de usuaario para suscribirlo
        s.getSubforo().get(0).addSubscriptor(s.getConectado());
        s.logOut();
        assertEquals(i + 1, s.getSubforo().get(0).getUsuariosSuscritos().size());//comporbamos que efectivamente se ha suscrito

    }
    
     @Test
    public void testAddSubscriptor2() {//intentamos suscribir un usuario ya suscrito al subforo
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }

        Sistema s = Sistema.getInstance();
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");//creamos el subforo
        int i = s.getSubforo().get(0).getUsuariosSuscritos().size(); //obtenemos el tamaño del array de suscritos
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123"); //cambiamos de usuaario para suscribirlo
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//suscribimos al usuario
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//suscribimos de nuevo al usuario
        s.logOut();
        assertEquals(i + 1, s.getSubforo().get(0).getUsuariosSuscritos().size());//comporbamos que efectivamente solo se suscribe una vez

    }
    

    /**
     * Test of notifySubscriptor method, of class Subforo.
     */
    @Test
    public void testNotifySubscriptor() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }

        Sistema s = Sistema.getInstance();//obtenemos el sistema y creamos un par de usuarios
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");//creamos el subforo
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//añadimos el suscritor
        s.logOut();

        s.logIn("pepe@urjc.es", "pepe", "123");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Entada prueba", "Esto es mi prueba");//creamos la entrada en el subforo
        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");

        assertEquals(s.getConectado().getNotificaciones().size(), 1);//comprobamos que al suscritor se le ha notificado
        s.logOut();
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

        Sistema s = Sistema.getInstance();//creamos un par de usuarios
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");
        s.registrarse("Juan", "a1", "a2", "juan", "juan@alumnos.urjc.es", "123", "Alumno");
        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");//craemos el subforo

        s.logOut();
        s.logIn("juan@alumnos.urjc.es", "juan", "123");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());//añadimos el suscritor
        int i = s.getSubforo().get(0).getUsuariosSuscritos().size();//nos quedamos con el tamaño del array
        s.getSubforo().get(0).deleteSubscriptor(s.getConectado());//borramos el suscritos
        s.logOut();
        assertEquals(i - 1, s.getSubforo().get(0).getUsuariosSuscritos().size());//comprobamso que hay un suscritor menos
    }

    /**
     * Test of crearEntrada method, of class Subforo.
     */
    @Test
    public void testCrearEntrada() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }

        Sistema s = Sistema.getInstance();
        s.registrarse("Pepe", "a1", "a2", "pepe", "pepe@urjc.es", "123", "Profesor");

        s.logIn("pepe@urjc.es", "pepe", "123");
        s.crearSubforo("Alimentacion");//creamos el subforo
        int i = s.getSubforo().get(0).getEntrada().size();
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Entrada prueba", "Esto es mi prueba");//creamos la entrada

        s.logOut();
        assertEquals(i + 1, s.getSubforo().get(0).getEntrada().size());//comprobamos que se ha creado la entrada

    }

}
