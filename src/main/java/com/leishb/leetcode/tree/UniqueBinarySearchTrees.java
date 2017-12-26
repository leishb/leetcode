package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2017/12/20.
 */
public class UniqueBinarySearchTrees {

    @Test
    public void test(){
        generateTrees(3);

        Assert.assertTrue(numTrees2(3)==5);
        Assert.assertTrue(numTrees2(2)==2);
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n==0){
            return 0;
        }
        int[][] memo = new int[n+1][n+1];
        return numOfTreesWithMemo(1, n, memo);
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int numTrees2(int n) {
        if (n==0){
            return 0;
        }
        int[] dp = new int[n+1];
        dp[0]=1;
        for (int i=1;i<=n;i++){
            for (int j=1;j<=i;j++){
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    /**
     * TLE
     * @param start
     * @param end
     * @return
     */
    private int numOfTrees(int start, int end){
        if (start > end){
            return 1;
        }
        int total = 0;
        for (int cur=start;cur<=end;cur++){
            int left = numOfTrees(start, cur-1);
            int right = numOfTrees(cur+1, end);
            total += left * right;
        }
        return total;
    }

    private int numOfTreesWithMemo(int start, int end, int[][] memo){
        if (start > end){
            return 1;
        }
        if (memo[start][end] != 0){
            return memo[start][end];
        }
        int total = 0;
        for (int cur=start;cur<=end;cur++){
            int left = numOfTreesWithMemo(start, cur-1, memo);
            int right = numOfTreesWithMemo(cur+1, end, memo);
            total += left * right;
        }
        memo[start][end] = total;
        return total;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n==0){
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }




    private List<TreeNode> generateTrees(int start, int end){
        List<TreeNode> ans = new ArrayList<>();
        if (start > end){
            ans.add(null);
            return ans;
        }
        for (int cur=start;cur<=end;cur++){
            List<TreeNode> left = generateTrees(start, cur-1);
            List<TreeNode> right = generateTrees(cur+1, end);
            for (TreeNode leftNode : left){
                for (TreeNode rightNode : right){
                    TreeNode root = new TreeNode(cur);
                    root.left = leftNode;
                    root.right = rightNode;
                    ans.add(root);
                }
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees2(int n) {
        List<TreeNode>[] dp = new List[n+1];
        dp[0] = new ArrayList<>();
        dp[0].add(null);
        for (int i=1;i<=n;i++){
            dp[i] = new ArrayList<>();
            for (int j=1;j<=i;j++){
                for (TreeNode left : dp[j-1]){
                    for (TreeNode right : dp[i-j]){
                        TreeNode root = new TreeNode(j);
                        root.left = left;
                        root.right = clone(right, j);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }


    private TreeNode clone(TreeNode root, int k){
        if (root!=null){
            TreeNode node = new TreeNode(root.val + k);
            node.left = clone(root.left, k);
            node.right = clone(root.right, k);
            return node;
        }
        return null;
    }

}
