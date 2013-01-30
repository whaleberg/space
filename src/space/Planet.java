/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import vector.Vector2d;

/**
 *
 * @author sailfish
 */
public class Planet extends AbstractOrbitable {
    private double orbitalRadius;
    private double orbitalPeriod;
    private double orbitalAngle;
    
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
    }
   
    private void orbit(long seconds){
        if (getParent() != null){
            double changeInAngle = (seconds / orbitalPeriod )*2*Math.PI;
            orbitalAngle += changeInAngle;
            while(orbitalAngle > 2*Math.PI){
                orbitalAngle -= 2*Math.PI;
            }
            Vector2d displace = getAngleVector(orbitalAngle).scale(orbitalRadius);
            this.getBody().setPos(getParent().getPos().translate(displace));
        } else {
            System.err.println("Planet with null parent");
        }
    }
    
    private static Vector2d getAngleVector(double angle){
        return new Vector2d(Math.cos(angle), Math.sin(angle));
    }
    
    @Override
    public void age(long seconds) {
        this.orbit(seconds);
        super.age(seconds);
    }


    @Override
    public IconType getIconType() {
       return IconType.PLANET;
    }

    @Override
    public void setParent(Orbitable orb) {
        super.setParent(orb);
        orbit(0);
    }

    @Override
    public String getID(){
        return "Planet:"+super.getID();
    }
    
    
}
