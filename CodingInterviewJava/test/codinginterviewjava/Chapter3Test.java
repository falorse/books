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
public class Chapter3Test {

    Chapter3 ch3_;
    ThreeStacks ts_;

    public Chapter3Test() {
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
        ch3_ = new Chapter3();
        ts_ = new ThreeStacks(96);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        System.out.println("test");
    }

    @Test
    public void testResize() {
        System.out.println("resize");

        assertEquals(true, ts_.resize(0));
        assertEquals(8, ts_.stackSizes_[0]);

        ts_.resize(2);
        assertEquals(8, ts_.stackSizes_[2]);
        assertEquals(4, ts_.stackSizes_[1]);

        ts_.resize(0);
        ts_.resize(0);

        assertEquals(32, ts_.stackSizes_[0]);

        ts_.resize(0);
        assertEquals(false, ts_.resize(0));
    }

    @Test
    public void testPush() throws Exception {
        System.out.println("push");

        ts_.push(1, 0);
        ts_.push(2, 0);

        assertEquals(2, ts_.nodes_[ts_.topNums_[0] - 1].data_);

        ts_.push(3, 1);

        assertEquals(3, ts_.nodes_[ts_.topNums_[1] - 1].data_);
        assertEquals(3, ts_.nodes_[4].data_);

        ts_.push(4, 0);
        ts_.push(5, 0);
        ts_.push(6, 2);
        ts_.push(7, 0);

        assertEquals(7, ts_.nodes_[ts_.topNums_[0] - 1].data_);
        assertEquals(3, ts_.nodes_[ts_.topNums_[1] - 1].data_);
        assertEquals(6, ts_.nodes_[ts_.topNums_[2] - 1].data_);

        assertEquals(3, ts_.nodes_[8].data_);
        assertEquals(6, ts_.nodes_[12].data_);

        ts_.push(8, 1);
        ts_.push(9, 1);
        ts_.push(10, 1);
        ts_.push(11, 1);

        assertEquals(11, ts_.nodes_[ts_.topNums_[1] - 1].data_);
        assertEquals(6, ts_.nodes_[ts_.topNums_[2] - 1].data_);
        assertEquals(6, ts_.nodes_[16].data_);
        assertEquals(7, ts_.nodes_[ts_.topNums_[0] - 1].data_);

        ts_.pop(1);
        assertEquals(10, ts_.pop(1));
        assertEquals(9, ts_.nodes_[ts_.topNums_[1] - 1].data_);

        assertEquals(7, ts_.pop(0));
        assertEquals(5, ts_.pop(0));
    }

    @Test
    public void testPop() throws Exception {
        System.out.println("pop");

        ts_.push(1, 0);
        ts_.push(2, 0);
        ts_.push(3, 1);
        ts_.push(4, 0);
        ts_.push(5, 0);
        ts_.push(6, 2);
        ts_.push(7, 0);
        ts_.push(8, 1);
        ts_.push(9, 1);
        ts_.push(10, 1);
        ts_.push(11, 1);

        ts_.pop(1);
        assertEquals(10, ts_.pop(1));
        assertEquals(9, ts_.nodes_[ts_.topNums_[1] - 1].data_);
        assertEquals(3, ts_.stackPiles_[1]);
        assertEquals(11, ts_.topNums_[1]);

        assertEquals(7, ts_.pop(0));
        assertEquals(5, ts_.pop(0));
        assertEquals(3, ts_.stackPiles_[0]);
        assertEquals(3, ts_.topNums_[0]);

    }

    @Test
    public void testPeek() throws Exception {
        System.out.println("peek");

        ts_.push(1, 0);
        ts_.push(2, 0);
        ts_.push(3, 1);
        ts_.push(4, 0);
        ts_.push(5, 0);
        ts_.push(6, 2);
        ts_.push(7, 0);
        ts_.push(8, 1);
        ts_.push(9, 1);
        ts_.push(10, 1);
        ts_.push(11, 1);

        assertEquals(11, ts_.peek(1));
        assertEquals(7, ts_.peek(0));
        assertEquals(6, ts_.peek(2));
    }

    @Test
    public void integrateTest() throws Exception {
        System.out.println("integrate");

        ts_.push(1, 0);
        ts_.push(2, 0);
        ts_.push(3, 0);
        ts_.push(4, 0);
        ts_.push(5, 0);
        ts_.push(1, 1);
        ts_.push(2, 1);
        ts_.push(3, 1);
        ts_.push(4, 1);
        ts_.push(5, 1);
        ts_.push(6, 1);
        ts_.push(7, 1);
        ts_.push(8, 1);
        ts_.push(9, 1);
        ts_.push(10, 1);
        ts_.push(1, 2);

        assertEquals(10, ts_.pop(1));
        assertEquals(25, ts_.topNums_[2]);
        assertEquals(17, ts_.topNums_[1]);
        assertEquals(5, ts_.topNums_[0]);
        assertEquals(16, ts_.stackSizes_[1]);
        assertEquals(8, ts_.stackSizes_[0]);
    }

    @Test
    public void testStackHasMin() {
        System.out.println("has min stack");

        StackHasMin shm = new StackHasMin();

        shm.push(1);
        shm.push(2);
        shm.push(3);
        shm.push(4);

        assertEquals(1, shm.min());

        shm.push(0);
        assertEquals(0, shm.min());

        shm.pop();
        assertEquals(1, shm.min());

        shm.pop();
        assertEquals(1, shm.min());

        shm.push(0);
        shm.push(0);

        assertEquals(0, shm.min());
        shm.pop();

        assertEquals(0, shm.min());

        shm.pop();
        assertEquals(1, shm.min());
    }

    @Test
    public void testPeekAt() {
        System.out.println("peek at");

        Stack s = new Stack();
        s.push(1);
        s.pop();

        assertEquals(true, s.isEmpty());
        s.pop();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        assertEquals(3, s.peekAt(1));
        assertEquals(4, s.peekAt(0));
        assertEquals(2, s.peekAt(2));
        assertEquals(1, s.peekAt(3));
        assertEquals(1, s.peekAt(4));
        assertEquals(1, s.peekAt(5));
    }
    
    @Test
    public void testSetOfStacks() throws Exception{
        System.out.println("set of stacks");
        SetOfStacks sos = new SetOfStacks(3);
        assertEquals(true, sos.isEmpty());
        sos.push(1);
        assertEquals(false, sos.isEmpty());
        assertEquals(1,sos.pop());
        sos.push(1);
        sos.push(2);
        sos.push(3);
        sos.push(4);
        assertEquals(4, sos.peek());
        assertEquals(4, sos.pop());
        assertEquals(3, sos.peek());
        assertEquals(3, sos.pop());
        sos.pop();
        sos.pop();
        assertEquals(null, sos.pop());
        assertEquals(true, sos.isEmpty());
        
        
        sos.push(1);
        sos.push(2);
        sos.push(3);
        sos.push(4);
        sos.push(5);
        sos.push(6);
        sos.push(7);
        
        assertEquals(6, sos.popAt(1));
        assertEquals(5, sos.popAt(1));
        assertEquals(3, sos.popAt(2));
        assertEquals(7, sos.popAt(0));
        assertEquals(null, sos.popAt(0));
        sos.push(7);
        assertEquals(7, sos.popAt(0));
        assertEquals(null, sos.popAt(0));
        sos.push(7);
        assertEquals(7,sos.peek());
        sos.push(8);
        assertEquals(8,sos.peek());
        sos.push(9);
        assertEquals(9,sos.peek());
        sos.push(10);
        assertEquals(9,sos.popAt(1));
    }
}
