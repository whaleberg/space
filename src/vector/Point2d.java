/*This file is based on the java vecmath package( available at 
 * http://java.net/projects/vecmath/), but has been modified from its 
 * original form.  
 * Modifications were made by Louis Bergelson - whaleberg@gmail.com.
 * This modified version maintains the classpath exception.
 * The following is the original copyright and licensing notice.
 * 2/2/13
 */

/*
 * $RCSfile$
 *
 * Copyright 1998-2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 *
 * $Revision: 127 $
 * $Date: 2008-02-28 15:18:51 -0500 (Thu, 28 Feb 2008) $
 * $State$
 */

package vector;

import java.util.Objects;

/**
 * A 2 element point that is represented by double precision floating 
 * point x,y coordinates.
 *
 */
public class Point2d implements java.io.Serializable {

    // Compatible with 1.1
    static final long serialVersionUID = 1133748791492571954L;
    private final Vector2d vect;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.vect);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point2d other = (Point2d) obj;
        if (!Objects.equals(this.vect, other.vect)) {
            return false;
        }
        return true;
    }
    /**
     * Constructs and initializes a Point2d from the specified xy coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point2d(double x, double y)
    {
         this.vect = new Vector2d(x,y);
    }

    public Point2d(Vector2d v){
        this.vect = v;
    }

    /**
     * Constructs and initializes a Point2d from the specified Point2d.
     * @param p the Point2d containing the initialization x y data
     */
    public Point2d(Point2d p)
    {
        this.vect = p.getVector();
    }




    /**
     * Constructs and initializes a Point2d to (0,0).
     */
    public Point2d()
    {
        this.vect = new Vector2d();
    }

  /**
   * Computes the square of the distance between this point and point p.
   * @param p the other point
   */
  public final double distanceSquared(Point2d p)
    {
      double dx, dy;

      dx = this.getX()-p.getX();  
      dy = this.getY()-p.getY();
      return dx*dx+dy*dy;
    }

  /**
   * Computes the distance between this point and point p1.
   * @param p the other point 
   */    
  public final double distance(Point2d p)
    {
      double  dx, dy;

      dx = this.getX()-p.getX();  
      dy = this.getX()-p.getY();
      return Math.sqrt(dx*dx+dy*dy);
    }


  /**
    * Computes the L-1 (Manhattan) distance between this point and
    * point p.  The L-1 distance is equal to abs(x1-x2) + abs(y1-y2).
    * @param p the other point
    */
  public final double distanceL1(Point2d p)
    {
      return( Math.abs(this.getX()-p.getX()) + Math.abs(this.getY()- p.getY()));
    }

  /**
    * Computes the L-infinite distance between this point and
    * point p.  The L-infinite distance is equal to 
    * MAX[abs(x1-x2), abs(y1-y2)]. 
    * @param p the other point
    */
  public final double distanceLinf(Point2d p)
    {
      return(Math.max( Math.abs(this.getX()-p.getX()), Math.abs(this.getY()-p.getY())));
    }

    public Vector2d getVector() {
       return this.vect;
    }
    
    public double getX(){
        return vect.getX();
    }
    public double getY(){
        return vect.getY();
    }

    public Point2d translate(Vector2d v){
        return new Point2d( this.vect.add(v));
    }
    
    @Override
    public String toString(){
        return vect.toString();
    }
}
