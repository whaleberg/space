/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.Collection;

/**
 *
 * @author sailfish
 * 
 * Must override age() to call age on all of it's children.
 */
public interface Orbitable extends Drawable, Active{
    
    public Collection<Orbitable> getDirectOrbiting();
    public double getMass();
    public double getRadius();
    public boolean addOrbiter(Orbitable orb);
    public boolean removeOrbiter(Orbitable orb);
    public void setParent(Orbitable orb);
}
