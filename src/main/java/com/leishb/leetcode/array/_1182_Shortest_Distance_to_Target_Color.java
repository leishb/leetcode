package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/11/20.
 */
public class _1182_Shortest_Distance_to_Target_Color {


    /**
     * Accepted
     * @param colors
     * @param queries
     * @return
     */
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<Integer> ans = new ArrayList<>();
        int[][] left = new int[3][colors.length];
        int[][] right = new int[3][colors.length];
        for (int i = 0;i<3;i++){
            Arrays.fill(left[i], -1);
            int j = 0;
            while (j<colors.length){
                while (j<colors.length && colors[j]!=i+1){
                    j++;
                }
                int k = j;
                while (k<colors.length && k>=0 && left[i][k]==-1){
                    left[i][k] = j-k;
                    k--;
                }
                j++;
            }
        }
        for (int i=0 ;i<3;i++){
            Arrays.fill(right[i], -1);
            int j = colors.length-1;
            while (j>=0){
                while (j>=0 && colors[j]!=i+1){
                    j--;
                }
                int k = j;
                while (k<colors.length && k>=0 && right[i][k]==-1){
                    right[i][k] = k-j;
                    k++;
                }
                j--;
            }
        }
        for (int[] query : queries){
            int x = left[query[1]-1][query[0]], y =right[query[1]-1][query[0]];
            if (x==-1 || y==-1){
                ans.add(Math.max(x, y));
            }else {
                ans.add(Math.min(x, y));
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param colors
     * @param queries
     * @return
     */
    public List<Integer> shortestDistanceColor2(int[] colors, int[][] queries) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0;i<colors.length;i++){
            int c = colors[i];
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        for (int i = 0;i< queries.length;i++){
            int c = queries[i][1];
            if (!map.containsKey(c)){
                ans.add(-1);
            }else {
                ans.add(binarySearch(map.get(c), queries[i][0]));
            }
        }
        return ans;
    }


    private int binarySearch(List<Integer> list, int k){
        int i = 0, j= list.size()-1, low = -1 , hi = list.size();
        while (i<=j){
            int m = (i+j)/2;
            if (list.get(m)==k) return 0;
            if (list.get(m) > k){
                hi = m;
                j = m -1;
            }else {
                low = m;
                i = m + 1;
            }
        }
        if (low==-1){
            return list.get(hi)-k;
        }else if (hi == list.size()){
            return k-list.get(low);
        }else {
            return Math.min(k-list.get(low), list.get(hi)-k);
        }
    }

    @Test
    public void test(){
        shortestDistanceColor(new int[]{1,1,2,1,3,2,2,3,3}, new int[][]{{6,1}});
    }
}
