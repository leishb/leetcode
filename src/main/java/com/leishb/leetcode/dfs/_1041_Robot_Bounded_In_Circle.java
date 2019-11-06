package com.leishb.leetcode.dfs;

/**
 * Created by me on 2019/10/29.
 */
public class _1041_Robot_Bounded_In_Circle {


    int[][] dirs = new int[][]{{0, 1}, {1,0}, {0, -1}, {-1, 0}};

    public boolean isRobotBounded(String instructions) {
        int cur = 0;
        int curi = 0, curj = 0;
        instructions = instructions + instructions + instructions + instructions;
        for(char c : instructions.toCharArray()){
            switch (c){
                case 'G':
                    curi += dirs[cur][0];
                    curj += dirs[cur][1];
                    break;
                case 'L':
                    cur = cur==0?3:cur-1;
                    break;
                case 'R':
                    cur = (cur+1)%4;
                    break;
            }
        }
        return cur==0 && curi==0 && curj == 0;
    }
}
