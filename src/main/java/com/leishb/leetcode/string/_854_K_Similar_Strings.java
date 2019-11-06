package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/10/31.
 */
public class _854_K_Similar_Strings {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int kSimilarity(String A, String B) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(A);
        visited.add(A);
        int k = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                String cur = queue.poll();
                if (cur.equals(B)){
                    return k;
                }
                List<String> neigbours = neighbours(cur, B);
                for (String next : neigbours){
                    if (!visited.contains(next)){
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            k++;
        }
        return 0;
    }


    private List<String> neighbours(String s, String target){
        char[] cs = s.toCharArray();
        List<String> list = new ArrayList<>();
        int i=0;
        for (;i<cs.length;i++){
            if (cs[i]!=target.charAt(i)) break;
        }
        for (int j=i+1;j<target.length();j++){
            if (cs[j]==target.charAt(i)){
                swap(cs, i, j);
                list.add(new String(cs));
                swap(cs, i, j);
            }
        }
        return list;
    }

    private void swap(char[] cs, int i, int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }
}
