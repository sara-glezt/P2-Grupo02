/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Subforo_Entrada.Entrada;
import Subforo_Entrada.EntradaGenerica;
import Subforo_Entrada.Subforo;
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
    private static Sistema instancia; //creo que el singleton con esto valdria y el constructor
    private Usuario u; //para saber que usuaario esta conectado(hacer login y logout con este usuario

    private void Sistema() { //declarado de esta forma el constructor hacemos el singleton
        usuario = new ArrayList<>();
        subforo = new ArrayList<>();
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
        //array usuario, y hacer dardealtasubforo (comprobar si esta bien)
        for (Usuario us : usuario) {
            us.darDeBajaSubforo(s.getNombre());
        }
        subforo.remove(s); //borro el subforo  s que ponga s.darDeBajaSubforo(); comprobar (hacer eliminacion segura?)
    }

    public boolean logIn(String email, String nick, String password) {//usar el usuario de las variables de arriba (asignarlo)
        boolean bool = false;
        Iterator<Usuario> i = usuario.iterator();

        boolean aceptar = true;

        while (i.hasNext() & aceptar) { // busco el usuario con su caracterisitcas(cambiar por iterator)
            Usuario us = i.next();
            if (us.getNick().equals(nick) & us.getEmail().equals(email) & us.getContraseña().equals(password)) {
                System.out.println("Usuario Correcto");
                u = us; //asignamos ese usuario para saber con cual estamos.
                bool = true;

            } else {
                System.out.println("Usuario no registrado, asegurese de introducir bien los datos");
            }
        }

        return bool;
    }

    public boolean logOut() {//que es exactamente lo que debe hacer?? osea, como oriento la programacion
        u = null;
        System.out.println("Hasta luego; fin de la conexion");
        return true;
    }

    /*    public ArrayList<Entrada> getEntradasMasVotada(){
    ArrayList<Entrada> e = new ArrayList<Entrada>();
    ArrayList<Entrada> entradasMasVotada = new ArrayList<Entrada>();
    int max = 0;
    for (Subforo s : subforo){//voy recorriendo todos los subforos
    e = s.getEntrada(); //le asigno a e las entradas que tiene el subforo
    for(int j=0; j<=e.size();j++){ //voy recorriendo hasta que llego al final del array de entradas
    if(e.get(j).getValoracion()>=e.get(max).getValoracion){//miro que sea mayor que el que ya tenia, se podria poner mayor que la media
    max = j; //si es asi, me quedo con la posicion  del de mayor valoracion
    }
    }
    entradasMasVotada.add(e.get(max));//meto la entrada mas votada
    e=null;//dejo el array limpio para la siguiente rondas de subforos
    }
    
    return entradasMasVotada;
    
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
}
//faltarian mas
