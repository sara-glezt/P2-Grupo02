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

        System.out.println("6-Mostramos los comentarios de la entrada");
        System.out.println(s.getSubforo().get(0).getEntrada().get(1).verComentarios());
        s.logOut();

        System.out.println("1-Un alumno crea una entrada para que no sea verficada ");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Pito dulce gana salto longuitud", "Menudo salto");
        System.out.println("");
        s.logOut();

        System.out.println("2- Inicamos sesion con el admin para banearla");
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        s.getSubforo().get(0).getEntrada().get(2).verificar(false);
        s.logOut();
        System.out.println("");

        System.out.println("3- Intentamos iniciar sesion como Ruben, y veremos que esta baneado");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("");

        System.out.println("4-Pasamos los dias para quitarle el baneo y ver que puede iniciar sesion");
        s.saltarDias(3);
        System.out.println("");

        System.out.println("5-Iniciamos sesion para ver que se la quitado el baneo");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("");

        System.out.println("6- Probamos a dar de baja en el subforo");
        s.getSubforo().get(0).deleteSubscriptor(s.getConectado());

        System.out.println("");

        System.out.println(" Hemos implementado la opción modificar() SOLO para TextoPlano. Dado que es un metodo sobreescrito de EntradaGenerica,\n"
                + "simplemente habría que implementar como se quiere modificar cada componente de la entrada, por ejemplo cambiando una \n"
                + "pregunta o modificando un texto de la encuesta");
        System.out.println("");
        System.out.println("7, Vamos a modificar el texto plano de la entrada creada por ruben");
        System.out.println("");
        System.out.println("La entrada antes de la modificación es:");

        s.getSubforo().get(0).getEntrada().get(1).mostrar();
        System.out.println("");
        System.out.println("La entrada depues de la modificacion es:");

        s.getSubforo().get(0).getEntrada().get(1).getComponentes().get(0).modificarEntrada(s.getConectado(), "Pepe lo bordo");
        s.getSubforo().get(0).getEntrada().get(1).mostrar();

        s.logOut();

            System.out.println("8-Eliminamos un usario");
         s.eliminarUsuario("grillos");
         System.out.println("");
         
         System.out.println("9-Comprobamos que se ha borrado");
         System.out.println(s.ListaUsuario());
         
         System.out.println("9-Eliminamos el subforo");
         s.eliminarSubforo("Deportes");
         System.out.println("");
         
         System.out.println(s.ListaSubforo());
    }

}
