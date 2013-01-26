/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javax.vecmath.Point2d;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailfish
 */
public class RectTest {
    
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
        Point2d p = null;
        Rect instance = null;
        boolean expResult = false;
        boolean result = instance.contains(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
