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

/**
 * A 2-element vector that is represented by double-precision floating 
 * point x,y coordinates.
 *
 */
public class Vector2d implements java.io.Serializable {

    /**
     * The x coordinate.
     */
    public final double	x;

    /**
     * The y coordinate.
     */
    public final double	y;


    /**
     * Constructs and initializes a Vector2d from the specified xy coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector2d(double x, double y)
    {
	this.x = x;
	this.y = y;
    }


    /**
     * Constructs and initializes a Vector2d from the specified Vector2d.
     * @param t1 the Vector2d containing the initialization x y data
     */
    public Vector2d(Vector2d t1)
    {
	this.x = t1.x;
	this.y = t1.y;
    }


   
    /**
     * Constructs and initializes a Vector2d to (0,0).
     */
    public Vector2d()
    {
	this.x = 0.0;
	this.y = 0.0;
    }









   /**
    *  Copies the value of the elements of this tuple into the array t.
    *  @param t the array that will contain the values of the vector
    */
   public final void get(double[] t)
    {
        t[0] = this.x;
        t[1] = this.y;
    }



    /**
     * Returns a new tuple which is the sum of this tuple and the other tuple.
     * @param t the other tuple
     */  
    public final Vector2d add(Vector2d t)
    {
        return new Vector2d(this.x + t.x, this.y + t.y);
    }

  
    /**
     * Sets the value of this tuple to the vector difference of
     * itself and tuple t1 (this = this - t1).
     * @param t1 the other vector
     */  
    public final Vector2d sub(Vector2d t)
    {
        return new Vector2d(this.x-t.x, this.y-t.y);
    }


    /**
     * Negates the value of this vector in place.
     */
    public final Vector2d negate()
    {
	return new Vector2d(- this.x, -this.y);
    }


    /**
     * Returns the scalar multiplication of this tuple
     * @param s the scalar value
     */
    public final Vector2d scale(double s)
    {
	return new Vector2d(this.x*s, this.y*s);
    }




    /**
     * Returns a hash code value based on the data values in this
     * object.  Two different Vector2d objects with identical data values
     * (i.e., Vector2d.equals returns true) will return the same hash
     * code value.  Two objects with different data members may return the
     * same hash value, although this is not likely.
     * @return the integer hash code value
     */  
    @Override
    public int hashCode() {
	long bits = 1L;
	bits = 31L * bits + VecMathUtil.doubleToLongBits(x);
	bits = 31L * bits + VecMathUtil.doubleToLongBits(y);
	return (int) (bits ^ (bits >> 32));
    }


   /**   
     * Returns true if all of the data members of Vector2d t1 are
     * equal to the corresponding data members in this Vector2d.
     * @param t  the vector with which the comparison is made
     * @return  true or false
     */  
    public boolean equals(Vector2d t)
    {
        if(t!=null){
           return(this.x == t.x && this.y == t.y);
        } else {
            return false;
        }
    }

   /**   
     * Returns true if the Object t1 is of type Vector2d and all of the
     * data members of t1 are equal to the corresponding data members in
     * this Vector2d.
     * @param t1  the object with which the comparison is made
     * @return  true or false
     */  
    @Override
    public boolean equals(Object t1)
    {
        if(t1 instanceof Vector2d){
           Vector2d t2 = (Vector2d) t1;
           return(this.x == t2.x && this.y == t2.y);
        } else {
            return false;
        }
 
    }

   /**
     * Returns true if the L-infinite distance between this tuple
     * and tuple t1 is less than or equal to the epsilon parameter, 
     * otherwise returns false.  The L-infinite
     * distance is equal to MAX[abs(x1-x2), abs(y1-y2)]. 
     * @param t1  the tuple to be compared to this tuple
     * @param epsilon  the threshold value  
     * @return  true or false
     */
    public boolean epsilonEquals(Vector2d t1, double epsilon)
    {
       double diff;

       diff = x - t1.x;
       if(Double.isNaN(diff)) {
            return false;
        }
       if((diff<0?-diff:diff) > epsilon) {
            return false;
        }

       diff = y - t1.y;
       if(Double.isNaN(diff)) {
            return false;
        }
       if((diff<0?-diff:diff) > epsilon) {
            return false;
        }

       return true;
    }

   /**
     * Returns a string that contains the values of this Vector2d.
     * The form is (x,y).
     * @return the String representation
     */  
    @Override
   public String toString()
   {
        return("(" + this.x + "," + this.y + ")");
   }





  /**
    *  Returns a new tuple equal with each component set to its absolute value
    */
  public final Vector2d absolute()
  {
      return new Vector2d(Math.abs(x), Math.abs(y));
  }




  /**  
    *  Linearly interpolates between this tuple and tuple t1 and 
    *  r the result. (1-alpha)*this + alpha*t1.
    *  @param t1  the first tuple
    *  @param alpha  the alpha interpolation parameter  
    */   
  public final Vector2d interpolate(Vector2d t1, double alpha) 
  { 
       double newX = (1-alpha)*this.x + alpha*t1.x;
       double newY = (1-alpha)*this.y + alpha*t1.y;
           return new Vector2d(newX, newY);   

  } 

 


	/**
	 * Get the <i>x</i> coordinate.
	 * 
	 * @return the <i>x</i> coordinate.
	 * 
	 * @since vecmath 1.5
	 */
	public final double getX() {
		return x;
	}




	/**
	 * Get the <i>y</i> coordinate.
	 * 
	 * @return the <i>y</i> coordinate.
	 * 
	 * @since vecmath 1.5
	 */
	public final double getY() {
		return y;
	}



  /**
   * Computes the dot product of the this vector and vector v1.
   * @param v1 the other vector
   */
  public final double dot(Vector2d v1)
    {
      return (this.x*v1.x + this.y*v1.y);
    }


    /**  
     * Returns the length of this vector.
     * @return the length of this vector
     */  
    public final double length()
    {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }

    /**  
     * Returns the squared length of this vector.
     * @return the squared length of this vector
     */  
    public final double lengthSquared()
    {
        return (this.x*this.x + this.y*this.y);
    }


    /**
     * Returns a normalized version of this vector.
     */  
    public final Vector2d normalize()
    {
        double norm;
        norm = 1.0/Math.sqrt(this.x*this.x + this.y*this.y);
        return new Vector2d(this.x* norm, this.y*norm);
    }


  /**
    *   Returns the angle in radians between this vector and the vector
    *   parameter; the return value is constrained to the range [0,PI].
    *   @param v1    the other vector
    *   @return   the angle in radians in the range [0,PI]
    */
   public final double angle(Vector2d v1)
   {
      double vDot = this.dot(v1) / ( this.length()*v1.length() );
      if( vDot < -1.0) {
           vDot = -1.0;
       }
      if( vDot >  1.0) {
           vDot =  1.0;
       }
      return Math.acos( vDot );

   }
   

   
}
