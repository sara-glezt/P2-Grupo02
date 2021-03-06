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
    private static Sistema instancia = null;
    private Usuario conectado;
    private ArrayList<Entrada> masVotadas = new ArrayList<>();
    private static final long serialVersionUID = 1L;

    private void Sistema() {

    }

    /**
    * getInstance() me da una instancia de Sistema
    * 
    */
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

    /**
 *
 * getConectado() me devulve el usuario conectado
 */
    public Usuario getConectado() {
        return conectado;
    }
/**
 * registrarse() registra el usuario con los parametros introducidos y se guardan sus datos en Sistema
 * @param nombre String
 * @param apellido1 String
 * @param apellido2 String
 * @param nick String
 * @param email String
 * @param password String
 * @param tipo String
 */
    public boolean registrarse(String nombre, String apellido1, String apellido2, String nick, String email, String password, String tipo) {
        boolean valido = verificarNuevoUsuario(nombre, apellido1, apellido2, nick, email, password, tipo);
  
        if (valido) {
            if (tipo.equals("alumno") || tipo.equals("Alumno")) {
                Usuario us = new Alumno(nombre, apellido1, apellido2, nick, email, password);
                usuarios.add(us);
                System.out.println("Se ha agregado un nuevo alumno.");
            } else if (tipo.equals("profesor") || tipo.equals("Profesor")) {
                Usuario us = new Profesor(nombre, apellido1, apellido2, nick, email, password);
                usuarios.add(us);
                System.out.println("Se ha agregado un nuevo profesor.");
            } else { //si no es profe ni alummn como he verificado, le queda solo ser admin
                Usuario us = new Administrador(nombre, apellido1, apellido2, nick, email, password);
                usuarios.add(us);
                System.out.println("Se ha agregado un nuevo Administrador.");
            }
        }
      
        return valido;
    }
/**
 *verificarNuevoUsuario() comprube si el usuario que se quiere introducir es valido
 * @param nombre String
 * @param apellido1 String
 * @param apellido2 String
 * @param nick String
 * @param email String
 * @param password String
 * @param tipo String
 */
    private boolean verificarNuevoUsuario(String nombre, String apellido1, String apellido2, String nick, String email, String password, String tipo) {//preguntar que hace exactamente
        Iterator<Usuario> i = usuarios.iterator();

        Scanner sc = new Scanner(email); //cogemos la parte del @ en adelante del email
        sc.useDelimiter("@");
        sc.next();
        String e = sc.next();

        boolean aceptar = true;
        
        if (usuarios.size()>0){
        while ((i.hasNext() && aceptar)) { //comprobamos que el nick no se repita (se puede hacer con las demas propiedades)
            Usuario u = i.next();
            if (u.getNick().equals(nick)) {
                System.out.println("El nick introducido  ya esta usado, elija otro");
                aceptar = false;
            }
            if (u.getEmail().equals(email)){
                System.out.println("El email introducido  ya esta usado o no es valido, elija otro");
                aceptar = false;
            }
        }}
        
        if ((!e.equals("alumnos.urjc.es") & !e.equals("urjc.es")) || (e.equals("alumnos.urjc.es") & tipo.equals("profesor")) || (e.equals(".urjc.es") & tipo.equals("alumno"))) { //verificamos el email
                System.out.println("El email introducido   no es valido, elija otro");
                aceptar = false;}
        
        

        if (!tipo.equals("Alumno") & !tipo.equals("Profesor") & !tipo.equals("Administrador")
                & !tipo.equals("alumno") & !tipo.equals("profesor") & !tipo.equals("administrador")) {
            System.out.println("El tipo de usuario no es valido. Asegurese de que sea A/alumno, P/profesor o A/administrados");
            aceptar = false;
        }
        return aceptar;
    }
/**
 *
 * eliminarUsuario() elimina el usuario con el nick introducido
 * @param nick String
 */
    public void eliminarUsuario(String nick) {

        Iterator<Usuario> i = usuarios.iterator();
        boolean aceptar = true;

        while (i.hasNext() & aceptar) { //bulce para recorrer a todos los usuarios
            Usuario us = i.next();
            if (us.getNick().equals(nick)) { //miro si existe ese usuario
                for (Subforo sub : subforo) {  // si existe, entonces voy recorriendo lo subforos
                    sub.deleteSubscriptor(us);   //voy eliminando ese usuario de los subforos
                }
                usuarios.remove(us);  //si lo encuenta lo borra
                System.out.println("El usuario de Nick < " + nick + " > ha sido eliminado");
                aceptar = false; //lo pongo a false para que se salga del bucle una vez se ha borrado
            }
        }
    }
