package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/5/14.
 */
public class RepeatedDNASequences {


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<s.length()-9;i++){
            String sub = s.substring(i,i+10);
            int count = map.getOrDefault(sub, 0);
            map.put(sub, count+1);
        }
        for (String key : map.keySet()){
            if(map.get(key)>1){
                result.add(key);
            }
        }
        return result;
    }
}
