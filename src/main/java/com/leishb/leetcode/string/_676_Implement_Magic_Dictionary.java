package com.leishb.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/7/29.
 */
public class _676_Implement_Magic_Dictionary {


    Set<String> set;

    /** Initialize your data structure here. */
    public _676_Implement_Magic_Dictionary() {
        set = new HashSet<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        set.clear();
        for(String word : dict){
            set.add(word);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        char[] cs = word.toCharArray();
        for (int i=0;i<cs.length;i++){
            char c = cs[i];
            for(int j=0;j<26;j++){
                if (j==c-'a'){
                    continue;
                }
                cs[i]=(char)('a'+j);
                if (set.contains(new String(cs))){
                    return true;
                }
            }
            cs[i] = c;
        }
        return false;
    }
}