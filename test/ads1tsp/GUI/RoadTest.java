/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Utils.Node;
import javafx.beans.property.DoubleProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Martinu
 */
public class RoadTest {
    
    public RoadTest() {
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
     * Test of startXProperty method, of class Road.
     */
    @Test
    public void testStartXProperty() {
        System.out.println("startXProperty");
        Road instance = null;
        DoubleProperty expResult = null;
        DoubleProperty result = instance.startXProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startYProperty method, of class Road.
     */
    @Test
    public void testStartYProperty() {
        System.out.println("startYProperty");
        Road instance = null;
        DoubleProperty expResult = null;
        DoubleProperty result = instance.startYProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endXProperty method, of class Road.
     */
    @Test
    public void testEndXProperty() {
        System.out.println("endXProperty");
        Road instance = null;
        DoubleProperty expResult = null;
        DoubleProperty result = instance.endXProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endYProperty method, of class Road.
     */
    @Test
    public void testEndYProperty() {
        System.out.println("endYProperty");
        Road instance = null;
        DoubleProperty expResult = null;
        DoubleProperty result = instance.endYProperty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Road.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Town.setScaleX(1);
        Town.setScaleY(1);
        Road instance = new Road(new Town(new Node (0,0,0)), new Town(new Node (1,2,3)));
        String expResult = "";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
