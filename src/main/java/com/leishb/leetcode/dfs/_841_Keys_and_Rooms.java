package com.leishb.leetcode.dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/8/19.
 */
public class _841_Keys_and_Rooms {


    /**
     * Accepted
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        dfs(rooms, 0, visited);
        return visited.size()==rooms.size();
    }


    private void dfs(List<List<Integer>> rooms, int start, Set<Integer> visited){
        visited.add(start);
        for (int next : rooms.get(start)){
            if (!visited.contains(next) && next!=start){
                visited.add(next);
                dfs(rooms, next, visited);
            }
        }
    }
}
