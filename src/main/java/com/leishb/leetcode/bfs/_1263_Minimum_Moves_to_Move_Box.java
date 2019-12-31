package com.leishb.leetcode.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by me on 2019/12/3.
 */
public class _1263_Minimum_Moves_to_Move_Box {



    int[][] moves = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    /**
     * Accepted
     * @param grid
     * @return
     */
    public int minPushBox(char[][] grid) {
        int[] target = new int[2], box = new int[2], sk = new int[2];
        int m = grid.length, n = grid[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                char c = grid[i][j];
                if (c=='B') box = new int[]{i, j};
                if (c=='S') sk = new int[]{i, j};
                if (c=='T') target = new int[]{i, j};
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> dist = new HashMap<>();
        int start = encode(box[0], box[1], sk[0], sk[1]);
        queue.offer(start);
        dist.put(start, 0);
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int cur = queue.poll();
            int[] arr = decode(cur);
            if (dist.get(cur) >= res)continue;
            if (arr[0]==target[0] && arr[1]==target[1]){
                res = Math.min(res, dist.get(cur));
                continue;
            }
            for (int[] move : moves){
                int snx = arr[2] + move[0];
                int sny = arr[3] + move[1];
                if (snx<0 || snx>=m || sny<0 || sny>=n || grid[snx][sny]=='#') continue;
                if (snx == arr[0] && sny == arr[1]){ //storekeeper meet the box
                    int bnx = arr[0] + move[0];
                    int bny = arr[1] + move[1];
                    if (bnx<0 || bnx>=m || bny<0 || bny>=n || grid[bnx][bny]=='#') continue;
                    int next = encode(bnx, bny, snx,sny);
                    if (dist.getOrDefault(next, Integer.MAX_VALUE) > dist.get(cur)){
                        queue.offer(next);
                        dist.put(next, dist.get(cur)+1);
                    }
                }else{
                    int next = encode(arr[0], arr[1], snx,sny);
                    if (dist.getOrDefault(next, Integer.MAX_VALUE) > dist.get(cur)){
                        queue.offer(next);
                        dist.put(next, dist.get(cur));
                    }
                }
            }
        }
        return res==Integer.MAX_VALUE?-1:res;
    }

    private int encode(int bx, int by, int sx, int sy){
        return (bx<<24)|(by<<16)|(sx<<8)|sy;
    }

    private int[] decode(int num){
        int[] ans = new int[4];
        ans[3] = num & (0xff);
        ans[2] = (num>>8) & (0xff);
        ans[1] = (num>>16) & (0xff);
        ans[0] = (num>>24) & (0xff);
        return ans;
    }
}
