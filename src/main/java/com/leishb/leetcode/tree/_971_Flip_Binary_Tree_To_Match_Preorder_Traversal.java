package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/6.
 */
public class _971_Flip_Binary_Tree_To_Match_Preorder_Traversal {


    /**
     * Accepted
     * @param root
     * @param voyage
     * @return
     */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int num : voyage){
            queue.offer(num);
        }
        if (preOrder(root, ans, queue) && queue.isEmpty()){
            return ans;
        }
        return Arrays.asList(-1);
    }


    private boolean preOrder(TreeNode root, List<Integer> list, Queue<Integer> queue){
        if (root!=null){
            if (queue.isEmpty()) return false;
            if (queue.peek()!=root.val) return false;
            int k = queue.poll();
            if (preOrder(root.left, list, queue) && preOrder(root.right, list, queue)){
                return true;
            }else if (preOrder(root.right, list, queue) && preOrder(root.left, list, queue)){
                list.add(root.val);
                return true;
            }else {
                queue.offer(k);
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(flipMatchVoyage(root, new int[]{1,2,3}));
        root.right = new TreeNode(3);
        System.out.println(flipMatchVoyage(root, new int[]{1,2,3}));
        System.out.println(flipMatchVoyage(root, new int[]{1,3,2}));
    }
}
