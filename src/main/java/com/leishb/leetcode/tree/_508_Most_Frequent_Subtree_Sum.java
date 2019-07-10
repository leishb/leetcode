package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/5.
 */
public class _508_Most_Frequent_Subtree_Sum {


    /**
     * Accepted
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root==null){
            return new int[0];
        }
        Map<Integer, Integer> freq = new HashMap<>();
        postOrder(root, freq);
        int max = 0;
        int count = 0;
        for (Integer key : freq.keySet()){
            if (freq.get(key) > max){
                max = freq.get(key);
                count=1;
            }else if (freq.get(key)==max){
                count++;
            }
        }
        int[] ans = new int[count];
        int k=0;
        for (Integer key : freq.keySet()){
            if (freq.get(key)==max){
                ans[k++] = key;
            }
        }
        return ans;
    }


    private int postOrder(TreeNode root, Map<Integer, Integer> freq){
        if (root==null){
            return 0;
        }
        int left = postOrder(root.left, freq);
        int right = postOrder(root.right, freq);
        int sum = left+right+root.val;
        freq.put(sum, freq.getOrDefault(sum, 0)+1);
        return sum;
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-5);
        findFrequentTreeSum(root);
    }
}
