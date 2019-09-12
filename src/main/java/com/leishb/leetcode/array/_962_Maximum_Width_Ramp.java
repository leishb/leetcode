package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/6.
 */
public class _962_Maximum_Width_Ramp {

    int max = 0;

    public int maxWidthRamp(int[] A) {
        TreeNode root = null;
        for (int i=0;i<A.length;i++){
            root = insertTreeNode(root, A[i], i);
        }
        return max;
    }

    private TreeNode insertTreeNode(TreeNode root, int val, int index){
        if (root==null){
            return new TreeNode(val, index);
        }
        if (val > root.val){
            max = Math.max(max, index-root.index);
            root.right = insertTreeNode(root.right, val, index);
        }else if (val==root.val){
            max = Math.max(max, index-root.index);
        }else if (val < root.val){
            root.left = insertTreeNode(root.left, val, index);
        }
        return root;
    }

    class TreeNode {
        int val;
        int index;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, int index){
            this.val = val;
            this.index = index;
        }
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int maxWidthRamp2(int[] A) {
        int[][] arr = new int[A.length][2];
        for (int i=0;i<A.length;i++){
            arr[i] = new int[]{A[i], i};
        }
        mergeSort(arr, 0, A.length-1);
        return max;
    }


    private void mergeSort(int[][] A, int start, int end){
        if (start<end){
            int mid = (start+end)/2;
            mergeSort(A, start , mid);
            mergeSort(A, mid+1 , end);
            merge(A, start, mid, end);
        }
    }


    private void merge(int[][] A, int start, int mid, int end){
        int i = start;
        int j = mid+1;
        int[][] temp = new int[end-start+1][2];
        int k = 0;
        int leftMinIndex = A.length;
        while (i<=mid && j<=end){
            if (A[i][0] <= A[j][0]){
                leftMinIndex = Math.min(leftMinIndex, A[i][1]);
                max = Math.max(max, A[j][1]-A[i][1]);
                temp[k++] = A[i++];
            }else {
                max = Math.max(max, A[j][1]>leftMinIndex?A[j][1]-leftMinIndex:0);
                temp[k++] = A[j++];
            }
        }
        while (i<=mid){
            temp[k++] = A[i++];
        }
        while (j<=end){
            max = Math.max(max, A[j][1]>leftMinIndex?A[j][1]-leftMinIndex:0);
            temp[k++] = A[j++];
        }
        for (int x=start;x<=end;x++){
            A[x] = temp[x-start];
        }
    }

    /**
     * Accepted
     * @param A
     * @return
     */
    public int maxWidthRamp3(int[] A) {
        int ans = 0;
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<A.length;i++){
            if (list.isEmpty() || A[i] < A[list.get(list.size()-1)]){
                list.add(i);
            }else {
                int left = 0, right = list.size()-1;
                int minIndex = right;
                while (left<=right){
                    int mid = (left+right)/2;
                    if (A[i] >= A[list.get(mid)]){
                        minIndex = Math.min(minIndex, mid);
                        right = mid-1;
                    }else {
                        left = mid+1;
                    }
                }
                ans = Math.max(ans, i-list.get(minIndex));
            }
        }
        return ans;
    }


    public int maxWidthRamp4(int[] A) {
        int ans = 0;
        Integer[] B = new Integer[A.length];
        for (int i=0;i<B.length;i++){
            B[i] = i;
        }
        Arrays.sort(B, (i, j)->Integer.compare(A[i], A[j]));
        int min = B.length;
        for (int i : B){
            ans = Math.max(ans, i-min);
            min = Math.min(min, i);
        }
        return ans;
    }


    public int maxWidthRamp5(int[] A) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<A.length;i++){
            if (stack.isEmpty() || A[stack.peek()] > A[i]){
                stack.push(i);
            }
        }
        for (int i=A.length-1;i>=0;i--){
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]){
                ans = Math.max(ans, i-stack.pop());
            }
        }
        return ans;
    }

    @Test
    public void test(){
        Assert.assertTrue(maxWidthRamp4(new int[]{6,0,8,2,1,5})==4);
        Assert.assertTrue(maxWidthRamp4(new int[]{9,8,1,0,1,9,4,0,4,1})==7);
        Assert.assertTrue(maxWidthRamp4(new int[]{2,4,1,3})==3);
    }
}
