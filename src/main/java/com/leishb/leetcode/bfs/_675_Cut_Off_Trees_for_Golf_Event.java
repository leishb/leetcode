package com.leishb.leetcode.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/10/18.
 */
public class _675_Cut_Off_Trees_for_Golf_Event {

    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};


    public int cutOffTree(List<List<Integer>> forest) {
        Queue<int[]> queue = new PriorityQueue<>((t1, t2)->t1[2]-t2[2]);
        for (int i = 0;i< forest.size();i++){
            for(int j = 0; j< forest.get(i).size(); j++){
                if (forest.get(i).get(j) > 1){
                    queue.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[2];
        int dist = 0;
        while (!queue.isEmpty()){
            int[] tree = queue.poll();
            int d = bfs(forest, start, tree);
            if (d<0) return -1;
            dist += d;
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return dist;
    }


    private int bfs(List<List<Integer>> forest, int[] start, int[] target){
        int m = forest.size();
        int n = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                if (cur[0]==target[0] && cur[1] == target[1]){
                    return step;
                }
                for (int[] dir : dirs){
                    int x = cur[0]+dir[0];
                    int y = cur[1]+dir[1];
                    if (x<0 || y<0 || x>=m||y>=n || visited[x][y] || forest.get(x).get(y)==0) continue;
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            step++;
        }
        return -1;
    }

}
