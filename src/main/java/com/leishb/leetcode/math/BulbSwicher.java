package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2017/9/28.
 */
public class BulbSwicher {


    @Test
    public void test(){
        Assert.assertTrue(bulbSwitch(10)==3);
        for (int i=2;i<100;i++){
            Assert.assertTrue(bulbSwitch(i)==(int)Math.sqrt(i));
        }
    }

    /**
     * 完全平方数的个数，即n的开方
     * @param n
     * @return
     */
    public int bulbSwitch3(int n){
        return (int)Math.sqrt(n);
    }

    /**
     * Accepted
     * 对于第N个灯泡，只有在第n的因子次变化时，才会改变状态
     * 例如：第36个灯泡，（1,36）、（2,18）、（3,6）、（4，9）、（6,6），前四次都是成对出现的，只有第五次完全平方数单独出现
     * 简化为完全平方数的个数
     * @param n
     * @return
     */
    public int bulbSwitch2(int n){
        int count=0;
        int k=1;
        while (k*k<=n){
            k++;
            count++;
        }
        return count;
    }

    /**
     * time Limit Exceeded
     * @param n
     * @return
     */
    public int bulbSwitch(int n){
        if (n==1){
            return n;
        }
        boolean[] bulbs = new boolean[n];
        Arrays.fill(bulbs, true);
        for (int i=2;i<=n;i++){
            for (int j=i-1;j<n;j=j+i){
                bulbs[j]=!bulbs[j];
            }
        }
        int count=0;
        for (boolean bulb:bulbs){
            if (bulb){
                count++;
            }
        }
        return count;
    }
}
