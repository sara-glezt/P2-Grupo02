/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author Miguel Angel
 */
public interface Observable {
    public void notifySubscriptor();
    public void addSubscriptor(Observer o);
    public void deleteSubscriptor(Observer o);
    
}
