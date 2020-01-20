package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/15.
 */
public class _1095_Find_in_Mountain_Array {

    interface MountainArray {
        public int get(int index);

        public int length();
    }


    /**
     * Accepted
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        return find(0, len-1, target, mountainArr);
    }


    private int find(int start, int end , int target, MountainArray mountainArr){
        if (start==end) return mountainArr.get(start)==target?start:-1;
        if (start>end) return -1;
        int mid = (start+end)/2;
        int val = mountainArr.get(mid);
        int r = mountainArr.get(mid+1);
        if (val < r){
            if (val==target)return mid;
            if (val > target){
                int left = find(start, mid-1, target, mountainArr);
                if (left!=-1) return left;
                return find(mid+1, end, target, mountainArr);
            }else {
                return find(mid+1, end, target, mountainArr);
            }
        }else {
            if (val>=target){
                int left = find(start, mid-1, target, mountainArr);
                if (left!=-1) return left;
                if (val==target) return mid;
                return find(mid+1, end, target, mountainArr);
            }else {
                return find(start, mid-1, target, mountainArr);
            }
        }
    }


    public int findInMountainArray2(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int peak = findPeak(len, mountainArr);
        if (mountainArr.get(peak)==target) return peak;
        int i = 0, j = peak-1;
        while (i<=j){
            int m = (i+j)/2;
            int val = mountainArr.get(m);
            if (val==target){
                return m;
            }
            if (val < target){
                i = m+1;
            }else {
                j = m-1;
            }
        }
        i = peak+1;
        j = len-1;
        while (i<=j){
            int m = (i+j)/2;
            int val = mountainArr.get(m);
            if (val==target){
                return m;
            }
            if (val > target){
                i = m+1;
            }else {
                j = m-1;
            }
        }
        return -1;
    }

    private int findPeak(int len , MountainArray mountainArr){
        int l = 0, r = len-1;
        while (l<=r){
            int m = (l+r)/2;
            int val = mountainArr.get(m);
            if (val < mountainArr.get(m+1)){
                l = m+1;
            }else if (val < mountainArr.get(m-1)){
                r = m-1;
            }else {
                return m;
            }
        }
        return 0;
    }

}
