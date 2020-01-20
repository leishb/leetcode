package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2020/1/14.
 */
public class _853_Car_Fleet {


    /**
     * Accepted
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[] parents = new int[n];
        for (int i=0;i<n;i++){
            parents[i] = i;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<n;i++){
            map.put(position[i], speed[i]);
        }
        Arrays.sort(position);
        for (int i=n-1;i>=0;i--){
            for (int j=i+1;j<n;j++){
                if (canHit(position[i], position[j], map.get(position[i]), map.get(position[j]), target)){
                    union(i, j, parents);
                    break;
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<n;i++){
            set.add(find(i, parents));
        }
        return set.size();
    }

    private boolean canHit(int p1, int p2, int s1, int s2, int target){
        return (double)(target-p1)/(double)s1 <= (double)(target-p2)/(double)s2;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        return parents[x] = find(parents[x], parents);
    }

    private void union(int x, int y, int[] parents){
        int rx = find(x, parents);
        int ry = find(y, parents);
        if (rx!=ry){
            parents[rx] = ry;
        }
    }


    /**
     * Accepted
     * @param target
     * @param position
     * @param speed
     * @return
     */
    public int carFleet2(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = new double[n][2];
        for (int i=0;i<n;i++){
            cars[i] = new double[]{position[i], (double)(target-position[i])/(double)speed[i]};
        }
        Arrays.sort(cars, (a, b)->Double.compare(a[0], b[0]));
        int res = 0;
        double cur = 0;
        for (int i=n-1;i>=0;i--){
            if (cars[i][1] > cur){
                res++;
                cur = cars[i][1];
            }
        }
        return res;
    }
}
