/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.vecmath.Point2d;

/**
 *
 * @author sailfish
 */
public class Star implements Orbitable, Positionable{
    private final PhysicalBody body = new PhysicalBody();
    private final List<Orbiter> children = new ArrayList<>();
    
    
    @Override
    public Point2d getPos() {
        return body.getPos();
    }

    @Override
    public Collection<Orbiter> getDirectOrbiting() {
       return children; 
    }

    @Override
    public double getMass() {
        return body.getMass();
    }

    @Override
    public double getRadius() {
        return body.getRadius();
    }

    @Override
    public boolean addOrbiter(Orbiter orb) {
        return children.add(orb);
    }

    @Override
    public boolean removeOrbiter(Orbiter orb) {
       return children.remove(orb);
    }
    
}
