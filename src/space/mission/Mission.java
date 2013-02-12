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
public interface Mission{
        
    public Command plan(Ship s);
}
