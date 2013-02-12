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
public class WaitForeverCommand implements Command {
        

    @Override
    public int followOrders(Ship s, int seconds) {
        s.setDesiredSpeed(0);
        return Integer.MAX_VALUE;
    }
    
}
