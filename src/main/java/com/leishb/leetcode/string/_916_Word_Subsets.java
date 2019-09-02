package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/8/30.
 */
public class _916_Word_Subsets {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> ans = new ArrayList<>();
        int[] counts = new int[26];
        for (String b : B){
            int[] bCounts = new int[26];
            for (char c : b.toCharArray()){
                bCounts[c-'a'] +=1;
            }
            for (int i=0;i<26;i++){
                counts[i] = Math.max(counts[i], bCounts[i]);
            }
        }
        for (String a : A){
            int[] aCounts = new int[26];
            for (char c : a.toCharArray()){
                aCounts[c-'a'] +=1;
            }
            boolean pass = true;
            for (int i=0;i<26;i++){
                if (counts[i] > aCounts[i]){
                    pass = false;
                    break;
                }
            }
            if (pass){
                ans.add(a);
            }
        }
        return ans;
    }
}
