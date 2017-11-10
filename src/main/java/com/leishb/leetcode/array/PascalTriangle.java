package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2017/11/8.
 */
public class PascalTriangle {

    @Test
    public void test(){
        System.out.println(getRow(4));

        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(-1));
        list.add(Arrays.asList(2,3));
//        list.add(Arrays.asList(1,-1,-3));
        Assert.assertTrue( minimumTotal(list)==1);
        Assert.assertTrue( minimumTotal2(list)==1);
    }


    /**
     * Accepted
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<=i;j++){
                if (j==0){
                    list.add(1);
                }else if (j==i && i!=0){
                    list.add(1);
                }else {
                    if (i!=0){
                        List<Integer> pre = ret.get(i-1);
                        list.add(pre.get(j-1)+pre.get(j));
                    }
                }
            }
            ret.add(list);
        }
        return ret;
    }


    /**
     * Accepted
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<>(rowIndex+1);
        int[] nums = new int[rowIndex+1];
        for (int i=0;i<=rowIndex;i++){
            for (int j=rowIndex;j>=0;j--){
                if (j==rowIndex || j==0){
                    nums[j]=1;
                }else {
                    nums[j]=nums[j-1] + nums[j];
                }
            }
        }
        for (int num : nums){
            ret.add(num);
        }
        return ret;
    }


    /**
     * Accepted
     * @param triangle
     * @return
     */
    @DynamicProgramming
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (m==0){
            return 0;
        }
        int n = triangle.get(triangle.size()-1).size();
        if (n==0){
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i=1;i<m;i++){
            for (int j=0;j<triangle.get(i).size();j++){
                if (j==0){
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size()-1){
                    dp[i][j] = dp[i-1][j-1]   + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0;i<n;i++){
            min = Math.min(min, dp[m-1][i]);
        }
        return min;
    }

    /**
     * Accepted
     * @param triangle
     * @return
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        if (triangle.size()==0){
            return 0;
        }
        int n = triangle.size();
        int[] dp = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i=0;i<n;i++){
            int k =triangle.get(i).size()-1;
            for (int j=k;j>=0;j--){
                if (j==k && j!=0){
                    dp[j] = dp[j-1]+triangle.get(i).get(j);
                }else if (j>0 && j<k){
                    dp[j] = Math.min(dp[j-1], dp[j]) + triangle.get(i).get(j);
                }else {
                    dp[j] = dp[0] + triangle.get(i).get(j);
                }
            }
        }
        for (int i=0;i<n;i++){
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    public int minimumTotal3(List<List<Integer>> triangle) {
        if (triangle.size()==0){
            return 0;
        }
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i=0;i<triangle.get(n-1).size();i++){
            dp[i]=triangle.get(n-1).get(i);
        }
        for (int i=n-2;i>=0;i--){
            for (int j=0;j<triangle.get(i).size();j++){
                dp[j]=Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
