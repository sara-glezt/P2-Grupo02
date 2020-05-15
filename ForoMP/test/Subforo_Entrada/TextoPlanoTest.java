/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import org.junit.Test;
import static org.junit.Assert.*;
import users.Profesor;

/**
 *
 * @author Ruben
 */
public class TextoPlanoTest {
    
    /**
     * Test of modificarEntrada method, of class TextoPlano.
     */
    @Test
    public void testModificarEntrada() {
        Sistema s = new Sistema();
        Profesor us = new Profesor("Pedro","Jimenez", "Garcia", "peter", "peter@urjc.es","123");
        TextoPlano txt = new TextoPlano(us,"Esto es la prueba", " no esta modificada todavia");
        txt.modificarEntrada(us, "Ahora si esta modificada");
        assertNotEquals(txt.getCuerpo()," no esta modificada todavia" );
        
    }
    
}
