package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/13.
 */
public class _774_Minimize_Max_Distance_to_Gas_Station {


    /**
     * Accepted
     * @param stations
     * @param K
     * @return
     */
    public double minmaxGasDist(int[] stations, int K) {
        if (stations.length<2) return 0;
        long M = 1_000_000;
        long[] stats = new long[stations.length];
        long higher = 0;
        for (int i=0;i<stations.length;i++){
            stats[i] = ((long) stations[i])*M;
            if (i>0)higher = Math.max(higher, stats[i]-stats[i-1]);
        }
        long lower = 0;
        long ans = higher;
        while (lower<=higher){
            long mid = (lower+higher)/2;
            if (valid(stats, K, mid)){
                ans = mid;
                higher = mid-1;
            }else {
                lower = mid +1;
            }
        }
        return (ans*1.0)/(M*1.0);
    }

    private boolean valid(long[] stations, long k, long dist){
        for (int i=1;i<stations.length;i++){
            long gap = stations[i]-stations[i-1];
            k -= gap%dist==0?(gap/dist-1):gap/dist;
            if (k<0) return false;
        }
        return k>=0;
    }

}
