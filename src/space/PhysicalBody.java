/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javax.vecmath.Point2d;

/**
 *
 * @author sailfish
 */
public class PhysicalBody{
    private double mass;
    private double radius;
    private Point2d position = new Point2d();

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point2d getPos() {
        return new Point2d(position);
    }
    
    public void setPos(Point2d pos){
        this.position = new Point2d(pos);
    }
  
}
