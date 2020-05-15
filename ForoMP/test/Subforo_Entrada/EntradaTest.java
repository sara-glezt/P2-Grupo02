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
import users.Usuario;
import Subforo_Entrada.Subforo;
/**
 *
 * @author Ruben
 */
public class EntradaTest {

    public EntradaTest() {
    }

    /**
     * Test of addTextoPlano method, of class Entrada.
     */
    @Test
    public void testAddTextoPlano() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        String titulo = "TITULO PRUEBA";
        String texto = "Esto es una prueba de entrada.";
        Alumno us = new Alumno("Pedro", "Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es", "123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloPlano = "Titulo texto plano";
        String texto2 = "Esto es la prueba del tecto plano";
        post.addTextoPlano(us, tituloPlano, texto2);
        //miramos si se ha añadido el texto plano
        assertEquals(i + 1, post.getComponentes().size());

    }

    /**
     * Test of addEncuesta method, of class Entrada.
     */
    @Test
    public void testAddEncuesta() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        Sistema s = Sistema.getInstance();
        s.registrarse("Pedro", "Jiménez", "García", "peter", "peter@urjc.es", "123", "profesor");
        s.logIn("peter@urjc.es", "peter", "123");
        Usuario us = s.getConectado();
        
        s.crearSubforo("Subforo de prueba");
        Subforo sf = s.getSubforo().get(0);
        
        String titulo = "TITULO PRUEBA 2";
        String texto = "Esto es una segunda prueba de entrada.";
        sf.crearEntrada(us, titulo, texto);
        Entrada e = sf.getEntrada().get(0);
        
        int i = e.getComponentes().size();
        String tituloE = "Titulo encuesta";
        e.addEncuesta(us, tituloE);
        //miramos si se ha añadido el texto plano
        assertEquals(i + 1, e.getComponentes().size());
    }

    /**
     * Test of addEjercicio method, of class Entrada.
     */
    @Test
    public void testAddEjercicio() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        String titulo = "TITULO PRUEBA 3";
        String texto = "Esto es una tercera prueba de entrada.";
        Alumno us = new Alumno("Pedro", "Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es", "123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloEj = "Titulo Ejercicios";
        String p = "Pregunta 1";
        String r = "Respuesta 1";
        post.addEjercicio(us, tituloEj, p, r);
        //miramos si se ha añadido el texto plano
        assertEquals(i + 1, post.getComponentes().size());
    }

    /**
     * Test of addComentario method, of class Entrada.
     */
    @Test
    public void testAddComentario() {
        File bbdd = new File("Sistema.obj");
        if (bbdd.exists()) {
            bbdd.delete();
        }
        Sistema s = Sistema.getInstance();
        s.registrarse("Pedro", "Jiménez", "García", "peter", "peter@urjc.es", "123", "profesor");
        s.logIn("peter@urjc.es", "peter", "123");
        Usuario us = s.getConectado();
        
        s.crearSubforo("Subforo de prueba");
        Subforo sf = s.getSubforo().get(0);
        
        String titulo = "TITULO PRUEBA";
        String texto = "Esto es una prueba de entrada.";
        sf.crearEntrada(us, titulo, texto);
        Entrada e = sf.getEntrada().get(0);
        
        int i = e.getComentarios().size();
        String com = "Comentario a añadir";
        e.addComentario(us, com);
        //miramos si se ha añadido el texto plano
        assertEquals(i + 1, e.getComentarios().size());
    }

    /**
     * Test of getComentarios method, of class Entrada.
     */
    @Test
    public void testGetComentarios() {
    }

    /**
     * Test of verComentarios method, of class Entrada.
     */
    @Test
    public void testVerComentarios() {
    }

    /**
     * Test of mostrar method, of class Entrada.
     */
    @Test
    public void testMostrar() {
    }

    /**
     * Test of toString method, of class Entrada.
     */
    @Test
    public void testToString() {
    }

    /**
     * Test of getComponentes method, of class Entrada.
     */
    @Test
    public void testGetComponentes() {
    }

    /**
     * Test of modificarEntrada method, of class Entrada.
     */
    @Test
    public void testModificarEntrada() {
    }

    /**
     * Test of getEncuestas method, of class Entrada.
     */
    @Test
    public void testGetEncuestas() {
    }

    /**
     * Test of getEjercicios method, of class Entrada.
     */
    @Test
    public void testGetEjercicios() {
    }

    /**
     * Test of getTextos method, of class Entrada.
     */
    @Test
    public void testGetTextos() {
    }

}
