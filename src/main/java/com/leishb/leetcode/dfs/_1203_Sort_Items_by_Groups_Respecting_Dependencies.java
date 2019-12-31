package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/11/28.
 */
public class _1203_Sort_Items_by_Groups_Respecting_Dependencies {


    /**
     * Accepted
     * @param n
     * @param m
     * @param group
     * @param beforeItems
     * @return
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int[] ans = new int[n];
        Map<Integer, Set<Integer>> itemGraph = new HashMap<>();
        Map<Integer, Integer> itemInDegree = new HashMap<>();
        Map<Integer, Set<Integer>> groupGraph = new HashMap<>();
        Map<Integer, Integer> groupInDegree = new HashMap<>();
        for (int i=0;i<n;i++) {
            itemGraph.put(i, new HashSet<>());
        }
        for (int g : group) {
            groupGraph.put(g, new HashSet<>());
        }
        for (int i=0;i<beforeItems.size();i++){
            for (int val : beforeItems.get(i)){
                itemGraph.get(val).add(i);
                itemInDegree.put(i, itemInDegree.getOrDefault(i, 0)+1);
                int g1 = group[val];
                int g2 = group[i];
                if (g1!=g2 && groupGraph.get(g1).add(g2)){
                    groupInDegree.put(g2, groupInDegree.getOrDefault(g2, 0)+1);
                }
            }
        }
        List<Integer> itemOrder = topologicSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrder = topologicSort(groupGraph, groupInDegree, groupGraph.size());
        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i : itemOrder){
            map.putIfAbsent(group[i], new ArrayList<>());
            map.get(group[i]).add(i);
        }
        int k = 0;
        for (int i : groupOrder){
            for (int j : map.get(i)){
                ans[k++] = j;
            }
        }
        return ans;
    }


    private List<Integer> topologicSort(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegree, int count){
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int k : graph.keySet()){
            if (inDegree.getOrDefault(k, 0)==0) {
                queue.offer(k);
            }
        }
        while (!queue.isEmpty()){
            int cur = queue.poll();
            ans.add(cur);
            count--;
            for (int next : graph.get(cur)){
                inDegree.put(next, inDegree.get(next)-1);
                if (inDegree.get(next)==0){
                    queue.offer(next);
                }
            }
        }
        return count>0 ? new ArrayList<>() : ans;
    }


    @Test
    public void test(){
        List<List<Integer>> beforeItems = new ArrayList<>();
        beforeItems.add(Arrays.asList(2,1,3));
        beforeItems.add(Arrays.asList(2,4));
        beforeItems.add(new ArrayList<>());
        beforeItems.add(new ArrayList<>());
        beforeItems.add(new ArrayList<>());

        sortItems(5, 5, new int[]{2,0,-1,3,0}, beforeItems);
    }
}
