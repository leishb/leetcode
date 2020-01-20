package com.leishb.leetcode.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by me on 2020/1/14.
 */
public class _1036_Escape_a_Large_Maze {


    int MAX_SIZE = 20000;

    /**
     * Accepted
     * @param blocked
     * @param source
     * @param target
     * @return
     */
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> set = new HashSet<>();
        for (int[] block : blocked){
            set.add(getKey(block));
        }
        return canVisit(set, source, target) && canVisit(set, target, source);
    }

    int[] dirs = new int[]{0, 1, 0, -1, 0};

    private boolean canVisit(Set<String> blockSet, int[] source, int[] target){
        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(source);
        visited.add(getKey(source));
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[0]==target[0] && cur[1]==target[1]){
                return true;
            }
            for (int d=0;d<4;d++){
                int x = cur[0] + dirs[d];
                int y = cur[1] + dirs[d+1];
                if (x<0 || y<0 || x>=1_000_000  || y>=1_000_000
                        || visited.contains(getKey(new int[]{x, y}))
                        || blockSet.contains(getKey(new int[]{x, y}))){
                    continue;
                }
                visited.add(getKey(new int[]{x, y}));
                queue.offer(new int[]{x, y});
                if (visited.size() >= MAX_SIZE){
                    return true;
                }
            }
        }
        return false;
    }

    private String getKey(int[] block){
        return block[0] + "," + block[1];
    }
}
