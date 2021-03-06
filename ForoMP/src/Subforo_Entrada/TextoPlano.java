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
public class TextoPlano extends EntradaGenerica {

    private String cuerpo;
/**
 * Cosntructor de TextoPlanos
 * @param u
 * @param titulo
 * @param cuerpo 
 */
    public TextoPlano(Usuario u, String titulo, String cuerpo) {
        super(u, titulo);
        this.cuerpo = cuerpo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    @Override
    public void mostrar() {
        System.out.println("Texto plano creado por " + getCreador().getNick());
        System.out.println("Título " + getTitulo());
        System.out.println("Texto: " + getCuerpo());
    }

    @Override
    public void modificarEntrada(Usuario u, String s) {
        if (u.equals(getCreador())) {
            cuerpo = s;
        }
    }

}
