/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demostrador;
import Sistema.*;
/**
 *
 * @author alvarogonzalez
 */
public class MainDemostrador2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema s = Sistema.getInstance();
        
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        
        System.out.println("Veamos la persistencia de datos");
        System.out.println("");
        
        System.out.println("1-Comprobamos la lista de subforos");
        System.out.println(s.ListaSubforo());
        System.out.println("");
        
        System.out.println("2-Comprobamos la lista de usuarios");
        System.out.println(s.ListaUsuario());
       System.out.println("");
       
       System.out.println("3-Comprobamos las entradas del Subforo Deporte"); //mirar porque lo veo sin hacer login
        System.out.println(s.getSubforo().get(0).mostrarListaEntrada());
        System.out.println("");
        
        System.out.println("4-Vemos la valoracion del subforo");
        System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
        System.out.println("");
        
        System.out.println("5-Mostramos los elementos de la entrada");//mirar porque lo veo sin hacer login
        s.getSubforo().get(0).getEntrada().get(1).mostrar();
        System.out.println("");
        s.logOut();
        
        System.out.println("6-Mostramos los comentarios de la entrada");
         System.out.println(s.getSubforo().get(0).getEntrada().get(1).verComentarios());
         
         //penalizacion
        
        
    }
    
}
