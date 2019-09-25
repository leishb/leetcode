package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/5/29.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList();
        for(int i=0; i< words.length; i++){
            for(int j=0; j< words.length; j++){
                if(i==j){
                    continue;
                }
                List<Integer> list = Arrays.asList(i, j);
                if(valid(words[i] + words[j])){
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    private boolean valid(String s){
        int i=0;
        int j=s.length()-1;
        while (i<j){
            if (s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
