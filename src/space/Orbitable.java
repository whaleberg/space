/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.Collection;
import java.util.Set;

/**
 *
 * @author sailfish
 */
public interface Orbitable extends Positionable{
    
    public Collection<Orbiter> getDirectOrbiting();
    public double getMass();
    public double getRadius();
    public boolean addOrbiter(Orbiter orb);
    public boolean removeOrbiter(Orbiter orb);
}
