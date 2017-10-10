package com.leishb.leetcode.math;

/**
 * Created by me on 2017/10/9.
 */
public class IntegerReplacement {


    /**
     * remove 0 and 1 , until binary number n left only 1
     * remove 0 needs one step, remove 1 needs two steps. so the best way to reduce n to 1 is to have less 1.
     * Accepted
     * @param n
     * @return
     */
    public int integerReplacement(int n){
        int count = 0;
        while (n!=1){
            if (n%2==0){
                n>>>=1;
            }else if (n==3 || ((n>>>1) & 1)==0){ //case : binariy number ends with *01
                n--;
            }else { //case : binary number ends with *11
                n++;
            }
            count++;
        }
        return count;
    }
}
