package test.space;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import space.Planet;
import space.Star;
import vector.Point2d;

public class PlanetTest {
	Star star = null;
	Planet planet1 = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	star = new Star(new Point2d(0,0), 1, 1);
	planet1 = Planet.newSatelliteOf(star, 10, 100, 0);

	}

	@After
	public void tearDown() throws Exception {
		star = null;
		planet1 = null;
	}

	private void assertCloseEnough(Point2d p1, Point2d p2){
		if(!p1.closeEnough(p2)){
			throw new AssertionError(p1.toString()+"was not within " + Point2d.EPSILON+
					"of "+p2.toString());
		}
	}
	
	@Test
	public final void testAge() {
		planet1.getPositionIn(10);
		assertCloseEnough(new Point2d(10, 0), planet1.getPos());
		star.age(25);
		assertCloseEnough(new Point2d(0, 10), planet1.getPos());
		star.age(25);
		assertCloseEnough(new Point2d(-10, 0), planet1.getPos());
		star.age(25);
		assertCloseEnough(new Point2d(0, -10), planet1.getPos());
		star.age(25);
		assertCloseEnough(new Point2d(10, 0), planet1.getPos());
	}

	@Test
	public final void testGetPositionIn_0() {
		star.age(25);
		Point2d result = planet1.getPositionIn(0);
		assertCloseEnough(planet1.getPos(),result );
	}

	@Test
	public final void testGetPositionInOneQuarterRevolution(){
	Point2d result = planet1.getPositionIn(25);
	star.age(25);
	Point2d expected = planet1.getPos();
	assertCloseEnough(expected, result);
	}
	
	@Test
	public final void testGetPositionIn_MultipleOrbiters(){
		Planet planet2 = Planet.newSatelliteOf(planet1, 5, 10, 0);
		Planet planet3 = Planet.newSatelliteOf(planet2, 3, 5, 0);
		Point2d result =  planet3.getPositionIn(0);
		Point2d start = planet3.getPos();
		assertCloseEnough(start, result);
		
		result = planet3.getPositionIn(30);
		star.age(30);
		assertCloseEnough(planet3.getPos(), result);

	}
}
