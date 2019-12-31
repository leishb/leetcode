package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by me on 2019/11/22.
 */
public class _864_Shortest_Path_to_Get_All_Keys {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int shortestPathAllKeys(String[] grid) {
        if (grid.length==0)return 0;
        int m = grid.length,n = grid[0].length();
        Queue<State> queue = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();
        int mask = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length();j++){
                if (grid[i].charAt(j)=='@'){
                    queue.offer(new State(0, i, j, 0));
                    dist.put(queue.peek().toString(), 0);
                }
                if (isKey(grid[i].charAt(j))){
                    int x = grid[i].charAt(j)-'a';
                    mask |= (1<<x);
                }
            }
        }
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        while (!queue.isEmpty()){
            State cs = queue.poll();
            if (cs.mask==mask){
                return cs.cost;
            }
            for (int[] dir : dirs){
                int x = cs.x + dir[0];
                int y = cs.y + dir[1];
                if (x<0 || y<0 || x>=m || y>=n || grid[x].charAt(y)=='#')continue;
                char c = grid[x].charAt(y);
                State state = null;
                if (c=='.' || c=='@'){
                    state = new State(cs.mask, x, y, cs.cost+1);
                }else if (isKey(c)){
                    state = new State(cs.mask|(1<<(c-'a')), x, y, cs.cost+1);
                }else if (isLock(c)){
                    if ((cs.mask&(1<<(c-'A')))==0) continue;
                    state = new State(cs.mask, x, y, cs.cost+1);
                }
                if (dist.getOrDefault(state.toString(), Integer.MAX_VALUE) > cs.cost+1){
                    dist.put(state.toString(), cs.cost+1);
                    queue.offer(state);
                }
            }
        }
        return -1;
    }


    private boolean isKey(char c){
        return c-'a'>=0 && c-'a'<6;
    }

    private boolean isLock(char c){
        return c-'A'>=0 && c-'A'<6;
    }

    class State{
        int mask;
        int x;
        int y;
        int cost;

        State(int mask, int x, int y, int cost){
            this.mask = mask;
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public String toString(){
            return mask + "-" + x + "-" + y;
        }
    }

    @Test
    public void test(){
        Assert.assertEquals(8, shortestPathAllKeys(new String[]{"@.a.#","###.#","b.A.B"}));
        Assert.assertEquals(10, shortestPathAllKeys(new String[]{"@...a",".###A","b.BCc"}));
    }
}
