package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/8/29.
 */
public class _898_Bitwise_ORs_of_Subarrays {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int subarrayBitwiseORs(int[] A) {
        if (A.length==0) return 0;
        Set<Integer>[] sets = new Set[A.length];
        for (int i=0;i<A.length;i++){
            sets[i] = new HashSet<>();
        }
        sets[0].add(A[0]);
        Set<Integer> set = new HashSet<>();
        set.addAll(sets[0]);
        for (int i=1;i<A.length;i++){
            sets[i].add(A[i]);
            for (int k : sets[i-1]){
                sets[i].add(A[i]|k);
            }
            set.addAll(sets[i]);
        }
        return set.size();
    }

    public int subarrayBitwiseORs2(int[] A) {
        if (A.length==0) return 0;
        Set<Integer> set = new HashSet<>();
        Set<Integer> prev = new HashSet<>();
        prev.add(A[0]);
        set.addAll(prev);
        for (int i=1;i<A.length;i++){
            Set<Integer> cur = new HashSet<>();
            cur.add(A[i]);
            for (int k : prev){
                cur.add(A[i]|k);
            }
            set.addAll(cur);
            prev = cur;
        }
        return set.size();
    }

    @Test
    public void test(){
        Assert.assertTrue(subarrayBitwiseORs2(new int[]{1,1,2})==3);
        Assert.assertTrue(subarrayBitwiseORs2(new int[]{1,2,4})==6);
    }
}
