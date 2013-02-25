package test.space

import space.Star
import space.Planet
import space.Ship
import vector.Point2d
import space.collision.PointImplicits._
import space.collision.PathPlanning._
import space.collision.Point

import org.junit.Assert._
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.scalatest.junit.JUnitSuite

class PathPlanningTest extends JUnitSuite{
	var star:Star= null
	var planet:Planet = null
	var ship1:Ship = null
	var moon: Planet = null
	
	def notMoving(time:Double) = new Point(10,10)
	def linear(time:Double) = {
	  val point = new Point(10,10);
	  val velocity = new Point(1,-1);
	  point+velocity.scale(time)
	}
	

	
	def assertCloseEnough(p1: Point, p2: Point)= {
	  if(!p1.closeEnough(p2)) {
	    throw new AssertionError(p1 + "not close enough to " + p2)
	  }
	}
	
	@Before def setUp()= {
	  star = new Star(new Point2d(10,10), 10,10);
	  planet = Planet.newSatelliteOf(star, 5, 20, 0)
	  ship1 = new Ship(new Point(10,10), new Point(-2,0))
	  moon = Planet.newSatelliteOf(planet, 2, 5, math.Pi)
	  
	}


	@After def tearDown() ={
		star = null
		planet = null
		ship1 = null
	}

	
	@Test def testIntersectionWithLinear()={
	 	val target = closestPointOfIntersectionWithLinearPath(ship1.getPos(), ship1.getVelocity()
	 	    , new Point(0,0), 2)
	 	val expected = new Point(0,10)
	 	assertCloseEnough(expected, target.get)
	  
	}

	@Test def testIntersectionWithBounded_notMoving()={
	  val target = closestPointOfIntersectionWithBoundedPath(star.getPos(), star.getPositionIn _
	      ,new Point(0,0), 2)
	  val expected = star.getPos();
	  assertCloseEnough(expected, target.get)

	}
	
	@Test def testIntersectionWithBounded_circular()= {
	  val speed = 1
	  val startPos = new Point(0,0)
	  val target = closestPointOfIntersectionWithBoundedPath(planet.getPos(), planet.getPositionIn _
	      ,startPos, speed)
	  println("Target pos = " + target)
	  val timeToTarget = timeTo(speed,startPos, target.get)
	  println("Time to Target" + timeToTarget)
	  val planetAtTarget = planet.getPositionIn(timeToTarget)
	  println("Planet at target time " + planetAtTarget )
	  assertCloseEnough(planetAtTarget, target.get)
	}
	
	



}