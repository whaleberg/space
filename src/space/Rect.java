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
public final class Rect {
    
    private final Point2d topLeft;
    private final double height;
    private final double width;
    public Rect(Point2d topLeft, double h, double w){
        this.topLeft = new Point2d(topLeft);
        this.height = h;
        this.width = w;
    }
    
    public Rect(Point2d p1, Point2d p2){
         
       this.topLeft = new Point2d(Math.min(p1.x, p2.x), Math.min(p1.y,p2.y));
       this.height = Math.abs(p1.x - p2.x);
       this.width = Math.abs(p1.y - p2.y);
    }
    
    public boolean contains(Point2d p){
        return ( (p.x >= topLeft.x && p.x <= topLeft.x+width)) 
            && (p.y >= topLeft.y && p.y <= topLeft.y + height);
    }


}
