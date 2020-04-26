/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;

import users.Usuario;



/**
 *
 * @author alvarogonzalez
 */
public class Ejercicio extends EntradaGenerica  {
    
    private String enunciado;
    private String solucion;
    

    public Ejercicio(Usuario u, String titulo, String enunciado, String solucion) {
        super(u, titulo);
        
        this.enunciado = enunciado;
        this.solucion = solucion;
    }

    public String getEnunciado() {
        return enunciado;
    }


    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public void mostrar() {
        System.out.println("Ejercicio creado por"  + getCreador());
        System.out.println("Título " + getTitulo());
        System.out.println("Enunciado: " + getEnunciado());
        System.out.println("Solucion: " + getSolucion());
    }
     
    
    
}
