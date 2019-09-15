package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/12.
 */
    public class _1039_Minimum_Score_Triangulation_of_Polygon {

    public int minScoreTriangulation(int[] A) {
        long mask = 0;
        return dfs(A, new boolean[A.length], 0, mask,  new HashMap<>());
    }


    private int dfs(int[] A, boolean[] visited, int count, long mask,  Map<Long, Integer> map){
        if (A.length-count==3) {
            int ans = 1;
            for (int i = 0; i < A.length; i++) {
                if (!visited[i]) {
                    ans *= A[i];
                }
            }
            return ans;
        }
        if (map.containsKey(mask)){
            return map.get(mask);
        }
        int min = Integer.MAX_VALUE;
        for (int i=0;i<A.length;i++){
            if (!visited[i]){
                visited[i] = true;
                int next = dfs(A, visited, count+1, mask | (1<<i), map);
                min = Math.min(next+findExtraProduct(A, visited, i), min);
                visited[i] = false;
            }
        }
        map.put(mask, min);
        return min;
    }


    private int findExtraProduct(int[] A, boolean[] visited, int cur){
        int i = (cur+1)%A.length;
        while (true){
            if (!visited[i]){
                break;
            }
            i = (i+1)%A.length;
        }
        int j = cur==0?A.length-1:cur-1;
        while (true){
            if (!visited[j]){
                break;
            }
            j = j==0?A.length-1:j-1;
        }
        return A[cur] * A[i] * A[j];
    }


    @Test
    public void test(){
        Assert.assertTrue(minScoreTriangulation(new int[]{1,3,1,4,1,5})==13);
        Assert.assertTrue(minScoreTriangulation(new int[]{3,7,4,5})==144);
        Assert.assertTrue(minScoreTriangulation(new int[]{35,73,90,27,71,80,21,33,33,13,48,12,68,70,80,36,66,3,70,58})==140295);
    }
}
