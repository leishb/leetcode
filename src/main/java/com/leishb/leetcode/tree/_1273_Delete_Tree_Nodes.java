package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/12/2.
 */
public class _1273_Delete_Tree_Nodes {


    /**
     * Accepted
     * @param nodes
     * @param parent
     * @param value
     * @return
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<Integer>[] graph = new List[nodes];
        for (int i=0;i<nodes;i++){
            graph[i] = new ArrayList<>();
        }
        for (int i=1;i<parent.length;i++){
            graph[parent[i]].add(i);
        }
        return dfs(graph, 0, value)[1];
    }


    private int[] dfs(List<Integer>[] graph, int cur,  int[] values){
        int sum = values[cur], count = 0;
        for (int next : graph[cur]){
            int[] arr = dfs(graph, next, values);
            sum += arr[0];
            count += arr[1];
        }
        count = sum!=0?count+1:0;
        return new int[]{sum, count};
    }
}
