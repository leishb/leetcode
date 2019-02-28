package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by me on 2019/2/28.
 */
public class StringMatch {



    public int indexOf(String a, String b){
        int n = a.length();
        int m = b.length();
        if (n < m){
            return -1;
        }
        int[] hash = new int[n-m+1];
        int[] pow = new int[m];
        pow[0] = 1;
        for (int i=1;i<m;i++){
            pow[i] = pow[i-1]*26;
        }
        for (int i=m-1;i>=0;i--){
            hash[0] += pow[m-1-i] * (a.charAt(i) - 'a');
        }
        for (int i=1;i<hash.length;i++){
            hash[i] = (hash[i-1] - (a.charAt(i-1) - 'a')*pow[m-1]) * 26 + (a.charAt(i+m-1) - 'a');
        }
        int h = 0;
        for (int i=m-1;i>=0;i--){
            h += (b.charAt(i)-'a') * pow[m-1-i];
        }
        for (int i=0;i<hash.length;i++){
            if (hash[i] == h){
                return i;
            }
        }
        return -1;
    }



    private int indexOf2(String a, String b){
        int n = a.length();
        int m = b.length();
        if (n < m){
            return -1;
        }
        int[] hash = new int[n-m+1];
        int h = 0;
        for (int i=0;i<m;i++){
            hash[0] += (a.charAt(i)-'a');
            h += (b.charAt(i) - 'a');
        }
        for (int i=1;i<n-m+1;i++){
            hash[i] = hash[i-1] + a.charAt(i+m-1) - a.charAt(i-1);
        }
        for (int i=0;i<hash.length;i++){
            if (hash[i] == h){
                if (match(a, i, b)){
                    return i;
                }
            }
        }
        return -1;
    }


    private boolean match(String a, int start, String b){
        int k = start;
        while (k-start<b.length()){
            if (a.charAt(k) != b.charAt(k-start)){
                return false;
            }
            k++;
        }
        return true;
    }





    private boolean match(char[][] source , char[][] target){
        int m1 = source.length;
        int m2 = target.length;
        if (m1 == 0 || m2 == 0 || m1 < m2){
            return false;
        }
        int n1 = source[0].length;
        int n2 = target[0].length;
        if(n1 < n2){
            return false;
        }
        int j=0;
        int i=0;
        while (i<m1-m2+1){
            for (int p=i;p<=m1;p++){
                if (p-i==m2){
                    return true;
                }
                if (p < m1 && !match(source[p], j, target[p-i])){
                    if (++j==(n1-n2+1)){
                        i++;
                        j=0;
                    }
                    break;
                }
            }
        }
        return false;
    }

    private boolean match(char[] source, int start, char[] target){
        int k = start;
        while (k-start<target.length){
            if (source[k] != target[k-start]){
                return false;
            }
            k++;
        }
        return true;
    }



    @Test
    public void testIndexOf(){
        int len1 = 30;
        StringBuffer a = new StringBuffer();
        for (int i=0;i<len1;i++){
            a.append(new Character((char) (new Random().nextInt(26) + 'a')));
        }


        for (int i=0;i<100;i++){
            int start = new Random().nextInt(24);
            String b = a.toString().substring(start, start+5);
            Assert.assertTrue(indexOf(a.toString(), b) == a.indexOf(b));
            Assert.assertTrue(indexOf2(a.toString(), b) == a.indexOf(b));
        }


        char[][] source = new char[][]{{'a', 'b','c', 'd'},{'e', 'f','g', 'h'},{'i', 'j','k', 'l'}};
        char[][] target = new char[][]{{'b','c', 'd'},{'f','g', 'h'}};
        char[][] target2 = new char[][]{{'b','c'},{'f','g'}};
        char[][] target3 = new char[][]{{'b','d'},{'f','g'}};
        char[][] target4 = new char[][]{{'a', 'b'},{'e', 'f'},{'i', 'j'}};
        char[][] target5 = new char[][]{{'e', 'f'},{'i', 'j'}};

        Assert.assertTrue(match(source, target));
        Assert.assertTrue(match(source, target2));
        Assert.assertFalse(match(source, target3));
        Assert.assertTrue(match(source, target4));
        Assert.assertTrue(match(source, target5));
    }

}
