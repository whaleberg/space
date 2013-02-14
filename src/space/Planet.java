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
   
 
    /**
     * Move the planet along its orbit
     * @param seconds the amount of time to use when advancing the orbit
     */
    private void orbit(long seconds){
        if (getParent() != null){
            Vector2d displace = calculateDisplacement(seconds);
            this.getBody().setPos(getParent().getPos().translate(displace));
        } else {
            System.err.println("Planet with null parent");
        }
    }

    /**
     * Calculate the displacement vector for moving a planet along it's orbit
     * @param seconds
     * @return
     */
	private Vector2d calculateDisplacement(long seconds) {
		double changeInAngle = (seconds / orbitalPeriod )*2*Math.PI;
		orbitalAngle += changeInAngle;
		while(orbitalAngle > 2*Math.PI){
		    orbitalAngle -= 2*Math.PI;
		}
		Vector2d displace = Vector2d.getAngleVector(orbitalAngle).scale(orbitalRadius);
		return displace;
	}
    
    @Override
	public Point2d getPositionIn(long seconds) {
    	return super.getPositionIn(seconds).translate(calculateDisplacement(seconds));		
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
