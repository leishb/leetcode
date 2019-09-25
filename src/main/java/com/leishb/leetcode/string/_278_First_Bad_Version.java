package com.leishb.leetcode.string;

import org.junit.Test;

/**
 * Created by me on 2019/9/24.
 */
public class _278_First_Bad_Version {

    public int firstBadVersion(int n) {
        long low = 1;
        long high = n;
        int ans = n;
        while (low<=high){
            long mid = (high-low)/2+low;
            if (mid > Integer.MAX_VALUE){
                high = mid-1;
            }else if (isBadVersion((int) mid)){
                ans = (int) Math.min(ans, mid);
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return ans;
    }


    boolean isBadVersion(int version){
        return false;
    }


    @Test
    public void test(){
        firstBadVersion2(Integer.MAX_VALUE);
    }


    public int firstBadVersion2(int n) {
        int start = 1, end = n;
        int ans = n;
        while (start<=end){
            int mid = (end-start)/2+start;
            if (isBadVersion(mid)){
                ans = Math.min(ans, mid);
                end = mid-1;
            }else {
                start= mid+1;
            }
        }
        return ans;
    }
}
