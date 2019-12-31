package com.leishb.leetcode.string;

import java.util.*;

/**
 * Created by me on 2019/12/5.
 */
public class _1257_Smallest_Common_Region {



    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parents = new HashMap<>();
        for (List<String> list : regions){
            String parent = list.get(0);
            for (int i=1;i<list.size();i++){
                parents.put(list.get(i), parent);
            }
        }
        Set<String> rp1 = new HashSet<>();
        while (region1!=null){
            rp1.add(region1);
            region1 = parents.get(region1);
        }
        while (region2!=null){
            if (rp1.contains(region2)){
                return region2;
            }
            region2 = parents.get(region2);
        }
        return "";
    }
}
