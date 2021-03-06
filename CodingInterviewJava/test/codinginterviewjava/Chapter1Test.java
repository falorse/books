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
public class Chapter1Test {

    public Chapter1Test() {
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
     * Test of isUniqueChars method, of class Chapter1.
     */
    @Test
    public void testIsUniqueChars() {
        System.out.println("isUniqueChars");
        Chapter1 instance = new Chapter1();

        String str = "sss";
        assertEquals(false, instance.isUniqueChars(str));

        str = "asdfghjkl;";
        assertEquals(true, instance.isUniqueChars(str));
    }

    @Test
    public void testReverseString() {
        System.out.println("reverseString");

        Chapter1 ch1 = new Chapter1();

        String str = "sss";
        assertEquals("sss", ch1.reverseString(str));

        str = "asdfasdf";
        assertEquals("fdsafdsa", ch1.reverseString(str));
    }

    @Test
    public void testPermutation() {
        System.out.println("permutation");

        Chapter1 ch1 = new Chapter1();

        String a = "sdf;asjflas";
        String b = "fa:0;ssss";
        assertEquals(false, ch1.permutation(a, b));

        a = "asdfghjkl;";
        b = "alsdkfjgh;";
        assertEquals(true, ch1.permutation(a, b));
    }

    @Test
    public void testReplaceSpace() {
        System.out.println("replace space");

        Chapter1 ch1 = new Chapter1();

        String case1 = "this is a pen.";
        assertEquals("this%20is%20a%20pen.", ch1.replaceSpace(case1));

        String case2 = "this   isapen. ";
        assertEquals("this%20%20%20isapen.%20", ch1.replaceSpace(case2));
    }

    @Test
    public void testCompress() {
        System.out.println("compress");

        Chapter1 ch1 = new Chapter1();

        String case1 = "aaaassssjjjjjjjjjjjs";
        assertEquals("a4s4j11s1", ch1.compress(case1));

        String case2 = "aajjsdf";
        assertEquals(case2, ch1.compress(case2));
    }

    @Test
    public void testRotate() {
        System.out.println("rotate");

        Picture pic = new Picture(6);

        pic.showPicture();
        pic.rotate();
        pic.showPicture();
    }

    @Test
    public void testChangeZero() {
        System.out.println("change zero");

        Chapter1 ch1 = new Chapter1();

        int size = 5;
        int[][] mat = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mat[i][j] = 1;
            }
        }

        mat[2][3] = 0;
        mat[4][4] = 0;

        assertEquals(23, sum(mat));
        
        ch1.changeZero(mat);

        assertEquals(9, sum(mat));
    }

    int sum(int[][] mat) {
        int sum = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                sum += mat[i][j];
            }
        }
        return sum;
    }
}
