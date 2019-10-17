package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/15.
 */
public class _670_Maximum_Swap {

    /**
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[]cs = s.toCharArray();
        int ans = num;
        for (int i=0;i<cs.length;i++){
            for (int j=i+1;j<cs.length;j++){
                swap(cs, i, j);
                int k = Integer.parseInt(new String(cs));
                if (k > ans){
                    ans = k;
                }
                swap(cs, i, j);
            }
        }
        return ans;
    }

    private void swap(char[] cs, int i, int j){
        char c = cs[i];
        cs[i] =cs[j];
        cs[j] = c;
    }
}
