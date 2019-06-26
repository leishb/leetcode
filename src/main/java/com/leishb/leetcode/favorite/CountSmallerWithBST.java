package com.leishb.leetcode.favorite;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/6/20.
 * Accepted
 */
public class CountSmallerWithBST {

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int sum;
        int dup;

        TreeNode(int val, int sum){
            this.val = val;
            this.sum = sum;
            this.dup = 1;
        }
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] counts = new Integer[nums.length];
        TreeNode root = null;
        for (int i=nums.length-1;i>=0;i--){
            root = insert(root, counts, i, nums[i], 0);
        }
        return Arrays.asList(counts);
    }


    public TreeNode insert(TreeNode node, Integer[] counts, int i, int num, int preSum){
        if (node==null){
            node = new TreeNode(num, 0);
            counts[i] = preSum;
            return node;
        }
        if (node.val == num){
            node.dup += 1;
            counts[i] = preSum+node.sum;
        }else if (node.val > num){//to left
            node.sum +=1;
            node.left = insert(node.left, counts, i, num, preSum);
        }else {//to right
            node.right = insert(node.right, counts, i, num, preSum+node.sum+node.dup);
        }
        return node;
    }


    @Test
    public void test(){
        System.out.println(countSmaller(new int[]{3,2,2,6,1}));
    }
}
