package com.leishb.leetcode.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by me on 2019/11/27.
 */
public class _1028_Recover_a_Tree_From_Preorder_Traversal {


    /**
     * Accepted
     * @param S
     * @return
     */
    public TreeNode recoverFromPreorder(String S) {
        Map<Integer, Stack<TreeNode>> map = new HashMap<>();
        dfs(S, map, 0);
        return map.get(0).peek();
    }



    private void dfs(String s, Map<Integer, Stack<TreeNode>> map, int index){
        if (s.length()==index) return;
        int dashes = 0;
        while (dashes+index<s.length() && s.charAt(dashes+index)=='-'){
            dashes++;
        }
        int num = 0, pos = index+dashes;
        while (pos < s.length() && s.charAt(pos)!='-'){
            num = num * 10 + s.charAt(pos)-'0';
            pos++;
        }
        TreeNode cur = new TreeNode(num);
        map.putIfAbsent(dashes, new Stack<>());
        map.get(dashes).push(cur);
        if (dashes>0){
            TreeNode parent = map.get(dashes-1).peek();
            if (parent.left==null){
                parent.left = cur;
            }else {
                parent.right = cur;
            }
        }
        dfs(s, map, pos);
    }


}
