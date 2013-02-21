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
        this.setOrbitalAngle(orbitalAngle);
    }
   
 
    /**
     * Move the planet along its orbit
     * @param seconds the amount of time to use when advancing the orbit
     */
    private void orbit(long seconds){
        if (getParent() != null){
        	this.setOrbitalAngle(getOrbitalAngleIn(seconds));
            Vector2d displace = getDisplacementFromParent(orbitalAngle);
            this.getBody().setPos(getParent().getPos().translate(displace));
        } else {
            System.err.println("Planet with null parent");
        }
    }

	private double getOrbitalAngleIn(double seconds) {
		return orbitalAngle + calculateChangeInOrbitalAngle(seconds);
	}
	
	private void setOrbitalAngle(double orbitalAngle) {
		this.orbitalAngle = orbitalAngle % (2*Math.PI);
	}

	/**
	 * returns a vector from the parents position to the position of the planet at orbitalAngle theta
	 * @param theta
	 * @return
	 */
	private Vector2d getDisplacementFromParent(double theta) {
		Vector2d displace = Vector2d.getAngleVector(theta).scale(orbitalRadius);
		return displace;
	}

	/**
	 * determines what angle the planet passes through in seconds time
	 * @param seconds
	 * @return
	 */
	private double calculateChangeInOrbitalAngle(double seconds) {
		double changeInAngle = (seconds / orbitalPeriod )*2*Math.PI;
		return changeInAngle;
	}
    
    @Override
	public Point2d getPositionIn(double seconds) {
    	return super.getPositionIn(seconds).translate(
    			getDisplacementFromParent(getOrbitalAngleIn(seconds)));		
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
