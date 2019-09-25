package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2018/1/5.
 */
public class WordBreak {


    @Test
    public void test(){
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak2("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak3("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak4("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak5("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
    }


    /**
     * TLE
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> ans = new ArrayList();
        Set<String> set = new HashSet();
        for (String word : wordDict) {
            set.add(word);
        }
        backtracking(s, 0, s.length(), "", ans, set);
        return ans;
    }


    /**
     * Accepted
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList();
        boolean[] dp = new boolean[s.length()+1];
        Set<String> set = new HashSet();
        for(String word : wordDict){
            set.add(word);
        }
        dp[0] = true;
        dp[1] = set.contains(s.substring(0,1));
        for(int i=1;i<s.length();i++){
            boolean flag = false;
            for(int j=i;j>=0; j--){
                if(set.contains(s.substring(j, i+1)) && dp[j]){
                    flag = true;
                    break;
                }
            }
            dp[i+1] = flag;
        }
        if(!dp[s.length()]){
            return ans;
        }
        backtracking(dp, 0, s.length(), s, "", ans, set);
        return ans;
    }


    private void backtracking(boolean[] dp , int start, int end, String s, String ss, List<String> ans, Set<String> set){
        if(start==end){
            ans.add(ss.substring(0, ss.length()-1));
            return;
        }
        for(int i=start;i<=end;i++){
            if(dp[i] && set.contains(s.substring(start, i))){
                ss = ss + s.substring(start, i) + " ";
                backtracking(dp, i, end, s, ss, ans, set);
                ss = ss.substring(0, ss.length()-(i-start+1));
            }
        }
    }


    private void backtracking(String s, int start, int end, String ss, List<String> ans, Set<String> set){
        if (start==end){
            ans.add(ss.substring(0, ss.length()-1));
            return;
        }
        for (int i=start;i<=end;i++){
            if (set.contains(s.substring(start, i))){
                backtracking(s, i, end,  ss + s.substring(start, i) + " ", ans, set);
            }
        }
    }

    /**
     * MLE
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak3(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String word : wordDict){
            set.add(word);
        }
        List<String>[] dp = new List[s.length()+1];
        for (int i=0;i<=s.length();i++){
            dp[i] = new ArrayList<>();
        }
        dp[0].add("");
        for (int i=1;i<=s.length();i++){
            for (int j=i;j>=0;j--){
                String ss = s.substring(j, i);
                if (set.contains(ss)){
                    for (String w : dp[j]){
                        dp[i].add(w + " " + ss);
                    }
                }
            }
        }
        for (String w : dp[s.length()]){
            result.add(w.substring(1, w.length()));
        }
        return result;
    }


    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict){
            set.add(word);
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i=1;i<=s.length();i++){
            for (int j=i-1;j>=0;j--){
                if (dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public List<String> wordBreak5(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict){
            set.add(word);
        }
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i=1;i<=s.length();i++){
            for (int j=i-1;j>=0;j--){
                if (dp[j] && set.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        List<String> result = new ArrayList<>();
        dfs(result, dp, s, "", 0, s.length(), set);
        return result;
    }


    private void dfs(List<String> result, boolean[] dp, String s, String ss,  int start, int end, Set<String> set){
        if (start==end){
            result.add(ss.substring(1, ss.length()));
            return;
        }
        for (int i=start;i<=end;i++){
            String temp = s.substring(start, i);
            if (dp[i] && set.contains(temp)){
                dfs(result, dp, s,  ss+" " + temp, i, end, set);
            }
        }
    }

    public List<String> wordBreak6(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String word : wordDict) {
            set.add(word);
        }
        List<String> result = new ArrayList<>();


        return result;
    }
}
