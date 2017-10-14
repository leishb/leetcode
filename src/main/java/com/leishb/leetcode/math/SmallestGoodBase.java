package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by me on 2017/10/13.
 */
@BinarySearch
public class SmallestGoodBase {


    @Test
    public void test(){
        Assert.assertTrue(smallestGoodBase("1000000000000000000").equals("999999999999999999"));
        Assert.assertTrue(smallestGoodBase("13").equals("3"));

        Assert.assertTrue(smallestGoodBase2("1000000000000000000").equals("999999999999999999"));
        Assert.assertTrue(smallestGoodBase2("13").equals("3"));
    }


    /**
     * Accepted
     * 解方程： (x^k-1)/(x-1) = n ，2=<k<=60
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        long nn = Long.parseLong(n);
        long result = Long.MAX_VALUE;
        for (int k=2;k<=60;k++){
            long l=2L;
            long r=nn;
            while (l<=r){//binary search , attention : '='
                long mid = (r-l)/2+l;
                BigInteger rb = BigInteger.valueOf(nn).multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE)); // n * (x-1)
                BigInteger lb = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE); // x^k-1
                int cmp = rb.compareTo(lb);
                if (cmp==0){
                    result = Math.min(mid, result);
                    break;
                }
                if (cmp<0){ //mid is bigger
                    r=mid-1; // mid
                }
                if (cmp>0){//mid is smaller
                    l=mid+1;
                }

            }
        }
        return ""+result;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public String smallestGoodBase2(String n) {
        long nn = Long.parseLong(n);
        long result = 0;
        for (int k=60;k>=2;k--){
            long l=2L;
            long r=nn;
            while (l<=r){
                long mid = (r-l)/2+l;
                BigInteger rb = BigInteger.valueOf(nn).multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE)); // n * (x-1)
                BigInteger lb = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE); // x^k-1
                int cmp = rb.compareTo(lb);
                if (cmp==0){
                    result = mid;
                    break;
                }
                if (cmp<0){ //mid is bigger
                    r=mid-1; // mid
                }
                if (cmp>0){//mid is smaller
                    l=mid+1;
                }
            }
            if (result!=0){
                break;
            }
        }
        return ""+result;
    }
}
