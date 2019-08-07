package com.leishb.leetcode.design;

/**
 * Created by me on 2019/8/5.
 */
public class _729_My_Calendar_I {

    TreeNode root = null;

    public _729_My_Calendar_I() {

    }

    public boolean book(int start, int end) {
        boolean[] ans = new boolean[1];
        root = insert(root, start, end, ans);
        return ans[0];
    }


    class TreeNode {
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end){
            this.start = start;
            this.end = end;
        }
    }


    public TreeNode insert(TreeNode root, int start, int end, boolean[] ans){
        if (root==null){
            TreeNode node = new TreeNode(start, end);
            ans[0] = true;
            return node;
        }
        if (root.start>=end){
            root.left = insert(root.left, start, end, ans);
            return root;
        }else if (root.end<=start){
            root.right = insert(root.right, start, end, ans);
            return root;
        }else {
            ans[0] = false;
            return root;
        }
    }


    public static void main(String[] args){
        _729_My_Calendar_I calendar_i = new _729_My_Calendar_I();
        System.out.println(calendar_i.book(10, 20));
        System.out.println(calendar_i.book(15, 25));
        System.out.println(calendar_i.book(20, 30));
    }
}
