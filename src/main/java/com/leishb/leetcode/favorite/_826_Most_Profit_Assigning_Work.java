package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/8/19.
 */
public class _826_Most_Profit_Assigning_Work {

    /**
     * Accepted
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] diffAndProfit = new int[profit.length][2];
        for (int i=0;i<profit.length;i++){
            diffAndProfit[i][0] = difficulty[i];
            diffAndProfit[i][1] = profit[i];
        }
        Arrays.sort(diffAndProfit, (a1, a2)->a1[0]-a2[0]);
        TreeNode root = buildTree(diffAndProfit, 0, diffAndProfit.length-1);
        int ans = 0;
        for (int i=0;i<worker.length;i++){
            int index = search(diffAndProfit, 0, diffAndProfit.length-1, worker[i]);
            if (index>0){
                ans += findRangeMax(root, 0, index-1);
            }
        }
        return ans;
    }


    class TreeNode{
        int start;
        int end;
        int max;
        TreeNode left;
        TreeNode right;
        public TreeNode(int start, int end ,int max){
            this.start= start;
            this.end = end;
            this.max = max;
        }
    }


    private TreeNode buildTree( int[][] diffAndProfit, int start, int end){
        if (start==end){
            return new TreeNode(start, end, diffAndProfit[start][1]);
        }
        int mid = (start+end)/2;
        TreeNode left = buildTree(diffAndProfit, start, mid);
        TreeNode right = buildTree(diffAndProfit, mid+1, end);
        TreeNode node = new TreeNode(start, end, Math.max(left.max, right.max));
        node.left = left;
        node.right = right;
        return node;
    }


    private int findRangeMax(TreeNode root, int start, int end){
        if (root.start == start && root.end == end){
            return root.max;
        }
        int mid = (root.start+root.end)/2;
        if (mid>=end){
            return findRangeMax(root.left, start, end);
        }else if (mid<start){
            return findRangeMax(root.right, start, end);
        }else {
            return Math.max(findRangeMax(root.left, start , mid), findRangeMax(root.right, mid+1, end));
        }
    }


    private int search(int[][] arr, int start, int end, int x){
        while (start<=end){
            int mid = (start+end)/2;
            if (arr[mid][0]>x){
                end = mid-1;
            }else if (arr[mid][0]<x){
                start = mid +1;
            }else {
                start = mid +1;
            }
        }
        return start;
    }

    /**
     * Accepted
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {
        int[][] diffAndProfit = new int[profit.length][2];
        for (int i=0;i<profit.length;i++){
            diffAndProfit[i][0] = difficulty[i];
            diffAndProfit[i][1] = profit[i];
        }
        Arrays.sort(diffAndProfit, (a1, a2)->a1[0]-a2[0]);
        Arrays.sort(worker);
        int i = 0;
        int ans = 0;
        int max = 0;
        for (int w : worker){
            while (i<profit.length && diffAndProfit[i][0]<=w){
                max = Math.max(max, diffAndProfit[i][1]);
                i++;
            }
            ans += max;
        }
        return ans;
    }

    /**
     * Accepted
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    public int maxProfitAssignment3(int[] difficulty, int[] profit, int[] worker) {
        int maxDif = 0;
        for (int dif : difficulty){
            maxDif = Math.max(maxDif, dif);
        }
        int[] bucket = new int[maxDif+1];
        for (int i=0;i<profit.length;i++){
            bucket[difficulty[i]] = Math.max(bucket[difficulty[i]], profit[i]);
        }
        for (int i=1;i<bucket.length;i++){
            bucket[i] = Math.max(bucket[i-1], bucket[i]);
        }
        int ans = 0;
        for (int w : worker){
            ans += w>maxDif ? bucket[maxDif] : bucket[w];
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(maxProfitAssignment3(new int[]{2,4,6,8,10}, new int[]{10,20,30,40,50}, new int[]{4,5,6,7})==100);
        Assert.assertTrue(maxProfitAssignment3(new int[]{2,4,4,6,8,10}, new int[]{10,20, 50 ,30,40,50}, new int[]{4,5,6,7})==200);
        Assert.assertTrue(maxProfitAssignment3(new int[]{2,4,4,6,8,10}, new int[]{10,20, 50 ,80,40,50}, new int[]{4,5,6,7})==260);
        Assert.assertTrue(maxProfitAssignment3(new int[]{85,47,57}, new int[]{24,66,99}, new int[]{40,25,25})==0);
        Assert.assertTrue(maxProfitAssignment3(new int[]{13,37,58}, new int[]{4,90,96}, new int[]{34,73,45})==4+96+90);
    }
}
