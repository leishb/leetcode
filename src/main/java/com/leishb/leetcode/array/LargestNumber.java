package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by me on 2019/5/13.
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        Comparator<String> comparable = (o1, o2) -> (o2+o1).compareTo(o1+o2);
        String[] str  = new String[nums.length];
        for (int i=0;i<nums.length;i++){
            str[i] = nums[i]+"";
        }
        Arrays.sort(str,comparable);
        StringBuffer sb = new StringBuffer("");
        for (int i=0;i<str.length;i++){
            sb.append(str[i]);
        }
        String result = sb.toString();
        while (result.startsWith("0") && !result.equals("0")){
            result = result.substring(1);
        }
        return result;
    }



    @Test
    public void test(){
        Assert.assertTrue(largestNumber(new int[]{0,0,0}).equals("0"));
    }
}
