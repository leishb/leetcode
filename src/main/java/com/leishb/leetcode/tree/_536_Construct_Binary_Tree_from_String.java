package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/10/8.
 */
public class _536_Construct_Binary_Tree_from_String {



    public TreeNode str2tree(String s) {
        if (s.isEmpty()) return null;
        int left = s.indexOf("(");
        if (left==-1) return new TreeNode(Integer.parseInt(s));
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, left)));
        int count = 0;
        int right = left;
        for (;right<s.length();right++){
            if (s.charAt(right)=='(')count++;
            else if (s.charAt(right)==')')count--;
            if (count==0)break;
        }
        root.left = str2tree(s.substring(left+1, right));
        if (right< s.length()-1){
            root.right = str2tree(s.substring(right+2, s.length()-1));
        }
        return root;
    }
}
