/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.io.Serializable;
import java.util.ArrayList;
import users.Alumno;
import users.Profesor;
import users.Usuario;

/**
 *
 * @author DAni
 */
public class Entrada extends EntradaGenerica implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Comentario> comentarios;
    private ArrayList<EntradaGenerica> componentes;
    private ArrayList<Encuesta> encuestas = new ArrayList<>();
    private ArrayList<Ejercicio> ejercicios = new ArrayList<>();
    private ArrayList<TextoPlano> textos = new ArrayList<>();

    Sistema s = Sistema.getInstance();
    private int valoracion;
    private String texto;
/***
 * Constructor de entrada 
 * @param u
 * @param titulo
 * @param texto 
 */
    public Entrada(Usuario u, String titulo, String texto) { //Debug exactamente que metemos por constructor
        super(u, titulo);
        this.texto = texto;
        comentarios = new ArrayList<Comentario>();
        componentes = new ArrayList<EntradaGenerica>();
    }

    /**
     * Agrega un texto plano a la entrada
     * @param u
     * @param titulo
     * @param cuerpo 
     */
    public void addTextoPlano(Usuario u, String titulo, String cuerpo) {

        if (getCreador().equals(u)) {
            boolean add = true;

            for (TextoPlano txt : textos) {
                if (txt.getTitulo().equals(titulo)) {
                    add = false;
                }
            }

            if (add) {
                TextoPlano texto = new TextoPlano(u, titulo, cuerpo);
                textos.add(texto);
                componentes.add(texto);
                System.out.println("Texto plano agregado con exito");
            } else {
                System.out.println("Este titulo ya esta en uso, elija otro");

            }
        }

    }

/**
 * Agrega una encuesta a la entrada
 * @param u
 * @param titulo 
 */
    public void addEncuesta(Usuario u, String titulo) {

        if (s.getConectado() instanceof Profesor) {
            boolean add = true;

            for (Encuesta enc : encuestas) {
                if (enc.getTitulo().equals(titulo)) {
                    add = false;
                }
            }
            if (add) {
                Encuesta encuesta = new Encuesta(u, titulo);
                System.out.println("La encuesta <<" + titulo + ">> ha sido creada con exito");
                encuestas.add(encuesta);
                componentes.add(encuesta);
            } else {
                System.out.println("Este titulo ya esta en uso, elija otro para la encuesta");

            }

        } else {
            System.out.println("Usted no puede crear encuestas");
        }
    }
/**
 * Agrega un ejercicio a la entrada
 * @param u
 * @param titulo
 * @param p
 * @param r 
 */
    public void addEjercicio(Usuario u, String titulo, String p, String r) {
        if (s.getConectado() instanceof Profesor) {
            boolean add = true;
            

            for (Ejercicio ej : ejercicios) {
                if (ej.getTitulo().equals(titulo)) {
                    add = false;
                }

            }
            if (add) {
                Ejercicio ejercicio = new Ejercicio(u, titulo, p, r);
                ejercicios.add(ejercicio);
                componentes.add(ejercicio);
            } else {
                System.out.println("No repita ejercicios, escoja otro enunciado");

            }
            
        }else System.out.println("Usted no es un profesor y por tanto no puede crear ejercicos");
    }

    /**
     * Agrega un comentario a la entrada
     * @param u
     * @param s 
     */
    public void addComentario(Usuario u, String s) {
        if (this.getVerificado()) {
            Comentario comen = new Comentario(u, s);
            comentarios.add(comen);
            System.out.println("Comentario agregado con exito");
        } else {
            System.out.println("No puedes comentar, pues no esta verificada la entrada");
        }
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    /**
     * Me muestra los comentarios de la entrada
     * @return 
     */
    public String verComentarios() {
        String info = null;
        for (Comentario comen : comentarios) {            
            info = comen.getTexto() + "\n"
                    + "\t";
            if(comen.respuestaComentarios() != null){
               info = info + comen.respuestaComentarios();  
            }
        }
        return info;
    }

    @Override
    public void mostrar() {

        if (s.getConectado() != null) {
            if (s.getConectado() instanceof Alumno) {
                if (getVerificado()) {
                    for (int i = 0; i < componentes.size(); i++) {
                        componentes.get(i).mostrar();
                        
                    }
                } else {
                    System.out.println("No esta verificado y no puede verlo");
                }
            } else {
                for (int i = 0; i < componentes.size(); i++) {
                    componentes.get(i).mostrar();
                  
                    if (getVerificado()) {
                        System.out.print(":verificada" + "\n");
                    } else {
                        System.out.print(": no verificada" + "\n");
                    }
                }

            }
        }
        
    }

    @Override
    public String toString() {
        return "Entrada{" + "comentarios=" + comentarios + ", componentes=" + componentes + ", s=" + s + ", valoracion=" + valoracion + '}';
    }

    public ArrayList<EntradaGenerica> getComponentes() {
        return componentes;
    }

    @Override
    public void modificarEntrada(Usuario u, String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Encuesta> getEncuestas() {
        return encuestas;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public ArrayList<TextoPlano> getTextos() {
        return textos;
    }

}
