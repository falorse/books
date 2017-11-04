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
    public void testResize(){
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
    public void testPush() throws Exception{
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
        
        assertEquals(11, ts_.nodes_[ts_.topNums_[1] - 1].data_ );
        assertEquals(6, ts_.nodes_[ts_.topNums_[2] - 1].data_);
        assertEquals(6, ts_.nodes_[16].data_);
        assertEquals(7, ts_.nodes_[ts_.topNums_[0] - 1].data_);
    }
}
