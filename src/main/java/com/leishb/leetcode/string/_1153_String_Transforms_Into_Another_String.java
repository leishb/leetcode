package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/10/31.
 */
public class _1153_String_Transforms_Into_Another_String {


    /**
     * Accepted
     * @param str1
     * @param str2
     * @return
     */
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;
        int[] map = new int[26];
        Arrays.fill(map, -1);
        int[] parents = new int[26];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<str1.length();i++){
            int j = str1.charAt(i)-'a';
            if (map[j]!=-1 && map[j] != str2.charAt(i)-'a') return false;
            map[j] = str2.charAt(i)-'a';
            set.add(map[j]);
        }
        for (int i=0;i<26;i++){
            int j = map[i];
            if (j==-1) continue;
            int rx = find(j, parents);
            int ry = find(i, parents);
            if (rx==ry && set.size()==26) {
                return false;
            }
            parents[rx] = ry;
        }
        return true;
    }

    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }


    /**
     * Accepted
     * @param str1
     * @param str2
     * @return
     */
    public boolean canConvert2(String str1, String str2) {
        if (str1.equals(str2)) return true;
        Map<Character, Character> map = new HashMap<>();
        for (int i =0;i<str1.length();i++){
            if (map.getOrDefault(str1.charAt(i), str2.charAt(i))!= str2.charAt(i)){
                return false;
            }
            map.put(str1.charAt(i), str2.charAt(i));
        }
        return new HashSet<>(map.values()).size() < 26;
    }

}
