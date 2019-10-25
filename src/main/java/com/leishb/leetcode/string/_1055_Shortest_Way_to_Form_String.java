package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by me on 2019/10/25.
 */
public class _1055_Shortest_Way_to_Form_String {


    /**
     * Accepted
     * @param source
     * @param target
     * @return
     */
    public int shortestWay(String source, String target) {
        TreeSet<Integer>[] sets = new TreeSet[26];
        for (int i=0;i<26;i++){
            sets[i] = new TreeSet();
        }
        for (int i=0;i<source.length();i++){
            sets[source.charAt(i)-'a'].add(i);
        }
        int ans = 0;
        int i=0;
        while (i<target.length()){
            int prev = -1;
            Integer cur = sets[target.charAt(i)-'a'].higher(prev);
            if (cur==null) return -1;
            while (cur!=null) {
                prev = cur;
                i++;
                if (i==target.length()) break;
                cur = sets[target.charAt(i) - 'a'].higher(prev);
            }
            ans ++;
        }
        return ans;
    }


    /**
     * Accepted
     * @param source
     * @param target
     * @return
     */
    public int shortestWay2(String source, String target) {
        for (int i=0;i<target.length();i++){
            if (source.indexOf(target.charAt(i))==-1) return -1;
        }
        return dfs(source, target, new HashMap<>());
    }


    //xyz, xzyxz
    private int dfs(String source, String target, Map<String, Integer> memo){
        int prev = -1;
        if (memo.containsKey(target)) return memo.get(target);
        String sub = source;
        for (int i=0;i<target.length();i++){
            sub = sub.substring(prev+1);
            prev = sub.indexOf(target.charAt(i));
            if (prev==-1){
                int ans = 1 + dfs(source, target.substring(i), memo);
                memo.put(target, ans);
                return ans;
            }
        }
        return 1;
    }


    @Test
    public void test(){
        Assert.assertTrue(shortestWay2("xyz", "xzyxz")==3);
        Assert.assertTrue(shortestWay2("aaa", "aaaaaaa")==3);

        Assert.assertTrue(shortestWay2("adbsc", "addddddddddddsbc")==13);
    }
}
