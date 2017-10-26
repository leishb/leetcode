package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2017/10/18.
 */
public class SolveEquation {

    @Test
    public void test(){
        Assert.assertTrue(solveEquation("x=x").equals("Infinite solutions"));
        Assert.assertTrue(solveEquation("2x=x").equals("x=0"));

        //"x+5-3+x=6+x-2"
        Assert.assertTrue(solveEquation("x+5-3+x=6+x-2").equals("x=2"));
        Assert.assertTrue(solveEquation("3x=33+22+11").equals("x=22"));
        Assert.assertTrue(solveEquation("99x=99").equals("x=1"));
        Assert.assertTrue(solveEquation("0x=0").equals("Infinite solutions"));
        Assert.assertTrue(solveEquation("-x=-1").equals("x=1"));
    }


    public String solveEquation(String equation) {
        String infine = "Infinite solutions";
        String no = "No solution";

        String left = equation.split("=")[0];
        String right = equation.split("=")[1];

        int lx = 0;
        int rx = 0;
        int ltotal = 0;
        int rtotal = 0;

        lx = getXandT2(left)[0];
        ltotal = getXandT2(left)[1];


        rx = getXandT2(right)[0];
        rtotal = getXandT2(right)[1];

        int diffx = lx-rx;
        int diftt = rtotal-ltotal;

        if (diffx ==0 && diftt ==0){
            return infine;
        }
        if (diffx==0 && diftt !=0){
            return no;
        }
        String result = null;
        if (diftt%diffx==0){
            result = ""+diftt/diffx;
        }else {
            result = "" + (double)diftt/(double)diffx;
        }
        StringBuffer sb = new StringBuffer("x");
        sb.append("=").append(result);
        return sb.toString();
    }


    /**
     * Accepted
     * @param string
     * @return
     */
    private int[] getXandT2(String string) {
        int lx = 0;
        int ltotal = 0;
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<string.length();i++){
            char c = string.charAt(i);
            if (c=='+'){
                list.add(1);
            }
            if (c=='-'){
                list.add(-1);
            }
        }
        List<String> nums = new ArrayList<String>();
        for (String s : string.split("\\+")) {
            nums.addAll(Arrays.asList(s.split("\\-")));
        }
        for (int i=0;i<nums.size();i++){
            String num = nums.get(i);
            if (num.length()==0){
                continue;
            }
            int sign;
            if (i==0){
                sign =1;
            }else {
                sign = list.get(i-1);
            }
            if (num.contains("x") && !num.equals("x")){
                lx += sign * Integer.parseInt(num.replace("x",""));
            }else if (num.equals("x")){
                lx += sign * 1;
            } else {
                ltotal += sign * Integer.parseInt(num);
            }
        }
        return new int[]{lx, ltotal};
    }


    /**
     * Accepted
     * @param string
     * @return
     */
    private int[] getXandT(String string){
        int lx =0;
        int ltotal =0;
        int sign  =1;
        int cur = 0;
        for (int i=0;i<string.length();i++){
            char c = string.charAt(i);
            if (c=='-'){
                sign = -1;
                cur = 0;
            }else if (c == '+'){
                cur = 0;
                sign = 1;
            }
            else if (c>='0' && c <='9'){
                cur = cur *10 + (c-'0'); //attention cur= has no '+'
            }else if (c == 'x'){
                if (cur==0){ //0x=0
                    if (i!=0 && string.charAt(i-1)=='0'){
                        cur=0;
                    }else {
                        cur = 1;
                    }
                }
                lx += sign * cur;
                cur = 0;
            }
        }
        sign =1;
        cur =0;
        for (int i=0;i<string.length();i++){
            char c = string.charAt(i);
            if (c=='-'){
                ltotal += sign * cur;
                sign =-1;
                cur =0;
            }else if (c=='+'){
                ltotal += sign * cur;
                sign =1;
                cur =0;
            }
            else if (c>='0' && c<='9'){
                cur = cur*10 + (c-'0');//attention cur= has no '+'
            }else if (c=='x'){
                cur = 0;
                sign = 1;
            }
            if (i==string.length()-1 && c!='x'){
                ltotal += sign * cur;
            }
        }
        return new int[]{lx,ltotal};
    }
}
