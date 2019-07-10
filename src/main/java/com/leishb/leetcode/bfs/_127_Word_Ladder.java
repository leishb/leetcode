package com.leishb.leetcode.bfs;

import java.util.*;

/**
 * Created by me on 2019/7/3.
 */
public class _127_Word_Ladder {

    /**
     * Accepted
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> adj = new HashMap<>();
        for (int i=0;i<wordList.size();i++){
            adj.put(wordList.get(i), new ArrayList<>());
        }
        for (int i=0;i<wordList.size();i++){
            for (int j=i+1;j<wordList.size();j++){
                if (isNeighbour(wordList.get(i), wordList.get(j))){
                    adj.get(wordList.get(i)).add(wordList.get(j));
                    adj.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (String s : wordList){
            if (isNeighbour(beginWord, s)){
                int step = bfs(s, endWord, adj);
                ans = step==-1?ans:Math.min(ans,step+2);
            }
        }
        return ans==Integer.MAX_VALUE?0:ans;
    }

    private int bfs(String start, String end, Map<String, List<String>> adj){
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> steps = new HashMap<>();
        queue.offer(start);
        visited.add(start);
        steps.put(start, 0);
        while (!queue.isEmpty()){
            String s = queue.poll();
            if (end.equals(s)){
                return steps.get(s);
            }
            for (String neighbour : adj.get(s)){
                if (visited.contains(neighbour)){
                    continue;
                }
                visited.add(neighbour);
                steps.put(neighbour, steps.get(s)+1);
                queue.offer(neighbour);
            }
        }
        return -1;
    }


    private boolean isNeighbour(String s1, String s2){
        int count = 0;
        for (int i=0;i<s1.length();i++){
            if (s1.charAt(i)!= s2.charAt(i)){
                count++;
            }
            if (count>1){
                return false;
            }
        }
        return true;
    }
}
