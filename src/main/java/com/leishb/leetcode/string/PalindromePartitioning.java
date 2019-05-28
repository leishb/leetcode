package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;
import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/5/23.
 */
@DynamicProgramming
@BackTracking
@DFS
public class PalindromePartitioning {


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s==null||s.length()==0){
            return result;
        }
        backtracking(0, s, new ArrayList<>(), result);
        return result;
    }


    public void backtracking(int start, String s, List<String> list, List<List<String>> result){
        if(start==s.length()){
            result.add(list);
            return;
        }
        for (int i=start;i<s.length();i++){
            String sub = s.substring(start, i+1);
            if (valid(sub)){
                List<String> copy = new ArrayList<>(list);
                copy.add(sub);
                backtracking(i+1, s, copy, result);
            }
        }
    }

    private boolean valid(String s){
        int i=0;
        int j=s.length()-1;
        while (i<j){
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s==null||s.length()==0){
            return result;
        }
        boolean[][] dp = partitionArr(s);
        backtracking(0, s, new ArrayList<>(), result, dp);
        return result;
    }

    public void backtracking(int start, String s, List<String> list, List<List<String>> result,  boolean[][] dp){
        if(start==s.length()){
            result.add(list);
            return;
        }
        for (int i=start;i<s.length();i++){
            String sub = s.substring(start, i+1);
            if (dp[start][i]){
                List<String> copy = new ArrayList<>(list);
                copy.add(sub);
                backtracking(i+1, s, copy, result, dp);
            }
        }
    }


    private boolean[][] partitionArr(String s){
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i=0;i<dp.length;i++){
            dp[i][i] = true;
        }
        for (int i=1;i<s.length();i++){
            for (int j=0;i+j<s.length();j++){
                if (s.charAt(j) == s.charAt(i+j) && (dp[j+1][i+j-1] || i==1)){
                    dp[j][i+j] = true;
                }
            }
        }
        return dp;
    }


    private int min = Integer.MAX_VALUE;


    /**
     * TLE
     * @param s
     * @return
     */
    public int minCut(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        boolean[][] dp = partitionArr(s);
        backtracking(s, 0, -1, dp);
        return min;
    }


    private void backtracking(String s, int start, int cut, boolean[][] dp){
        if (start==s.length()){
            min = Math.min(min, cut);
            return;
        }
        if (cut > min){
            return;
        }
        for (int i=start;i<s.length();i++){
            if (dp[start][i]){
                backtracking(s, i+1, cut+1, dp);
            }
        }
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int minCut2(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            dp[i][i] = 0;
        }
        for (int i=1;i<s.length();i++){
            for (int j=0;i+j<s.length();j++){
                if (s.charAt(j) == s.charAt(i+j) && (dp[j+1][i+j-1]==0 || i==1)){
                    dp[j][i+j]=0;
                }else {
                    dp[j][i+j] = Integer.MAX_VALUE;
                    for (int k=0;j+k+1<=i+j;k++){
                        dp[j][i+j]= Math.min(dp[j][j+k]+dp[j+k+1][i+j]+1, dp[j][i+j]);
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    /**
     * Accepted
     * @param s
     * @return
     */
    public int minCut3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int[] cut = new int[s.length()];
        for (int i=0;i<s.length();i++){
            cut[i] = i;
            for (int j=0;j<=i;j++){
                if (s.charAt(j) == s.charAt(i) && (i==j || i-j==1 || dp[j+1][i-1])){
                    dp[j][i] = true;
                    if (j==0){
                        cut[i] = 0;
                    }else {
                        cut[i] = Math.min(cut[j-1]+1, cut[i]);
                    }
                }
            }
        }
        return cut[s.length()-1];
    }



    @Test
    public void test(){
        System.out.println(partition2("abbab"));
        long start = System.currentTimeMillis();
        String s = "apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp";

        System.out.println(minCut2(s));
        System.out.println(minCut3(s));
        System.out.println("cost : " + (System.currentTimeMillis()-start));
        System.out.println(minCut2("abbab"));
        System.out.println(minCut3("abbab"));
        System.out.println(minCut2("leet"));
        System.out.println(minCut3("leet"));

    }

}
