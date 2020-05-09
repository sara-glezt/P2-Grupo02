/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvarogonzalez
 */
public class AlumnoTest {
    
    public AlumnoTest() {
    }

    /**
     * Test of penalizar method, of class Alumno.
     */
    @Test
    public void testPenalizar() {
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        us.penalizar();

        assertEquals(2,us.getPenalizacion());
    }

    /**
     * Test of penalizado method, of class Alumno.
     */
    @Test
    public void testPenalizado() {
        Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        us.penalizar(); //penaliza con 2 dias
        
        assertTrue(us.penalizado());
        
    }

    /**
     * Test of actualizarPenalizacion method, of class Alumno.
     */
    @Test
    public void testActualizarPenalizacion() {
       Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
        us.penalizar();//penaliza con 2 dias
        us.actualizarPenalizacion(1); //quitamos 1
        assertEquals(1, us.getPenalizacion());//vemos que todavia está 1 dia penalizado
        assertTrue(us.penalizado());//miramos que sigue penalizado
        
        us.actualizarPenalizacion(1);//le quitamos otro dia
        assertFalse(us.penalizado());//vemos que ya no esta penalizado
        
        us.actualizarPenalizacion(7);  
        assertEquals(0,us.getPenalizacion());//vemos que que el limite inferior es 0
    }
    
}
