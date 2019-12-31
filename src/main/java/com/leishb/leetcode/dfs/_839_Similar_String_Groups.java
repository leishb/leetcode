package com.leishb.leetcode.dfs;

import java.util.*;

/**
 * Created by me on 2019/11/22.
 */
public class _839_Similar_String_Groups {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numSimilarGroups(String[] A) {
        Set<String> set = new HashSet<>(Arrays.asList(A));
        String[] strs = set.toArray(new String[set.size()]);
        int[] parents = new int[strs.length];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
        }
        for (int i=0;i<strs.length;i++){
            for (int j=i+1;j<strs.length;j++){
                int rx = find(i, parents);
                int ry = find(j, parents);
                if (rx==ry) continue;
                if (similar(strs[i], strs[j])){
                    parents[rx] = ry;
                }
            }
        }
        Set<Integer> groups = new HashSet<>();
        for (int i=0;i<parents.length;i++){
            groups.add(find(i, parents));
        }
        return groups.size();
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }


    private boolean similar(String s, String t){
        if (s.equals(t)) return true;
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<t.length();i++){
            if (s.charAt(i)!=t.charAt(i)){
                list.add(i);
            }
            if (list.size()>2) return false;
        }
        return list.size()==2 && s.charAt(list.get(0))==t.charAt(list.get(1)) && s.charAt(list.get(1))==t.charAt(list.get(0));
    }
}
