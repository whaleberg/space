/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.mission;

import space.Positionable;
import space.Ship;

/**
 *
 * @author sailfish
 */

public class MoveTo implements Mission{
  
    public static final double CLOSE_ENOUGH = 1;
    
    private final Positionable target;
    
    public MoveTo(Positionable target){
        this.target = target;
    }
    
    @Override
    public Command plan(Ship s) {
        if(s.getPos().distance(target.getPos()) < CLOSE_ENOUGH){
            return null;
        } else {
            return null;
        }
    }
    
    
}
