/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class PhysicalBody{
    private double mass;
    private double radius;
    private ReadOnlyObjectWrapper<Point2d> position = new ReadOnlyObjectWrapper<>();
    public ReadOnlyObjectProperty<Point2d> positionProperty() {
        return position.getReadOnlyProperty();
    }
    
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
        return position.get();
    }
    
    public void setPos(Point2d pos){
       position.set(pos);
    }
  
}
