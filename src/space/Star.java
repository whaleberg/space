/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class Star implements Orbitable, Drawable{
    private final PhysicalBody body = new PhysicalBody();
    private final List<Orbiter> children = new ArrayList<>();
    
    private static final IconType icon = IconType.STAR;
    
    public Star(Point2d position, double mass, double radius){
        this.body.setPos(position);
        this.body.setMass(mass);
        this.body.setRadius(radius);
    }
    
    @Override
    public Point2d getPos() {
        return body.getPos();
    }

    @Override
    public Collection<Orbiter> getDirectOrbiting() {
       return Collections.unmodifiableCollection(children); 
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
        orb.setParent(this);
        return children.add(orb);
    }

    @Override
    public boolean removeOrbiter(Orbiter orb) {
       return children.remove(orb);
    }

    @Override
    public IconType getIconType() {
        return icon;
    }

    @Override
    public void age(long seconds) {
        for(Orbiter orb: children){
            orb.age(seconds);
        }
    }
    
}
