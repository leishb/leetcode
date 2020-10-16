package com.iqiyi.pay.cashier.alg;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class MaximumOfWindowSize {


    public int[] maxOfMin(int[] arr){
        int n = arr.length;
        int[] ans = new int[n + 1];
        int[] right = new int[n], left = new int[n];//next smaller
        for (int i=0;i<n;i++){
            right[i] = n;
            left[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();;
        for (int i=n-1;i>=0;i--){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        for (int i=0;i<n;i++){
            int len = right[i] - left[i] - 1;
            ans[len] = Math.max(ans[len], arr[i]);
        }
        for (int i=n-1;i>=1;i--){
            ans[i] = Math.max(ans[i], ans[i+1]);
        }
        return ans;
    }


    @Test
    public void test(){
        System.out.println(Arrays.toString(maxOfMin(new int[]{10, 20, 30, 50, 10, 70, 30})));
    }
}
