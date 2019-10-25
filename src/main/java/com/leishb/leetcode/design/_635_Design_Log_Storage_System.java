package com.leishb.leetcode.design;

import java.util.*;

/**
 * Created by me on 2019/10/23.
 */
public class _635_Design_Log_Storage_System {


    TreeMap<String, List<Integer>> logs = new TreeMap<>();
    Map<String, Integer> map = new HashMap<>();
    String min = null;
    String max = null;
    public _635_Design_Log_Storage_System() {
        min = "0000:00:00:00:00:00";
        max = "2019:12:31:23:59:59";
        map.put("Year", 4);
        map.put("Month", 7);
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
    }

    public void put(int id, String timestamp) {
        logs.putIfAbsent(timestamp, new ArrayList<>());
        logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        String start = s.substring(0, map.get(gra)) + min.substring(map.get(gra));
        String end = e.substring(0, map.get(gra)) + max.substring(map.get(gra));
        List<Integer> ans = new ArrayList<>();
        for (String key : logs.subMap(start, true, end, true).keySet()){
            ans.addAll(logs.get(key));
        }
        return ans;
    }
}
