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
public class StackTest {
    Stack s_;
    
    public StackTest() {
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
        s_ = new Stack();
        
        s_.push(1);
        s_.push(2);
        s_.push(3);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of pop method, of class Stack.
     */
    @Test
    public void testPop() {
        System.out.println("pop");
        
        Object item = s_.pop();
        
        assertEquals(3, (int)item);
        assertEquals("12", s_.showStack(null));
        
        assertEquals(2, s_.pop());
        assertEquals("1", s_.showStack(null));
        
        s_.pop();
        assertEquals(null, s_.pop());
        assertEquals(null, s_.pop());
    }

    /**
     * Test of push method, of class Stack.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        
        Object item = 4;
        
        s_.push(item);
        assertEquals("1234", s_.showStack(null));
        
        s_.push(5);
        s_.push(6);
        assertEquals("123456", s_.showStack(null));
        
        Stack newS = new Stack();
        
        assertEquals("", newS.showStack(null));
        
        newS.push(1);
        
        assertEquals("1", newS.showStack(null));
    }

    /**
     * Test of peek method, of class Stack.
     */
    @Test
    public void testPeek() {
        System.out.println("peek");
        
        assertEquals(3, s_.peek());
        
        Stack newS = new Stack();
        
        assertEquals(null,newS.peek());
        
        newS.push(1);
        
        assertEquals(1, newS.peek());
    }
    
}
