package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/18.
 */
public class _1129_Shortest_Path_with_Alternating_Colors {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int[] ans = new int[n];
        List<Integer>[] redAdj = new List[n];
        List<Integer>[] blueAdj = new List[n];
        for (int i=0;i<n;i++){
            redAdj[i] = new ArrayList<>();
            blueAdj[i] = new ArrayList<>();
        }
        for (int[] edge : red_edges){
            redAdj[edge[0]].add(edge[1]);
        }
        for (int[] edge : blue_edges){
            blueAdj[edge[0]].add(edge[1]);
        }
        Map<Integer, Integer> redFirst = new HashMap<>();
        Map<Integer, Integer> blueFirst = new HashMap<>();
        bfs(redFirst, redAdj, blueAdj);
        bfs(blueFirst, blueAdj, redAdj);
        for (int i=0;i<n;i++){
            if (!redFirst.containsKey(i) && !blueFirst.containsKey(i)){
                ans[i] = -1;
            }else {
                ans[i] = Math.min(redFirst.getOrDefault(i, Integer.MAX_VALUE), blueFirst.getOrDefault(i, Integer.MAX_VALUE));
            }
        }
        return ans;
    }



    private void bfs(Map<Integer, Integer> map, List<Integer>[] firstAdj, List<Integer>[] secondAdj){
        Set<Integer> firstSet = new HashSet<>();
        Set<Integer> secondSet = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        secondSet.add(0);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int k = queue.poll();
                map.putIfAbsent(k, step);
                if (step%2==0){
                    for (int next : firstAdj[k]){
                        if (!firstSet.contains(next)){
                            queue.offer(next);
                            firstSet.add(next);
                        }
                    }
                }
                if (step%2==1){
                    for (int next : secondAdj[k]){
                        if (!secondSet.contains(next) ){
                            queue.offer(next);
                            secondSet.add(next);
                        }
                    }
                }
            }
            step+=1;
        }
    }


    public int[] shortestAlternatingPaths2(int n, int[][] red_edges, int[][] blue_edges) {
        int[] ans = new int[n];
        List<Integer>[] redAdj = new List[n];
        List<Integer>[] blueAdj = new List[n];
        for (int i = 0; i < n; i++) {
            redAdj[i] = new ArrayList<>();
            blueAdj[i] = new ArrayList<>();
        }
        for (int[] edge : red_edges) {
            redAdj[edge[0]].add(edge[1]);
        }
        for (int[] edge : blue_edges) {
            blueAdj[edge[0]].add(edge[1]);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1});
        queue.offer(new int[]{0, 2});
        Set<String> set = new HashSet<>();
        set.add("0-1");
        set.add("0-2");
        int step = 0;
        Arrays.fill(ans, -1);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                if (ans[cur[0]]==-1){
                    ans[cur[0]] = step;
                }
                if (cur[1]==2){
                    for (int next : redAdj[cur[0]]){
                        if (!set.contains(next+"-"+1)){
                            queue.offer(new int[]{next, 1});
                            set.add(next+"-"+1);
                        }
                    }
                }
                if (cur[1]==1){
                    for (int next : blueAdj[cur[0]]){
                        if (!set.contains(next+"-"+2)){
                            queue.offer(new int[]{next, 2});
                            set.add(next+"-"+2);
                        }
                    }
                }
            }
            step++;
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertArrayEquals(shortestAlternatingPaths2(5, new int[][]{{2,2},{0,1},{0,3},{0,0},{0,4},{2,1},{2,0},{1,4},{3,4}}, new int[][]{{1,3},{0,0},{0,3},{4,2},{1,0}}), new int[]{0,1,2,1,1});
        Assert.assertArrayEquals(shortestAlternatingPaths2(5, new int[][]{{0,1},{1,2},{2,3},{3,4}}, new int[][]{{1,2},{2,3},{3,1}}), new int[]{0,1,2,3,7});
        Assert.assertArrayEquals(shortestAlternatingPaths2(6, new int[][]{{4,1},{3,5},{5,2},{1,4},{4,2},{0,0},{2,0},{1,1}},
                new int[][]{{5,5},{5,0},{4,4},{0,3},{1,0}}), new int[]{0,-1,4,1,-1,2});
    }
}
