package com.leishb.leetcode.string;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/12/18.
 */
public class _1178_Number_of_Valid_Words_for_Each_Puzzle {


    /**
     * Accepted
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Trie root = new Trie();
        for (String word : words){
            char[] cs = word.toCharArray();
            Arrays.sort(cs);
            StringBuffer sb = new StringBuffer();
            sb.append(cs[0]);
            for (int i=1;i<cs.length;i++){
                if (cs[i]!=cs[i-1])sb.append(cs[i]);
            }
            addWord(sb.toString(), root);
        }
        List<Integer> list = new ArrayList<>();
        for (String puzzle : puzzles){
            list.add(search(puzzle, root,  'a'));
        }
        return list;
    }

    private int search(String puzzle, Trie cur, char start){
        int count = 0;
        if (cur.word!=null && cur.word.indexOf(puzzle.charAt(0))!=-1){
            count+=cur.count;
        }
        for (char c = start; c<='z';c++){
            if (cur.children[c-'a']!=null && puzzle.indexOf(c)!=-1){
                count+=search(puzzle, cur.children[c-'a'], (char)(c+1));
            }
        }
        return count;
    }

    private void addWord(String word, Trie root){
        Trie cur = root;
        for (int i=0;i<word.length();i++){
            int j = word.charAt(i)-'a';
            if (cur.children[j]==null){
                cur.children[j] = new Trie();
            }
            cur = cur.children[j];
        }
        cur.word = word;
        cur.count++;
    }

    class Trie{
        Trie[] children = new Trie[26];
        String word = null;
        int count = 0;
    }


    /**
     * Accepted
     * @param words
     * @param puzzles
     * @return
     */
    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words){
            int k = toInt(word);
            map.put(k, map.getOrDefault(k, 0)+1);
        }
        List<Integer> list = new ArrayList<>();
        for (String p : puzzles){
            int mask = toInt(p);
            int first = 1<<(p.charAt(0)-'a');
            int sub = mask;
            int count = 0;
            while (true){
                if ((sub & first) == first && map.containsKey(sub)){
                    count += map.get(sub);
                }
                if (sub==0) break;
                sub = (sub-1) & mask;
            }
            list.add(count);
        }
        return list;
    }

    public int toInt(String s){
        int mask = 0;
        for (char c : s.toCharArray()){
            mask|=(1<<(c-'a'));
        }
        return mask;
    }


    public List<Integer> findNumOfValidWords3(String[] words, String[] puzzles) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            char[] cs = word.toCharArray();
            Arrays.sort(cs);
            StringBuffer sb = new StringBuffer();
            sb.append(cs[0]);
            for (int i = 1; i < cs.length; i++) {
                if (cs[i] != cs[i - 1]) sb.append(cs[i]);
            }
            String s = sb.toString();
            map.put(s, map.getOrDefault(s, 0)+1);
        }
        List<Integer> list = new ArrayList<>();
        for (String p : puzzles){
            int count = 0;
            List<String> subList = new ArrayList<>();
            char[] cs = p.toCharArray();
            Arrays.sort(cs);
            backtracking(new String(cs), "", 0, subList);
            for (String s : subList){
                if (s.indexOf(p.charAt(0))!=-1){
                    count+=map.getOrDefault(s, 0);
                }
            }
            list.add(count);
        }
        return list;
    }


    private void backtracking(String s, String sub,  int pos, List<String> list){
        if (sub.length()!=0) list.add(sub);
        for (int i=pos;i<s.length();i++){
            backtracking(s, sub+s.charAt(i), i+1, list);
        }
    }

    @Test
    public void test(){
        System.out.println(findNumOfValidWords3(new String[]{"aaaa","asas","able","ability","actt","actor","access"},
                new String[]{"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"}));
        System.out.println(findNumOfValidWords3(new String[]{"af", "ac"}, new String[]{"abcdef"}));
    }
}
