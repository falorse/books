/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codinginterviewjava;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fukuda
 */
public class QueueTest {
    
    Queue q_;
    
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("set up");
        q_ = new Queue();
        q_.enqueue(new Node(1));
        q_.enqueue(new Node(2));
        q_.enqueue(new Node(3));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of enqueue method, of class Queue.
     */
    @Test
    public void testEnqueue() {
        System.out.println("enqueue");
        q_.enqueue(new Node(4));
        
        assertEquals("1234",q_.showQueue());
        
        Queue newQ = new Queue();
        
        assertEquals("", newQ.showQueue());
        
        newQ.enqueue(new Node(10));
        
        assertEquals("10", newQ.showQueue());
    }

    /**
     * Test of dequeue method, of class Queue.
     */
    @Test
    public void testDequeue() {
        System.out.println("dequeue");
        
        Object item = q_.dequeue();
        
        assertEquals(1, (int)item);
        assertEquals("23", q_.showQueue());
    }
    
}
