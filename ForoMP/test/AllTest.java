/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alvarogonzalez
 */

import users.*;
import Subforo_Entrada.*;
import Sistema.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SistemaTest.class, UsuarioTest.class,
               ProfesorTest.class, UsuarioTest.class, ComentarioTest.class, EjercicioTest.class, EncuestaTest.class, EntradaTest.class,
               SubforoTest.class, EntradaGenericaTest.class})
public class AllTest {
   
    
}
