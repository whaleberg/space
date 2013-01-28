/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import vector.Point2d;
import vector.Vector2d;

/**
 *
 * @author sailfish
 */
public class Planet implements Orbiter{
    private PhysicalBody body;
    private double orbitalRadius;
    private double orbitalPeriod;
    private double orbitalAngle;
    private Orbitable parent;
    
    public static Planet newSatelliteOf(Orbitable orb, double orbitalRadius,
            double orbitalPeriod, double orbitalAngle){
       Planet p = new Planet(orbitalRadius, orbitalPeriod, orbitalAngle);
       orb.addOrbiter(p);
       return p;
    }
    
    public Planet(double orbitalRadius, double orbitalPeriod, double orbitalAngle){
        this.orbitalRadius = orbitalRadius;
        this.orbitalPeriod = orbitalPeriod;
        this.orbitalAngle = orbitalAngle;
        body = new PhysicalBody();
    }
   
    private void orbit(long seconds){
        if (parent != null){
            System.out.print("Orbit:"+body.getPos().toString() + " -> ");
            double changeInAngle = (seconds / orbitalPeriod )*2*Math.PI;
            orbitalAngle += changeInAngle;
            while(orbitalAngle > 2*Math.PI){
                orbitalAngle -= 2*Math.PI;
            }
            Vector2d displace = getAngleVector(orbitalAngle).scale(orbitalRadius);
            this.body.setPos(parent.getPos().translate(displace));
            System.out.println(body.getPos().toString());
        }
    }
    
    private static Vector2d getAngleVector(double angle){
        return new Vector2d(Math.cos(angle), Math.sin(angle));
    }
    
    @Override
    public void age(long seconds) {
        this.orbit(seconds);
    }

    @Override
    public Point2d getPos() {
        return body.getPos();
    }

    @Override
    public IconType getIconType() {
       return IconType.PLANET;
    }

    @Override
    public void setParent(Orbitable orb) {
        parent = orb;
        orbit(0);
    }

    
    
}
