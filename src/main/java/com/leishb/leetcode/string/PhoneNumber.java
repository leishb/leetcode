package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2017/11/22.
 */
@BackTracking
public class PhoneNumber {

    @Test
    public void test(){
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations2("23"));
        System.out.println(letterCombinations("234"));
        System.out.println(letterCombinations2("234"));
    }



    public static final String[] KEYS = new String[]{"","",  "abc","def", "ghi", "jkl","mno", "pqrs", "tuv","wxyz"};


    public List<String> letterCombinations2(String digits) {
        List<String> ans = new ArrayList<>();
        helper(digits, 0, "", ans);
        return ans;
    }


    private void helper(String digits, int start, String s, List<String> ans){
        if (s.length()==digits.length()){
            ans.add(s);
            return;
        }
        char c = digits.charAt(start);
        for (int i=0;i<KEYS[c-'0'].length();i++){
            helper(digits, start+1, s + KEYS[c-'0'].charAt(i), ans);
        }
    }



    /**
     * Accepted
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<List<Character>> data = new ArrayList<>();
        data.add(new ArrayList<>());
        data.add(Arrays.asList('a','b', 'c'));
        data.add(Arrays.asList('d','e', 'f'));
        data.add(Arrays.asList('g','h', 'i'));
        data.add(Arrays.asList('j','k', 'l'));
        data.add(Arrays.asList('m','n', 'o'));
        data.add(Arrays.asList('p','q', 'r', 's'));
        data.add(Arrays.asList('t','u', 'v'));
        data.add(Arrays.asList('w','x', 'y','z'));
        List<List<Character>> list = new ArrayList<>();

        for (int i=0;i<digits.length();i++){
            list.add(data.get(digits.charAt(i)-'0'-1));
        }
        List<String> ret = new ArrayList<>();
        if (digits.length()==0){
            return ret;
        }
        helper(0, list, "", ret);
        return ret;
    }

    private void helper( int start, List<List<Character>> list, String s ,  List<String> ret){
        if (s.length()==list.size()){
            ret.add(s);
            return;
        }
        for (int i=0;i<list.get(start).size();i++){
            s = s + list.get(start).get(i);
            helper(start+1, list, s, ret);
            s = s.substring(0, s.length()-1);
        }
    }
}
