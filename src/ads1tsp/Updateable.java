/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 * 
 */

/**
 * Notification system
 * allows to send and receive notifications
 */
public interface Updateable {
    void Notify();
    void setListener(Updateable that);
    void sendMessage();
    
}
