package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/25.
 */
public class MaximumSubArray {


    @Test
    public void test(){
        Assert.assertTrue(maximumSubArray(new int[]{-2,1,-3,4,-1,2,1,-2})==6);
        Assert.assertTrue(maximumSubArray(new int[]{-2,-1,-4})==-1);


        Assert.assertTrue(maximumSubArrayWithDp(new int[]{-2,1,-3,4,-1,2,1,-2})==6);
        Assert.assertTrue(maximumSubArrayWithDp(new int[]{-2,-1,-4})==-1);


        Assert.assertArrayEquals(maximumSubArrayWithDp2(new int[]{-2,-1,-4}), new int[]{1,1});
        Assert.assertArrayEquals(maximumSubArrayWithDp2(new int[]{-2,1,-3,4,-1,2,1,-2}), new int[]{3,6});

        Assert.assertArrayEquals(maximumSubArray2(new int[]{-2,-1,-4}), new int[]{1,1});
        Assert.assertArrayEquals(maximumSubArray2(new int[]{-2,1,-3,4,-1,2,1,-2}), new int[]{3,6});
    }


    /**
     * Accepted
     * 逐个遍历数组元素并将其加入累加和（sum），如果累加和大于记录的最大和（maxSum），更新maxSum，
     * 如果sum小于等于0，表明之前的子数组不会提高后续数组的相加和，抛弃之前的子数组，将sum重新置为0
     * @param nums
     * @return
     */
    public int maximumSubArray(int[] nums){
        int result = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums){
            sum += num;
            result = Math.max(result, sum);
            if (sum <0){
                sum =0;
            }
        }
        return result;
    }

    public int maximumSubArrayWithDp(int[] nums){
        if (nums.length<1){
            return 0;
        }
        int[] dp = new int[nums.length];//保存以i个元素结尾的子数组的最大和
        dp[0] = nums[0];
        int result = dp[0];
        for (int i=1;i<nums.length;i++){
            dp[i] = nums[i] + (dp[i-1] <0 ? 0 : dp[i-1]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }


    public int[] maximumSubArrayWithDp2(int[] nums){
        if (nums.length<1){
            return new int[]{-1,-1};
        }
        int[] dp = new int[nums.length];//保存以i个元素结尾的子数组的最大和
        dp[0] = nums[0];
        int max = dp[0];
        int l = 0; //临时的
        int r = 0;//临时的
        int maxL = 0;
        int maxR = nums.length-1;
        for (int i=1;i<nums.length;i++){
            if (dp[i-1] <0){
                l = i;
                r = i;
                dp[i] = nums[i];
            }else {
                dp[i] = dp[i-1] + nums[i];
                r = i;
            }
            if (max < dp[i]){
                max = dp[i];
                r = i;
                maxL = l;
                maxR = r;
            }
        }
        return new int[]{maxL, maxR};
    }


    public int[] maximumSubArray2(int[] nums){
        int result = Integer.MIN_VALUE;
        int sum = 0;
        int l = 0; //临时的
        int r = 0;//临时的
        int maxL = 0;
        int maxR = nums.length-1;
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            if (result < sum){
                r = i;
                maxL = l;
                maxR = r;
                result = sum;
            }
            if (sum <0){
                l = i +1;
                r = i +1;
                sum = 0;
            }
        }
        return new int[]{maxL, maxR};
    }
}
