package com.leishb.leetcode.bfs;

import java.util.*;

/**
 * Created by me on 2019/11/22.
 */
public class _773_Sliding_Puzzle {


    /**
     * Accepted
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        String dest = toString(new int[][]{{1,2,3},{4,5,0}});
        Queue<int[][]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(board);
        visited.add(toString(board));
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[][] cur = queue.poll();
                if (dest.equals(toString(cur))) return step;
                List<int[][]> neighbours = neighbours(cur);
                for (int[][] next : neighbours){
                    String key = toString(next);
                    if (!visited.contains(key)){
                        queue.offer(next);
                        visited.add(key);
                    }
                }
            }
            step++;
        }
        return -1;
    }


    private List<int[][]> neighbours(int[][] board){
        List<int[][]> list = new ArrayList<>();
        int x = 0, y = 0;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if (board[i][j] == 0) {
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for (int[] dir : dirs){
            int i = x + dir[0];
            int j = y + dir[1];
            if (i>=0 && j>=0 && i<2 && j<3){
                int[][] copy = new int[2][3];
                copy[0] = board[0].clone();
                copy[1] = board[1].clone();
                copy[i][j] = 0;
                copy[x][y] = board[i][j];
                list.add(copy);
            }
        }
        return list;
    }

    private String toString(int[][] board){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                sb.append(board[i][j]).append(",");
            }
        }
        return sb.toString();
    }

    /**
     * Accepted
     * @param board
     * @return
     */
    public int slidingPuzzle2(int[][] board) {
        String dest = "123450";
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                sb.append(board[i][j]);
            }
        }
        queue.offer(sb.toString());
        visited.add(sb.toString());
        int[][] dirs = new int[][]{{1,3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (dest.equals(cur)) return step;
                int index = cur.indexOf('0');
                for (int x : dirs[index]){
                    StringBuffer next = new StringBuffer(cur);
                    next.setCharAt(x, '0');
                    next.setCharAt(index, cur.charAt(x));
                    String key = next.toString();
                    if (!visited.contains(key)){
                        queue.offer(key);
                        visited.add(key);
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
