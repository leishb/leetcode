package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/8/8.
 */
public class _763_Partition_Labels {


    /**
     * Accepted
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] counts = new int[26];
        for (char c : S.toCharArray()){
            counts[c-'a'] +=1;
        }
        int i=0;
        Set<Character> set = new HashSet<>();
        for (int j=i;j<S.length();j++){
            set.add(S.charAt(j));
            counts[S.charAt(j)-'a']--;
            boolean end = true;
            for (char c : set){
                if (counts[c-'a'] !=0){
                    end = false;
                    break;
                }
            }
            if (end){
                ans.add(j-i+1);
                set.clear();
                i=j+1;
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param S
     * @return
     */
    public List<Integer> partitionLabels2(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] counts = new int[26];
        for (char c : S.toCharArray()){
            counts[c-'a'] +=1;
        }
        int i=0;
        Set<Character> set = new HashSet<>();
        int total = 0;
        for (int j=i;j<S.length();j++){
            if (!set.contains(S.charAt(j))){
                total += counts[S.charAt(j)-'a'];
                set.add(S.charAt(j));
            }
            if (total==j-i+1){
                ans.add(j-i+1);
                set.clear();
                i=j+1;
                total=0;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        System.out.println(partitionLabels2("ababcbacadefegdehijhklij"));
    }
}
