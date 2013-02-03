/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import javafx.collections.ObservableSet;
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
public class ViewTest {
    
    Rect r10x10 = new Rect( new Point2d(), 10, 10);
    Rect r10x10Shift = new Rect( new Point2d(-10, -10), 10, 10);
    Rect r100x100 = new Rect(new Point2d(),100,100);
    
    public ViewTest() {
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
     * Test of view method, of class View.
     */
    @Test
    public void testView_double() {
        System.out.println("view");
        double length = 100;
        View instance = new View(r100x100,r10x10);
        double expResult = 10;
        double result = instance.view(length);
        assertEquals(expResult, result, 0.0);
    
    }

    /**
     * Test of project method, of class View.
     */
    @Test
    public void testProject_double() {
        System.out.println("project");
        double length = 0.0;
        View instance = null;
        double expResult = 0.0;
        double result = instance.project(length);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateOutputRect method, of class View.
     */
    @Test
    public void testUpdateOutputRect() {
        System.out.println("updateOutputRect");
        Rect r = null;
        View instance = null;
        instance.updateOutputRect(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of view method, of class View.
     */
    @Test
    public void testView_Point2d() {
        System.out.println("view");
        Point2d p = new Point2d(5,5);
        View instance = new View(r10x10, r10x10);
        Point2d expResult = new Point2d(5,5);
        Point2d result = instance.view(p);
        //System.out.println(result);
        assertEquals(expResult, result);
        
        p = new Point2d(1,3);
        expResult = new Point2d(1,3);
        result = instance.view(p);
         assertEquals(expResult, result);
       
     
         
         instance = new View(r100x100, r10x10);
         p = new Point2d(50, 50);
         expResult = new Point2d(5,5);
         result = instance.view(p);
         assertEquals(expResult,result);
         
        instance = new View(r10x10Shift, r10x10);
         p = new Point2d(-5, -5);
         expResult = new Point2d(5,5);
         result = instance.view(p);
         assertEquals(expResult,result);
    }

    /**
     * Test of project method, of class View.
     */
    @Test
    public void testProject_Point2d() {
        System.out.println("project");
        Point2d p = null;
        View instance = null;
        Point2d expResult = null;
        Point2d result = instance.project(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    /**
     * Test of toString method, of class View.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        View instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
