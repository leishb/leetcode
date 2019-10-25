package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/22.
 */
public class _609_Find_Duplicate_File_in_System {

    public List<List<String>> findDuplicate(String[] paths) {
        List<String> files = new ArrayList<>();
        for (String path : paths){
            String[] ss = path.split(" ");
            String dir = ss[0].indexOf("(")==-1?ss[0]+"/":"/";
            for (String s : ss){
                if (s.indexOf("(")!=-1){
                    files.add(dir + s);
                }
            }
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String file : files){
            int left = file.indexOf("(");
            String content = file.substring(left);
            map.putIfAbsent(content, new ArrayList<>());
            map.get(content).add(file.substring(0, left));
        }
        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()){
            if (map.get(key).size() > 1){
                ans.add(map.get(key));
            }
        }
        return ans;
    }
}
