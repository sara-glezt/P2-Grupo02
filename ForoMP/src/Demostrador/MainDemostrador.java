/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demostrador;

/**
 *
 * @author Equipo
 */
import Observer.*;
import Sistema.*;
import Subforo_Entrada.*;
import users.*;

public class MainDemostrador {

    public static void main(String[] args) {
        System.out.println("Empezamos la prueba del foro ");
        System.out.println("1. Creamos un sistema");
        Sistema s = Sistema.getInstance();

        if (s != null) {
            System.out.println("Se ha creado el Sistema");
        } else {
            System.out.println("No se ha creado el Sistema");
        }

        //Pruebas registro Usuario
        System.out.println("2. Registramos un usuario alumno");
        s.registrarse("Ruben", "A1", "A2", "grillos", "ruben@alumnos.urjc.es", "1122", "alumno");
        //System.out.println("Nombre: Ruben", "A1", "A2", "grillos", "ruben@alumnno.urjc.es", "1122", "alumno");

        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println("Esta lista solo la deberían ver los Admin, pero aquí la vemos todos para la prueba");
        System.out.println(s.ListaUsuario());
        System.out.println("");

        System.out.println("3. Intentamos registrar el mismo usuario (mismo email)");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben@alumnos.urjc.es", "1122", "alumno");

        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        System.out.println("");

        //debug: no devuelve el mensaje de nick igual
        System.out.println("4. Intentamos registrar el mismo usuario (mismo nick)");
        s.registrarse("Ruben", "A1", "A2", "grillos", "ruben12@alumnos.urjc.es", "1122", "alumno");

        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());
        System.out.println("");

