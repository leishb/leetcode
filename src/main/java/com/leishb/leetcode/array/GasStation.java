package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/6/12.
 */
public class GasStation {


    /**
     * Accepted
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i=0;i<gas.length;i++){
            if (canCompleteCircuit(gas, cost, i)){
                return i;
            }
        }
        return -1;
    }


    public boolean canCompleteCircuit(int[] gas, int[] cost, int index) {
        int amount = 0;
        for (int i=index;i<gas.length;i++){
            amount = amount+gas[i]-cost[i];
            if(amount<0){
                return false;
            }
        }
        for (int i=0;i<index;i++){
            amount = amount+gas[i]-cost[i];
            if(amount<0){
                return false;
            }
        }
        return amount>=0;
    }


    /**
     * Accepted
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int amount = 0;
        int startIndex = gas.length-1;
        int rightEnd = gas.length;
        int leftStart = 0;
        while (startIndex>=0){
            boolean flag = false;
            int rightStart = startIndex;
            while (rightStart<rightEnd){
                amount += (gas[rightStart]-cost[rightStart]);
                if (amount<0){
                    flag = true;
                    rightEnd = startIndex;
                    startIndex--;
                    break;
                }
                rightStart++;
            }
            if (flag){
                continue;
            }
            while (leftStart<startIndex){
                amount += (gas[leftStart]-cost[leftStart]);
                if (amount<0){
                    rightEnd = startIndex;
                    startIndex--;
                    leftStart++;
                    break;
                }
                leftStart++;
            }
            if (amount>=0){
                return startIndex;
            }
        }
        return -1;
    }


    @Test
    public void test(){
        Assert.assertTrue(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2})==3);
        Assert.assertTrue(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3})==-1);


        Assert.assertTrue(canCompleteCircuit2(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2})==3);
        Assert.assertTrue(canCompleteCircuit2(new int[]{2,3,4}, new int[]{3,4,3})==-1);
    }
}
