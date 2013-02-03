/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import com.google.common.collect.ImmutableSet;
import javafx.collections.FXCollections;
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
public class StarMapTest {
    
    private final Drawable d1 = new Star(new Point2d(5,5), 10, 10);
    private final Drawable d2 = new Star(new Point2d(10,10), 10, 10);
    private final StarMap map = new StarMap();
   
    
    public StarMapTest() {
         map.add(d1);
         map.add(d2);
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
     * Test of getWithinBounds method, of class StarMap.
     */
    @Test
    public void getWithinBounds() {
        System.out.println("watch");
        Rect r = new Rect(new Point2d(), 20,20);
        StarMap instance = map;
        ImmutableSet<Drawable> expResult = ImmutableSet.of(d1,d2);
        ImmutableSet<Drawable> result = instance.getWithinBounds(r);
        assert(expResult.equals(result));
    }

    /**
     * Test of getBounds method, of class StarMap.
     */
    @Test
    public void testGetBounds() {
        System.out.println("getBounds");
        StarMap instance = new StarMap();
        instance.add(d1);
        instance.add(d2);
        Rect expResult = new Rect(new Point2d(5,5), new Point2d(10,10));
        Rect result = instance.getBounds();
        assertEquals(expResult, result);
   }
}
