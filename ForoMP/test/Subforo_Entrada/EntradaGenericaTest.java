/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.io.File;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import users.Usuario;

/**
 *
 * @author Equipo
 */
public class EntradaGenericaTest {
    


    @Test
    public void testVotar() {//esta en comentario; es el mismo
    }

    @Test
    public void testVerificar() {
             
        Sistema s = Sistema.getInstance();

        s.registrarse("Ascension", "Lovillo", "Perollono", "Ascen", "ascen@urjc.es", "123", "Profesor");
        s.logIn("ascen@urjc.es", "Ascen", "123");
        s.crearSubforo("Subforo1");
        s.logOut();
        s.registrarse("Dani", "Haro", "Murcia", "danii", "danii@alumnos.urjc.es", "123", "Alumno");
        s.logIn("danii@alumnos.urjc.es", "danii", "123");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Entrada1", "Verificame por favor");
        boolean b = true;        
        //Probamos a verificar con un Alumno
        assertFalse(s.getSubforo().get(0).getEntrada().get(0).verificar(b));
        s.logOut();
        s.deleteBD();
        
    }
    
    @Test
    public void testVerificar1() {
            
        Sistema s = Sistema.getInstance();

        s.registrarse("Ascension", "Lovillo", "Perollono", "Ascen", "ascen@urjc.es", "123", "Profesor");
        s.logIn("ascen@urjc.es", "Ascen", "123");
        s.crearSubforo("Subforo1");
        s.logOut();
        s.registrarse("Dani", "Haro", "Murcia", "danii", "danii@alumnos.urjc.es", "123", "Alumno");
        s.logIn("danii@alumnos.urjc.es", "danii", "123");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Entrada1", "Verificame por favor");
        s.logOut();
        s.registrarse("Paco", "admin", "1", "admin1", "adminPaco@urjc.es", "123", "Administrador");
        s.logIn("adminPaco@urjc.es", "admin1", "123");
        boolean b = true;  
        
        //Verificamos con Admin
        assertTrue(s.getSubforo().get(0).getEntrada().get(0).verificar(b));
        s.logOut();
        s.deleteBD();
    }




    @Test
    public void testModificarEntrada() {//se hace en las componentes de la entrada
    }

    public class EntradaGenericaImpl extends EntradaGenerica {

        public EntradaGenericaImpl() {
            super(null, "");
        }

        public void mostrar() {
        }

        public void modificarEntrada(Usuario u, String s) {
        }
    }
    
}

