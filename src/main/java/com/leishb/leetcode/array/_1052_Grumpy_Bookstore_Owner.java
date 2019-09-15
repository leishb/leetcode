package com.leishb.leetcode.array;

/**
 * Created by me on 2019/9/15.
 */
public class _1052_Grumpy_Bookstore_Owner {

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int left = 0;
        int max = 0;
        int sum = 0;
        for (int i=0;i<customers.length;i++){
            if (i<X){
                max += customers[i] * grumpy[i];
                sum += customers[i] * grumpy[i];
            }else {
                sum = sum + customers[i] * grumpy[i] - (customers[i-X] * grumpy[i-X]);
                if (sum > max){
                    left = i-X+1;
                    max = sum;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<customers.length;i++){
            if (grumpy[i]==0 || (i>=left && i<left+X)){
                ans += customers[i];
            }
        }
        return ans;
    }
}
