package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2019/9/16.
 */
public class _1079_Letter_Tile_Possibilities {

    public int numTilePossibilities(String tiles) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0;i<tiles.length();i++){
            map.put(tiles.charAt(i), map.getOrDefault(tiles.charAt(i), 0)+1);
        }
        for (int i=1;i<=tiles.length();i++){
            Set<String> set = new HashSet<>();
            backtracking(set, i, map, "");
            ans += set.size();
        }
        return ans;
    }


    private void backtracking(Set<String> set, int count, Map<Character, Integer> map, String s){
        if (count==0){
            set.add(s);
            return;
        }
        for (char c : map.keySet()){
            if (map.getOrDefault(c, 0) > 0){
                map.put(c, map.get(c)-1);
                backtracking(set, count-1, map, s+c);
                map.put(c, map.get(c)+1);
            }
        }
    }


    public int numTilePossibilities2(String tiles) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0;i<tiles.length();i++){
            map.put(tiles.charAt(i), map.getOrDefault(tiles.charAt(i), 0)+1);
        }
        for (int i=1;i<=tiles.length();i++){
            ans += backtracking(i, map);
        }
        return ans;
    }


    private int backtracking(int count, Map<Character, Integer> map){
        if (count==0){
            return 1;
        }
        int sum = 0;
        for (char c : map.keySet()){
            if (map.getOrDefault(c, 0) > 0){
                map.put(c, map.get(c)-1);
                sum += backtracking(count-1, map);
                map.put(c, map.get(c)+1);
            }
        }
        return sum;
    }

    @Test
    public void test(){
        numTilePossibilities2("ABCDEFG");
    }
}
