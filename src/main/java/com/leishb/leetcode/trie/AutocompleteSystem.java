package com.leishb.leetcode.trie;

import java.util.*;

/**
 * Created by me on 2019/10/11.
 */
public class AutocompleteSystem {

    class TrieNode {
        TrieNode[] children = new TrieNode[27];
        Map<String, Integer> counts = new HashMap<>();
    }

    TrieNode root = new TrieNode();

    TrieNode curNode = root;

    StringBuffer sb = new StringBuffer();

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            add(sentence, times[i]);
        }
    }


    private void add(String sentence, int freq){
        TrieNode cur = root;
        for (char c : sentence.toCharArray()){
            int index = c!=' '?c-'a':26;
            if (cur.children[index]==null){
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
            cur.counts.put(sentence, cur.counts.getOrDefault(sentence, 0)+freq);
        }
    }


    public List<String> input(char c) {
        if (c == '#') {
            add(sb.toString(), 1);
            sb = new StringBuffer();
            curNode = root;
            return new ArrayList<>();
        }
        sb.append(c);
        int i = c==' '?26:c-'a';
        curNode = curNode.children[i];
        if (curNode==null){
            curNode = new TrieNode();
            return new ArrayList<>();
        }
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((e1, e2)->e1.getValue()==e2.getValue()?e1.getKey().compareTo(e2.getKey())
        :e2.getValue()-e1.getValue());
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : curNode.counts.entrySet()){
            queue.offer(entry);
        }
        for (int j=0;j<3 && !queue.isEmpty();j++){
            ans.add(queue.poll().getKey());
        }
        return ans;
    }
}
