/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.util.ArrayList;
import java.util.Iterator;
import users.Usuario;

/**
 *
 * @author alvarogonzalez
 */
public class Encuesta extends EntradaGenerica {

    private ArrayList<String> preguntas;
    private ArrayList<String> respuestas;

    public Encuesta(Usuario u, String titulo) {
        super(u, titulo);
        preguntas = new ArrayList<String>();
        respuestas = new ArrayList<String>();
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }
/**
 * Agrega la pregunta que le introduzca
 * @param prg 
 */
    public void addPreguntas(String prg) {
        if (!prg.isEmpty() && !preguntas.contains(prg)) {
            preguntas.add(prg);
            System.out.println("La pregunta <<" + prg + ">> ha sido agregado con exito");
        } else {
            System.out.println("Usted ha metido una pregutna repetida o no ha introducido nada");
        }
    }
/**
 * Responde la pregunta i con la repuesta resp
 * @param resp
 * @param i 
 */
    public void reponderPreguntas(String resp, int i) { // el numero de la pregunta debe ser mayor que cero
        if (i>0 && i<=preguntas.size()){
            System.out.println("Ha respondido la pregunta: " + preguntas.get(i - 1) + " con " + "<< " + resp + " >>");
            respuestas.add(resp);
            System.out.println("-------------");
        }
    }
/**
 * Me muestra las respuestas
 * @return 
 */
    public String verRespuestas() {
        System.out.println("RESPUESTAS DE LA ENCUESTA ");
        System.out.println();
        String info = "\n";
        int n = 1;
        Iterator<String> c = preguntas.iterator();
        Iterator<String> r = respuestas.iterator();

        while (c.hasNext() & r.hasNext()) {//mientas haya respuestas que ver estara en el bucle
            String ans = c.next();
            String res = r.next();

            System.out.println(n + "-" + ans);
            System.out.println(res);
            n++;

        }
        System.out.println("-------------");
        return info;

    }
/**
 * Me muestra las preguntas
 * @return 
 */
    public String verPreguntas() {
        String info = "\n";

        Iterator<String> c = preguntas.iterator();
        int n = 1;
        while (c.hasNext()) {//mientas haya pregutnas que ver estara en el bucle
            String ans = c.next();

            System.out.println(n + "-" + ans);

            n++;
        }
        System.out.println("-------------");
        return info;
    }

    @Override
    public void mostrar() {
        System.out.println("Encuesta creada por" + getCreador().getNick());
        System.out.println("Título " + getTitulo());
        System.out.println(verPreguntas());
        System.out.println(verRespuestas());
    }

    @Override
    public void modificarEntrada(Usuario u, String s) {

    }

}
