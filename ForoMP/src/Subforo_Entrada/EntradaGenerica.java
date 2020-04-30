/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import users.Administrador;
import users.Usuario;

/**
 *
 * @author Dani
 */
public abstract class EntradaGenerica implements Serializable, Comparable<EntradaGenerica> {

    private static final long serialVersionUID = 1L;
    private int valoracion;
    private int numTotalVal;
    private Date fecha;
    private String titulo;
    private boolean publicada;
    private boolean verificado;
    private Usuario creador;
    private Hashtable<String, Integer> usuVoto;
    private Subforo pertenezco;
    Sistema s = Sistema.getInstance();

    /**
     * Constructor de entrada Generica
     * @param creador
     * @param titulo 
     */
    public EntradaGenerica(Usuario creador, String titulo) {
        this.valoracion = valoracion;
        this.numTotalVal = numTotalVal;
        this.fecha = fecha;
        this.titulo = titulo;
        this.publicada = false;
        this.verificado = false;
        this.creador = creador;
        this.usuVoto = new Hashtable<String, Integer>();

    }

    public String getTitulo() {
        return titulo;
    }
/**
 * Me duvuelve la valoracio de la entrada
 * @return 
 */
    public int getValoracion() {
        return valoracion;
    }

    /**
     * votar() modifica la valoracion de la entrada. Recibe una valoracion por
     * parte de un usuario. Esta puede ser positiva (voto de 1) o negativa (voto
     * de -1) Se actualiza la suma de votaciones y la lista de usuarios que han
     * votado. Por cada vez que se haga votar() se sumará el voto del usuario a
     * la valoracion de la entrada. En caso de que el usuario no haya votado
     * previamente, se almacena su voto. En caso de que el usuario ya haya
     * votado, se comprueba si su voto es igual o no. Si no lo es, se almacena y
     * contabiliza solo el último voto
     *
     * @param u Usuario
     * @param val int
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
        } else if (u.equals(creador)) {
            System.out.println("El creador no puede votar");
        }
    }

    /**
     * ComprobarUsuarioVoto es un metodo privado que comprueba si el valor de
     * voto es valido, para no sumar cosas extrañas en la valoracion de la
     * entrada
     *
     * @param u
     *
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
     * sumarValoracion devuelve la suma de los valores de la tabla donde se
     * almacenan los usuarios que han votado y su voto
     *
     * @param usuVoto
     * @return
     */
    private int sumarValoracion(Hashtable usuVoto) {
        Enumeration e = usuVoto.elements();
        Object valor;
        valoracion = 0;
        while (e.hasMoreElements()) {
            valor = e.nextElement();

            // Hacemos un cast, pero antes nos hemos assegurado de que
            //el valor es numerico
            valoracion = valoracion + (Integer) valor;
        }

        return valoracion;
    }

    /**
     * Verifica la entrada si la ha creado un alumno, dicho metodo solo lo puede invocar un admin
     * @param bool
     * @return 
     */
    public boolean verificar(boolean bool) {
        if (getCreador().getClass().getSimpleName().equals("Alumno")) {
            if (s.getConectado() instanceof Administrador) {
                verificado = bool;
                publicada = bool;
            }
            if (!bool) {
                getCreador().penalizar();

            } else {
                System.out.println("Entrada <<" + getTitulo() + ">> verificada");
            }
            pertenezco.notifySubscriptor(this);
        }

        return verificado;
    }

    /**
     * Actualiza el publicada
     * @param publicada 
     */
    public void setPublicada(boolean publicada) {
        this.publicada = publicada;
    }
/**
 * Actualiza el verificado
 * @param verificado 
 */
    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public boolean getVerificado() {
        return verificado;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setPertenezco(Subforo pertenezco) {
        this.pertenezco = pertenezco;
    }
/**
 * Muestra los elementos que tiene
 */
    public abstract void mostrar();
/**
 * me compara lo elementos del tipo EntradaGenerica
 * @param e
 * @return 
 */
    @Override
    public int compareTo(EntradaGenerica e) {
        int val = e.getValoracion();

        //Compara para que devuelva en orden descendente
        return val - this.valoracion;

    }
/**
 * Me modifica la entrada
 * @param u
 * @param s 
 */
    public abstract void modificarEntrada(Usuario u, String s);

}
