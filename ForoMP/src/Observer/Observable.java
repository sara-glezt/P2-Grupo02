/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Subforo_Entrada.EntradaGenerica;


/**
 *
 * @author Miguel Angel
 */
public interface Observable {
    public void notifySubscriptor(EntradaGenerica ent);
    public void addSubscriptor(Observer o);
    public void deleteSubscriptor(Observer o);
    
}
