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


    /**
     * Accepted
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();
        wordList.add(beginWord);
        for (int i=0;i<wordList.size();i++){
            for (int j=i+1;j<wordList.size();j++){
                if (isNeighbour(wordList.get(i), wordList.get(j))){
                    graph.putIfAbsent(wordList.get(i), new ArrayList<>());
                    graph.putIfAbsent(wordList.get(j), new ArrayList<>());
                    graph.get(wordList.get(i)).add(wordList.get(j));
                    graph.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
        if (!graph.containsKey(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                String cur = queue.poll();
                if (cur.equals(endWord)) return step;
                for (String next : graph.get(cur)){
                    if (set.contains(next))continue;
                    queue.offer(next);
                    set.add(next);
                }
            }
            step++;
        }
        return 0;
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

    /**
     * Accepted
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                String cur = queue.poll();
                if (cur.equals(endWord)) return step;
                char[] cs = cur.toCharArray();
                for(int i=0;i<cs.length;i++){
                    for(char c = 'a';c<='z';c++){
                        if (cs[i]==c) continue;
                        char oldChar = cs[i];
                        cs[i] = c;
                        String next = new String(cs);
                        if (dict.contains(next)){
                            queue.offer(next);
                            dict.remove(next);
                        }
                        cs[i] = oldChar;
                    }
                }
            }
            step++;
        }
        return 0;
    }
}
