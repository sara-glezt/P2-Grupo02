/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Equipo
 */
public class Comentario implements Serializable {
private static final long serialVersionUID = 1L;
    private int valoracion;

    private ArrayList<Comentario> comentarios;
    private String texto;

    public Comentario(String txt) {
        texto = txt;
        valoracion = 0;
        this.comentarios = new ArrayList<Comentario>();

    }
    
    public void responderComentario(String s){
      Comentario come = new Comentario(s); 
      this.comentarios.add(come);
      System.out.println("Comentario comentado");
    }
    
    public String respuestaComentarios(){
        String info=null;
        for(Comentario comen : comentarios){
            info = comen.getTexto();
        }
       return info; 
    }
    
    

    //faltan metodos

    public String getTexto() {
        return texto;
    }
}
