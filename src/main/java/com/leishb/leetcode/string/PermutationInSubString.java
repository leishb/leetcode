package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.SlidingWindow;
import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2017/11/30.
 */
@TwoPointers
@SlidingWindow
public class PermutationInSubString {


    @Test
    public void test(){
        Assert.assertTrue(permutationInString("aabcdefg", "bcdeaa"));
        Assert.assertTrue(!permutationInString("aabc", "bcdeaa"));
        Assert.assertTrue(permutationInString("wzaabcdefg", "bcdeaa"));
        Assert.assertTrue(permutationInString("wzaabcadeafg", "bcdeaa"));
        Assert.assertTrue(permutationInString("wzaabcadecafg", "bccdeaa"));
        Assert.assertTrue(permutationInString("wzaafaabcdefg", "bcdeaa"));
        Assert.assertTrue(permutationInString("wzaafaaqwrtbcdeaa", "bcdeaa"));
        Assert.assertFalse(permutationInString("wzaafaabcwwdefg", "bcdeaa"));
        Assert.assertFalse(permutationInString("ooolleoooleh", "hello"));
        Assert.assertFalse(permutationInString2("ooolleoooleh", "hello"));
    }


    /**
     * Accepted
     * @param s2
     * @param s1
     * @return
     */
    public boolean permutationInString(String s2, String s1){
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int count = s1.length();
        int left = 0;
        int right = 0;
        while (right<s2.length()){
            if (map.containsKey(s2.charAt(right)) && map.get(s2.charAt(right)) >0){
                if (!map.containsKey(s2.charAt(left))){//attention
                    left = right;
                }
                count--;
                map.put(s2.charAt(right), map.get(s2.charAt(right))-1);
                right++;
            }else {
                while (s2.charAt(left) != s2.charAt(right)){
                    if(map.containsKey(s2.charAt(left))){//attention
                        count++;
                        map.put(s2.charAt(left), map.get(s2.charAt(left))+1);
                    }
                    left++;
                }
                left++;
                right++;
            }
            if (count==0){
                return true;
            }
        }
        return false;
    }


    /**
     * Accepted
     * @param s2
     * @param s1
     * @return
     */
    public boolean permutationInString2(String s2, String s1){
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int start = 0;
        int end = 0;
        while (end < s2.length()){
            if (map.containsKey(s2.charAt(end))  && map.get(s2.charAt(end)) >0){
                if (!map.containsKey(s2.charAt(start))){//init set start
                    start = end;
                }
                map.put(s2.charAt(end), map.get(s2.charAt(end))-1);
                end++;
            }else {
                while (s2.charAt(start) != s2.charAt(end)){
                    map.put(s2.charAt(start), map.get(s2.charAt(start))+1);
                    start++;
                }
                start++;
                end++;
            }
            if (end-start==s1.length()){
                return true;
            }
        }
        return false;
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap();
        int count = p.length();
        for(char c : p.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> ans = new ArrayList();
        int left = 0;
        int right = 0;
        while(right < s.length()){
            if(map.containsKey(s.charAt(right)) && map.get(s.charAt(right)) > 0){
                if(!map.containsKey(s.charAt(left))){
                    left = right;
                }
                map.put(s.charAt(right) , map.get(s.charAt(right))-1);
                count--;
                right++;
            }else{
                while(s.charAt(left) != s.charAt(right)){
                    if(map.containsKey(s.charAt(left))){
                        map.put(s.charAt(left) , map.get(s.charAt(left))+1);
                        count++;
                    }
                    left++;
                }
                left++;
                right++;
            }
            if(count==0){
                ans.add(left);
                map.put(s.charAt(left) , map.get(s.charAt(left))+1);
                count++;
                left++;
            }
        }
        return ans;
    }
}
