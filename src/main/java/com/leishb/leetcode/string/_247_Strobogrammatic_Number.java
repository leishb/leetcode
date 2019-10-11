package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/8.
 */
public class _247_Strobogrammatic_Number {

    public List<String> findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("0", "0");
        map.put("1", "1");
        map.put("6", "9");
        map.put("8", "8");
        map.put("9", "6");
        backtracking(ans, map, "", n);
        return ans;
    }



    private void backtracking(List<String> ans, Map<String, String> map, String s, int n){
        if (s.length() == n){
            ans .add(s);
            return;
        }
        if (s.length()==n-1){
            int len = s.length();
            ans.add(s.substring(0, len/2)+"0"+s.substring(len/2));
            ans.add(s.substring(0, len/2)+"1"+s.substring(len/2));
            ans.add(s.substring(0, len/2)+"8"+s.substring(len/2));
            return;
        }
        for (String key : map.keySet()){
            int len = s.length();
            if (len==0 && "0".equals(key)){
                continue;
            }
            backtracking(ans, map, s.substring(0, len/2)+ key + map.get(key) + s.substring(len/2), n);
        }
    }

}
