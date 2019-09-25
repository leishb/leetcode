package com.leishb.leetcode.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/9/25.
 */
public class _489_Robot_Room_Cleaner {


    interface Robot {
             // Returns true if the cell in front is open and robot moves into the cell.
                     // Returns false if the cell in front is blocked and robot stays in the current cell.
                     public boolean move();
        
                     // Robot will stay in the same cell after calling turnLeft/turnRight.
                     // Each turn will be 90 degrees.
                     public void turnLeft();
                     public void turnRight();
        
                     // Clean the current cell.
                     public void clean();
         }

    int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public void cleanRoom(Robot robot) {
        backtracking(robot, new HashSet<>(), 0, 0, 0);
    }


    private void backtracking(Robot robot, Set<String> set, int x, int y, int arrow){
        String key = x+"-"+y;
        if (set.contains(key)) return;
        robot.clean();
        set.add(key);
        for (int i=0 ; i< 4; i++){
            if (robot.move()) {
                int nextX = x + dirs[arrow][0];
                int nextY = y + dirs[arrow][1];
                backtracking(robot, set, nextX, nextY, arrow);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
            arrow = (arrow+1)%4;
        }
    }

}
