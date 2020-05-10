/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import org.junit.Test;
import static org.junit.Assert.*;
import users.Alumno;
import users.Usuario;

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
        String titulo = "TITULO PRUEBA";
        String texto = "Esto es una prueba de entrada.";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloPlano = "Titulo texto plano";
        String texto2 = "Esto es la prueba del tecto plano";    
        post.addTextoPlano(us, tituloPlano, texto2);
        //miramos si se ha añadido el texto plano
        assertEquals(i+1,post.getComponentes().size());
        
        
    }

    /**
     * Test of addEncuesta method, of class Entrada.
     */
    @Test
    public void testAddEncuesta() {
        String titulo = "TITULO PRUEBA 2";
        String texto = "Esto es una segunda prueba de entrada.";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloE = "Titulo encuesta";   
        post.addEncuesta(us, tituloE);
        //miramos si se ha añadido el texto plano
        assertEquals(i+1,post.getComponentes().size());
    }

    /**
     * Test of addEjercicio method, of class Entrada.
     */
    @Test
    public void testAddEjercicio() {
        String titulo = "TITULO PRUEBA 3";
        String texto = "Esto es una tercera prueba de entrada.";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloEj = "Titulo Ejercicios";
        String p = "Pregunta 1";  
        String r = "Respuesta 1";
        post.addEjercicio(us, tituloEj, p, r);
        //miramos si se ha añadido el texto plano
        assertEquals(i+1,post.getComponentes().size());
    }

    /**
     * Test of addComentario method, of class Entrada.
     */
    @Test
    public void testAddComentario() {
        String titulo = "TITULO PRUEBA 4";
        String texto = "Esto es una cuarta prueba de entrada.";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String com = "Comentario a añadir"; 
        post.addComentario(us, com);
        //miramos si se ha añadido el texto plano
        assertEquals(i+1,post.getComponentes().size());
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
