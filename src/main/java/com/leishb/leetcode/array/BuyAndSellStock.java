package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/8.
 */
@DynamicProgramming
public class BuyAndSellStock {

    @Test
    public void test(){
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,3,6,4})==5);
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,3,6})==maxProfit(new int[]{7,1,5,3,6}));
        Assert.assertTrue(maxProfit2(new int[]{7,1,5,2,9,8})==maxProfit(new int[]{7,1,5,2,9,8}));
        Assert.assertTrue(maxProfit2(new int[]{1,1,5,3,6,7})==maxProfit(new int[]{1,1,5,3,6,7}));


        Assert.assertTrue(maxProfit4(new int[]{3,3,5,0,0,3,1,4})==6);
        Assert.assertTrue(maxProfit5(new int[]{3,3,5,0,0,3,1,4})==6);
        Assert.assertTrue(maxProfit6(new int[]{3,3,5,0,0,3,1,4})==6);
        Assert.assertTrue(maxProfit6(new int[]{3,3,0,3,4,6})==6);
        Assert.assertTrue(maxProfit2(2, new int[]{3,3,0,3,4,6})==6);


        Assert.assertTrue(maxProfit(new int[]{1,3,2,8,4,9}, 2)==8);
        Assert.assertTrue(maxProfit(new int[]{1,3,2,8,9,4,9}, 2)==9);
        Assert.assertTrue(maxProfit(new int[]{7,1,5,3,6,4}, 1)==5);
        Assert.assertTrue(maxProfit(new int[]{7,1,5,3,6,4}, 0)==7);
        Assert.assertTrue(maxProfit(new int[]{1,3,7,5,10,3}, 3)==6);
        Assert.assertTrue(maxProfit(new int[]{1,4,6,2,8,3,10,14}, 3)==13);
        Assert.assertTrue(maxProfit(new int[]{2,2,1,1,5,5,3,1,5,4}, 2)==4);




        System.out.print(maxProfit10(new int[]{6,1,6,4,3,0,2}));

    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int[] diff = new int[prices.length-1];
        for(int i=0;i<diff.length;i++){
            diff[i] = prices[i+1] - prices[i];
        }
        int profit = 0;
        int sum = 0;
        for(int i=0;i<diff.length;i++){
            sum += diff[i];
            profit = Math.max(sum, profit);
            if (sum < 0){
                sum=0;
            }
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int profit = 0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0) + (prices[i] - prices[i-1]);
            profit = Math.max(profit, sum);
        }
        return profit;
    }


    /**
     * Time limit exceeded
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        for (int i=0;i<prices.length;i++){
            result = Math.max(helper(prices, 0, i)+ helper(prices, i, prices.length-1), result);
        }
        return result;
    }

    private int helper(int[] prices, int start, int end){
        int profit = 0;
        int sum = 0;
        for (int i=start+1;i<=end;i++){
            sum = Math.max(sum, 0) + (prices[i] - prices[i-1]);
            profit = Math.max(profit, sum);
        }
        return profit;
    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    @DynamicProgramming
    public int maxProfit4(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        int[] dp1 = new int[prices.length];
        dp1[0]=0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            int diff = prices[i]-prices[i-1];
            sum = Math.max(sum, 0)+diff;
            dp1[i] = Math.max(sum, dp1[i-1]);
        }
        int[] dp2 = new int[prices.length];
        sum = 0;
        dp2[prices.length-1]=0;
        for (int i=prices.length-2;i>=0;i--){
            int diff = prices[i]-prices[i+1];
            sum = Math.min(sum, 0) + diff;
            dp2[i]=Math.min(dp2[i+1], sum);
        }
        for (int i=0;i<prices.length;i++){
            result = Math.max(result, Math.max(0, dp1[i])-Math.min(0, dp2[i]));
        }
        return result;
    }


    /**
     * Accepted
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        if (prices.length==0){
            return 0;
        }
        int result = 0;
        int[] dp1 = new int[prices.length];
        dp1[0]=0;
        int sum = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0)+prices[i]-prices[i-1];
            dp1[i] = Math.max(sum, dp1[i-1]);
        }
        sum = 0;
        int curMin = Integer.MAX_VALUE;
        for (int i=prices.length-2;i>=0;i--){
            sum = Math.min(sum, 0) + prices[i]-prices[i+1];
            curMin = Math.min(curMin, sum);
            result = Math.max(result, Math.max(0, dp1[i])-Math.min(0, curMin));
        }
        result = Math.max(result, dp1[prices.length-1]);
        return result;
    }


    public int maxProfit6(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0]=0;
        int sum = 0;
        int result = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0) + (prices[i]-prices[i-1]);
            dp[i] = Math.max(dp[i-1], sum);
        }
        int minSum = 0;
        int curOff = 0;
        for (int i=prices.length-2;i>=0;i--){
            minSum = Math.min(minSum, 0) + prices[i]-prices[i+1];
            curOff = Math.min(minSum, curOff);
            result = Math.max(result, dp[i]-curOff);
        }
        result = Math.max(result ,dp[prices.length-1]);
        return result;
    }


    /**
     * TLE
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (k==0 || prices.length<=1){
            return 0;
        }
        if (k >= prices.length){
            k = prices.length-1;
        }
        int[][] dp = new int[k+1][prices.length];
        int sum = 0;
        dp[1][0]=0;
        int result = 0;
        for (int i=1;i<prices.length;i++){
            sum = Math.max(sum, 0) + prices[i] - prices[i-1];
            dp[1][i] = Math.max(sum, dp[1][i-1]);
            result = Math.max(result, dp[1][i]);
        }
        for (int i=2;i<=k;i++){
            for (int j=1;j<prices.length;j++){
                int curOff = 0;
                int minSum = 0;
                dp[i][j] = dp[i-1][j];
                for (int p=j-1;p>=0;p--){
                    minSum = Math.min(minSum, 0) + prices[p] - prices[p+1];
                    curOff = Math.min(curOff, minSum);
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][p]-curOff);
                }
                result = Math.max(dp[i][j], result);
            }
        }
        return result;
    }

    /**
     * Accepted
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit2(int k, int[] prices) {
        if (k == 0 || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        if (k >= prices.length/2){
            for(int i=1;i<prices.length;i++){
                if (prices[i]-prices[i-1]>0){
                    maxProfit += prices[i] - prices[i-1];
                }
            }
            return maxProfit;
        }
        int[][] dp = new int[k+1][prices.length];
        for (int i=1;i<=k;i++){
            int tempMax = dp[0][0] - prices[0];
            for (int j=1;j<prices.length;j++){
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + tempMax);
                tempMax = Math.max(dp[i-1][j]-prices[j], tempMax);
                maxProfit = Math.max(maxProfit, dp[i][j]);
            }
        }
        return maxProfit;
    }


    /**
     * Accepted
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices.length==0){
            return 0;
        }
        int profit = 0;
        int hold = prices[0];
        int sell = hold;
        boolean hasBuy = false;
        for (int i=1;i<prices.length;i++){
            if (!hasBuy && prices[i] < prices[i-1]){
                if (prices[i] < hold) {
                    hold = prices[i];
                }
            }else if (!hasBuy && prices[i] - hold > fee){
                hasBuy = true;
                sell = prices[i];
            }else if (hasBuy && prices[i] > prices[i-1]){
                if (prices[i] > sell){
                    sell = prices[i];
                }
            }else if (hasBuy && sell - prices[i] >fee ){
                profit += sell - hold - fee;
                hasBuy = false;
                hold = prices[i];
            }
        }
        if (hasBuy){
            profit += sell - hold - fee;
        }
        return profit;
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     * Accepted
     * @param prices
     * @return
     */
    public int maxProfit10(int[] prices) {
        int n = prices.length;
        if(n==0){
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        int max = 0;
        for(int i=1;i<n;i++){
            dp[i] = 0;
            for(int j=i-1;j>=0;j--){
                if(prices[i] > prices[j]){
                    if(j<=1){
                        dp[i] = Math.max(dp[i], prices[i]-prices[j]);
                    }else{
                        dp[i] = Math.max(dp[i], dp[j-2] + prices[i]-prices[j]);
                    }
                }
                dp[i] = Math.max(max, dp[i]);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
