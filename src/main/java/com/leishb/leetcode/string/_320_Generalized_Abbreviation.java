package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/11/7.
 */
public class _320_Generalized_Abbreviation {



    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtrack(res, word, "", 0, 0);
        return new ArrayList<>(res);
    }



    private void backtracking(Set<String> res, String word, String s, boolean lastNum){
        if (word.length()==0){
            res.add(s);
            return;
        }
        for (int i=1;i<=word.length();i++){
            backtracking(res, word.substring(i), s + word.substring(0, i), false);
            if (!lastNum){
                backtracking(res, word.substring(i), s + i, true);
            }
        }
    }


    private void backtrack(List<String> ret, String word, String cur, int pos,  int count){
        if (pos==word.length()){
            if (count > 0) cur += count;
            ret.add(cur);
            return;
        }
        backtrack(ret, word, cur, pos+1, count+1);
        backtrack(ret, word, cur + (count>0?count:"") + word.charAt(pos), pos+1, 0);
    }


    @Test
    public void test(){
        System.out.println(generateAbbreviations("ab"));
    }
}
