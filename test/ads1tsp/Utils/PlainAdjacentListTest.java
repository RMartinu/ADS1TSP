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
public class PlainAdjacentListTest {
    Node nodeList[];
    
    public PlainAdjacentListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
               nodeList=new Node[5];
        nodeList[0]=new Node(0,0);
        nodeList[1]=new Node(1,1);
        nodeList[2]=new Node(2,2);
        nodeList[3]=new Node (3,3);
        nodeList[4]=new Node (0,3);
        nodeList[2].Name="Ust Nathar";
        
    }
    
    @After
    public void tearDown() {
    }





    /**
     * Test of rebuildAdjacentList method, of class PlainAdjacentList.
     */
    @Test
    public void testRebuildAdjacentList() {
        System.out.println("rebuildAdjacentList");
        PlainAdjacentList instance = new PlainAdjacentList(nodeList);
        //instance.addNode(new Node (5,6));
        instance.rebuildAdjacentList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addNode method, of class PlainAdjacentList.
     */
    @Test
    public void testAddNode() {
        System.out.println("addNode");
        Node A = new Node (10,10);
        PlainAdjacentList instance = new PlainAdjacentList(nodeList);
        instance.addNode(A);
        System.out.println("Added a Node:");
        instance.print();
        assertEquals(instance.length, nodeList.length+1);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDistance method, of class PlainAdjacentList.
     */
    @Test
    public void testGetDistance() {
        System.out.println("getDistance");
        Node A = null;
        Node B = null;
        PlainAdjacentList instance = new PlainAdjacentList();
        double expResult = -1;
        double result = instance.getDistance(A, B);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Notify method, of class PlainAdjacentList.
     */
    @Test
    public void testNotify() {
        System.out.println("Notify");
        PlainAdjacentList instance = new PlainAdjacentList();
        instance.Notify();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLength method, of class PlainAdjacentList.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        PlainAdjacentList instance = new PlainAdjacentList();
        int expResult = 0;
        int result = instance.getLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeList method, of class PlainAdjacentList.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");
        PlainAdjacentList instance = new PlainAdjacentList();
        Node[] expResult = null;
        Node[] result = instance.getNodeList();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of print method, of class PlainAdjacentList.
     */
    @Test
    public void testPrint() {
        System.out.println("print");

        PlainAdjacentList instance = new PlainAdjacentList(nodeList);
        
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
      
    }
    
}
