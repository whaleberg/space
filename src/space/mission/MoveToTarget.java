/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space.mission;

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
        	Point2d destination = computeClosestIntercept(s, target);
            return new TravelToPoint(destination);
        }
    }

	private Point2d computeClosestIntercept(Ship s, Positionable target) {
		double startDistance = s.getPos().distance(target.getPos());
		double timeToCoverStartDistance = startDistance / s.getMaxSpeed();
		if (target.getPos() == target.getPositionIn((long) timeToCoverStartDistance));
		
		return null;
	}
    
    
}
