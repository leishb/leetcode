package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2017/11/23.
 */
public class GroupAnagrams {


    /**
     * Accepted
     * @param args
     * @return
     */
    public List<List<String>> groupAnagrams(String[] args){
        Map<String, List<String>> map = new HashMap<>();
        for (int i=0;i<args.length;i++){
            char[] cs = args[i].toCharArray();
            Arrays.sort(cs);
            String s =new String(cs);
            if (map.containsKey(s)){
                map.get(s).add(args[i]);
            }else {
                List<String> list = new ArrayList<>();
                list.add(args[i]);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams2(String[] args){
        Map<String, List<String>> map = new HashMap<>();
        int[] count = new int[26];
        for (String arg : args){
            Arrays.fill(count, 0);
            for (char c : arg.toCharArray()){
                count[c-'a'] +=1;
            }
            StringBuffer sb = new StringBuffer();
            for (int i=0;i<26;i++){//hash gen
                sb.append("#");
                sb.append(count[i]);
            }
            if (!map.containsKey(sb.toString())){
                map.put(sb.toString(), new ArrayList<>());
            }
            map.get(sb.toString()).add(arg);
        }
        return new ArrayList<>(map.values());
    }

}
