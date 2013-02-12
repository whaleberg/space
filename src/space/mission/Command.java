/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.mission;

import space.Ship;

/**
 *
 * @author sailfish
 */
public interface Command {
    public int followOrders(Ship s, int seconds);
}

