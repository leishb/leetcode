package com.leishb.leetcode.trie;

import java.util.*;

/**
 * Created by me on 2019/10/11.
 */
public class _642_Design_Search_Autocomplete_System {

    class TrieNode {
        TrieNode[] children = new TrieNode[27];
        String sentence = null;
        int freq = 0;
    }

    TrieNode root = new TrieNode();

    TrieNode curNode = root;

    StringBuffer sb = new StringBuffer();

    public _642_Design_Search_Autocomplete_System(String[] sentences, int[] times) {
        for (int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            TrieNode cur = root;
            for (char c : sentence.toCharArray()){
                int index = c!=' '?c-'a':26;
                if (cur.children[index]==null){
                    cur.children[index] = new TrieNode();
                }
                cur = cur.children[index];
            }
            cur.sentence = sentence;
            cur.freq = times[i];
        }
    }

    public List<String> input(char c) {
        if (c=='#'){
            curNode.sentence = sb.toString();
            curNode.freq += 1;
            sb = new StringBuffer();
            curNode = root;
            return new ArrayList<>();
        }
        sb.append(c);
        int i = c!=' '?c-'a':26;
        if (curNode.children[i]==null){
            curNode.children[i] = new TrieNode();
            curNode = curNode.children[i];
            return new ArrayList<>();
        }
        curNode = curNode.children[i];
        Map<String, Integer> map = new HashMap<>();
        findSentences(curNode, map);
        List<String > ans = new ArrayList<>(map.keySet());
        Collections.sort(ans, (s1, s2)->map.get(s1)==map.get(s2)?s1.compareTo(s2):map.get(s2)-map.get(s1));
        return ans;
    }


    private void findSentences(TrieNode curNode, Map<String, Integer> map){
        if (curNode.sentence!=null){
            map.put(curNode.sentence, curNode.freq);
            if (map.size()>3){
                String leastKey = curNode.sentence;
                for (String k : map.keySet()){
                    if (map.get(k) < map.get(leastKey) || (map.get(k)==map.get(leastKey) && k.compareTo(leastKey) >0)){
                        leastKey = k;
                    }
                }
                map.remove(leastKey);
            }
        }
        for (TrieNode child : curNode.children){
            if (child!=null){
                findSentences(child, map);
            }
        }
    }
}
