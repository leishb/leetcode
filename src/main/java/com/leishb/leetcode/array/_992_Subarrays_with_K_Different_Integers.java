package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/31.
 */
public class _992_Subarrays_with_K_Different_Integers {


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K)-atMostK(A, K-1);
    }

    private int atMostK(int[] A, int K){
        Map<Integer, Integer> freq = new HashMap<>();
        int j = 0;
        int res = 0;
        for (int i=0;i<A.length;i++) {
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
            while (freq.size() >K){
                freq.put(A[j], freq.get(A[j])-1);
                if (freq.get(A[j])==0) freq.remove(A[j]);
                j++;
            }
            if (freq.size() <= K){
                res += i-j+1;
            }
        }
        return res;
    }


    @Test
    public void test(){
        Assert.assertTrue(subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2)==7);
        Assert.assertTrue(subarraysWithKDistinct(new int[]{2,2,1,2,2,2,1,1}, 2) ==23);
    }
}
