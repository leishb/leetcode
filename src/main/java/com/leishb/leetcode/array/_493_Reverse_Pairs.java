package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/12/20.
 */
public class _493_Reverse_Pairs {


    /**
     * TLE
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        TreeNode root = null;
        int ans = 0;
        for (int i=nums.length-1;i>=0;i--){
            ans += search(root, nums[i]);
            root = insert((long) nums[i] * 2, root);
        }
        return ans;
    }



    class TreeNode {
        TreeNode left;
        TreeNode right;
        long val;
        int less = 0;
        int dup = 1;

        TreeNode (long val){
            this.val =val;
        }
    }



    private TreeNode insert(long val, TreeNode root){
        if (root==null){
            return new TreeNode(val);
        }
        if (val == root.val){
            root.dup+=1;
        }else if (val < root.val){
            root.less+=1;
            root.left = insert(val, root.left);
        }else {
            root.right = insert(val, root.right);
        }
        return root;
    }


    private int search(TreeNode root, long val){
        if (root==null)return 0;
        int ans = 0;
        if (val==root.val){
            return root.less;
        }else if (val > root.val){
            ans += root.less + root.dup;
            ans += search(root.right, val);
        }else {
            ans += search(root.left, val);
        }
        return ans;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums, int l, int r) {
        if (l>=r) return 0;
        int res = 0;
        int m = (l+r)/2;
        res += reversePairs(nums, l, m) + reversePairs(nums, m+1, r);
        int[] temp  =new int[r-l+1];
        int i = l, j = m+1, k= 0 , p = m+1;
        while (i<=m){
            while (p<=r && nums[i] > 2L * nums[p]) p++;
            res += p-(m+1);
            while (j<=r && nums[i]>=nums[j])temp[k++] = nums[j++];
            temp[k++] = nums[i++];
        }
        while (j<=r)temp[k++] = nums[j++];
        for (i=0;i<=r-l;i++){
            nums[l+i] = temp[i];
        }
        return res;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int reversePairs2(int[] nums) {
        return mergeAndCount(nums, 0, nums.length-1);
    }


    private int mergeAndCount(int[] nums, int start, int end){
        if (start>=end) return 0;
        int res = 0;
        int mid = (start+end)/2;
        res += mergeAndCount(nums, start, mid) + mergeAndCount(nums, mid+1, end);
        int j = mid+1;
        for (int i=start;i<=mid;i++){
            while (j<=end && nums[i] > 2L * nums[j]){
                j++;
            }
            res += j-(mid+1);
        }
        merge(nums, start, mid, end);
        return res;
    }


    private void merge(int[] nums, int start, int mid, int end){
        int[] temp = new int[end-start+1];
        int i=start, j=mid+1, k=0;
        while (i<=mid && j<=end){
            while (i<=mid && nums[i]<=nums[j])temp[k++] = nums[i++];
            while (j<=end && nums[i] > nums[j]) temp[k++] = nums[j++];
        }
        while (i<=mid)temp[k++] = nums[i++];
        while (j<=end) temp[k++] = nums[j++];
        for (i=0;i<=end-start;i++){
            nums[i+start] = temp[i];
        }
    }

    @Test
    public void test(){
        reversePairs2(new int[]{1,3,2,3,1});
    }

}
