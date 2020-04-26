/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import Sistema.Sistema;
import java.util.ArrayList;
import users.Profesor;
import users.Usuario;

/**
 *
 * @author Equipo
 */
public class Entrada extends EntradaGenerica{
    
    private ArrayList<Comentario> comentarios;
    private ArrayList<EntradaGenerica> componentes;
    Sistema s = Sistema.getInstance();
    
    public Entrada(Usuario u, String titulo){ //Debug exactamente que metemos por constructor
        super(u, titulo);
        comentarios = new ArrayList<Comentario>();
        componentes = new ArrayList<EntradaGenerica>();
        //metodos crear ejercicios, textoplano, encuesta
        
    }
    
 
    
  //Sistema instancia = Sistema.getInstance(); con esto para controlar quien hace el que, si profe...
  //if(instancia.getConectado() instanceof Profesor) 
    
    public void addTextoPlano(Usuario u, String titulo, String cuerpo){
        
            TextoPlano texto = new TextoPlano(u, titulo, cuerpo);
            componentes.add(texto);
        
        
    }
    
    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEncuesta(Usuario u, String titulo){
        if(s.getConectado() instanceof Profesor){
            Encuesta encuesta = new Encuesta(u, titulo);
            componentes.add(encuesta);
        }
    }
    
    //Duda, para controlar que es un profesor, esta bien pasarlo como argumento?
    public void addEjercicio (Usuario u, String titulo, String p, String r){
        if(s.getConectado() instanceof Profesor){
        Ejercicio ejercicio = new Ejercicio(u, titulo, p, r);
        componentes.add (ejercicio);
        }
    }

    @Override
    public void mostrar() {
        for(int i = 0; i <= componentes.size(); i++){
            componentes.get(i).mostrar();
        }
    }
            
}