/**
 *
 * crearSubforo()  me crea el subforo
 * @param sr String
 */
    public void crearSubforo(String sr) {
        if (getConectado() != null) {
            if (getConectado() instanceof Profesor) {
                boolean bol = true;

                Iterator<Subforo> i = subforo.iterator();
                while (i.hasNext() & bol) { //iterator para si lo encuento antes poder salirme
                    Subforo sub = i.next();
                    if (sub.getNombre().equals(sr)) {//compruebo que el nombre no esta usado
                        System.out.println("Esta subforo ya existe, elija otro titulo e intentelo de nuevo");
                        bol = false;

                    }
                }

                if (bol) { //si el nombre no esta usado entonces lo creamos
                    Subforo s1 = new Subforo(sr);
                    subforo.add(s1); // lo agregamos a la list a de subforos
                    System.out.println("Se ha agregado un nuevo subforo; el subforo: " + s1.getNombre());
                }
            } else {
                System.out.println("Usted no tiene los permisos para crear subforos.");
            }
        } else {
            System.out.println("Inicie sesión para crear el subforo");
        }
    }
/**
 * eliminarSubforo() elimina el subforo que le indique
 * @param s  String
 */
    public void eliminarSubforo(String s) {
   
        boolean bol = true;
        Subforo aux = null;
        Iterator<Subforo> i = subforo.iterator();
        while (i.hasNext() & bol) {
            aux = i.next();
            if (aux.getNombre().equals(s)) {
                 if(getConectado().getNombre().equals(aux.getCreador().getNombre())){ 
                bol = false;
                 
            }else System.out.println("Solo el creador puede borrar el subforo");
        }
        if (!bol) {
            for (Usuario us : usuarios) { //recorro los usuarios y lo voy dando de baja del subforo que quiero eliminar
                us.darDeBajaSubforo(aux.getNombre());
            }
            subforo.remove(aux); //borro el subforo  s 
            System.out.println("El subforo " + aux.getNombre() + " ha sido borrado.");
        
    }
    }}
/**
 * login() inicia sesion el usuario que se indique
 * @param email
 * @param nick
 * @param password
 * @return 
 */
    public boolean logIn(String email, String nick, String password) {
        boolean bool = false;
        boolean aceptar = true;
        Iterator<Usuario> i = usuarios.iterator();

        if (conectado == null) { //miro que no haya ningun usuario conectado
            while (i.hasNext() & aceptar) { // busco el usuario con su caracterisitcas
                Usuario us = i.next();
                if (us.getNick().equals(nick) & us.getEmail().equals(email) & us.getPassword().equals(password)) {

                    if (us instanceof Alumno) {   //miro si es un alumno para comprobar si esta penalizado
                        if (((Alumno) us).penalizado()) {
                            aceptar = false;
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
/**
 * logOut() cierra sesion
 * @return 
 */
    public boolean logOut() {//cerramos la sesion actual y guardamos
       boolean aceptar = false;
        if (conectado != null) {
            conectado = null;
            System.out.println("Hasta luego; fin de la conexion"); // hacer el guardar sistema
            guardarSistema();
            aceptar = true;
        } else {
            System.out.println("No hay ningun usuario conectado");
            
        }
        return aceptar;
    }
/**
 * gatMasVotadas() me devulve el array con las entradas mas votadas
 * @return 
 */
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
        
        while (masVotadas.size() > 0) {
            masVotadas.remove(masVotadas.size() - 1);
        }

        int i=0;
        boolean aceptar = true;
        
        while (i<3 && aceptar){
           masVotadas.add(entradasConcat.get(i));
            System.out.println(masVotadas.get(i).getTitulo());
           
            if((entradasConcat.size()-1)== i){
                aceptar= false;
                
            }
             i++;
        }
       
        return masVotadas;

    }

    /**
     * guardasSistema() guarda el sistema
     * @return 
     */
    public boolean guardarSistema() {//asi guardaria la clase sistema entera
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
/**
 * cargarSistema() carga el sistema
 * @return 
 */
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
/**
 * muestra una lista de subforos
 * @return 
 */
    public String ListaSubforo() {
        String info = "Subforos " + "\n";
        for (Subforo s : subforo) {
            info += "\t" + s.getNombre() + "\n";
        }
        return info;
    }
/**
 * muestra la lista de usuarios
 * @return 
 */
    public String ListaUsuario() {
        String info = "\t" + "\t" + "\t" + "Usuarios Registrados " + "\n";
        //if (getConectado() instanceof Administrador || getConectado() instanceof Profesor) { //esto es lo correcto, pero para la prueba lo habilitamos

        for (Usuario us : usuarios) {
            info += "\t" + "Nombre: " + us.getNombre() + "  " + us.getApellido1() + " " + us.getApellido2()
                    + " Nick: " + us.getNick() + " Email: " + us.getEmail() + " " + us.getClass().getSimpleName() + "\n";
        }
        //}
        return info;

    }
/**
 * Salta los dias para pasar la penalizacion
 * @param dias 
 */
    public void saltarDias(int dias) {
        for (Usuario u : usuarios) {
            if (u instanceof Alumno) {
                u.actualizarPenalizacion(dias);
            }
        }
    }
/**
 * me duevuelve una ArrayLsit de subforos
 * @return 
 */
    public ArrayList<Subforo> getSubforo() {
        return subforo;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void deleteBD(){
        File bbdd = new File("Sistema.obj");
       if (bbdd.exists()) {
            bbdd.delete();
        }
        
        if (instancia != null){
      instancia = null;}
      
       
    }
}
