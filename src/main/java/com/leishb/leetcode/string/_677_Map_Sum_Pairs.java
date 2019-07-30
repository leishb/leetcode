package com.leishb.leetcode.string;

/**
 * Created by me on 2019/7/30.
 */
public class _677_Map_Sum_Pairs {


    class TrieNode{
        int val;
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
    }

    TrieNode root;

    public _677_Map_Sum_Pairs() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        char[] cs = key.toCharArray();
        int i=0;
        TrieNode cur = root;
        while (i<cs.length){
            if (cur.children[cs[i]-'a']==null){
                cur.children[cs[i]-'a'] = new TrieNode();
            }
            cur = cur.children[cs[i]-'a'];
            i++;
        }
        cur.isWord = true;
        cur.val = val;
    }

    public int sum(TrieNode cur) {
        if (cur==null)return 0;
        int sum = cur.isWord?cur.val:0;
        for (TrieNode node : cur.children){
            if (node!=null){
                sum += sum(node);
            }
        }
        return sum;
    }


    public int sum(String prefix){
        TrieNode cur = root;
        char[]cs = prefix.toCharArray();
        int i=0;
        while (cur!=null && i<cs.length){
            cur = cur.children[cs[i]-'a'];
            i++;
        }
        return sum(cur);
    }


}
