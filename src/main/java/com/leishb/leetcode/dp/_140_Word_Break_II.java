package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/22.
 */
public class _140_Word_Break_II {

    /**
     * TLE
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (String w : wordDict){
            addWord(root, w);
        }
        search(ans, s.toCharArray(), 0, "", root);
        return ans;
    }


    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        String word = null;
    }


    private void addWord(TrieNode root, String word){
        TrieNode cur = root;
        for (int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                cur.children[c-'a'] = new TrieNode();
            }
            cur = cur.children[c-'a'];
        }
        cur.isEnd = true;
        cur.word = word;
    }


    private void search(List<String> ans, char[] cs, int index, String s, TrieNode root){
        TrieNode cur = root;
        for (int i=index;i<cs.length;i++){
            cur = cur.children[cs[i]-'a'];
            if (cur==null) return;
            if (cur.isEnd){
                if (i==cs.length-1){
                    ans.add((s.length()==0?"":s+" ")+cur.word);
                    return;
                }
                search(ans, cs, i+1, (s.length()==0?"":s+" ")+cur.word, root);
            }
        }
    }


    /**
     * Accepted
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }



    private List<String> dfs(String s, List<String> dict, Map<String, List<String>> memo){
        if (memo.containsKey(s)) return memo.get(s);
        if ("".equals(s)){
            return new ArrayList<>(Arrays.asList(""));
        }
        List<String> list = new ArrayList<>();
        for (String w : dict){
            if (s.startsWith(w)){
                List<String> ll = dfs(s.substring(w.length()), dict, memo);
                for (String ss : ll){
                    list.add(w+(ss.length()!=0?" "+ss:ss));
                }
            }
        }
        memo.put(s, list);
        return list;
    }


    public List<String> wordBreak3(String s, List<String> wordDict) {
        List<String>[] dp = new List[s.length()+1];
        Set<String> dict = new HashSet<>(wordDict);
        for (int i=0;i<dp.length;i++){
            dp[i] = new ArrayList<>();
        }
        dp[0].add("");
        for (int i=1;i<dp.length;i++){
            for (int j=i-1;j>=0;j--){
                String sub = s.substring(j, i);
                if (dict.contains(sub)){
                    for (String w : dp[j]){
                        dp[i].add((w.isEmpty()?"":w+" ")+ sub);
                    }
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void test(){
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak2("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
        System.out.println(wordBreak3("catsanddog", Arrays.asList("cat","cats","and","sand","dog")));
    }
}
