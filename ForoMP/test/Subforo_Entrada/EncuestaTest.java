/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Alumno;
import users.Profesor;

/**
 *
 * @author Ruben
 */
public class EncuestaTest {
    /**
     * Test of añadirPreguntas method, of class Encuesta.
     */
    @Test
    public void testAñadirPreguntas() {
        Sistema s = new Sistema();
        String titulo = "Test1";
        Profesor us = new Profesor("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123");
        Encuesta e = new Encuesta(us,titulo); //salta error aqui por hacer Sistema.getInstance()
        int i = e.getPreguntas().size();
        e.añadirPreguntas(titulo);
        //miramos si se ha añadido la pregunta
        assertEquals(i+1,e.getPreguntas().size());
        
        
    }

    /**
     * Test of reponderPreguntas method, of class Encuesta.
     */
    @Test
    public void testReponderPreguntas() {
    }

    /**
     * Test of verRespuestas method, of class Encuesta.
     */
    @Test
    public void testVerRespuestas() {
    }

    /**
     * Test of verPreguntas method, of class Encuesta.
     */
    @Test
    public void testVerPreguntas() {
    }

    /**
     * Test of mostrar method, of class Encuesta.
     */
    @Test
    public void testMostrar() {
    }

    /**
     * Test of modificarEntrada method, of class Encuesta.
     */
    @Test
    public void testModificarEntrada() {
    }
    
}
