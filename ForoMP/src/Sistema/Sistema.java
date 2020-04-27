/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Subforo_Entrada.Entrada;
import Subforo_Entrada.EntradaGenerica;
import Subforo_Entrada.Subforo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import users.*;

/**
 *
 * @author alvarogonzalez
 */
public class Sistema {

    private ArrayList<Usuario> usuario;
    private ArrayList<Subforo> subforo;
    private static Sistema instancia = null; //creo que el singleton con esto valdria y el constructor
    private Usuario conectado; //para saber que usuaario esta conectado(hacer login y logout con este usuario
    private ArrayList<Entrada> masVotadas;


    private void Sistema() { //declarado de esta forma el constructor hacemos el singleton
        usuario = new ArrayList<>();
        subforo = new ArrayList<>();
        masVotadas = new ArrayList<>();
    }

    public static Sistema getInstance() {
        if (instancia == null) {
            File bbdd = new File("Sistema.obj");
            if (bbdd.exists()) {
                instancia = cargarSistema();

            } else {
                instancia = new Sistema();
            }
        }
        return instancia;
    }

    public Usuario getConectado() {
        return conectado;
    }

    public boolean registrarse(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña, String tipo) {
        boolean valido = verificarNuevoUsuario(nombre, apellido1, apellido2, nick, email, contraseña, tipo);

        if (valido) {
            if (tipo.equals("alumno") || tipo.equals("Alumno")) {
                Usuario us = new Alumno(nombre, apellido1, apellido2, nick, email, contraseña);//poner ifes dependiendo del tipo users
                usuario.add(us);
                System.out.println("Se ha añadido un nuevo alumno.");
            } else if (tipo.equals("profesor") || tipo.equals("Profesor")) {
                Usuario us = new Profesor(nombre, apellido1, apellido2, nick, email, contraseña);//poner ifes dependiendo del tipo users
                usuario.add(us);
                System.out.println("Se ha añadido un nuevo profesor.");
            } else { //si no es profe ni alummn como he verificado, le queda solo ser admin
                Usuario us = new Administrador(nombre, apellido1, apellido2, nick, email, contraseña);//poner ifes dependiendo del tipo users
                usuario.add(us);
                System.out.println("Se ha añadido un nuevo profesor.");
            }
        }
        return valido;
    }

