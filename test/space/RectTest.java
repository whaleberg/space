/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.space;

import com.google.common.collect.Lists;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import space.geometry.Rect;
import vector.Point2d;

/**
 *
 * @author sailfish
 */
public class RectTest {
    
    private final Point2d p0x0 =new Point2d();
    private final Point2d p10x10 = new Point2d(10,10);
    private final Point2d p100x100 = new Point2d(100,100);
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



    /**
     * Test of aspectRatio method, of class Rect.
     */
    @Test
    public void testAspectRatio() {
        System.out.println("aspectRatio");
        Rect instance = new Rect(p10x10,10,20);
        double expResult = 2.0;
        double result = instance.aspectRatio();
        assertEquals(expResult, result, 0.0);
     
    }

    /**
     * Test of hasZeroArea method, of class Rect.
     */
    @Test
    public void testHasZeroArea() {
        System.out.println("hasZeroArea");
        Rect instance = new Rect(p0x0, p0x0);
        boolean expResult = true;
        boolean result = instance.hasZeroArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of boundingRect method, of class Rect.
     */
    @Test
    public void testBoundingRect() {
        System.out.println("boundingRect");
        Collection<Point2d> items = Lists.newArrayList(p0x0,p100x100,p100x500, new Point2d(-10,20));
        Rect expResult = new Rect(new Point2d(-10,0), new Point2d(100, 500));
        Rect result = Rect.boundingRect(items);
        assertEquals(expResult, result);
    }
  
    
}
