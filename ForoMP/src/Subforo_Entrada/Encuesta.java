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

    public void añadirPreguntas(String prg) {
        if(!prg.isEmpty()){
        preguntas.add(prg);
    }else{
            System.out.println("Usted no ha metido ningun usuario");
        }
    }

    public void reponderPreguntas(String resp, int i) { // el numero de la pregunta debe ser mayor que cero
        System.out.println("Ha respondido la pregunta: " + preguntas.get(i - 1) + " con " + "<< " + resp + " >>");
        respuestas.add(resp);
        System.out.println("-------------");
    }

    public String verRespuestas() {
        System.out.println("RESPUESTAS DE LA ENCUESTA ");
        System.out.println();
        String info = "\n";
        int n = 1;
        Iterator<String> c = preguntas.iterator();
        Iterator<String> r = respuestas.iterator();

        while (c.hasNext() & r.hasNext()) {//mientas haya pregutnas que reponder estara en el bucle
            String ans = c.next();
            String res = r.next();

            System.out.println(n + "-" + ans);
            System.out.println(res);
            n++;

        }
        System.out.println("-------------");
        return info;

    }

    public String verPreguntas() {
        String info = "\n";

        Iterator<String> c = preguntas.iterator();
        int n = 1;
        while (c.hasNext()) {//mientas haya pregutnas que reponder estara en el bucle
            String ans = c.next();

            System.out.println(n + "-" + ans);

            n++;
        }
        System.out.println("-------------");
        return info;
    }

    /* public void añadirPreguntas() {
    int n = 1;
    String exit = "in";
    
    while (exit.equals("in")) {//mientras que el usuario no quiera salir se pediran instrucciones
    System.out.println("Escriba la pregunta " + n + " de la encuesta <<" + this.nombre + ">>");
    
    Scanner sc = new Scanner(System.in);
    String ans = sc.nextLine();
    if (!ans.equals("exit")) {
    preguntas.add(ans);
    n++;
    System.out.println();
    }else{
    exit = "out";}
    
    }
    }*/

 /*public void responderPreguntas() {
    System.out.println("ENCUESTA " + nombre );
    int n = 1;
    Iterator<String> c = preguntas.iterator();
    
    while (c.hasNext()) {//mientas haya pregutnas que reponder estara en el bucle
    String ans = c.next();
    System.out.println(n + "-" + ans);
    
    Scanner sc = new Scanner(System.in);
    String pre = sc.nextLine();
    
    respuestas.add(pre);
    n++;
    
    }
    
    }
     */

    @Override
    public void mostrar() {
        System.out.println("Encuesta creada por"  + getCreador().getNick());
        System.out.println("Título " + getTitulo());
        System.out.println("Preguntas: " + verPreguntas());
        System.out.println("Respuestas: " + verRespuestas());
    }


    
}
