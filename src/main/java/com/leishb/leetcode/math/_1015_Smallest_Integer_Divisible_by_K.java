package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/12/26.
 */
public class _1015_Smallest_Integer_Divisible_by_K {


    public int smallestRepunitDivByK(int K) {
        if(K%10 != 1 && K%10!=3 && K%10 != 7 && K%10!=9) return -1;
        Set<Integer> set = new HashSet<>();
        int m = 1;
        for (int len=1;len<=K;len++){
            if (m%K==0) return len;
            if (set.contains(m%K)){
                break;
            }
            set.add(m%K);
            m = m%K * 10 +1;
        }
        return -1;
    }


    @Test
    public void test(){
        Assert.assertEquals(1, smallestRepunitDivByK(1));
    }
}
