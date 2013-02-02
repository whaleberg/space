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
public abstract class AbstractOrbitable implements Orbitable {
    private final PhysicalBody body = new PhysicalBody();
    private Orbitable parent = null;
    
    public PhysicalBody getBody() {
        return body;
    }
    
    private final List<Orbitable> children = new ArrayList<>();

    @Override
    public boolean addOrbiter(Orbitable orb) {
        orb.setParent(this);
        return children.add(orb);
    }

    @Override
    public void age(long seconds) {
        for (Orbitable orb : children) {
            orb.age(seconds);
        }
    }

    @Override
    public Collection<Orbitable> getDirectOrbiting() {
        return Collections.unmodifiableCollection(children);
    }

    @Override
    public double getMass() {
        return body.getMass();
    }

    @Override
    public Point2d getPos() {
        return body.getPos();
    }

    @Override
    public double getRadius() {
        return body.getRadius();
    }

    @Override
    public boolean removeOrbiter(Orbitable orb) {
        return children.remove(orb);
    }

    @Override
    public void setParent(Orbitable orb){
        this.parent = orb;
    }
    
    public Orbitable getParent(){
        return parent;
    }
    
}
