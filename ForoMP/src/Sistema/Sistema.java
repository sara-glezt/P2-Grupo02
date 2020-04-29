/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Subforo_Entrada.*;
import users.*;
import Observer.*;

import Subforo_Entrada.EntradaGenerica;
import Subforo_Entrada.Subforo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import users.*;

/**
 *
 * @author alvarogonzalez
 */
public class Sistema implements Serializable {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Subforo> subforo = new ArrayList<>();
    private static Sistema instancia = null; //creo que el singleton con esto valdria y el constructor
    private Usuario conectado;//= null; //para saber que usuaario esta conectado(hacer login y logout con este usuario
    private ArrayList<Entrada> masVotadas = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    private void Sistema() { //declarado de esta forma el constructor hacemos el singleton
        /* usuario = new ArrayList<>();
        subforo = new ArrayList<>();
        masVotadas = new ArrayList<>();
        conectado = null;*/
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
                usuarios.add(us);
                System.out.println("Se ha añadido un nuevo alumno.");
            } else if (tipo.equals("profesor") || tipo.equals("Profesor")) {
                Usuario us = new Profesor(nombre, apellido1, apellido2, nick, email, contraseña);//poner ifes dependiendo del tipo users
                usuarios.add(us);
                System.out.println("Se ha añadido un nuevo profesor.");
            } else { //si no es profe ni alummn como he verificado, le queda solo ser admin
                Usuario us = new Administrador(nombre, apellido1, apellido2, nick, email, contraseña);//poner ifes dependiendo del tipo users
                usuarios.add(us);
                System.out.println("Se ha añadido un nuevo Administrador.");
            }
        }
        return valido;
    }

    //creo que verifico todo lo que se deberia, pero echarle un ojo por si acaso
    /*    private boolean verificarNuevoUsuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña, String tipo) {//preguntar que hace exactamente
    Iterator<Usuario> i = usuarios.iterator();
    
    boolean aceptar = true;
    Scanner sc = new Scanner(email); //cogemos la parte del @ en adelante del email
    sc.useDelimiter("@");
    sc.next();
    try{
    sc.next();
    aceptar = false;
    } catch (Exception e){
    System.out.println("El email introducido no cumple las condiciones necesarias");
    }
    if (aceptar){
    Scanner sc2 = new Scanner(email);
    sc2.useDelimiter("@");
    sc2.next();
    String fin = sc2.next();
    try{
    while (i.hasNext()) { //comprobamos que el nick no se repita (se puede hacer con las demas propiedades)
    Usuario u = i.next();
    if (u.getNick().equals(nick)) {
    System.out.println("El nick introducido  ya esta usado, elija otro");
    throw new RunTimeException("El nick introducido  ya esta usado, elija otro");
    }
    
    if (u.getEmail().equals(email) || (!fin.equals("alumnos.urjc.es") & !fin.equals("urjc.es"))) { //verificamos el email
    System.out.println("El email introducido  ya esta usado o no es valido, elija otro");
    throw new RunTimeException("El email introducido  ya esta usado o no es valido, elija otro");
    }
    }
    
    if (!tipo.equals("Alumno") & !tipo.equals("Profesor") & !tipo.equals("Administrador")
    & !tipo.equals("alumno") & !tipo.equals("profesor") & !tipo.equals("administrador")) {
    System.out.println("El tipo de usuario no es valido. Asegurese de que sea A/alumno, P/profesor o A/administrados");
    throw new RunTimeException("El tipo de usuario no es valido. Asegurese de que sea A/alumno, P/profesor o A/administrados");
    }
    } catch (RunTimeException e){
    aceptar = false;
    }
    }
    return aceptar;
    }*/
    
     private boolean verificarNuevoUsuario(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña, String tipo) {//preguntar que hace exactamente
        Iterator<Usuario> i = usuarios.iterator();

        Scanner sc = new Scanner(email); //cogemos la parte del @ en adelante del email
        sc.useDelimiter("@");
        sc.next();
        String e = sc.next();

        boolean aceptar = true;
        while (i.hasNext() & aceptar) { //comprobamos que el nick no se repita (se puede hacer con las demas propiedades)
            Usuario u = i.next();
            if (u.getNick().equals(nick)) {
                System.out.println("El nick introducido  ya esta usado, elija otro");
                aceptar = false;
            }
            if (u.getEmail().equals(email) || (!e.equals("alumnos.urjc.es") & !e.equals("urjc.es")) || (e.equals("alumnos.urjc.es") & tipo.equals("profesor")) || (e.equals(".urjc.es") & tipo.equals("alumno"))) { //verificamos el email
                System.out.println("El email introducido  ya esta usado o no es valido, elija otro");
                aceptar = false;
            }
        }

        if (!tipo.equals("Alumno") & !tipo.equals("Profesor") & !tipo.equals("Administrador")
                & !tipo.equals("alumno") & !tipo.equals("profesor") & !tipo.equals("administrador")) {
            System.out.println("El tipo de usuario no es valido. Asegurese de que sea A/alumno, P/profesor o A/administrados");
            aceptar = false;
        }
        return aceptar;
    }

    public void eliminarUsuario(String nick) {

        Iterator<Usuario> i = usuarios.iterator();
        boolean aceptar = true;

        while (i.hasNext() & aceptar) { //bulce para recorrer a todos los usuarios
            Usuario us = i.next();
            if (us.getNick().equals(nick)) { //miro si existe ese usuario
                for (Subforo sub : subforo) {  // si existe, entonces voy recorriendo lo subforos
                    sub.deleteSubscriptor(us);   //voy eliminando ese usuarui de los subforos
                }
                usuarios.remove(us);  //si lo encuenta lo borra
                System.out.println("El usuario de Nick < " + nick + " > ha sido eliminado");
                aceptar = false; //lo pongo a false para que se salga del bucle una vez se ha borrado
            }
        }
    }

    public void crearSubforo(String sr) {
        if (getConectado() instanceof Profesor) {
            boolean bol = true;

            Iterator<Subforo> i = subforo.iterator();
            while (i.hasNext() & bol) { //iterator para si lo encuento antes poder salirme
                Subforo sub = i.next();
                if (sub.getNombre().equals(sr)) {//compruebo que el nobmre no esta usado
                    System.out.println("Esta subforo ya existe, elija otro titulo e intentelo de nuevo");
                    bol = false;

                }
            }

            if (bol) { //si el nombre no esta usado entonces lo creamos
                Subforo s1 = new Subforo(sr);
                subforo.add(s1); // lo añadido a la list a de subforos
                System.out.println("Se ha añadido un nuevo subforo; el subforo: " + s1.getNombre());
            }
        } else {
            System.out.println("Usted no tiene los permisos para crear subforos.");
        }
    }

    public void eliminarSubforo(String s) {
        boolean bol = true;
        Subforo aux = null;
        Iterator<Subforo> i = subforo.iterator();
        while (i.hasNext() & bol) {
            aux = i.next();
            if (aux.getNombre().equals(s)) {
                bol = false;
            }
        }
        if (!bol) {
            for (Usuario us : usuarios) { //recorro los usuarios y lo voy dando de baja del subforo que quiero eliminar
                us.darDeBajaSubforo(aux.getNombre());
            }
            subforo.remove(aux); //borro el subforo  s 
            System.out.println("El subforo " + aux.getNombre() + " ha sido borrado.");
        }
    }

    public boolean logIn(String email, String nick, String password) {
        boolean bool = false;
        boolean aceptar = true;
        Iterator<Usuario> i = usuarios.iterator();

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
                            aceptar = false;
                            bool = true;
                        }
                    } else { //si no es alumno, entonces es profe o admin y hace login pues no se pueden banear
                        System.out.println("Usuario Correcto");
                        conectado = us; //asignamos ese usuario para saber con cual estamos.
                        bool = true;
                        aceptar = false;
                    }
                } //si no encuentro el usuario digo que no esta registrado

            }
            if (aceptar) //si no encuento el usuario lo notifico
            {
                System.out.println("Usuario no registrado, asegurese de introducir bien los datos");
            }

        } else { //si hay alguien conectado no podre hacer login
            System.out.println("Ya hay un usuario conectado");
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

    public ArrayList<Entrada> getMasVotadas() {
        //Lo que haremos sera concatenar todos los arrays de Entradas de cada
        //subforo en uno. Ese lo ordenamos y extraemos los 3 primeros.

        //Concatenamos
        ArrayList<Entrada> entradasConcat = new ArrayList<>();
        for (int i = 0; i < subforo.size(); i++) {
            entradasConcat.addAll(subforo.get(i).getEntrada());

        }
        //Ordenamos
        Collections.sort(entradasConcat);

        //Guardamos en el array de masVotadas las 3 primeras de las ordenadas
        for (int i = 0; i < 3; i++) {
            masVotadas.add(entradasConcat.get(i));

        }
        return masVotadas;

    }

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

    public String ListaSubforo() {
        String info = "Subforos " + "\n";
        for (Subforo s : subforo) {
            info += "\t" + s.getNombre() + "\n";
        }
        return info;
    }

    public String ListaUsuario() {
        String info = "\t" + "\t" + "\t" + "Usuarios Registrados " + "\n";
        //if (getConectado() instanceof Administrador || getConectado() instanceof Profesor) {

            for (Usuario us : usuarios) {
                info += "\t" + "Nombre: " + us.getNombre() + "  " + us.getApellido1() + " " + us.getApellido2()
                        + " Nick: " + us.getNick() + " Email: " + us.getEmail() + "\n";
            }
        //}
        return info;

    }

    public void saltarDias(int dias) {
        for (Usuario u : usuarios) {
            if (u instanceof Alumno) {
                u.actualizarPenalizacion(dias);
            }
        }
    }

    private static class RunTimeException extends Exception {

        public RunTimeException(String el_nick_introducido__ya_esta_usado_elija_) {
        }
    }

    public ArrayList<Subforo> getSubforo() {
        return subforo;
    }
    
    
}
//faltarian mas
