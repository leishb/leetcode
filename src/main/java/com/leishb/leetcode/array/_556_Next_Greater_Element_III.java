package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2019/7/16.
 */
public class _556_Next_Greater_Element_III {

    public int nextGreaterElement(int n) {
        List<Integer> digits = new ArrayList<>();
        int k = n;
        int prev = -1;
        while (k!=0){
            int x = k%10;
            digits.add(k%10);
            k = k/10;
            if (x<prev){
                prev = x;
                break;
            }
            prev = x;
        }
        Collections.sort(digits);
        int index = -1;
        for (int i=0;i<digits.size();i++){
            if (digits.get(i)>prev){
                index = i;
                break;
            }
        }
        if (index==-1){
            return -1;
        }
        long ans = k * 10 + digits.get(index);
        for (int i=0;i<digits.size();i++){
            if (i!=index){
                ans = ans * 10 + digits.get(i);
            }
        }
        return ans>Integer.MAX_VALUE?-1: (int) ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(nextGreaterElement(12)==21);
        Assert.assertTrue(nextGreaterElement(21)==-1);
        Assert.assertTrue(nextGreaterElement(18523)==18532);
        Assert.assertTrue(nextGreaterElement(1253)==1325);
        Assert.assertTrue(nextGreaterElement(65544321)==-1);
        Assert.assertTrue(nextGreaterElement(12253)==12325);
        Assert.assertTrue(nextGreaterElement(230241)==230412);
        Assert.assertTrue(nextGreaterElement(1999999999)==-1);

    }
}
