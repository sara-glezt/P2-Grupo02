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
public class TextoPlano extends EntradaGenerica { // para hacer el patron composite faltaria crear la entradagenerica y crear la clase entrada
                                                  // una vez eso, definir en la clase entrada un arrayList de EntradaGenerica
    
    private String texto;

    public TextoPlano(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
}
