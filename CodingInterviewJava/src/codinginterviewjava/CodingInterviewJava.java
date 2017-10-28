/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codinginterviewjava;

/**
 *
 * @author fukuda
 */
public class CodingInterviewJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Chapter1 ch1 = new Chapter1();
        System.out.println(ch1.isUniqueChars("ssss"));
    }

}

class Chapter1 {

    public boolean isUniqueChars(String str) {
        if (str.length() > 256) {
            return false;
        }

        boolean[] char_set = new boolean[256];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public String reverseString(String str) {
        String returnString = "";
        int length = str.length();
        for (int i = 0; i < str.length(); i++) {
            returnString += str.charAt(length - 1 - i);
        }
        return returnString;
    }

    public boolean permutation(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int[] counts_a = charCount(a);
        int[] counts_b = charCount(b);

        for (int i = 0; i < 256; i++) {
            if (counts_a[i] != counts_b[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] charCount(String a) {
        int[] counts = new int[256];
        for (int i = 0; i < a.length(); i++) {
            counts[a.charAt(i)]++;
        }
        return counts;
    }

    public String replaceSpace(String str) {
        String returnString = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                returnString += "%20";
            } else {
                returnString += str.charAt(i);
            }
        }
        return returnString;
    }

    public String compress(String str) {
        char ch = str.charAt(0);
        int num = 1;

        String returnString = "";
        for (int i = 1; i < str.length(); i++) {
            if (ch == str.charAt(i)) {
                num++;
            } else {
                returnString += ch;
                returnString += String.valueOf(num);
                num = 1;
                ch = str.charAt(i);
            }
        }

        returnString += ch;
        returnString += String.valueOf(num);
        
        return str.length() < returnString.length() ? str : returnString;
    }
}
