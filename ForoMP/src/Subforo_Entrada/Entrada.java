/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.util.ArrayList;
import users.Profesor;

/**
 *
 * @author Equipo
 */
public class Entrada extends EntradaGenerica{
    
    private ArrayList<Comentario> comentarios;
    private ArrayList<EntradaGenerica> componentes;
    
    public Entrada(String titulo){ //Debug exactamente que metemos por constructor
        super();
        comentarios = new ArrayList<Comentario>();
        componentes = new ArrayList<EntradaGenerica>();
        //metodos crear ejercicios, textoplano, encuesta
        
    }
    
    //Duda, realmente los comentarios solo los tienen las entradas, al margen
    // de su tipo. ¿comentar() estaria en entrada o EntradaGenerica?
  
    
  //Sistema instancia = Sistema.getInstance(); con esto para controlar quien hace el que, si profe...
  //if(instancia.getConectado() instanceof Profesor) 
    
    public void addTextoPlano(){
        TextoPlano texto = new TextoPlano();
        componentes.add(texto);
    }
    
    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEncuesta(Profesor p){
        Encuesta encuesta = new Encuesta();
    }
    
    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEjercicio (Profesor prof, String p, String r){
        Ejercicio ejercicio = new Ejercicio(p, r);
        componentes.add (ejercicio);
        
    }
            
}
