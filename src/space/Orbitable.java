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
public interface Orbitable extends Active{
    
    public Collection<Orbiter> getDirectOrbiting();
    public double getMass();
    public double getRadius();
    public boolean addOrbiter(Orbiter orb);
    public boolean removeOrbiter(Orbiter orb);
}
