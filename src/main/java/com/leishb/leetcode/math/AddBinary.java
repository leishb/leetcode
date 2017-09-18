/*
 * *****************************************************
 * Copyright (C) 2016 iQIYI.COM - All Rights Reserved
 * This file is part of iQiyi Pay project.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential.
 * ****************************************************
 */
package com.leishb.leetcode.math;

/**
 * Created by leishb on 9/18/17.
 */
public class AddBinary {


    public static void main(String[] args){
        System.out.println(addBinary("1","1"));
        System.out.println(addBinary("1","10"));
        System.out.println(addBinary("10","1111"));
        System.out.println(addBinary("111","1"));
        System.out.println(addBinary("1","101"));
        System.out.println(addBinary("1","1"));
        System.out.println(addBinary("1","1"));
        System.out.println(addBinary("0","1"));
        System.out.println(addBinary("0","0"));

    }




    public static String addBinary(String s1, String s2){
        int[] pos = new int[Math.max(s1.length(),s2.length())+1];
        int j = s1.length()-1;
        int k = s2.length()-1;
        int carry = 0;
        for (int i = pos.length-1;i>=0;i--){
            int num1 = 0;
            int num2 = 0;
            if (j>=0){
                num1=s1.charAt(j) - '0';
            }
            if (k>=0){
                num2 = s2.charAt(k) - '0';
            }
            int sum = carry+ num1+num2;
            pos[i] = sum%2;
            carry = sum/2;
            j--;
            k--;
        }

        StringBuffer sb = new StringBuffer();
        boolean zeroStart = true;
        for (int i : pos){
            if (i!=0){
                zeroStart = false;
            }
            if (!zeroStart) {
                sb.append(i);
            }
        }
        return zeroStart ? "0" : sb.toString();
    }

}
