/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Subforo_Entrada.Subforo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import users.*;

/**
 *
 * @author alvarogonzalez
 */
public class Sistema {

    private ArrayList<Usuario> usuario;
    private ArrayList<Subforo> subforo;
    private static Sistema instancia; //creo que el singleton con esto valdria y el constructor

    private void Sistema() { //declarado de esta forma el constructor hacemos el singleton
        usuario = new ArrayList<Usuario>();
        subforo = new ArrayList<Subforo>();
    }

    // primer problema, para registar hay que crear, habria que pasa el tipo pues usuario es abstracto y hacer unos ifes para verlo
    public boolean registrarse(String nombre, String apellido1, String apellido2, String nick, String email, String contraseña) {
        
        Iterator <Usuario> i = usuario.iterator();
        
        boolean aceptar = true;
        while(i.hasNext() & aceptar){ //comprobamos que el nick no se repita (se puede hacer con las demas propiedades)
            Usuario u = i.next();
                if(u.getNick().equals(nick)){
                    System.out.println("El nick introducido ya esta usado, elija otro");
                    aceptar = false;
                }    
        }
        
        if (aceptar){ //si no hay ninguna usuario con el mismo nick procedemos a crearlo
        Usuario u = new Usuario(nombre, apellido1, apellido2, nick, email, contraseña);
        usuario.add(u);
        System.out.println("Se ha añadido un nuevo usuario.");
        }
        return aceptar;
    }

    public boolean verificarNuevoUsuario() {//preguntar que hace exactamente
        return true;
    }

    public void eliminarUsuario(String nick) {
        for (Usuario us : usuario) { //primero busco el usuario por su nick(pues es unico)
            if (us.getNick().equals(nick)) {
                usuario.remove(us);  //si lo encuenta lo borra
                System.out.println("El usuario de Nick < " + nick + " > ha sido eliminado");
            }
        }
    }

    public void crearSubforo(String titulo) {
        boolean bol = true;
        for (Subforo sub : subforo) { //esto es eficiente cuando hay poco, si hay mucho seria mejor usar un iterator como en registrarse.
            if (sub.getNombre().equals(titulo)) {
                bol = false;
                System.out.println("Esta subforo ya existe, elija otro titulo e intentelo de nuevo");
            }
        }
        if (bol = true) { //si el nombre no esta usado entonces lo creamos
            Subforo s = new Subforo(titulo); //creamos el subforo con el titulo (Ver que no se repitan el nombre?)
            subforo.add(s); // lo añadido a la list a de subforos 
        }                 
    }

    public void darDeBajaSubforo() {
        subforo.remove(this); //borro el subforo  s que ponga s.darDeBajaSubforo(); comprobar
    }

    public boolean logIn(String email, String nick, String password) {
        boolean bool = false;

        for (Usuario us : usuario) { //primero busco el usuario por su nick(pues es unico)
            if (us.getNick().equals(nick) & us.getEmail().equals(email) & us.getContraseña().equals(password)) {
                System.out.println("Usuario Correcto");
                bool = true;
            } else {
                System.out.println("Usuario no registrado, asegurese de introducir bien los datos");
            }
        }
        return bool;
    }

    public boolean logOut() {//que es exactamente lo que debe hacer?? osea, como oriento la programacion
        return true;
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
}
