/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import java.io.Serializable;
import java.util.Date;
import users.Administrador;
import users.Usuario;

/**
 *
 * @author Dani
 */
public class EntradaGenerica implements Serializable{
    private int valoracion;
    private int numTotalVal; // No añadido en UML. Necesario para le media
    private Date fecha;
    private String titulo;
    private boolean publicada;
    private boolean verificado;
    private Usuario creador; //creador no está en el UML La flecha de es creada.
                            //deberia ser hacia EntradaGenerica.
    
    public EntradaGenerica(){
        this.valoracion = valoracion;
        this.numTotalVal = numTotalVal;
        this.fecha = fecha; //como obtener la fecha del new?
        this.titulo = titulo;
        this.publicada = publicada;
        this.verificado = verificado;
        this.creador = creador;
                
    }
    
    /**
     * votar() modifica la valoracion de la entrada.
     * Recibe una valoracion por parte de un usuario y se calculara la media
     * entre todas las valoraciones recibidas.
     * Por cada vez que se haga votar() se sumará 1 al numero total de 
     * valoraciones
     * @param u Usuario
     * @param val int
     */
    public void votar(Usuario u, int val){ //arg valoracion no añadido en UML
        if (val >= 0 || val <=10){
            numTotalVal = numTotalVal + 1;
            valoracion = (valoracion + val)/numTotalVal;            
        }
    }
    
    public boolean verificar(Administrador a){
        /*Se le pasa una lista de palabras y si no las encuentra en el texto de
        la entrada, entonces verificado a true
        */ 
        
        return verificado;        
    }
    
    
    
}