    //creo que verifico todo lo que se deberia, pero echarle un ojo por si acaso
    public boolean verificarNuevoUsuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña, String tipo) {//preguntar que hace exactamente
        Iterator<Usuario> i = usuario.iterator();

        Scanner sc = new Scanner(email); //cogemos la parte del @ en adelante del email
        sc.useDelimiter("@");
        sc.next();
        String e = sc.next();

        boolean aceptar = true;
        while (i.hasNext() & aceptar) { //comprobamos que el nick no se repita (se puede hacer con las demas propiedades)
            Usuario u = i.next();
            if (u.getEmail().equals(email)) {
                System.out.println("El nick introducido  ya esta usado, elija otro");
                aceptar = false;
            }
            if (u.getEmail().equals(email) || !e.equals("alumnos.urjc.es") || !e.equals("urcj.es")) { //verificamos el email
                System.out.println("El email introducido  ya esta usado o no es valido, elija otro");
                aceptar = false;
            }
        }

        if (!tipo.equals("Alumno") || !tipo.equals("Profesor") || !tipo.equals("Administrados")
                || !tipo.equals("alumno") || !tipo.equals("profesor") || !tipo.equals("administrados")) {
            System.out.println("El tipo de usuario no es valido. Asegurese de que sea A/alumno, P/profesor o A/administrados");
            aceptar = false;
        }
        return aceptar;
    }

    public void eliminarUsuario(String nick) {

        Iterator<Usuario> i = usuario.iterator();
        boolean aceptar = true;

        while (i.hasNext() & aceptar) { //bulce para recorrer a todos los usuarios
            Usuario us = i.next();
            if (us.getNick().equals(nick)) { //miro si existe ese usuario
                for (Subforo sub : subforo) {  // si existe, entonces voy recorriendo lo subforos
                    sub.deleteSubscriptor(us);   //voy eliminando ese usuarui de los subforos
                }
                usuario.remove(us);  //si lo encuenta lo borra
                System.out.println("El usuario de Nick < " + nick + " > ha sido eliminado");
                aceptar = false; //lo pongo a false para que se salga del bucle una vez se ha borrado
            }
        }
    }

    public void crearSubforo(String titulo) {
        boolean bol = true;
        Iterator<Subforo> i = subforo.iterator();

        while (i.hasNext() & bol) { //iterator para si lo encuento antes poder salirme
            Subforo sub = i.next();
            if (sub.getNombre().equals(titulo)) {//compruebo que el nobmre no esta usado
                System.out.println("Esta subforo ya existe, elija otro titulo e intentelo de nuevo");
                bol = false;

            }
        }

        if (bol = true) { //si el nombre no esta usado entonces lo creamos
            Subforo s = new Subforo(titulo); //creamos el subforo con el titulo (Ver que no se repitan el nombre?)
            subforo.add(s); // lo añadido a la list a de subforos
        }
    }

    public void eliminarSubforo(Subforo s) {
        for (Usuario us : usuario) { //recorro los usuarios y lo voy dando de baja del subforo que quiero eliminar
            us.darDeBajaSubforo(s.getNombre());
        }
        subforo.remove(s); //borro el subforo  s 
    }

    public boolean logIn(String email, String nick, String password) {
        boolean bool = false;
        boolean aceptar = true;
        Iterator<Usuario> i = usuario.iterator();

        if (conectado == null) { //miro que no haya ningun usuario conectado
            while (i.hasNext() & aceptar) { // busco el usuario con su caracterisitcas(cambiar por iterator)
                Usuario us = i.next();
                if (us.getNick().equals(nick) & us.getEmail().equals(email) & us.getContraseña().equals(password)) {

                    if (us instanceof Alumno) {   //miro si es un alumno para comprobar si esta penalizado
                        if (((Alumno) us).penalizado()) {
                            System.out.println("Este usuario se encuentra baneado");
                        } else {  //si no esta penalizado entonces hace login
                            System.out.println("Usuario Correcto");
                            conectado = us; //asignamos ese usuario para saber con cual estamos.
                            bool = true;
                        }
                    } else { //si no es alumno, entonces es profe o admin y hace login pues no se pueden banear
                        System.out.println("Usuario Correcto");
                        conectado = us; //asignamos ese usuario para saber con cual estamos.
                        bool = true;
                    }
                } else { //si no encuentro el usuario digo que no esta registrado
                    System.out.println("Usuario no registrado, asegurese de introducir bien los datos");
                }
            }
        }

        return bool;
    }

    public boolean logOut() {//que es exactamente lo que debe hacer?? osea, como oriento la programacion
        if (conectado != null) {
            conectado = null;
            System.out.println("Hasta luego; fin de la conexion"); // hacer el guardar sistema
            guardarSistema();
        } else {
            System.out.println("No hay ningun usuario conectado");
        }
        return true;
    }

    /*public ArrayList<Entrada> getEntradaMasVotadas(){ //obtengo de cada suforo las entradas mas votadas
    Entrada[] ent = new Entrada[3];
    for(Subforo s : subforo)
    ent = s.getMasVotada();
    for (int i = 0; i<3; i++){
    if(ent[i] != null){
    masVotadas.add(ent[i]);
    }
    }
    return masVotadas;
    
    }*/

    public boolean guardarSistema() {//asi guardaria la clase sistema entera, mejor guardar por separado usuarios y alumnos?
        try {
            FileOutputStream f = new FileOutputStream("Sistema.obj");
            ObjectOutputStream finalFile = new ObjectOutputStream(f);
            finalFile.writeObject(this);
            finalFile.close();
            f.close();
            return true;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static Sistema cargarSistema() {
        Sistema u = null;
        try {
            FileInputStream file = new FileInputStream("Sistema.obj");
            ObjectInputStream inputFile = new ObjectInputStream(file);
            u = (Sistema) inputFile.readObject();

            inputFile.close();
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return u;
    }
    
    public void saltarDias(int dias){
    for (Usuario u: usuario){
     if (u instanceof Alumno){
        u.actualizarPenalizacion(dias);}}
    }
}
//faltarian mas
