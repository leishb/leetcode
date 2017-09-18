package com.leishb.leetcode.math;

/**
 * Created by leishengbao on 2017/9/18.
 */
public class PlusOne {


    public static void main(String[] args){
        PlusOne plusOne = new PlusOne();
        printInt(plusOne.plusOne(new int[]{1,2,3,4,5}));
        printInt(plusOne.plusOne(new int[]{1,2,3,4,9}));
        printInt(plusOne.plusOne(new int[]{9,9,9,9,9}));
        printInt(plusOne.plusOne(new int[]{0}));
    }

    private static void printInt(int[] nums){
        StringBuffer sb = new StringBuffer();
        for (int i : nums){
            sb.append(i).append(",");
        }
        System.out.println(sb);
    }


    public int[] plusOne(int[] nums){
        nums[nums.length-1] = nums[nums.length-1]+1;
        for (int i = nums.length-1; i>0;i--){
            if (nums[i]>=10){
                nums[i-1]+=nums[i]/10;
                nums[i]=nums[i]%10;
            }
        }
        if (nums[0]<10){
            return nums;
        }
        int[] result  = new int[nums.length+1];
        result[0]=nums[0]/10;
        result[1]=nums[0]%10;
        for (int i = 1;i< nums.length;i++){
            result[i+1]=nums[i];
        }
        return result;
    }


    public int[] plusOne2(int[] nums){
        for (int i = nums.length-1;i>=0;i--){
            if (nums[i]<9){
                nums[i]+=1;
                return nums;
            }
            nums[i]=0;
        }

        int[] result = new int[nums.length+1];
        result[0]=1;
        return result;
    }
}
