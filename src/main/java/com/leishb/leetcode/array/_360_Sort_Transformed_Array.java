package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/10/28.
 */
public class _360_Sort_Transformed_Array {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        double temp = -b/(2*a*1.0);
        int[] arr = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            if (a>=0)arr[i] = a*nums[i]*nums[i]+b*nums[i]+c;
            else arr[i] = -a*nums[i]*nums[i]-b*nums[i]-c;
        }
        int i=0;
        for (;i<nums.length;i++){
            if (nums[i]>=temp) break;
        }
        int j=i;
        i-=1;
        int k = 0;
        while (i>=0 || j<nums.length){
            if (i<0){
                res[k++] = arr[j++];
            }else if (j>=nums.length){
                res[k++] = arr[i--];
            }else if (arr[i]<=arr[j]){
                res[k++] = arr[i--];
            }else {
                res[k++] = arr[j++];
            }
        }
        if (a<0){
            i=0;
            j=res.length-1;
            while (i<j){
                int t = res[i];
                res[i] = -res[j];
                res[j] = -t;
                i++;
                j--;
            }
            if (i==j) res[i] *=-1;
        }
        return res;
    }


    public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int start = 0;
        int end = nums.length-1;
        int i = a>=0 ? nums.length-1 : 0;
        while (start<=end){
            int startNum = getNum(nums[start] ,a, b, c);
            int endNum = getNum(nums[end] ,a, b, c);
            if (a>=0){
                if (startNum <= endNum){
                    res[i--] = endNum;
                    end--;
                }else {
                    res[i--] = startNum;
                    start++;
                }
            }else {
                if (startNum <= endNum){
                    res[i++] = startNum;
                    start++;
                }else {
                    res[i++] = endNum;
                    end--;
                }
            }
        }
        return res;
    }


    private int getNum(int num, int a, int b, int c){
        return num * num * a + num * b + c;
    }
    @Test
    public void test(){
        sortTransformedArray(new int[]{-4,-2,2,4}, 1,3,5);
    }
}
