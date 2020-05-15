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
        Sistema s = Sistema.getInstance();
        String titulo = "TITULO PRUEBA";
        String texto = "Esto es una prueba de entrada.";
        Alumno us = new Alumno("Pedro", "Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es", "123");
        Entrada post = new Entrada(us, titulo, texto);
        int i = post.getComponentes().size();
        String tituloPlano = "Titulo texto plano";
        String texto2 = "Esto es la prueba del tecto plano";
        post.addTextoPlano(us, tituloPlano, texto2);
        //miramos si se ha agregada el texto plano
        assertEquals(i + 1, post.getComponentes().size());
        s.deleteBD();
    }

    /**
     * Test of addEncuesta method, of class Entrada.
     */
    @Test
    public void testAddEncuesta() {
        
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
        //miramos si se ha agregado el texto plano
        assertEquals(i + 1, e.getComponentes().size());
        s.deleteBD();
    }

    /**
     * Test of addEjercicio method, of class Entrada.
     */
    @Test
    public void testAddEjercicio() {
        
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
        
        int i = s.getSubforo().get(0).getEntrada().get(0).getEjercicios().size();
        s.getSubforo().get(0).getEntrada().get(0).addEjercicio(us, "Ejer Prueba", "Esto furula", "yuuupi");
        assertEquals(i+1,s.getSubforo().get(0).getEntrada().get(0).getEjercicios().size());
        s.logOut();
        s.deleteBD();
    }

    /**
     * Test of addComentario method, of class Entrada.
     */
    @Test
    public void testAddComentario() {
        
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
        String com = "Comentario a agregar";
        e.addComentario(us, com);
        //miramos si se ha agregado el texto plano
        assertEquals(i + 1, e.getComentarios().size());
        s.deleteBD();
    }

}
