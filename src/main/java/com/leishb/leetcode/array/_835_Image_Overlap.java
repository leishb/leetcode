package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/12/26.
 */
public class _835_Image_Overlap {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int ans = 0;
        for (int i=0;i<B.length;i++){
            for (int j=0;j<B[0].length;j++){
                ans = Math.max(ans, overlaps(A, B, i, j));
                ans = Math.max(ans, overlaps(B, A, i, j));
            }
        }
        return ans;
    }


    private int overlaps(int[][] A, int[][] B, int r, int c){
        int ans = 0;
        for (int i=r;i<B.length;i++){
            for (int j=c;j<B[0].length;j++){
                if (B[i][j]==0) continue;
                ans += A[i-r][j-c];
            }
        }
        return ans;
    }




    public int largestOverlap2(int[][] A, int[][] B) {
        List<int[]> la = new ArrayList<>();
        List<int[]> lb = new ArrayList<>();
        for (int i=0;i<A.length;i++){
            for (int j=0;j<A.length;j++){
                if (A[i][j]==1) la.add(new int[]{i, j});
                if (B[i][j]==1) lb.add(new int[]{i, j});
            }
        }
        Map<String, Integer> map = new HashMap<>();
        for (int[] a : la){
            for (int[] b : lb){
                String key = (b[0]-a[0]) + "," + (b[1]-a[1]);
                map.put(key, map.getOrDefault(key, 0)+1);
            }
        }
        int count = 0;
        for (int v : map.values()){
            count = Math.max(count, v);
        }
        return count;
    }
}
