/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp;

import ads1tsp.Utils.Node;
import ads1tsp.Utils.SimpleNodeList;
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
public class SimpleNodeListTest {
    
    public SimpleNodeListTest() {
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
     * Test of addNode method, of class SimpleNodeList.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Node A = new Node (0,1,2);
        SimpleNodeList instance = new SimpleNodeList();
        instance.addNode(A);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findClosestNeighbor method, of class SimpleNodeList.
     */
    @Test
    public void testFindClosestNeighbor() {
        System.out.println("findClosestNeighbor");
        Node A = new Node (1, 25,25);
        Node B= new Node (0,-25,25);
        Node C= new Node (2, 100,100);
        SimpleNodeList instance = new SimpleNodeList();
        instance.addNode(A);
        instance.addNode(B);
        instance.addNode(C);
        Node expResult = B;
        Node result = instance.extractClosestNeighbor(A);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
