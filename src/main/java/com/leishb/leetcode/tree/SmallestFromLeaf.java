package com.leishb.leetcode.tree;

import org.junit.Test;

/**
 * Created by me on 2019/5/9.
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 */
public class SmallestFromLeaf {


    private String smallest = null;

    private char[] cs = new char[]{'a','b','c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * Accepted
     * @param root
     * @return
     */
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, "");
        return smallest;
    }


    private void dfs(TreeNode root, String s){
        if (root==null){
            return;
        }
        if (root.left==null && root.right==null) {
            if (smallest==null || smallest.compareTo(cs[root.val] + s) > 0){
                smallest = cs[root.val] + s;
            }
            return;
        }
        dfs(root.left, cs[root.val] + s);
        dfs(root.right, cs[root.val] + s);
    }


    /**
     * Wrong
     *"ab" < "abab", but "abz" > "ababz"
     * https://leetcode.com/problems/smallest-string-starting-from-leaf/discuss/244205/Divide-and-conquer-technique-doesn't-work-for-this-problem
     * @param root
     * @return
     */
    public String smallestFromLeaf2(TreeNode root) {
        if (root==null){
            return "";
        }
        String left = smallestFromLeaf2(root.left);
        String right = smallestFromLeaf2(root.right);
        return left.compareTo(right) < 0 ? (left + (char)(root.val+'a')) : (right + (char)(root.val+'a'));
    }


    @Test
    public void test(){
        System.out.println(""+cs[25]);
    }
}
