/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import users.Usuario;

/**
 *
 * @author Dani
 */
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    private int valoracion;
    private Hashtable<String, Integer> usuVoto;
    private Usuario creador;
    private ArrayList<Comentario> comentarios;
    private String texto;
/**
 * constructor de la clase Comentario
 * @param u
 * @param txt 
 */
    public Comentario(Usuario u, String txt) {
        texto = txt;
        valoracion = 0;
        creador = u;
        this.comentarios = new ArrayList<Comentario>();
        this.usuVoto = new Hashtable<String, Integer>();

    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    
/**
 * reponde el comentario con la respuesta que se quiera
 * @param u
 * @param s 
 */
    public void responderComentario(Usuario u, String s) {
        Comentario come = new Comentario(u, s);
        this.comentarios.add(come);
        System.out.println("Comentario comentado");
    }
/**
 * me duvuel el comentario
 * @return 
 */
    public String respuestaComentarios() {
        String info = null;
        for (Comentario comen : comentarios) {
            info = comen.getTexto();
        }
        return info;
    }
/**
 * Sirve para vota el comentario con la valoracion indicada
 * @param u
 * @param val 
 */
    public void votar(Usuario u, int val) {

        //Comprobamos que el valor numerico del voto es valido (-1 o 1)        
        if (comprobarVoto(val) && u != creador) {

            //Si no ha votado aun y no es el creador almacenamos y procesamos su voto
            if (!usuVoto.containsKey(u.getEmail())) {
                usuVoto.put(u.getEmail(), val);
                sumarValoracion(usuVoto);

            } //Si ya ha votado, reemplazamos su voto anterior
            else if (!usuVoto.containsValue(val)) {
                usuVoto.replace(u.getEmail(), usuVoto.get(u.getEmail()), val);
                sumarValoracion(usuVoto);
            } else {
                System.out.println("No puede votar dos veces el mismo valor");
            }
        } else {
            System.out.println("El creador no puede votar");
        }
    }
/**
 * Comprueba que el voto es valido
 * @param val
 * @return 
 */
    private boolean comprobarVoto(int val) {
        if (val == 1 || val == -1) {
            return true;
        } else {
            System.out.println("El voto debe ser o 1 o -1");
            return false;
        }

    }
/**
 * Suma la valoracion del comentario
 * @param usuVoto
 * @return 
 */
    private int sumarValoracion(Hashtable usuVoto) {
        Enumeration e = usuVoto.elements();
        Object valor;
        valoracion = 0;
        while (e.hasMoreElements()) {
            valor = e.nextElement();
            //System.out.println("Valoracion: " + valor);
            // Hacemos un cast, pero antes nos hemos assegurado de que
            //el valor es numerico
            valoracion = valoracion + (Integer) valor;
        }
        return valoracion;
    }

    public String getTexto() {
        return texto;
    }

    public int getValoracion() {
        return valoracion;
    }

}
