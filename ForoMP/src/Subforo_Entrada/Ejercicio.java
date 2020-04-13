/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subforo_Entrada;



/**
 *
 * @author alvarogonzalez
 */
public class Ejercicio extends EntradaGenerica  {
    private String nombre;
    private String enunciado;
    private String solucion;
    

    public Ejercicio(String nombre, String enunciado, String solucion) {
        this.nombre = nombre;
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
     
    
    
}
