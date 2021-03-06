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
public class EncuestaTest {
    /**
     * Test of addPreguntas method, of class Encuesta.
     */
    @Test
    public void testAddPreguntas() {
        Sistema s = Sistema.getInstance();
        String titulo = "Test";
        Profesor us = new Profesor("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123");
        Encuesta e = new Encuesta(us,titulo);
        int i = e.getPreguntas().size();
        e.addPreguntas(titulo);
        
        //miramos si se ha agregado la pregunta
        assertEquals(i+1,e.getPreguntas().size());
        s.deleteBD();
    }

    /**
     * Test of reponderPreguntas method, of class Encuesta.
     */
    @Test
    public void testReponderPreguntas() {
        Sistema s = Sistema.getInstance();
        String titulo = "Test";
        Profesor us = new Profesor("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123");
        Encuesta e = new Encuesta(us,titulo);
        e.addPreguntas(titulo);
        titulo = "Respuesta";
        int i = e.getRespuestas().size();
        e.reponderPreguntas(titulo, 0);
        
        //miramos si se agrega la respuesta a una pregunta fuera del rango del array
        assertEquals(i,e.getRespuestas().size());
        
        i = e.getRespuestas().size();
        e.reponderPreguntas(titulo, 2);
        
        //probamos a responder la segunda pregunta habiendo solo una
        assertEquals(i,e.getRespuestas().size());
        
        i = e.getRespuestas().size();
        e.reponderPreguntas(titulo, 1);
        
        //probamos a responder la pregunta previamente agregada
        assertEquals(i+1,e.getRespuestas().size());
        s.deleteBD();
    }    
}
