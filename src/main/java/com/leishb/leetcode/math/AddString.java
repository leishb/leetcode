
package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leishb on 9/18/17.
 */
public class AddString {



    @Test
    public void test(){
        Assert.assertTrue(addString("123","999").equals("1122"));
        Assert.assertTrue(addString("0","0").equals("0"));
    }

    public String addString(String s1, String s2){
        int[] pos = new int[Math.max(s1.length(),s2.length())+1];
        int i = s1.length()-1;
        int j = s2.length()-1;
        int carry = 0;
        for (int k = pos.length-1;k>=0;k--){
            int n1 = 0;
            int n2 = 0;
            if (i>=0){
                n1=s1.charAt(i)-'0';
            }
            if (j>=0){
                n2=s2.charAt(j)-'0';
            }
            int sum = n1+n2+carry;
            pos[k]=sum%10;
            carry = sum/10;
            i--;
            j--;
        }
        StringBuffer sb = new StringBuffer();
        boolean zeroStart = true;
        for (int n : pos){
            if (n!=0){
                zeroStart = false;
            }
            if (!zeroStart){
                sb.append(n);
            }
        }
        return zeroStart ? "0" : sb.toString();
    }

}
