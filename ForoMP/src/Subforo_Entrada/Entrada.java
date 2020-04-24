/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.util.ArrayList;

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
            
}
