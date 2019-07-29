package com.leishb.leetcode.string;

import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2019/7/24.
 */
public class _648_Replace_Words {


    /**
     * Accepted
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        Collections.sort(dict, (s1, s2)->Integer.compare(s1.length(), s2.length()));
        String[] ss = sentence.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<ss.length;i++){
            for (int j=0;j<dict.size();j++){
                if (ss[i].startsWith(dict.get(j))){
                    ss[i] = dict.get(j);
                    break;
                }
            }
            sb.append(ss[i]).append(" ");
        }
        return sb.substring(0,sb.length()-1);
    }

    class TrieNode {
        char data;
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        TrieNode(char data){
            this.data = data;
        }
    }


    /**
     * Accepted
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords2(List<String> dict, String sentence) {
        TrieNode root = new TrieNode('/');
        for (String s : dict){
            insertNode(root, s);
        }
        String[] ss = sentence.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<ss.length;i++){
            String s = search(root, ss[i]);
            sb.append(s).append(" ");
        }
        return sb.substring(0,sb.length()-1);
    }


    private void insertNode(TrieNode root, String word){
        TrieNode cur = root;
        int i=0;
        while (i<word.length()){
            char c = word.charAt(i);
            if (cur.children[c-'a']==null){
                cur.children[c-'a']=new TrieNode(c);
            }
            i++;
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }


    private String search(TrieNode root, String s){
        int i=0;
        TrieNode cur = root;
        while (i<s.length() && !cur.isWord){
            char c = s.charAt(i);
            if (cur.children[c-'a']==null){
                return s;
            }
            i++;
            cur = cur.children[c-'a'];
        }
        return s.substring(0, i);
    }
}
