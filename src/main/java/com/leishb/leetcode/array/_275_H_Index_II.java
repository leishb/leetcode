package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;

/**
 * Created by me on 2019/7/19.
 */
@BinarySearch
public class _275_H_Index_II {


    /**
     * Accepted
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if (citations.length==0) return 0;
        if (citations.length==1) return citations[0]<=1?citations[0]:1;
        int low = 0;
        int high = citations.length;
        int h = 0;
        while (low<=high){
            int mid = (low+high)/2;
            if (mid==0 || citations[citations.length-mid]>=mid){
                h = Math.max(h , mid);
                low=mid+1;
            }else {
                high=mid-1;
            }
        }
        return h;
    }
}
