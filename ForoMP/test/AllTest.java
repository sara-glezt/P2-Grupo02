/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alvarogonzalez
 */
import Sistema.Sistema;
import org.junit.Before;
import users.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({UsuarioTest.class})
public class AllTest {
    /*  @Before
    public void metodoBefore(){
    Sistema sist = Sistema.getInstance();
    Alumno us = new Alumno("Pedro","Jimenez", "Garcia", "peter", "peter@alumnos.urjc.es","123");
    sist.logIn("peter@alumnos.urjc.es", "peter", "123");
    }
    */
    
}
