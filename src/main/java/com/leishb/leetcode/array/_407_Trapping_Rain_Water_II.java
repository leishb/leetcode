package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/12/25.
 */
public class _407_Trapping_Rain_Water_II {


    /**
     * Accepted
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length==0) return 0;
        int m = heightMap.length, n = heightMap[0].length;
        int ans = 0;
        Queue<int[]> pq = new PriorityQueue<>((a, b)->a[2]-b[2]);
        boolean[][] visited = new boolean[m][n];
        for (int i=0;i<m;i++){
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n-1, heightMap[i][n-1]});
            visited[i][0] = true;
            visited[i][n-1] = true;
        }
        for (int j=0;j<n;j++){
            pq.offer(new int[]{0, j, heightMap[0][j]});
            pq.offer(new int[]{m-1, j, heightMap[m-1][j]});
            visited[0][j] = true;
            visited[m-1][j] = true;
        }
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            for (int i=0;i<4;i++){
                int nx = cur[0] + dirs[i];
                int ny = cur[1] + dirs[i+1];
                if (nx>=0 && nx<m && ny>=0 && ny<n && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    ans += Math.max(0, cur[2]-heightMap[nx][ny]);
                    pq.offer(new int[]{nx, ny, Math.max(cur[2], heightMap[nx][ny])});
                }
            }
        }
        return ans;
    }






    @Test
    public void test(){
        Assert.assertEquals(3,trapRainWater(new int[][]{{5,5,5,1},{5,1,1,5},{5,1,5,5},{5,2,5,8}}));


        Assert.assertEquals(44, trapRainWater(new int[][]{{78,16,94,36}
                ,{87,93,50,22}
                ,{63,28,91,60}
                ,{64,27,41,27}
                ,{73,37,12,69}
                ,{68,30,83,31}
                ,{63,24,68,36}}));
    }
}
