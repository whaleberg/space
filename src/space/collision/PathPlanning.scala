package space.collision

import scala.math._
import vector.Point2d
import space.Positionable
import space.Ship
import vector.Vector2d
import space.collision.PointImplicits._


object PathPlanning {

	/**
	 * based on the algorithm here
	 * http://stackoverflow.com/questions/10358022/find-the-better-intersection-of-two-moving-objects
	 * 
	 * thanks to meriton
	 * 
	 * sx^2 + sy^2 + (2 * sx * vx + 2 * sy * vy) * t + (vx^2 + vy^2 - v2^2) * t^2 = 0
	 * \---   ---/   \------------   ----------/       \--------   ------/
	 *     \ /                    \ /                           \ /
	 *      c                      b                             a
	 */
	def closestPointOfIntersectionWithLinearPath(targetPos: Point, targetVel: Point, 
			currentPos: Point, speed : Double): Option[Point] = {

			val offset = targetPos - currentPos
			val c = pow(offset.x, 2) +pow(offset.y,2)
			val b = 2 * offset.x * targetVel.x + 2*offset.y * targetVel.y
			val a = pow(targetVel.x,2) + pow(targetVel.y,2) - pow(speed,2)					
					
			for {
			  (r1, r2) <- solveQuadraticForRealRoots(a, b, c)
			} yield { 
			val t = (r1, r2) match {
			  case (r1, r2) if r1 >=0 && r2>=0 => min(r1, r2)
			  case (r1, _) if r1 >= 0 => r1
			  case (_ , r2) if r2 >= 0 => r2
			}
			
			val collisionPos = targetPos+targetVel.scale(t);
			new Point(collisionPos.x, collisionPos.y)
			}
			
	}

	def linearIntersection(target: Ship, ship: Ship):Point2d= {
	  closestPointOfIntersectionWithLinearPath(target.getPos(), target.getVelocity()
	      , ship.getPos(), ship.getMaxSpeed()) match {
	    case None => null
	    case Some(point) => point
	  }
	}
	
	def orbitalIntersection(target: Positionable, ship: Ship):Point2d= {
	  closestPointOfIntersectionWithBoundedPath(target.getPos(), target.getPositionIn _
			  , ship.getPos(), ship.getMaxSpeed()) match {
	    case None => null
	    case Some(point) => point 
	  }
	}
	
	def stationaryIntersection(target: Positionable, ship: Ship):Point2d= {
		if (ship.getMaxSpeed() == 0) {
		  null
		} else {
		  target.getPos()
		}
	}
	
	
	def timeTo(speed: Double, start: Point2d, end: Point2d)= ( (start - end).magnitude ) / speed
	
	def closestPointOfIntersectionWithBoundedPath(targetPos: Point, getFuturePos: Double => Point,
	    	pos: Point, speed: Double): Option[Point] = {
	  def timeToTarget = (target: Point) => timeTo(speed, pos, target)
	  
	  def timeToTargetAtTime(t: Double):Double= timeToTarget(getFuturePos(t))
	  val initialTimeGuess = timeToTarget(targetPos)
	  
	  if(speed == 0){
	    return None
	  }
	  
	  var time = 1.0  
	  val CUTOFF = Double.MaxValue
	  while (time < timeToTargetAtTime(time)){
	      time = time*2
	      if(time > CUTOFF){
	    	  throw new IllegalArgumentException("Path should be bounded, but a solution cannot be found.")
	      }
	   }
	  
	  val maxTime = time
	  while(timeToTargetAtTime(time) != time){
	    time = (time + timeToTargetAtTime(time))/2
	  }
	  return Some(getFuturePos(time))
	  
	}
	
		
	def getClosestIntersectionPoint(target: Positionable, mover : Ship):Point2d = {
	  closestPointOfIntersectionWithBoundedPath(target.getPos(), (x:Double) => target.getPositionIn(x.toLong), 
	      mover.getPos(), mover.getMaxSpeed()) match{
	    case Some(value) => return value
	    case None => throw new AssertionError("Can't catch the target");
	  }
	}
	
	/**
	 * solve equation of the form ax+b = 0 for x
	 */
	def solveLinear(a: Double, b:Double):Double = {
			val x = -b / a
			return x
	}
	
	/**
	 * solve equation for the form ax^2 + bx + c = 0
	 * for the real roots of x
	 * 
	 * if a = 0 it will solve it as a linear equation, but still return 2 roots
	 */
	def solveQuadraticForRealRoots(a: Double, b: Double, c: Double):Option[(Double, Double)]={
			a match{
			  case a if a == 0 =>
			    val r = solveLinear(b, c)
			    System.out.println(r);
			    Some(r, r)
			  case _ => 
				val disc = b*b - 4*a*c
				disc match {
				  case d if d < 0 => None
				  case d if d >= 0 =>
				  val r1 = (-b + sqrt(d))/(2 *a)
				  val r2 = (-b - sqrt(d))/(2* a)
				  System.out.println(r1 +","+ r2);
				  Some(r1, r2)
				  
				}
			}
			
	}

}