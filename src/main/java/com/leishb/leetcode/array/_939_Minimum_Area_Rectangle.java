package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/16.
 */
public class _939_Minimum_Area_Rectangle {


    /**
     * Accepted
     * @param points
     * @return
     */
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        List<Integer> xList = new ArrayList<>();
        for (int i=0;i<points.length;i++){
            if (!map.containsKey(points[i][0])){
                map.put(points[i][0], new HashSet<>());
            }
            map.get(points[i][0]).add(points[i][1]);
        }
        xList.addAll(map.keySet());
        int minArea = Integer.MAX_VALUE;
        for (int i=0;i<xList.size();i++){
            for (int j=i+1;j<xList.size();j++){
                List<Integer> list = new ArrayList<>();
                int x1 = xList.get(i);
                int x2 = xList.get(j);
                for (int y : map.get(x1)){
                    if (map.get(x2).contains(y)){
                        list.add(y);
                    }
                }
                if (list.size()<=1){
                    continue;
                }
                Collections.sort(list);
                int minY = list.get(list.size()-1)-list.get(0);
                for (int k=1;k<list.size();k++){
                    minY = Math.min(list.get(k)-list.get(k-1), minY);
                }
                minArea = Math.min(minArea, Math.abs(x2-x1)*minY);
            }
        }
        return minArea==Integer.MAX_VALUE?0:minArea;
    }


    @Test
    public void test(){
        Assert.assertTrue(minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}})==4);
        Assert.assertTrue(minAreaRect(new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}})==2);
    }
}
