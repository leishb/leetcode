package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/3.
 */
public class _126_Word_Ladder_2 {

    /**
     * Accepted
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, Integer> distance = new HashMap<>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();
        int minLen = bfs(beginWord,endWord, wordList, distance, nodeNeighbors);
        if (minLen==-1){
            return ans;
        }
        dfs(beginWord, endWord, wordList, Arrays.asList(beginWord), ans, new HashSet<>(),  minLen, distance, nodeNeighbors);
        return ans;
    }

    public void dfs(String beginWord, String endWord, List<String> wordList, List<String > list, List<List<String>> result,
                    Set<String> visited, int minLen,  Map<String, Integer> distance, HashMap<String, ArrayList<String>> nodeNeighbors){
        if (list.size()>minLen){
            return;
        }
        if (beginWord.equals(endWord)){
            result.add(list);
            return;
        }
        visited.add(beginWord);
        for (String s : nodeNeighbors.get(beginWord)){
            if (visited.contains(s)){
                continue;
            }
            if (distance.get(s) == distance.get(beginWord)+1){
                List<String> copy = new ArrayList<>(list);
                copy.add(s);
                dfs(s, endWord, wordList, copy, result, visited, minLen, distance, nodeNeighbors);
            }

        }
        visited.remove(beginWord);
    }


    private int bfs(String beginWord, String endWord, List<String> wordList,  Map<String, Integer> distance , HashMap<String, ArrayList<String>> nodeNeighbors){
        for (String str : wordList)
            nodeNeighbors.put(str, new ArrayList<>());
        nodeNeighbors.put(beginWord, new ArrayList<>());
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        boolean found = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                String k = queue.poll();
                for (String s : wordList){
                    if (!isNeighbour(s, k)){
                        continue;
                    }
                    nodeNeighbors.get(k).add(s);
                    if ( !distance.containsKey(s)){
                        distance.put(s, distance.get(k)+1);
                        queue.offer(s);
                        if (endWord.equals(s)){
                            found = true;
                        }
                    }
                }
                if (found){
                    break;
                }
            }
        }
        return distance.containsKey(endWord)?distance.get(endWord)+1:-1;
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

    @Test
    public void test(){
        System.out.println(findLadders("lost", "miss", Arrays.asList("most","mist","miss","lost","fist","fish")));
        System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(findLadders("hot", "dog", Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot")));
    }
}
