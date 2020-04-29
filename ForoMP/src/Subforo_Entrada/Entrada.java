/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.util.ArrayList;
import users.Alumno;
import users.Profesor;
import users.Usuario;

/**
 *
 * @author Equipo
 */
public class Entrada extends EntradaGenerica {

    private ArrayList<Comentario> comentarios;
    private ArrayList<EntradaGenerica> componentes;
    
    
    Sistema s = Sistema.getInstance();
    private int valoracion;
    private String texto;

    public Entrada(Usuario u, String titulo, String texto) { //Debug exactamente que metemos por constructor
        super(u, titulo);
        this.texto = texto;
        comentarios = new ArrayList<Comentario>();
        componentes = new ArrayList<EntradaGenerica>();
        this.valoracion = super.getValoracion();
        //metodos crear ejercicios, textoplano, encuesta

    }

    //Sistema instancia = Sistema.getInstance(); con esto para controlar quien hace el que, si profe...
    //if(instancia.getConectado() instanceof Profesor) 
    public void addTextoPlano(Usuario u, String titulo, String cuerpo) {
        if (getCreador().equals(u)) {
            TextoPlano texto = new TextoPlano(u, titulo, cuerpo);
            componentes.add(texto);
        }

    }

    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEncuesta(Usuario u, String titulo) {
        if (s.getConectado() instanceof Profesor) {
            Encuesta encuesta = new Encuesta(u, titulo);
            componentes.add(encuesta);
        } else {
            System.out.println("Usted no puede crear encuestas");
        }
    }

    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEjercicio(Usuario u, String titulo, String p, String r) {
        if (s.getConectado() instanceof Profesor) {
            Ejercicio ejercicio = new Ejercicio(u, titulo, p, r);
            componentes.add(ejercicio);
        }
    }

    public void addComentario(Usuario u, String s) {
        if (this.getVerificado()) {
            Comentario comen = new Comentario(u,s);
            comentarios.add(comen);
            System.out.println("Comentario añadido con exito");
        } else {
            System.out.println("No puedes comentar, pues no esta verificada la entrada");
        }
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public String verComentarios() {
        String info = null;
        for (Comentario comen : comentarios) {
            info = comen.getTexto() + "\n"
                    + "\t" + "-" + comen.respuestaComentarios();
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

}
