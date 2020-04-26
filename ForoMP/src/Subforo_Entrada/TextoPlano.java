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
public class TextoPlano extends EntradaGenerica { // para hacer el patron composite faltaria crear la entradagenerica y crear la clase entrada
                                                  // una vez eso, definir en la clase entrada un arrayList de EntradaGenerica
    
    private String cuerpo;

    public TextoPlano(Usuario u, String titulo, String cuerpo) {
        super(u, titulo);
        this.cuerpo = cuerpo;
    }
    
    public String getCuerpo() {
        return cuerpo;
    }

    @Override
    public void mostrar() {
        System.out.println("Ejercicio creado por"  + getCreador());
        System.out.println("Título " + getTitulo());
        System.out.println("Texto: " + getCuerpo());
    }
    
    
    
}
