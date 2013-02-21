package space.collision

import space.Star
import space.Planet
import vector.Point2d
import space.collision.PointImplicits._

object PathPlanningTest extends App {
	val p1 = new Point(10, 10)
	val v1 = new Point(1,-1)
	val p2 = new Point(0, 0)
	val s2 = 2
	
	val star = new Star(new Point2d(10,10), 10,10);
	val planet = Planet.newSatelliteOf(star, 5, 20, 0)

	
	def notMoving(time:Double) = new Point(10,10)
	def linear(time:Double) = {
	  val point = new Point(10,10);
	  val velocity = new Point(1,-1);
	  point+velocity.scale(time)
	}
	
	for(i <-0 to 20){
	  println(i+":"+planet.getPositionIn(i))
	}
	println(planet.getPos());
	println(planet.getPositionIn(50))

	
	
	//System.out.println("Stationary "+PathPlanning.closestPointOfIntersectionWithArbitraryPath(new Point(10,10), notMoving, new Point(0,0), 2))
	//System.out.println("Moving "+PathPlanning.closestPointOfIntersectionWithArbitraryPath(new Point(10,10), linear, new Point(0,0), 2))
	//System.out.println("Linear "+PathPlanning.closestPointOfIntersectionWithLinearPath(p1, v1, p2, s2))
	System.out.println("Orbitting "+PathPlanning.closestPointOfIntersectionWithArbitraryPath(planet.getPos(), planet.getPositionIn, new Point(0,0), 1)	)
}