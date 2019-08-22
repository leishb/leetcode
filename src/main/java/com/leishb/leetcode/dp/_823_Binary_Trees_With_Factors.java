package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/8/16.
 */
public class _823_Binary_Trees_With_Factors {


    /**
     * 错误反例
     * @param A
     * @return
     */
    public int numFactoredBinaryTrees(int[] A) {
        Arrays.sort(A);
        int M = 1_000_000_007;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : A){
            map.put(num, 1);
        }
        for (int i=0;i<A.length;i++){
            for (int j=0;i+j<A.length;j++){
                int fac = (A[j] * A[i+j]);
                if (map.containsKey(fac)){
                    int count1 = map.get(A[j]);
                    int count2 = map.get(A[i+j]);
                    if (i==0){
                        map.put(fac, map.get(fac)+count1*count2);
                    }else {
                        map.put(fac, (map.get(fac)+count1*count2*2)%M);
                    }
                }
            }
        }
        int ans = 0;
        for (int value : map.values()){
            ans = (ans+value)%M;
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numFactoredBinaryTrees2(int[] A) {
        Arrays.sort(A);
        int M = 1000000007;
        Map<Integer, Long> map = new HashMap<>();
        for (int num : A){
            map.put(num, 1L);
        }
        for (int i=0;i<A.length;i++){
            for (int j=0;j<i;j++){
                if (A[i]%A[j] == 0){//j is left
                    if (map.containsKey(A[i]/A[j])){
                        map.put(A[i], map.get(A[i])+map.get(A[j]) * map.get(A[i]/A[j]));
                    }
                }
            }
        }
        long ans = 0;
        for (long value : map.values()){
            ans = (ans+value)%M;
        }
        return (int) ans;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numFactoredBinaryTrees3(int[] A) {
        Arrays.sort(A);
        int M = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();
        long[] dp = new long[A.length];
        Arrays.fill(dp, 1);
        for (int i=0;i<A.length;i++){
            map.put(A[i], i);
        }
        for (int i=0;i<A.length;i++){
            for (int j=0;j<i;j++){
                if (A[i]%A[j]==0 && map.containsKey(A[i]/A[j])){
                    dp[i] = (dp[i] + dp[j] * dp[map.get(A[i]/A[j])])%M;
                }
            }
        }
        long ans = 0;
        for (long value : dp){
            ans += value;
        }
        return (int) (ans%M);
    }

    /**
     * 使用乘法时，注意要都用long类型。尽量使用除法
     * Accepted
     * @param A
     * @return
     */
    public int numFactoredBinaryTrees4(int[] A) {
        Arrays.sort(A);
        int M = 1_000_000_007;
        Map<Long, Long> map = new HashMap<>();
        for (int num : A){
            map.put((long) num, 1L);
        }
        for (int i=0;i<A.length;i++){
            for (int j=0;j<=i;j++){
                long fac = (long) A[i] * (long) A[j];
                if (map.containsKey(fac)){
                    long tmp = map.get((long)A[i]) * map.get((long)A[j]);
                    if (i==j){
                        map.put(fac, (map.get(fac) + tmp));
                    }else {
                        map.put(fac, (map.get(fac) + tmp*2));
                    }
                }
            }
        }
        long ans = 0;
        for (long value : map.values()){
            ans = (ans+value)%M;
        }
        return (int) ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(numFactoredBinaryTrees4(new int[]{2,4,5,10})==7);
        Assert.assertTrue(numFactoredBinaryTrees4(new int[]{2,4})==3);
        Assert.assertTrue(numFactoredBinaryTrees4(new int[]{2,3,6,18})==12);
        Assert.assertTrue(numFactoredBinaryTrees4(new int[]{45,42,2,18,23,12,41,40,9,47,24,33,28,10,32,29,17,46,11, 37, 6, 26, 21, 49, 31,14,19,8,13,7,27,22,3,36,34,38,39,30,43,15,4,16,35,25,20,44,5,48,759,1170})==777);
    }
}
