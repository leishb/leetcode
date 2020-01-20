package com.leishb.other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/462223/Google-or-Can-one-string-be-transformed-into-another
 * Created by me on 2020/1/16.
 */
public class StringTransfer {

    public boolean canTransform(String s1, String s2){
        if (s1.equals(s2)) return true;
        if (s1.length()!=s2.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<s1.length();i++){
            if (map.getOrDefault(s1.charAt(i), s2.charAt(i))!=s2.charAt(i)){
                return false;
            }
            map.put(s1.charAt(i), s2.charAt(i));
            set.add(s2.charAt(i)-'a');
        }
        int[] parents = new int[26];
        for (int i=0;i<26;i++){
            parents[i] = i;
        }
        for (char c : map.keySet()){
            char v = map.get(c);
            int rx = find(c-'a', parents);
            int ry = find(v-'a', parents);
            if (rx==ry && set.size()==26) return false;
            parents[rx] = ry;
        }
        return true;
    }

    public boolean canConvert(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i)) {
                return false;
            }
        }
        return new HashSet<>(map.values()).size()<26;
    }

    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        return parents[x] = find(parents[x], parents);
    }
}
