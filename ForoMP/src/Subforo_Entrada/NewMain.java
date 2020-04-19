/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;
import users.*;
/**
 *
 * @author alvarogonzalez
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Encuesta e = new Encuesta("MI PRIMERA PRUEBA");
        e.añadirPreguntas("Como te llamas");
        e.añadirPreguntas("Peli fav");
        e.añadirPreguntas("");
        e.verPreguntas();
        e.reponderPreguntas("Juan", 1);
        e.reponderPreguntas("iron man", 2);
        e.verRespuestas();
        Subforo s = new Subforo("mi primer subforo");
        Usuario u = new Usuario("Juan", "Baltasar", "pepe", "naboDulce", "www.urjc.es", "montiel");
        Usuario w = new Usuario("Juan", "Baltasar", "pepe", "naboDulce", "www.urjc.es", "montiel");
        s.añadirSubscritor(u);
        s.eliminarSubscritor(u);
        
    }
    
}
