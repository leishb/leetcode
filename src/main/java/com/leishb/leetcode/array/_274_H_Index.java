package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/7/19.
 */
public class _274_H_Index {


    /**
     * Accepted
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i=0;i<citations.length;i++){
            h = Math.max(h, Math.min(citations[i], citations.length-i));
        }
        return h;
    }

    /**
     * Accepted
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for (int i=0;i<n;i++){
            if (citations[i]>=n){
                buckets[n]++;
            }else {
                buckets[citations[i]]++;
            }
        }
        int count = 0;
        for (int i=n;i>=0;i--){
            count+=buckets[i];
            if (count>=i){
                return i;
            }
        }
        return 0;
    }
}
