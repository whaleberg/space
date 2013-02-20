package space.collision

object PathPlanningTest extends App {
	val p1 = new Point(10, 10)
	val v1 = new Point(1,-1)
	val p2 = new Point(0, 0)
	val s2 = 2
	
	def notMoving(time:Double) = new Point(10,10)
	def linear(time:Double) = {
	  val point = new Point(10,10);
	  val velocity = new Point(1,-1);
	  point+velocity.scale(time)
	}
	System.out.println(PathPlanning.closestPointOfIntersectionWithArbitraryPath(new Point(10,10), notMoving, new Point(0,0), 2))
	System.out.println(PathPlanning.closestPointOfIntersectionWithArbitraryPath(new Point(10,10), linear, new Point(0,0), 2))
	System.out.println(PathPlanning.closestPointOfIntersectionWithLinearPath(p1, v1, p2, s2))
}