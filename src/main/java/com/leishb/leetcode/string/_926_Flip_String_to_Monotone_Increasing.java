package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/3.
 */
public class _926_Flip_String_to_Monotone_Increasing {


    /**
     * Accepted
     * @param S
     * @return
     */
    public int minFlipsMonoIncr(String S) {
        List<Integer> zeroList = new ArrayList<>();
        List<Integer> oneList = new ArrayList<>();
        for (int i=0;i<S.length();i++){
            if(S.charAt(i)=='0'){
                zeroList.add(i);
            }else {
                oneList.add(i);
            }
        }
        int min = S.length();
        for (int i=0;i<=S.length();i++){
            int zeros = i;
            int ones = S.length()-i;
            int changeOnes = lowerSize(oneList, zeros);//1-> 0
            int changeZeros = ones-(oneList.size()-changeOnes);
            min = Math.min(changeOnes + changeZeros, min);
        }
        return min;
    }


    private int lowerSize(List<Integer> list, int k){
        int i = 0;
        int j = list.size()-1;
        while (i<=j){
            int mid = (i+j)/2;
            if (list.get(mid)==k){
                return mid;
            }
            if (list.get(mid) > k){
                j = mid-1;
            }else {
                i = mid+1;
            }
        }
        return i;
    }


    public int minFlipsMonoIncr2(String S) {
        int[] sum = new int[S.length()+1];
        for (int i=1;i<=S.length();i++){
            if (S.charAt(i-1)=='1'){
                sum[i] = sum[i-1] +1;
            }else {
                sum[i] = sum[i-1] ;
            }
        }
        int min = S.length();
        for (int i=0;i<=S.length();i++){
            int zeros = i;
            int ones = S.length()-i;
            int changeOnes =  sum[zeros];//1->0
            int changeZeros = ones-(sum[S.length()]-changeOnes);
            min = Math.min(changeOnes+changeZeros, min);
        }
        return min;
    }


    public int minFlipsMonoIncr3(String S) {
        int[] f0 = new int[S.length()+1];
        int[] f1 = new int[S.length()+1];
        for (int i=1;i<=S.length();i++){
            f0[i] = f0[i-1] + (S.charAt(i-1)=='1' ? 1: 0);
        }
        for (int i=S.length()-1;i>=0;i--){
            f1[i] = f1[i+1] + (S.charAt(i)=='0'?1:0);
        }
        int min = S.length();
        for (int i=0;i<f0.length;i++){
            min = Math.min(min, f0[i]+f1[i]);
        }
        return min;
    }


    @Test
    public void test(){
        Assert.assertTrue(minFlipsMonoIncr3("00011000")==2);
        Assert.assertTrue(minFlipsMonoIncr3("00110")==1);
        Assert.assertTrue(minFlipsMonoIncr3("010110")==2);
    }
}
