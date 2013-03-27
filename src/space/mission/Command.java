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
	/**
	 * Causes a ship to perform the action assigned to it*/
    public int followOrders(Ship s, int seconds);
}

