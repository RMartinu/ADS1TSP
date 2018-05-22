/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

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
public class NodeTest {
    
    public NodeTest() {
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
     * Test of setCoordinates method, of class Node.
     */
    @Test
    public void testSetCoordinates() {
        System.out.println("setCoordinates");
        double x = 0.0;
        double y = 0.0;
        Node instance = new Node(x,y);
        System.out.println(instance.toString());
        instance.setCoordinates(7, 13);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(instance.toString());
    }

    /**
     * Test of setIndex method, of class Node.
     */
    @Test
    public void testSetIndex() {
        System.out.println("setIndex");
        int i = 0;
        Node instance = new Node (12,14);
        instance.setIndex(i);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(instance.toString());
    }

    /**
     * Test of calculateDistance method, of class Node.
     */
    @Test
    public void testCalculateDistance_Node() {
        System.out.println("calculateDistance");
        Node other = new Node (25,25);
        Node instance = new Node (0,0);
        double expResult = (2*(25*25));
        double result = instance.calculateDistance(other);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of calculateDistance method, of class Node.
     */
    @Test
    public void testCalculateDistance_Node_Node() {
        System.out.println("calculateDistance");
        Node A = new Node (0,0);
        Node B = new Node (1,1);
        double expResult = 2.0;
        double result = Node.calculateDistance(A, B);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
