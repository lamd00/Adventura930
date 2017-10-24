/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utls;

/**
 *
 * @author Dominik
 */
public interface Observer {
    
     void registerObserver(Observer observer);
     
     void removeObserver(Observer observer);
     
     void notifyObservers();
    
}
