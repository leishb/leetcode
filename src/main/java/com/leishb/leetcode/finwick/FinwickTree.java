package com.leishb.leetcode.finwick;

public class FinwickTree {
    int[] sum;

    public FinwickTree(int n){
        sum = new int[n + 1];
    }

    public void update(int i, int delta){
        while (i < sum.length){
            sum[i] += delta;
            i += lowbit(i);
        }
    }

    public int prefixSum(int i){
        int ans = 0;
        while (i > 0){
            ans += sum[i];
            i -= lowbit(i);
        }
        return ans;
    }


    private int lowbit(int x){
        return x & (-x);
    }
}
