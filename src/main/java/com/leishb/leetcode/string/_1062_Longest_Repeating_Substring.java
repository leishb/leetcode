package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/23.
 */
public class _1062_Longest_Repeating_Substring {

    public int longestRepeatingSubstring(String S) {
        int low = 0;
        int high = S.length()-1;
        int ans = 0;
        while (low<=high){
            int mid = (low+high)/2;
            if (exist(S, mid)){
                ans = Math.max(ans, mid);
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return ans;
    }


    private boolean exist(String s, int len){
        for (int i=0;i<s.length()-len;i++){
            String sub = s.substring(i, i+len);
            if (s.substring(i+1).indexOf(sub)!=-1){
                return true;
            }
        }
        return false;
    }

    private boolean search(String s, int len){
        Set<Integer> seen = new HashSet<>();
        for (int i=0;i<=s.length()-len;i++){
            String sub = s.substring(i, i+len);
            int hash = sub.hashCode();
            if (seen.contains(hash)){
                return true;
            }
            seen.add(hash);
        }
        return false;
    }


    @Test
    public void test(){
        Assert.assertTrue(exist("aaaaa", 4));
    }

}
