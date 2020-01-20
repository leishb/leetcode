package com.leishb.contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2020/1/19.
 */
public class Contest_172 {



    public int maximum69Number (int num) {
        String s = String.valueOf(num);
        String ans = s;
        char[] cs = s.toCharArray();
        for (int i=0;i<s.length();i++){
            char c = cs[i];
            cs[i] = '9';
            String ss = new String(cs);
            if (ss.compareTo(ans) > 0){
                ans = ss;
            }
            cs[i] = c;
        }
        return Integer.parseInt(ans);
    }


    public List<String> printVertically(String s) {
        List<String> list = new ArrayList<>();
        String[] ss = s.split(" ");
        int maxLen = 0;
        for (String str : ss){
            maxLen = Math.max(maxLen, str.length());
        }
        for (int i=0;i<maxLen;i++){
            StringBuffer sb = new StringBuffer();
            for (String str : ss){
                if (i>=str.length()){
                    sb.append(" ");
                }else {
                    sb.append(str.charAt(i));
                }
            }
            String str = sb.toString();
            while (str.endsWith(" ")){
                str = str.substring(0, str.length()-1);
            }
            list.add(str);
        }
        return list;
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x){
            this.val = x;
        }
    }

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        dfs(root, null,  target);
        if (root!=null && root.val==target && root.left==null && root.right==null){
            return null;
        }
        return root;
    }


    private void dfs(TreeNode root, TreeNode parent,  int target){
        if (root==null) return;
        dfs(root.left, root, target);
        dfs(root.right, root, target);
        if (root.val==target && root.left==null && root.right==null){
            if (parent!=null && parent.left==root){
                parent.left=null;
            }
            if (parent!=null && parent.right==root){
                parent.right=null;
            }
        }
    }


    private TreeNode dfs(TreeNode root, int target){
        if (root==null) return null;
        root.left = dfs(root.left,  target);
        root.right = dfs(root.right,  target);
        if (root.val==target && root.left==null && root.right==null){
            return null;
        }
        return root;
    }

    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[ranges.length][2];
        for (int i=0;i<ranges.length;i++){
            intervals[i] = new int[]{i-ranges[i], i+ranges[i]};
        }
        Arrays.sort(intervals, (t1, t2) ->t1[0]!=t2[0]?t1[0]-t2[0]:t2[1]-t1[1]);
        int start = 0;
        int ans = 0;
        while (true){
            int maxEnd = 0;
            for (int[] clip : intervals){
                if (clip[0]<=start){
                    maxEnd = Math.max(maxEnd, clip[1]);
                }
            }
            ans+=1;
            if (maxEnd>=n){
                break;
            }
            if (maxEnd<=start){
                return -1;
            }
            start = maxEnd;
        }
        return ans;
    }
}
