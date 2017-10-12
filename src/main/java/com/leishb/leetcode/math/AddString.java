
package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leishb on 9/18/17.
 */
public class AddString {



    @Test
    public void test(){
        Assert.assertTrue(addStrings("123","999").equals("1122"));
        Assert.assertTrue(addStrings("1","999").equals("1000"));
        Assert.assertTrue(addStrings("123","456").equals("579"));
        Assert.assertTrue(addStrings("0","0").equals("0"));
    }

    /**
     * Accepted
     * @param s1
     * @param s2
     * @return
     */
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


    /**
     * Accepted
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int[] nums = new int[Math.max(num1.length(),num2.length())+1];
        int carry = 0;
        int i = num1.length()-1;
        int j = num2.length()-1;
        int k= nums.length-1;
        while (i >=0 && j>=0){
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(j) - '0';
            int sum = digit1 + digit2 + carry;
            nums[k] = sum%10;
            carry = sum/10;
            i--;
            j--;
            k--;
        }
        while (i >= 0){
            nums[k] = num1.charAt(i)-'0'+carry;
            carry =nums[k]/10;
            nums[k]%=10;
            i--;
            k--;
        }
        while (j >= 0){
            nums[k] = num2.charAt(j)-'0'+carry;
            carry=nums[k]/10;
            nums[k]%=10;
            j--;
            k--;
        }
        while (k >=0){
            nums[k] = carry;
            k--;
            carry =0;
        }
        StringBuffer sb = new StringBuffer();
        for (int l=0;l<nums.length;l++){
            sb.append(nums[l]);
        }
        return nums[0]!=0||(nums[0]==0&&nums.length==1) ? sb.toString() : sb.substring(1);
    }

}
