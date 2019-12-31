package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/12/4.
 */
public class _425_Word_Squares {


    /**
     * Accepted
     * @param words
     * @return
     */
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        TrieNode root = new TrieNode('/');
        for (String word : words){
            addWord(root, word);
        }
        backtracking(new ArrayList<>(), ans, root, root, 0);
        return ans;
    }



    private void backtracking(List<String> list,  List<List<String>> ans, TrieNode cur , TrieNode root ,  int col){
        if (cur.word!=null){
            List<String> copy = new ArrayList<>(list);
            copy.add(cur.word);
            if (copy.size()==cur.word.length()){
                ans.add(copy);
            }else {
                backtracking(copy, ans, root, root,  0);
            }
            return;
        }
        if (col >= list.size()){
            for (TrieNode child : cur.children){
                if (child==null) continue;
                backtracking(list, ans, child,root, col+1);
            }
        }else if (cur.children[list.get(col).charAt(list.size())-'a'] != null){//next
            backtracking(list, ans,  cur.children[list.get(col).charAt(list.size())-'a'] ,root, col+1);
        }
    }



    private void addWord(TrieNode root, String word){
        TrieNode cur = root;
        for (int i=0;i<word.length();i++){
            int j = word.charAt(i)-'a';
            if (cur.children[j] == null){
                cur.children[j] = new TrieNode(word.charAt(i));
            }
            cur = cur.children[j];
        }
        cur.word = word;
    }

    class TrieNode {
        char data;
        String word = null;
        TrieNode[] children = new TrieNode[26];
        TrieNode (char data){
            this.data = data;
        }
    }


    /**
     * Accepted
     * @param words
     * @return
     */
    public List<List<String>> wordSquares2(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words){
            for (int i=0;i<word.length();i++){
                String prefix = word.substring(0, i);
                map.putIfAbsent(prefix, new ArrayList<>());
                map.get(prefix).add(word);
            }
        }
        backtracking(ans , new ArrayList<>(), "", map);
        return ans;
    }


    private void backtracking(List<List<String>> ans, List<String> list, String prefix, Map<String, List<String>> map){
        if (list.size() >0 && list.size()==list.get(0).length()){
            ans.add(list);
            return;
        }
        for (String next : map.getOrDefault(prefix, new ArrayList<>())){
            List<String> copy = new ArrayList<>(list);
            copy.add(next);
            int len = copy.size();
            StringBuffer sb = new StringBuffer();
            if (copy.size() < next.length()){
                for (int i=0;i<len;i++){
                    sb.append(copy.get(i).charAt(copy.size()));
                }
            }
            backtracking(ans, copy, sb.toString(), map);
        }
    }


    @Test
    public void test(){
        wordSquares2(new String[]{"area","lead","wall","lady","ball"});
    }
}
