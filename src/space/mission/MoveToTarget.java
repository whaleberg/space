/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.mission;

import java.nio.file.Path;

import space.collision.PathPlanning;

import space.Positionable;
import space.Ship;
import vector.Point2d;

/**
 *
 * @author sailfish
 */

public class MoveToTarget implements Mission{
  
    public static final double CLOSE_ENOUGH = 1;
    
    private final Positionable target;
    
    public MoveToTarget(Positionable target){
        this.target = target;
    }
    
    @Override
    public Command plan(Ship s) {
        if(s.getPos().distance(target.getPos()) < CLOSE_ENOUGH){
            return WaitForeverCommand.WAIT_FOREVER;
        } else {
        	Point2d destination = target.getIntersectionPoint(s);
        	if (destination != null){
        		return new TravelToPoint(destination);
        	} else {
        		return WaitForeverCommand.WAIT_FOREVER;
        	}
        }
    }
    
    

	
    
    
}