        System.out.println("5. Intentamos registrar un profesor con un email de alumno");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben1@alumnos.urjc.es", "1122", "profesor");

        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());

        System.out.println("6. Intentamos registrar un usuario con otro tipo (limpiador) ");
        s.registrarse("Ruben", "A1", "A2", "grillos1", "ruben1@alumnos.urjc.es", "1122", "limpiador");

        System.out.println("Comprobamos que NO esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());

        //Metemos profe
        System.out.println("7. Intentamos registrar un Profesor ");

        //debug: se come @urjc.es con tipo alumno y lo mete en la lista
        s.registrarse("Antonio", "A1", "A2", "Tony", "antonio@urjc.es", "1122", "profesor");

        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());

        //Registramos un admin
        System.out.println("Intentamos registrar un Admin ");
        s.registrarse("Gustavo", "A1", "A2", "Gusyluz", "gustavo@urjc.es", "1122", "administrador");

        System.out.println("Comprobamos que esta en la lista de Usuarios");
        System.out.println(s.ListaUsuario());

        //FASE2 Intentar hacer LOGIN y LOGOUT
        System.out.println("LOGIN Y LOGOUT");
        System.out.println("");

        System.out.println("1. Hacemos login con un usuario NO registrado previamente");
        s.logIn("gustavo@urjc.com", "Gusyluz", "1122");
        System.out.println("");

        System.out.println("2. Hacemos login con un usuario registrado previamente");
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        System.out.println("");

        System.out.println("3. Hacemos login de otro usuario sin hacer logout");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("");

        System.out.println("4. Hacemos logout");
        s.logOut();
        System.out.println("");

        System.out.println("5. Hacemos otro logout con el sistema no iniciado");
        s.logOut();
        System.out.println("");

        //Empezamos a crear subforos y entradas
        System.out.println("CREAR ENTRADAS Y SUBFOROS");
        System.out.println("");

        System.out.println("1.Intentamos crear un subforo con ningun usuario conectado");
        s.crearSubforo("Deportes");
        System.out.println("");

        System.out.println("2.Intentamos crear un subforo con un alumno");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.crearSubforo("Deportes");
        System.out.println("");

        System.out.println("3.Intentamos crear un subforo con un profesor");
        System.out.println("Para ello hacemos logOut del alumno y logIn del profesor");
        s.logOut();
        s.logIn("antonio@urjc.es", "Tony", "1122");
        s.crearSubforo("Deportes");
        
        System.out.println("");

        System.out.println("4. Creamos una entrada con un usuario profesor y vemos si se crea");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Nadal gana al Covid", "De un reves le mando de vuelta");
        System.out.println(s.getSubforo().get(0).mostrarListaEntrada());
        System.out.println("");

        System.out.println("5. El mismo creador vota la entrada con 1");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
            System.out.println("");
        } else {
            System.out.println("La entrada no esta verificada");
        }

        System.out.println("");

        System.out.println("6. Comprobamos que la valoracion no cambia");
        System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
        System.out.println("");

        System.out.println("7. Hacemos logout, conectamos a otro usuario y la vota con 1");
        s.logOut();
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
            System.out.print("Valoracion: ");
            System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
           
            System.out.println("");
        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println("");

        System.out.println("8. Hacemos que el usuario vote lo mismo y vemos como varia");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 1);
            System.out.print("Valoracion: ");
            System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
            System.out.println("");
        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println("");

        System.out.println("9. Hacemos que el usuario vote lo contrario, un -1");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), -1);
            System.out.print("Valoracion: ");
            System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());

            System.out.println("");
        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println("");

        System.out.println("10. Hacemos que el usuario vote algo no valido");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), 7);
            System.out.println("");
        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println("");

        System.out.println("11. Hacemos que vote otro usuario mas con -1 y comprobamos la suma");
        s.logOut();
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        if (s.getSubforo().get(0).getEntrada().get(0).getVerificado()) {
            System.out.print("Valoracion: ");
            s.getSubforo().get(0).getEntrada().get(0).votar(s.getConectado(), -1);

        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println(s.getSubforo().get(0).getEntrada().get(0).getValoracion());
        s.logOut();
        System.out.println("");

        //mostar lista si esta publicado
        //hacer mostar Subscritos?
        //en verificar(EntradaGenerica) si es de alummno y esta a false entonces penalizar
        //intentar votar sin haber verificado
        System.out.println("12- Añadimos un suscriptor a un subforo");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());
        System.out.println("");

        System.out.println("13- Intentamos suscribir el mismo usuario otra vez a un subforo que ya esta sucrito");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());
        s.logOut();
        System.out.println("");

        System.out.println("14-Iniciamos sesion con otro usuario para susribirlo");
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        s.getSubforo().get(0).addSubscriptor(s.getConectado());
        s.logOut();
        System.out.println("");

        System.out.println("15-Uniciamos sesio con alumno  para crear una entrada ");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Elefante gana atleta", "Dumbo volo y ganó");
        System.out.println("");

        System.out.println("16-Como la ha creado un alummno y no esta verificada comprobamos que no se puede ver por el alumnado ni se ha notificado");
        System.out.println(s.getSubforo().get(0).mostrarListaEntrada());
        s.getConectado().mostrarNotificaciones();
        System.out.println("No nos aparece la notificacion porque no esta verificado todavia, solo aparece la que creo el profesor");
        System.out.println("");

        System.out.println("17- Intentamos votar la entrada sin ser verificada y vemos que no podemos");
        if (s.getSubforo().get(0).getEntrada().get(1).getVerificado()) {
            s.getSubforo().get(0).getEntrada().get(1).votar(s.getConectado(), -1);

        } else {
            System.out.println("La entrada no esta verificada");
        }
        System.out.println("");

        System.out.println("18- Intentamos comentar la entrada que no esta verificada");
        s.getSubforo().get(0).getEntrada().get(1).addComentario(s.getConectado(), "Madre Willy, que me cuentas");
        System.out.println("");

        System.out.println("19-Añadimos un texto plano a la entrada para ver efectivamente por ser el creador nos deja");
        s.getSubforo().get(0).getEntrada().get(1).addTextoPlano(s.getConectado(), "Mas de 100 Km/h", "Alcanzo la velocidad del sonido");

        System.out.println("");

        System.out.println("20- Intentamos verla, pero como no esta verificada no se puede");
        s.getSubforo().get(0).getEntrada().get(1).mostrar();

        System.out.println("");
        System.out.println("21- Cerramos sesion, y la iniciamos como admin para verficar la entrada");
        s.logOut();
        s.logIn("gustavo@urjc.es", "Gusyluz", "1122");
        s.getSubforo().get(0).getEntrada().get(1).verificar(true);
        System.out.println("");

        System.out.println("22-Una vez verificada vemos si ha notificado a los subscritores del subforo");
        s.getConectado().mostrarNotificaciones();
        System.out.println("");

        System.out.println("");
        System.out.println("23-Una vez verificada, vemos con un Alumno si es asi");
        s.logOut();
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("Mostramos la entrada");
        s.getSubforo().get(0).getEntrada().get(1).mostrar();
        System.out.println("");

        
        System.out.println("24- Probamos a comentar la entrada una vez verificada");
        s.getSubforo().get(0).getEntrada().get(1).addComentario(s.getConectado(), "Madre Willy, que me cuentas");
        System.out.println("");

        System.out.println("25- Probamos a comentar el comentario");
        s.getSubforo().get(0).getEntrada().get(1).getComentarios().get(0).responderComentario(s.getConectado(), "Como lo oyes Vegetta");
        System.out.println("");

        System.out.println("26- Vemos que se ha podido comentar la entrada y el propio comentario");
        System.out.println(s.getSubforo().get(0).getEntrada().get(1).verComentarios());
        System.out.println("");
        

        System.out.println("27- Creamos una encuesta con un alumno para ver que pasa");
        s.getSubforo().get(0).getEntrada().get(1).addEncuesta(s.getConectado(), "Aprobaras?");
        s.logOut();
        System.out.println("");

        System.out.println("28- Creamos la encuesta con un profesor");
        s.logIn("antonio@urjc.es", "Tony", "1122");
        s.getSubforo().get(0).getEntrada().get(1).addEncuesta(s.getConectado(), "Comparte tu opinion");
        System.out.println("");

        System.out.println("29- Añadimos un par de preguntas a la encuesta.");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).añadirPreguntas("De que color es el cielo");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).añadirPreguntas("Como crees que esta saliendo la practica");
        System.out.println("");
        
        System.out.println("30- Intentamos meter una pregunta repetida y vemos que no podemos");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).añadirPreguntas("De que color es el cielo");
        System.out.println("");

        System.out.println("31- Vemos las preguntas que tiene nuestra encuesta");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).verPreguntas();
        s.logOut();
        System.out.println("");
        
        System.out.println("32- Iniciamos sesion con un alummno para responder las preguntas.");
         s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
         System.out.println("");

        System.out.println("33- Respondemos las preguntas de nuestra encuesta");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).reponderPreguntas("Azul", 1);
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).reponderPreguntas("Está saliendo de lujo", 2);
        System.out.println("");

        System.out.println("34- Vemos las respuestas de nuestra escuenta");
        s.getSubforo().get(0).getEntrada().get(1).getEncuestas().get(0).verRespuestas();
        
        System.out.println("");
        
        System.out.println("35- Vamos a crear un ejercicio con un Alumno");
        s.getSubforo().get(0).getEntrada().get(1).addEjercicio(s.getConectado(), "Apendre mates", "¿Cuanto es 2+2?", "4");
        System.out.println("");
        
        System.out.println("36.- Creamos el ejercico con un profesor");
        s.logOut();
        s.logIn("antonio@urjc.es", "Tony", "1122");
        s.getSubforo().get(0).getEntrada().get(1).addEjercicio(s.getConectado(), "Apendre mates", "¿Cuanto es 2+2?", "4");
        System.out.println("");
        
        System.out.println("37- Mostramos el ejercicio con su solucion");
          s.getSubforo().get(0).getEntrada().get(1).getEjercicios().get(0).mostrar();
        System.out.println("");
        
        System.out.println("38- Vemos todo el contenido de la entrada");
        s.getSubforo().get(0).getEntrada().get(1).mostrar();
        System.out.println("");
        
        System.out.println("ENTRADAS MAS VOTADAS");
        System.out.println("");
        
        System.out.println("1-Para ello primeros creamos algunas entradas mas");
        s.getSubforo().get(0).crearEntrada(s.getConectado(), "Cristiano le mete un gol al covid", "Tiro libre y por la escuadra!!");
        s.getSubforo().get(0).crearEntrada(s.getConectado(),"Anulacion de la liga", "Con motivo del covid se suspende la ligas hasta septiembre");
        System.out.println("");
        
        System.out.println("2- Votamos una entrada negativa para que sea la menos votada, para ello cambiamos de usuario");
        s.logOut();
        System.out.println("");
        
        System.out.println("3- Iniciamos sesion con un usuario para que las vote");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        System.out.println("Votamos la etrada <Nadal gana al covid> con -1");
        s.getSubforo().get(0).getEntrada().get(3).votar(s.getConectado(), -1);
        System.out.println("");
        
        System.out.println("4-  Veamos primero que nos muestra todas las entradas");
        System.out.println(s.getSubforo().get(0).mostrarListaEntrada());
        System.out.println("5- Vemos que las entradas mas Votadas son: ");
        s.getMasVotadas();
        System.out.println("");
        
               
       
        System.out.println("");
        s.logOut();
        System.out.println("6-Intentamos ver las entradas mas votadas sin hacer login");
        
        System.out.println("Las entradas mas Votadas son");
        s.getMasVotadas();
        System.out.println("");
        
        System.out.println("VOTAR COMENTARIOS");
        System.out.println("");
        
        System.out.println("1.Volvemos a conectarnos para comentar una entrada");
        s.logIn("ruben@alumnos.urjc.es", "grillos", "1122");
        s.getSubforo().get(0).getEntrada().get(1).addComentario(s.getConectado(), "Esto es un comentario para votar");
        System.out.println("2- Mostramos que se ha añadido el comentario");
        System.out.println(s.getSubforo().get(0).getEntrada().get(1).verComentarios());
        
        System.out.println("");
        System.out.println("3.Ahora vamos a votar ese comentario con el mismo usuario. NO DEBERIA");
        s.getSubforo().get(0).getEntrada().get(1).getComentarios().get(0).votar(s.getConectado(), -1);
        
        System.out.println("Salimos del sistema para conectar a otro que puda votar el comentario");
        s.logOut();
        System.out.println("");
        System.out.println("4.Entramos con Antonio");
        s.logIn("antonio@urjc.es", "Tony", "1122");
        
        System.out.println("");
        System.out.println("5.Antonio vota el comentario con -1");
        s.getSubforo().get(0).getEntrada().get(1).getComentarios().get(0).votar(s.getConectado(), -1);
        s.getSubforo().get(0).getEntrada().get(1).getComentarios().get(0).getValoracion();
        
        System.out.println("");
        System.out.println("6.Vemos la valoracion del comentario:");
        System.out.println(s.getSubforo().get(0).getEntrada().get(1).getComentarios().get(0).getValoracion());
        

        
        
        

        
        
    }

}
