package com.leishb.leetcode.tree;

import java.util.*;

/**
 * Created by me on 2019/7/24.
 */
public class _652_Find_Duplicate_Subtrees {


    /**
     * Accepted
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        serialize(root, map);
        List<TreeNode> result = new ArrayList<>();
        for (String key : map.keySet()){
            if (map.get(key).size()>1){
                result.add(map.get(key).get(0));
            }
        }
        return result;
    }

    public static String SPLITER = "#";
    public static String NULL = "NULL";

    public String serialize(TreeNode root, Map<String, List<TreeNode>> map) {
        if (root==null){
            return NULL;
        }
        String s = root.val + SPLITER + serialize(root.left, map) + SPLITER + serialize(root.right, map);
        map.put(s, map.getOrDefault(s, new ArrayList<>()));
        map.get(s).add(root);
        return s;
    }
}
