package com.leishb.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2020/1/19.
 */
public class Construct_a_word_using_dice {

    public boolean solve(String word, char[][] dice){
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i=0;i<dice.length;i++){
            for (char c : dice[i]){
                map.putIfAbsent(c, new ArrayList<>());
                map.get(c).add(i);
            }
        }
        return dfs(word, 0, map, new boolean[word.length()]);
    }


    private boolean dfs(String word ,int index, Map<Character, List<Integer>> map, boolean[] used){
        if (index==word.length()){
            return true;
        }
        char c = word.charAt(index);
        for (int next : map.getOrDefault(c, new ArrayList<>())){
            if (used[next]) continue;
            used[next] = true;
            if (dfs(word, index+1, map, used)){
                return true;
            }
            used[next] = false;
        }
        return false;
    }


    private boolean dfs(String word ,int index, Map<Character, List<Integer>> map, int mask, Map<String, Boolean> cache){
        if (index==word.length()){
            return true;
        }
        String key = index + "," + mask;
        if (cache.containsKey(key)) return cache.get(key);
        char c = word.charAt(index);
        for (int next : map.getOrDefault(c, new ArrayList<>())){
            if ((mask&(1<<next))!=0) continue;
            if (dfs(word, index+1, map, mask|(1<<next), cache)){
                cache.put(key, true);
                return true;
            }
        }
        cache.put(key, false);
        return false;
    }


}
