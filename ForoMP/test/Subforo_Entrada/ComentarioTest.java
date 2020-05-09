/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import org.junit.Test;
import static org.junit.Assert.*;
import users.Alumno;

/**
 *
 * @author Ruben
 */
public class ComentarioTest {
    /**
     * Test of responderComentario method, of class Comentario.
     */
    @Test
    public void testResponderComentario() {
        String s = "Test";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Comentario c = new Comentario(us,s);
        int i = c.getComentarios().size();
        c.responderComentario(us, s);
        
        assertEquals(i+1,c.getComentarios().size());
    }
    /**
     * Test of votar method, of class Comentario.
     */
    @Test
    public void testVotar() {
        String s = "Test";
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        Comentario c = new Comentario(us,s);
        int votos = c.getValoracion();
        c.votar(us, +1);
        //probamos a votar con el creador 
        assertEquals(votos,c.getValoracion());
        
        Alumno us2 = new Alumno("Paco","Garcia", "Garcia", "paquito", "paquito@alumnos.urjc.es","123");
        c.votar(us2, +2);
        //probamos a votar con otro usuario una valoracion no valida
        assertEquals(votos,c.getValoracion());
        
        c.votar(us2, +1);
        //probamos a votar con otro usuario
        assertEquals(votos+1,c.getValoracion());
        
        c.votar(us2, +1);
        //probamos a votar con el mismo usuario de antes, la misma puntuacion
        assertEquals(votos+1,c.getValoracion());
        
        
        c.votar(us2, -1);
        //probamos a votar con el mismo usuario de antes, la puntuacion contraria
        assertEquals(votos-1,c.getValoracion());
    }
    
}
