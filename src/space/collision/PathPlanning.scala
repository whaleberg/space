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

	def timeTo(start: Point, end: Point, speed: Double)= ( (start - end).magnitude ) / speed
	
	def epsilonEquals(epsilon: Double, d1: Double, d2: Double): Boolean = {
		(d1 >= d2-epsilon) && (d1 <= d2+epsilon)
	}
	
	def closestPointOfIntersectionWithArbitraryPath(targetPos: Point, getFuturePos: Double => Point, 
					currentPos: Point, currentSpeed: Double): Option[Point] = {
	  val epsilon = .1
	  var estimatedTime = timeTo(targetPos, currentPos, currentSpeed)
	  var tmpPos = getFuturePos(estimatedTime)
	  var counts = 0;
	  var farthest = 0.0;
	  
	  while( !epsilonEquals (currentSpeed, estimatedTime , 
			  				timeTo(currentPos,tmpPos, currentSpeed))){
		
	    System.out.println("est:"+estimatedTime+","+"pos:"+tmpPos);
		 counts+=1
		 estimatedTime = timeTo(tmpPos, currentPos, currentSpeed)
		 farthest = scala.math.max(farthest, estimatedTime);
		 tmpPos = getFuturePos(estimatedTime)
		 if(counts >= 100){
		   return None
		 }
	  }
	  Some(tmpPos)
	}
	
	def canCatchUpTo(mover: Ship, target:Positionable){

	
	  val result = closestPointOfIntersectionWithArbitraryPath(target.getPos(), ( x: Double )=> target.getPositionIn(x.toLong), 
			  								mover.getPos(), mover.getMaxSpeed())
      result.isDefined
	}
	
	def getClosestIntersectionPoint(target: Positionable, mover : Ship):Point2d = {
	  closestPointOfIntersectionWithArbitraryPath(target.getPos(), (x:Double) => target.getPositionIn(x.toLong), 
	      mover.getPos(), mover.getMaxSpeed()) match{
	    case Some(value) => return value
	    case None => throw new AssertionError("Can't catch the target");
	  }
	}
	
	def closestPointOfIntersectionWithCircularPath(targetPos: Point, targetSpeed: Double, 
						currentPos: Point, currentSpeed: Double): Option[Point]= {
	  
	  None
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