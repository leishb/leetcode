package com.leishb.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/11/22.
 */
public class _963_Minimum_Area_Rectangle_II {


    /**
     * Accepted
     * @param points
     * @return
     */
    public double minAreaFreeRect(int[][] points) {
        Set<String> set = new HashSet<>();
        for (int[] p : points){
            set.add(p[0] + "," + p[1]);
        }
        double ans = Double.MAX_VALUE;
        for (int[] p1 : points){
            for (int[] p2 : points){
                if (p1[0]==p2[0] && p1[1]==p2[1]){
                    continue;
                }
                for (int[] p3 : points){
                    if (dist(p1, p3) + dist(p2, p3) != dist(p1, p2)){
                        continue;
                    }
                    int x = p2[0] - p3[0] + p1[0];
                    int y = p2[1] - p3[1] + p1[1];
                    if (!set.contains(x + "," + y)){
                        continue;
                    }
                    double area = Math.sqrt(dist(p1, p3)) * Math.sqrt(dist(p2, p3));
                    if (Double.compare(area, 0)==0) continue;
                    ans = Math.min(ans, area);
                }
            }
        }
        return Double.compare(ans, Double.MAX_VALUE)==0?0:ans;
    }


    private int dist(int[] p1, int[] p2){
        return (p1[0]-p2[0]) * (p1[0]-p2[0]) + (p1[1]-p2[1]) * (p1[1]-p2[1]);
    }
}
