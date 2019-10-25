package com.leishb.leetcode.dp;

import java.util.*;

/**
 * Created by me on 2019/10/24.
 */
public class _1066_Campus_Bikes_II {

    public int assignBikes(int[][] workers, int[][] bikes) {
        return dfs(workers, bikes, new boolean[bikes.length], 0);
    }


    private int dfs(int[][] workers, int[][] bikes, boolean[] used, int index){
        if (index==workers.length){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i=0;i<bikes.length;i++){
            if (used[i]) continue;
            used[i] = true;
            int dist = Math.abs(workers[index][0]-bikes[i][0]) + Math.abs(workers[index][1]-bikes[i][1]);
            min = Math.min(min, dist + dfs(workers, bikes, used, index+1));
            used[i] = false;
        }
        return min;
    }



    public int assignBikes2(int[][] workers, int[][] bikes) {
        return dfs(workers, bikes, 0, 0, new HashMap<>());
    }



    private int dfs(int[][] workers, int[][] bikes, int mask, int index, Map<String, Integer> memo){
        if (index==workers.length){
            return 0;
        }
        String key = mask + "-"+index;
        if (memo.containsKey(key)) return memo.get(key);
        int min = Integer.MAX_VALUE;
        for (int i=0;i<bikes.length;i++){
            if ((mask & (1<<i))!=0)continue;
            int dist = Math.abs(workers[index][0]-bikes[i][0]) + Math.abs(workers[index][1]-bikes[i][1]);
            min = Math.min(min, dist + dfs(workers, bikes, mask|(1<<i), index+1, memo));
        }
        memo.put(key, min);
        return min;
    }


    public int[] assignBikesI(int[][] workers, int[][] bikes) {
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        int[][] dist = new int[workers.length*bikes.length][3];
        int k = 0;
        for (int i=0;i<workers.length;i++){
            for (int j=0;j<bikes.length;j++){
                int d =  Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
                dist[k++] = new int[]{i, j, d};
            }
        }
        Arrays.sort(dist, (d1, d2)->{
            if (d1[2]!=d2[2]) return d1[2]-d2[2];
            else if (d1[0]!=d2[0])return d1[0]-d2[0];
            else return d1[1]-d2[1];
        });
        boolean[] used = new boolean[bikes.length];
        for (int i=0;i<dist.length;i++){
            if (res[dist[i][0]]!=-1) continue;
            if (!used[dist[i][1]]){
                res[dist[i][0]] = dist[i][1];
                used[dist[i][1]] = true;
            }
        }
        return res;
    }

    public int[] assignBikesI2(int[][] workers, int[][] bikes) {
        int[] res = new int[workers.length];
        Arrays.fill(res, -1);
        List<int[]>[] dist = new List[2001];
        for (int i=0;i<=2000;i++){
            dist[i] = new ArrayList<>();
        }
        for (int i=0;i<workers.length;i++){
            for (int j=0;j<bikes.length;j++){
                int d =  Math.abs(workers[i][0]-bikes[j][0]) + Math.abs(workers[i][1]-bikes[j][1]);
                dist[d].add(new int[]{i, j});
            }
        }
        boolean[] used = new boolean[bikes.length];
        for (int i=0;i<=2000;i++){
            for (int[] arr : dist[i]){
                if (res[arr[0]]==-1 && !used[arr[1]]){
                    used[arr[1]] = true;
                    res[arr[0]] = arr[1];
                }
            }
        }
        return res;
    }
}
