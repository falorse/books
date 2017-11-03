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
public class Chapter2Test {

    public Chapter2Test() {
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

    public Node makeTestList() {
        Node preNode = new Node(0);
        Node head = preNode;
        for (int i = 1; i < 10; i++) {
            Node node = new Node(i);
            preNode = preNode.appendToTail(node);
            if (i == 4 || i == 5) {
                Node node2 = new Node(i);
                preNode = preNode.appendToTail(node2);
            }
        }

        return head;
    }

    @Test
    public void testList() {
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("list");

        Chapter2 ch2 = new Chapter2();

        Node head = makeTestList();
        ch2.showList(head);
    }

    @Test
    public void testDeleteDuplicateNode() {
        System.out.println("delete duplicate");

        Chapter2 ch2 = new Chapter2();

        Node head = makeTestList();
        head.appendToTail(new Node(7));
        head.appendToTail(new Node(6));

        ch2.deleteDuplicateNode(head);

        assertEquals("0 1 2 3 8 9", ch2.list(head));

    }

    @Test
    public void testDeleteDups() {
        System.out.println("delete dups");

        Chapter2 ch2 = new Chapter2();

        Node head = makeTestList();

        head.appendToTail(new Node(7));
        head.appendToTail(new Node(6));

        ch2.deleteDups(head);

        assertEquals("0 1 2 3 4 5 6 7 8 9", ch2.list(head));
    }

    @Test
    public void testLastK() {
        System.out.println("last k");

        Chapter2 ch2 = new Chapter2();
        Node head = makeTestList();

        head.appendToTail(new Node(7));
        head.appendToTail(new Node(6));

        ch2.showList(head);
        assertEquals(7, ch2.lastKAns(head, 5).data_);
        assertEquals(4, ch2.lastKAns(head, 10).data_);
        assertEquals(0, ch2.lastKAns(head, 14).data_);
        assertEquals(0, ch2.lastKAns(head, 15).data_);
    }

    @Test
    public void testDeleteNodeByData() {
        System.out.println("delete node by data");

        Chapter2 ch2 = new Chapter2();

        Node head = makeTestList();

        ch2.deleteNodeByData(head, 3);
        ch2.deleteNodeByData(head, 6);
        ch2.deleteNodeByData(head, 5);
        ch2.deleteNodeByData(head, 2);
        ch2.deleteNodeByData(head, 9);

        assertEquals("0 1 4 4 7 8", ch2.list(head));

        head = ch2.deleteNodeByData(head, 0);

        assertEquals("1 4 4 7 8", ch2.list(head));
    }

    @Test
    public void testSeparateByX() {
        System.out.println("separate by x");

        Chapter2 ch2 = new Chapter2();

        Node head = makeTestList();

        head.appendToTail(new Node(7));
        head.appendToTail(new Node(6));
        head.appendToTail(new Node(4));
        assertEquals("0 1 2 3 4 4 4 5 5 6 7 8 9 7 6", ch2.list(ch2.separateByX(head, 5)));

        head = makeTestList();

        head.appendToTail(new Node(7));
        head.appendToTail(new Node(6));
        head.appendToTail(new Node(4));
        assertEquals("0 1 2 3 4 4 5 5 6 6 4 7 8 9 7", ch2.list(ch2.separateByX(head, 7)));

    }
    
    @Test
    public void testPlusList(){
        System.out.println("plus list");
        
        Chapter2 ch2 = new Chapter2();
        Node n1 = new Node(1);
        n1.appendToTail(new Node(3));
        n1.appendToTail(new Node(4));
        
        Node n2 = new Node(4);
        n2.appendToTail(new Node(3));
        n2.appendToTail(new Node(2));
        
        assertEquals("5 6 6",ch2.list(ch2.plusList(n1,n2)));
        
        n1.appendToTail(new Node(8));
        n2.appendToTail(new Node(7));
        
        assertEquals("5 6 6 5 1",ch2.list(ch2.plusList(n1, n2)));
        
        n1.appendToTail(new Node(1));
        n1.appendToTail(new Node(3));
        
        assertEquals("5 6 6 5 2 3",ch2.list(ch2.plusList(n1, n2)));
    }
}
