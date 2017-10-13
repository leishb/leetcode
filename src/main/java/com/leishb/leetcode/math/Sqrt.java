package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/18.
 */
@BinarySearch
public class Sqrt {


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(17));
        System.out.println(sqrt.sqrt(18));
        System.out.println(sqrt.sqrt(19));
        System.out.println(sqrt.sqrt(12));
        System.out.println(sqrt.sqrt(26));
        System.out.println(sqrt.sqrt(4));
        System.out.println(sqrt.sqrt(225));
        System.out.println(sqrt.sqrt(1024));
        System.out.println(sqrt.sqrt(1025));
        System.out.println(sqrt.sqrt(1888));

        System.out.println(sqrt.sqrt(2147395599));
    }


    @Test
    public void test(){
        Assert.assertTrue(sqrt2(5)==2);
        Assert.assertTrue(sqrt2(4)==2);
        Assert.assertTrue(sqrt2(8)==2);
        Assert.assertTrue(sqrt2(12)==3);
        Assert.assertTrue(sqrt2(13)==3);
    }

    public int sqrt(int x){
       if (x==0||x==1){
           return x;
       }
        if (x < 0) {
            return 0;
        }
        int left = 0;
        int right = x;
        for (int i = left;i<=right;i++){
            //attention 不是完全平方数的情况
            if (left+1==right){
                return left;
            }
            int half = (left+right)/2;
            //防止溢出，不使用乘法
            if (x/half == half){
                return half;
            }
            if (half > x/half){
                right = half;
            }
            if (half < x/half){
                left = half;
            }
        }
        return 0;
    }

    /**
     * Accepted
     * @param x
     * @return
     */
    public int sqrt2(int x){
        if (x<=1){
            return x;
        }
        int left =0;
        int right = x;
        while (left<=right){
            int mid = (right-left)/2 + left;
            if (x/mid==mid){
                return mid;
            }
            if (x/mid > mid){//mid is smaller
                left=mid+1;
            }
            if (x/mid<mid){
                right=mid-1;
            }
            if (x/mid > mid && x/(mid+1) < (mid+1)){ // mid^2 < x < (mid+1)^2
                return mid;
            }
        }
        return left;
    }
}
