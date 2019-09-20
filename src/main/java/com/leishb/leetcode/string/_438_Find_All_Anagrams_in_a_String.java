package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.SlidingWindow;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/17.
 */
@SlidingWindow
public class _438_Find_All_Anagrams_in_a_String {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length()<p.length()) return ans;
        int[] counts1 = new int[26];
        int[] counts2 = new int[26];
        for (int i=0;i<p.length();i++){
            counts1[s.charAt(i)-'a'] +=1;
            counts2[p.charAt(i)-'a'] +=1;
        }
        if (Arrays.equals(counts1, counts2)){
            ans.add(0);
        }
        for (int i=0;i<s.length()-p.length() ;i++){
            counts1[s.charAt(i)-'a'] -=1;
            counts1[s.charAt(i+p.length())-'a'] +=1;
            if (Arrays.equals(counts1, counts2)){
                ans.add(i+1);
            }
        }
        return ans;
    }



    public int findAnagram(String s, String p) {
        int[] map = new int[26];
        Set<Character> set = new HashSet<>();
        for(char c : p.toCharArray()){
            map[c-'a'] +=1;
            set.add(c);
        }
        int counts = p.length();
        int i=0;
        int j=0;
        while (j<s.length()){
            if (!set.contains(s.charAt(j))){
                while (i<j){
                    map[s.charAt(i++)-'a']+=1;
                    counts++;
                }
                i++;
            }else {
                while (map[s.charAt(j)-'a']==0){
                    map[s.charAt(i++)-'a']+=1;
                    counts++;
                }
            }
            if (map[s.charAt(j)-'a']>0){
                map[s.charAt(j)-'a']-=1;
                counts--;
            }
            if (counts==0){
                return j-p.length()+1;
            }
            j++;
        }
        return -1;
    }


    @Test
    public void test(){
        findAnagrams("cbaebabacd", "abc");
    }
}
