package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/10/24.
 */
public class SubArraySum {


    @Test
    public void test(){
        Assert.assertTrue(subArraySumOfK(new int[]{1,1,1}, 2)==2);
        Assert.assertTrue(subArraySumOfK(new int[]{1,2,3}, 6)==1);
        Assert.assertTrue(subArraySumOfK(new int[]{1}, 1)==1);
        Assert.assertTrue(subArraySumOfK(new int[]{1}, 2)==0);
        Assert.assertTrue(subArraySumOfK(new int[]{0,0,0,0}, 0)==10);


        Assert.assertTrue(subArraySum(new int[]{1,1,1}, 2)==2);
        Assert.assertTrue(subArraySum(new int[]{1,2,3}, 6)==1);
        Assert.assertTrue(subArraySum(new int[]{1}, 1)==1);
        Assert.assertTrue(subArraySum(new int[]{1}, 2)==0);
        Assert.assertTrue(subArraySum(new int[]{0,0,0,0}, 0)==10);
    }

    public int subarraySum(int[] nums, int k)
    {
        int[] sums = new int[nums.length + 1];

        for (int i = 1; i < sums.length; i++)
        {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        return sub(sums, new int[sums.length], 0, sums.length - 1, k);
    }

    private int sub(int[] sums, int[] aux, int start, int end, int k)
    {
        if (start >= end)
            return 0;

        int mid = start + (end - start) / 2;

        int res = sub(sums, aux, start, mid, k) + sub(sums, aux, mid + 1, end, k);

        res += merge(sums, aux, start, mid, end, k);

        return res;
    }

    private int merge(int[] sums, int[] aux, int start, int mid, int end, int k)
    {
        for (int i = start; i <= end; i++)
        {
            aux[i] = sums[i];
        }
        int count = 0, i = start, j = mid + 1, t = start, p = mid + 1;

        while (i <= mid)
        {
            while (p <= end && aux[p] - k < aux[i])
                p++;

            for (int q = 0; q + p <= end; q++)
            {
                if (aux[p + q] - k > aux[i])
                    break;

                else
                    count++;
            }
            while (j <= end && aux[i] > aux[j])
                sums[t++] = aux[j++];

            sums[t++] = aux[i++];
        }

        while (j <= end)
            sums[t++] = aux[j++];

        return count;
    }


    public int subArraySumOfK(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        map.put(0,1);
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            count += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum-k, 0)+1);
        }
        return count;
    }


    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int subArraySum(int[] nums, int k){
        int count = 0;
        for (int i=0;i<nums.length;i++){
            int sum = 0;//保存以i 开头的子数组的和
            for (int j=i;j<nums.length;j++){
                sum += nums[j];
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }
}
