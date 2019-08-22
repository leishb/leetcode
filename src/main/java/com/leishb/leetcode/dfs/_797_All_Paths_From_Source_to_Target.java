package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/8/13.
 */
public class _797_All_Paths_From_Source_to_Target {


    /**
     * Accepted
     * @param graph
     * @return
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(graph, ans, new ArrayList<>(Arrays.asList(0)), 0, graph.length-1);
        return ans;
    }


    private void dfs(int[][] graph, List<List<Integer>> ans , List<Integer> list, int start, int target){
        if (start==target){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i : graph[start]){
            list.add(i);
            dfs(graph, ans, list, i, target);
            list.remove(list.size()-1);
        }
    }
}
