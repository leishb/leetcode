package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.DivideAndConquer;
import com.leishb.leetcode.tag.SlidingWindow;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/10/20.
 */
@DivideAndConquer
@SlidingWindow
public class LongestSubString {

    @Test
    public void test(){
        Assert.assertTrue(longestSubstring2("aaabc",3)==3);
        Assert.assertTrue(longestSubstring2("ababbc",2)==5);
        Assert.assertTrue(longestSubstring2("a",2)==0);
        Assert.assertTrue(longestSubstring2("weitong",2)==0);


        Assert.assertTrue(longestSubstring3("aaabc",3)==3);
        Assert.assertTrue(longestSubstring3("ababbc",2)==5);
        Assert.assertTrue(longestSubstring3("a",2)==0);
        Assert.assertTrue(longestSubstring3("weitong",2)==0);

        Assert.assertTrue(longestSubstring4("aaabc",3)==3);
        Assert.assertTrue(longestSubstring4("ababbc",2)==5);
        Assert.assertTrue(longestSubstring4("a",2)==0);
        Assert.assertTrue(longestSubstring4("weitong",2)==0);
        Assert.assertTrue(longestSubstring4("a",1)==1);
    }


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring3(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        return div(s, 0, s.length(),k);
    }


    private int div(String s, int start, int end, int k){
        if (end-start<k)
            return 0;
        int[] counts = new int[26];
        for (int i=start;i<end;i++){
            counts[s.charAt(i)-'a'] +=1;
        }
        for (int i=0;i<counts.length;i++){
            if (counts[i]>0 && counts[i]<k){
                for (int j=start;j<end;j++){
                    if (s.charAt(j)-'a'==i){
                        //split
                        int left = div(s, start, j,k);
                        int right = div(s, j+1,end,k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end-start;
    }


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring4(String s, int k) {
        int max = 0;
        for (int i=1;i<=26;i++){
            max = Math.max(max, findLength(s, i, k));
        }
        return max;
    }


    private  int findLength(String s, int numUniqTarget, int targetK){
        int max = 0;
        int start = 0;
        int end = 0;
        int numUniq = 0;
        int numAtleastK = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()){
            char c = s.charAt(end);
            int count = map.getOrDefault(c, 0);
            map.put(c, count+1);
            if (count==0){
                numUniq++;
            }
            if(count+1==targetK){
                numAtleastK++;
            }
            while (numUniq > numUniqTarget){
                int leftCount = map.get(s.charAt(start));
                map.put(s.charAt(start), leftCount-1);
                if (leftCount==1){
                    numUniq--;
                }
                if (leftCount==targetK){
                    numAtleastK--;
                }
                start++;
            }
            if (numUniq == numUniqTarget && numAtleastK == numUniq){
                max = Math.max(max, end-start+1);
            }
            end++;
        }
        return max;
    }

    /**
     * Time limit exceeded
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {

        if (s.length()<k){
            return 0;
        }
        int[][][] dp = new int[s.length()][s.length()][26];

        int max = 0;
        for (int i=0;i<s.length();i++){
            for (int j=i;j<i+k && i+k-1 < s.length();j++){
                char c = s.charAt(j);
                dp[i][i+k-1][c-'a'] += 1;
            }
        }

        for (int i=0;i<s.length();i++){
            for (int j=i+k;j<s.length();j++){
                char c = s.charAt(j);
                dp[i][j] = Arrays.copyOfRange(dp[i][j-1], 0, 26);
                dp[i][j][c-'a'] +=1;
            }
        }

        for (int i=0;i<s.length();i++){
            for (int j=i+k-1;j<s.length();j++){
                int[] counts = dp[i][j];
                boolean valid = true;
                for (int count : counts){
                    if (count != 0 && count < k){
                        valid = false;
                        break;
                    }
                }
                if (valid){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }



    /**
     * Time limit exceeded
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        char[] cs = s.toCharArray();
        if(cs.length<k){
            return 0;
        }
        int max =0;
        for(int i=0;i<cs.length;i++){
            for(int j=i+k-1;j<cs.length;j++){
                if(atLeastK(cs,i,j,k)){
                    max = Math.max(max, j-i+1);
                }
            }
        }
        return max;
    }


    private boolean atLeastK(char[] cs, int i, int j, int k){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int less = k;
        for(int p=i;p<=j;p++){
            if(map.containsKey(cs[p])){
                map.put(cs[p], map.get(cs[p]) + 1);
            }else{
                map.put(cs[p],1);
            }
        }
        for(Integer l : map.values()){
            less = Math.min(less, l);
        }
        return less >=k;
    }
}
