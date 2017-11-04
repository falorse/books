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
    
    static Chapter3 ch3_;
    
    public Chapter3Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("set up class");
        ch3_ = new Chapter3();
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("tear down class");
    }
    
    @Before
    public void setUp() {
        System.out.println("set up");
    }
    
    @After
    public void tearDown() {
        System.out.println("tear down");
    }

    @Test
    public void testSomeMethod() {
        System.out.println("test");
    }
    
}
