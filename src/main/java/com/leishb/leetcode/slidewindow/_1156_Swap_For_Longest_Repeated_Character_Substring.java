package com.leishb.leetcode.slidewindow;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/20.
 */
public class _1156_Swap_For_Longest_Repeated_Character_Substring {

    public int maxRepOpt1(String text) {
        int[] left = new int[text.length()];
        int[] counts = new int[26];
        int count = 1;
        int ans = 1;
        counts[text.charAt(0)-'a'] +=1;
        for (int i=1;i<text.length();i++){
            left[i] = count;
            if (text.charAt(i) == text.charAt(i-1)){
                count+=1;
            }else {
                count = 1;
            }
            counts[text.charAt(i)-'a'] +=1;
        }
        int[] right = new int[text.length()];
        count = 1;
        for (int i=text.length()-2;i>=0;i--){
            right[i] = count;
            if (text.charAt(i) == text.charAt(i+1)){
                count+=1;
            }else {
                count = 1;
            }
        }
        for (int i=0;i<text.length();i++){
            if (i> 0 && i<text.length()-1 && text.charAt(i-1)==text.charAt(i+1)){
                int ext = counts[text.charAt(i-1)-'a'] > left[i]+right[i] ? 1 : 0;
                ans = Math.max(ans, left[i]+right[i] +ext);
            }
            if (i> 0 && counts[text.charAt(i-1)-'a'] > left[i]){
                ans = Math.max(ans, left[i]+1);
            }
            if (i< text.length()-1 && counts[text.charAt(i+1)-'a'] > right[i]){
                ans = Math.max(ans, right[i]+1);
            }
        }
        return ans;
    }

    public int maxRepOpt12(String text) {
        int len = text.length();
        int[] counts = new int[26];
        for (int i=0;i<text.length();i++){
            counts[text.charAt(i)-'a']+=1;
        }
        int[] newCounts = new int[26];
        int i=0;
        int ans = 0;
        for (int j=0;j<len;j++){
            newCounts[text.charAt(j)-'a']+=1;
            if (countOfOthers(newCounts) > 1){
                newCounts[text.charAt(i)-'a']-=1;
                i++;
            }
            if (counts[maxCountChar(newCounts)] >= j-i+1){
                ans = Math.max(j-i+1, ans);
            }
        }
        return ans;
    }


    private int maxCountChar(int[] counts){
        int i = 0;
        for (int j=1;j<26;j++){
            if (counts[j] > counts[i]){
                i=j;
            }
        }
        return i;
    }

    private int countOfOthers(int[] counts){
        int max = 0;
        int total = 0;
        for (int i=0;i<counts.length;i++){
            total += counts[i];
            max =Math.max(max, counts[i]);
        }
        return total-max;
    }

    @Test
    public void test(){
        Assert.assertTrue(maxRepOpt12("ababa")==3);
        Assert.assertTrue(maxRepOpt12("aaabaaa")==6);
        Assert.assertTrue(maxRepOpt12("aaabbaaa")==4);
        Assert.assertTrue(maxRepOpt12("aa")==2);
        Assert.assertTrue(maxRepOpt12("a")==1);
        Assert.assertTrue(maxRepOpt12("aaaaa")==5);
        Assert.assertTrue(maxRepOpt12("abcdef")==1);
    }
}
