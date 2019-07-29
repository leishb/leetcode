package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/7/25.
 */
public class _654_Maximum_Binary_Tree {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        SegmentTreeNode root = buildTree(0, nums.length-1, nums);
        return constructMaximumBinaryTree(root, nums, 0, nums.length-1);
    }


    public TreeNode constructMaximumBinaryTree(SegmentTreeNode root, int[] nums, int start, int end) {
        if (start>end){
            return null;
        }
        int[] max = getMax(root, start, end);
        TreeNode node = new TreeNode(max[1]);
        TreeNode left = constructMaximumBinaryTree(root, nums, start, max[0]-1);
        TreeNode right = constructMaximumBinaryTree(root, nums,  max[0]+1, end);
        node.left = left;
        node.right = right;
        return node;
    }

    class SegmentTreeNode{
        int start;
        int end;
        int max;
        int index;
        SegmentTreeNode left;
        SegmentTreeNode right;

        SegmentTreeNode(int start, int end, int max, int index){
            this.start = start;
            this.end = end;
            this.max = max;
            this.index = index;
        }
    }



    public SegmentTreeNode buildTree(int start, int end, int[] nums){
        if (start==end){
            return new SegmentTreeNode(start, end, nums[start], start);
        }
        int mid = (start+end)/2;
        SegmentTreeNode left = buildTree(start, mid, nums);
        SegmentTreeNode right = buildTree(mid+1, end, nums);
        int index = left.max>=right.max?left.index:right.index;
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.max(left.max, right.max), index);
        root.left = left;
        root.right = right;
        return root;
    }


    public int[] getMax(SegmentTreeNode root, int start, int end){
        if (root.start==start && root.end == end){
            return new int[]{root.index, root.max};
        }
        int mid = (root.start+root.end)/2;
        if (mid>=end){
            return getMax(root.left, start, end);
        }else if (mid < start){
            return getMax(root.right, start, end);
        }else {
            int[] left = getMax(root, start, mid);
            int[] right = getMax(root, mid+1, end);
            if (left[1]>=right[1]){
                return left;
            }else {
                return right;
            }
        }
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        return buildTree(nums, 0, nums.length-1);
    }



    private TreeNode buildTree(int[] nums, int start, int end){
        if (start>end){
            return null;
        }
        int index = start;
        for (int i=start;i<=end;i++){
            if (nums[i]>=nums[index]){
                index = i;
            }
        }
        TreeNode node = new TreeNode(nums[index]);
        node.left = buildTree(nums, start, index-1);
        node.right = buildTree(nums, index+1, end);
        return node;
    }


    @Test
    public void test(){
        TreeNode root = constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});

        System.out.print(root.val);
    }


}
