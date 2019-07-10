package com.leishb.leetcode.bfs;

import com.leishb.leetcode.tag.BFS;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/3.
 */
@BFS
public class _433_Minimum_Genetic_Mutation {


    /**
     * Accepted
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        List<Integer>[] adj = new List[bank.length];
        for (int i=0;i<bank.length;i++){
            adj[i] = new ArrayList<>();
        }
        int endIndex = -1;
        for (int i=0;i<bank.length;i++){
            if (end.equals(bank[i])) {
                endIndex = i;
            }
            for (int j=i+1;j<bank.length;j++){
                if (isNeighbour(bank[i], bank[j])){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<adj.length;i++){
            boolean[] visisted = new boolean[adj.length];
            if (isNeighbour(start, bank[i])){
                ans = Math.min(ans, bfs(i, endIndex, adj, visisted)+1);
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }


    private int bfs(int start, int end, List<Integer>[] adj, boolean[] visisted){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visisted[ start] = true;
        Map<Integer, Integer> step = new HashMap<>();
        step.put(start, 0);
        while (!queue.isEmpty()){
            int k = queue.poll();
            if (k==end){
                return step.get(k);
            }
            for (int neighbour : adj[k]){
                if (visisted[neighbour]){
                    continue;
                }
                visisted[neighbour]=true;
                step.put(neighbour,step.get(k)+1);
                queue.offer(neighbour);
            }
        }
        return Integer.MAX_VALUE-1;
    }

    public boolean isNeighbour(String s1, String s2){
        int count = 0;
        for (int i=0;i<s1.length();i++){
            if (s1.charAt(i)!=s2.charAt(i)){
                count++;
            }
            if (count>1){
                return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertTrue(minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"})==3);
        Assert.assertTrue(minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"})==2);
    }
}
