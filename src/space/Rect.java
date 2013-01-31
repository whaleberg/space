/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.Collection;
import java.util.Objects;
import vector.Point2d;
/**
 *
 * @author sailfish
 */
public final class Rect {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.topLeft);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "Rect{" + topLeft + ", h" + height + ", w=" + width + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rect other = (Rect) obj;
        if (!Objects.equals(this.topLeft, other.topLeft)) {
            return false;
        }
        if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
            return false;
        }
        return true;
    }
    
    public final Point2d topLeft;
    public final double height;
    public final double width;
    public Rect(Point2d topLeft, double height, double width){
        this.topLeft = new Point2d(topLeft);
        this.height = height;
        this.width = width;
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

    public boolean matchesAspectRatio(Rect r){
        return aspectRatio() == r.aspectRatio();
    }
    
    public double aspectRatio(){
        return Math.abs(width / height);
    }
    
    public Rect expandToAspectRatio(Rect r) {
        assert (r != null);
       
        if (r.aspectRatio() == this.aspectRatio()) {
            return this;
        } else if (this.aspectRatio() < r.aspectRatio() ) {
            return new Rect(this.topLeft, this.height, this.width * r.aspectRatio());
        } else if (this.aspectRatio() > r.aspectRatio()) {
            return new Rect(this.topLeft, this.height / r.aspectRatio(), this.width);
        }
        throw new AssertionError("Something is wrong if it got here...");
        
    }
    
    public boolean hasZeroArea(){
        return height == 0 || width == 0;
    }



    
    public static Rect boundingRect(Collection<Point2d> points) {
        double l = Double.MAX_VALUE;
        double r = 0;
        double t = Double.MAX_VALUE;
        double b = 0;
        for (Point2d pos : points) {
            l = Math.min(l, pos.getX());
            r = Math.max(r, pos.getX());
            t = Math.min(t, pos.getY());
            b = Math.max(b, pos.getY());
        }
        Point2d lt = new Point2d(l, t);
        Point2d rb = new Point2d(r, b);
        return new Rect(lt, rb);
    }
}
