package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/12.
 */
public class _249_Group_Shifted_Strings {

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> freq = new HashMap<>();
        buidGraph(strings, graph, freq);
        for (String s : strings){
            if (freq.get(s) > 0){
                List<String> list = new ArrayList<>();
                dfs(graph, s, freq, list);
                ans.add(list);
            }
        }
        return ans;
    }


    private void dfs(Map<String, List<String>> graph, String s, Map<String, Integer> freq, List<String> list){
        freq.put(s, freq.get(s)-1);
        list.add(s);
        for (String neighbour : graph.get(s)){
            if (freq.get(neighbour) > 0){
                dfs(graph, neighbour, freq, list);
            }
        }
    }


    private void buidGraph(String[] strings, Map<String, List<String>> graph,  Map<String, Integer> freq){
        for (int i=0;i<strings.length;i++){
            freq.put(strings[i], freq.getOrDefault(strings[i], 0)+1);
            graph.putIfAbsent(strings[i], new ArrayList<>());
            for (int j=i+1;j<strings.length;j++){
                graph.putIfAbsent(strings[j], new ArrayList<>());
                if (isNeighbour(strings[i], strings[j])){
                    graph.get(strings[i]).add(strings[j]);
                    graph.get(strings[j]).add(strings[i]);
                }
            }
        }
    }

    private boolean isNeighbour(String s1, String s2){
        if (s1.length()!=s2.length()) return false;
        if (s1.length()==0) return true;
        int diff = s1.charAt(0)-s2.charAt(0);
        diff = diff <0 ? diff+26:diff;
        for (int i=1;i<s1.length();i++){
            int k = s1.charAt(i)-s2.charAt(i);
            k = k<0?k+26:k;
            if (k!=diff) return false;
        }
        return true;
    }


    public List<List<String>> groupStrings2(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings){
            String hash = hash(s);
            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String hash(String s){
        int len = s.length();
        StringBuffer sb = new StringBuffer(len+"@");
        for (int i=1;i<s.length();i++){
            int dif = s.charAt(i)-s.charAt(i-1);
            dif = dif<0?dif+26:dif;
            sb.append(dif).append("#");
        }
        return sb.toString();
    }


    @Test
    public void test(){
        Assert.assertTrue(hash("qcpr").equals(hash("eqdf")));


    }



}
