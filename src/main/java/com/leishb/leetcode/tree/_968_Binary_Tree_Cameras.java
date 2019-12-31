package com.leishb.leetcode.tree;

/**
 * Created by me on 2019/11/26.
 */
public class _968_Binary_Tree_Cameras {


    enum State {CAMERA, MONITOR, NOT_MONITOR}


    /**
     * Accepted
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        Status cur = dfs(root);
        if (cur.state==State.NOT_MONITOR){
            cur.cameras++;
        }
        return cur.cameras;
    }



    private Status dfs(TreeNode root){
        if (root==null) return new Status(0, State.MONITOR);
        Status left = dfs(root.left);
        Status right = dfs(root.right);
        Status cur = new Status(left.cameras+right.cameras, State.NOT_MONITOR);
        if (left.state==State.NOT_MONITOR || right.state==State.NOT_MONITOR){
            cur.cameras++;
            cur.state = State.CAMERA;
        }else if (left.state==State.CAMERA || right.state==State.CAMERA){
            cur.state = State.MONITOR;
        }
        return cur;
    }


    class Status{
        int cameras;
        State state;

        Status(int cameras, State state){
            this.cameras = cameras;
            this.state = state;
        }
    }
}
