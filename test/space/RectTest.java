/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class RectTest {
    
    private final Point2d p0x0 =new Point2d();
    private final Point2d p10x10 = new Point2d(10,10);
    private final Point2d p100x100 = new Point2d(100,100);
    private final Point2d p5x10 = new Point2d(5,10);
    private final Point2d p10x5 = new Point2d(10,5);
    private final Point2d p100x500 = new Point2d(100,500);
    private final Point2d p500x500 = new Point2d(500,500);
    
    public RectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of contains method, of class Rect.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Point2d p = new Point2d(5,5);
        Rect instance = new Rect(new Point2d(), 10, 10);
        boolean expResult = true;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of alternate two point constructor method, of class Rect.
     */
    @Test
    public void twoPointConstructor() {
        System.out.println("twoPointConstructor");
        Rect r = new Rect(new Point2d(), new Point2d(5,10));
        Rect instance = new Rect(new Point2d(5,10), new Point2d());
        boolean expResult = true;
        boolean result = instance.equals(r);
        assertEquals(expResult, result);

    }

    /**
     * Test of matchesAspectRatio method, of class Rect.
     */
    @Test
    public void testMatchesAspectRatio() {
        System.out.println("matchesAspectRatio");
        Rect r = new Rect(new Point2d(), new Point2d(5,10));
        Rect instance = new Rect(new Point2d(), new Point2d(10,20));
        boolean expResult = true;
        boolean result = instance.matchesAspectRatio(r);
        assertEquals(expResult, result);

    }

    /**
     * Test of expandToAspectRatio method, of class Rect.
     */
    @Test
    public void testExpandToAspectRatio() {
        System.out.println("expandToAspectRatio");
        Rect r = new Rect(p0x0, p10x10);
        Rect instance = new Rect(p0x0, p100x500);
        Rect expResult = new Rect(p0x0, p500x500);
        runAspectRatio(instance, r,expResult);
        
        runAspectRatio(new Rect(p0x0, p10x10), new Rect(p0x0,p10x5), new Rect(p0x0,new Point2d(20,10)));
    }
    
    public void runAspectRatio(Rect instance, Rect r, Rect expResult){
        Rect result = instance.expandToAspectRatio(r);
        assertEquals(expResult, result);
    
    }
  
    
}
