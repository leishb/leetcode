package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/17.
 */
public class _524_Longest_Word_in_Dictionary_through_Deleting {


    /**
     * Accepted
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        Map<Integer, List<String>> map = new HashMap<>();
        int maxLen = 0;
        for (String s2 : d){
            if (s2.length() >= maxLen && match(s, s2)){
                maxLen = s2.length();
                map.put(s2.length(), map.getOrDefault(s2.length(), new ArrayList<>()));
                map.get(s2.length()).add(s2);
            }
        }
        if (maxLen==0)return "";
        List<String> list = map.get(maxLen);
        Collections.sort(list);
        return list.isEmpty()?"":list.get(0);
    }


    private boolean match(String s1, String s2){
        int i=0;
        int j=0;
        while (i<s1.length() && j<s2.length()){
            if (s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }else {
                i++;
            }
        }
        return j==s2.length();
    }


    public String findLongestWord2(String s, List<String> d) {
        Collections.sort(d, (o1, o2) -> {
            if (o1.length()==o2.length()){
                return o1.compareTo(o2);
            }else {
                return Integer.compare(o2.length(), o1.length());
            }
        });
        for (String s2 : d){
            if (match(s, s2)){
                return s2;
            }
        }
        return "";
    }

    @Test
    public void test(){
        Assert.assertTrue(findLongestWord2("abpcplea", Arrays.asList("ale","apple","monkey","plea")).equals("apple"));
        Assert.assertTrue(findLongestWord2("abpcplea", Arrays.asList("a", "b", "c")).equals("a"));
    }
}
