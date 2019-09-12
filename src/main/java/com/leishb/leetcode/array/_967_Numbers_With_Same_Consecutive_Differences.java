package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/9/6.
 */
public class _967_Numbers_With_Same_Consecutive_Differences {

    /**
     * Accepted
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<>();
        int start = N==1?0:1;
        for (int i=start;i<=9;i++){
            dfs(list, K, N-1, i, i);
        }
        int[] ans = new int[list.size()];
        for (int i=0;i<list.size();i++){
            ans[i] = list.get(i);
        }
        return ans;
    }


    private void dfs(List<Integer> list, int K, int N, int cur, int num) {
        if (N==0){
            list.add(num);
            return;
        }
        if (cur-K>=0){
            dfs(list, K, N-1, cur-K, num*10+cur-K);
        }
        if (cur+K<=9 && cur-K!=cur+K){
            dfs(list, K, N-1, cur+K, num*10+cur+K);
        }
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(numsSameConsecDiff(3,7), new int[]{181,292,707,818,929});
        Assert.assertArrayEquals(numsSameConsecDiff(1,0), new int[]{0,1,2,3,4,5,6,7,8,9});
        Assert.assertArrayEquals(numsSameConsecDiff(2,1), new int[]{10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98});
    }
}
