/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.Collection;
import javax.vecmath.Point2d;
import javax.vecmath.Vector2d;

/**
 *
 * @author sailfish
 */
public class Planet implements Orbiter, Positionable{
    private PhysicalBody body;
    private double orbitalRadius;
    private double orbitalPeriod;
    private double orbitalAngle;
    private Orbitable parent;
    

   
    private void orbit(long seconds){
        double changeInAngle = (seconds / orbitalPeriod )*2 * Math.PI;
        orbitalAngle = changeInAngle + orbitalAngle;
        if(orbitalAngle > 2*Math.PI){
            orbitalAngle = orbitalAngle-Math.PI;
        }
        Vector2d displace = new Vector2d(Math.sin(orbitalAngle), Math.cos(orbitalAngle));
        displace.scale(orbitalRadius);
        displace.add(parent.getPos());
        this.body.setPos( displace );
        
    }
    
    private static Vector2d getAngleVector(double angle){
        return new Vector2d(Math.sin(angle), Math.cos(angle));
    }
    
    @Override
    public void age(long seconds) {
        this.orbit(seconds);
    }

    @Override
    public Point2d getPos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
