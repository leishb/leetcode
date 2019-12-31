package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/27.
 */
public class _11_Container_With_Most_Water {


    /**
     * Accepted
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i=0, j = height.length-1, ans = 0;
        while (i<j){
            ans = Math.max(ans, (j-i)*Math.min(height[i], height[j]));
            if (height[i]<=height[j]){
                i++;
            }else {
                j--;
            }
        }
        return ans;
    }
}
