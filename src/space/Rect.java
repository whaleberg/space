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
         
       this.topLeft = new Point2d(Math.min(p1.getX(), p2.getX()), Math.min(p1.getY(),p2.getY()));
       this.height = Math.abs(p1.getX() - p2.getX());
       this.width = Math.abs(p1.getY() - p2.getY());
    }
    
    public boolean contains(Point2d p){
        return ( (p.getX() >= topLeft.getX() && p.getX() <= topLeft.getX()+width)) 
            && (p.getY() >= topLeft.getY() && p.getY() <= topLeft.getY() + height);
    }


}
