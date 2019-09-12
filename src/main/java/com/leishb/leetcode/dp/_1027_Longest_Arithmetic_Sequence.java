package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by me on 2019/9/11.
 */
public class _1027_Longest_Arithmetic_Sequence {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int longestArithSeqLength(int[] A) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : A){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (max==min) return A.length;
        int[][] incr = new int[A.length][max-min+1];
        int[][] decr = new int[A.length][max-min+1];
        int ans = 1;
        for (int i=0;i<A.length;i++){
            for (int j=i-1;j>=0;j--){
                if (A[i] >= A[j]){
                    incr[i][A[i]-A[j]] = Math.max(incr[i][A[i]-A[j]], incr[j][A[i]-A[j]] +1);
                    ans = Math.max(ans, incr[i][A[i]-A[j]]+1);
                }else {
                    decr[i][A[j]-A[i]] = Math.max(decr[i][A[j]-A[i]], decr[j][A[j]-A[i]] +1);
                    ans = Math.max(ans, decr[i][A[j]-A[i]]+1);
                }
            }
        }
        return ans;
    }

    public int longestArithSeqLength2(int[] A) {
        HashMap<Integer, Integer>[] maps = new HashMap[A.length];
        int ans = 0;
        for (int i=0;i<A.length;i++){
            maps[i] = new HashMap<>();
            for (int j=0;j<i;j++){
                int dif = A[i]-A[j];
                maps[i].put(dif, maps[j].getOrDefault(dif, 0)+1);
                ans = Math.max(ans, maps[i].get(dif));
            }
        }
        return ans+1;
    }
    @Test
    public void test(){
        Assert.assertTrue(longestArithSeqLength2(new int[]{2,1,2,2,3,2})==4);
    }
}
