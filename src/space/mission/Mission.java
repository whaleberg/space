/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.mission;

import space.Ship;



/**
 *
 * @author sailfish
 * 
 * Represents a specific goal for a ship.  This may be simple like "travel to earth"
 * or more complex like "trade goods between planets"
 */
public interface Mission{
        
	/**
	 * 
	 * @param s
	 * @return
	 */
    public Command plan(Ship s);
}
