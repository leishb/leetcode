package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/15.
 */
public class _567_Permutation_in_String {

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        int count = s1.length();
        for (int i=0;i<s1.length();i++){
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0)+1);
        }
        for (int i=0;i<s2.length();i++){
            if (map.containsKey(s2.charAt(i)) && map.get(s2.charAt(i)) > 0){
                map.put(s2.charAt(i), map.get(s2.charAt(i))-1);
                count--;
                if (count==0){
                    return true;
                }
            }else if (map.containsKey(s2.charAt(i)) && map.get(s2.charAt(i))==0){
                int j = i-(s1.length()-count);
                for (;j<i;j++){
                    if (s2.charAt(j)==s2.charAt(i)){
                        break;
                    }
                    map.put(s2.charAt(j), map.get(s2.charAt(j))+1);
                    count++;
                }
            } else if (!map.containsKey(s2.charAt(i))) {
                int j = i-(s1.length()-count);
                for (;j<i;j++){
                    map.put(s2.charAt(j), map.get(s2.charAt(j))+1);
                    count++;
                }
            }
        }
        return false;
    }

    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length()>s2.length()) return false;
        int[] counts1 = new int[26];
        for (char c : s1.toCharArray()){
            counts1[c-'a']+=1;
        }
        for (int i=0;i<=s2.length()-s1.length();i++){
            int[] counts2 = new int[26];
            for (int j=0;j<s1.length();j++){
                counts2[s2.charAt(j+i)-'a'] +=1;
            }
            if (Arrays.equals(counts1, counts2)){
                return true;
            }
        }
        return false;
    }


    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length()>s2.length()) return false;
        int[] counts1 = new int[26];
        for (char c : s1.toCharArray()){
            counts1[c-'a']+=1;
        }
        int[] counts2 = new int[26];
        for (int j=0;j<s1.length()-1;j++){
            counts2[s2.charAt(j)-'a'] +=1;
        }
        int i=0;
        while (i<=s2.length()-s1.length()){
            counts2[s2.charAt(i+s1.length()-1)-'a']+=1;
            if (Arrays.equals(counts1, counts2)){
                return true;
            }
            counts2[s2.charAt(i)-'a']-=1;
            i++;
        }
        return false;
    }


    public boolean checkInclusion4(String s1, String s2) {
        if (s1.length()>s2.length()) return false;
        int[] counts1 = new int[26];
        int[] counts2 = new int[26];
        for (int i=0;i<s1.length();i++){
            counts1[s1.charAt(i)-'a']+=1;
            counts2[s2.charAt(i)-'a']+=1;
        }
        for (int i=0;i<s2.length()-s1.length();i++){
            if (Arrays.equals(counts1, counts2)){
                return true;
            }
            counts2[s2.charAt(i)-'a'] -=1;
            counts2[s2.charAt(i+s1.length())-'a'] +=1;
        }
        return Arrays.equals(counts1, counts2);
    }

    public boolean checkInclusion5(String s1, String s2) {
        if (s1.length()>s2.length()) return false;
        int[] counts = new int[26];
        for (int i=0;i<s1.length();i++){
            counts[s1.charAt(i)-'a'] +=1;
            counts[s2.charAt(i)-'a'] -=1;
        }
        if (Arrays.equals(counts, new int[26])){
            return true;
        }
        for (int i=0;i<s2.length()-s1.length() ;i++){
            counts[s2.charAt(i)-'a'] +=1;
            counts[s2.charAt(i+s1.length())-'a'] -=1;
            if (Arrays.equals(counts, new int[26])){
                return true;
            }
        }
        return false;
    }

    @Test
    public void test(){
        checkInclusion5("adc", "dcda");
    }
}
