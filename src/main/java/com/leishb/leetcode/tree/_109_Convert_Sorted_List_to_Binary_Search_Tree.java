package com.leishb.leetcode.tree;

import com.leishb.leetcode.linkedlist.ListNode;

/**
 * Created by me on 2020/1/20.
 */
public class _109_Convert_Sorted_List_to_Binary_Search_Tree {



    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return buildTree(head, null);
    }


    private TreeNode buildTree(ListNode head, ListNode tail){
        if(head==tail) return null;
        ListNode slow = head;
        ListNode fast = slow.next;
        while(fast!=tail  && fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildTree(head, slow);
        root.right = buildTree(slow.next, tail);
        return root;
    }
}
