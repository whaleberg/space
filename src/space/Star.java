/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class Star extends AbstractOrbitable{
    
    private static final IconType icon = IconType.STAR;
    
    public Star(Point2d position, double mass, double radius){
        this.getBody().setPos(position);
        this.getBody().setMass(mass);
        this.getBody().setRadius(radius);
    }

    @Override
    public IconType getIconType() {
        return icon;
    }

    @Override
    public String getID(){
        return "Star:"+super.getID();
    }

}
