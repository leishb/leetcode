package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2017/10/16.
 *
 *
 *
 * Input:"-1/2+1/2+1/3"
   Output: "1/3"
 */
public class FractionAdditionAndSubtraction {


    @Test
    public void test(){
        Assert.assertTrue(fractionAddition("-1/2+1/2+1/3").equals("1/3"));
        Assert.assertTrue(fractionAddition("1/3-1/2").equals("-1/6"));
        Assert.assertTrue(fractionAddition("5/3+1/3").equals("2/1"));
        Assert.assertTrue(fractionAddition("5/3").equals("5/3"));
        Assert.assertTrue(fractionAddition("-7/3").equals("-7/3"));


        Assert.assertTrue(fractionAddition2("-1/2+1/2+1/3").equals("1/3"));
        Assert.assertTrue(fractionAddition2("1/3-1/2").equals("-1/6"));
        Assert.assertTrue(fractionAddition2("5/3+1/3").equals("2/1"));
        Assert.assertTrue(fractionAddition2("5/3").equals("5/3"));
        Assert.assertTrue(fractionAddition2("-7/3").equals("-7/3"));
        Assert.assertTrue(gcd(-7,3)==-1);
    }


    /**
     * Accepted
     * @param expression
     * @return
     */
    public String fractionAddition2(String expression){
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if (i!=0 && c=='+'){
                list.add(1);
            }
            if (i!=0 && c=='-'){
                list.add(-1);
            }
        }
        List<String> nums = new ArrayList<String>();
        for (String s : expression.split("\\+")){
            nums.addAll(Arrays.asList(s.split("\\-")));
        }
        if (nums.get(0).length()==0){
            nums.remove(0);
        }
        int sign = expression.charAt(0)=='-' ? -1 :1;
        int resultFz = 0;
        int resultFm = 0;
        for (int j=0;j<nums.size();j++){
            String num = nums.get(j);
            int fz = Integer.parseInt(num.split("/")[0]);
            int fm = Integer.parseInt(num.split("/")[1]);
            if (j==0){
                resultFz = sign * fz;
                resultFm = fm;
                continue;
            }
            sign = list.get(j-1);
            resultFz = resultFz * fm + sign * fz * resultFm;
            resultFm = resultFm * fm;
        }
        StringBuffer sb = new StringBuffer();
        int gcd = gcd(resultFz, resultFm);
        sb.append(isNegative(resultFz ,resultFm) ? "-" : "").append(Math.abs(resultFz/gcd)).append("/").append(Math.abs(resultFm/gcd));
        return sb.toString();
    }


    /**
     * Accepted
     * @param expression
     * @return
     */
    public String fractionAddition(String expression) {
        int sign = 1;
        int resultFz = 0;
        int resultFm =0;
        int fz = 0;
        int fm = 0;
        boolean isFm = false;
        boolean isFz = true;
        boolean isFirst = true;
        for (int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if (i==0 && c == '-'){
                sign = -1;
                continue;
            }
            if (isFz && c >='0' && c <='9'){
                fz = fz * 10 + (c-'0');
            }
            else if (c == '/'){
                isFz = false;
                isFm = true;
            }
            else if (isFm && c >='0' && c <='9'){
                fm = fm * 10 + (c-'0');
            }else if (isFm && (c == '+' || c == '-')){ //after this, to parse second fraction
                if (isFirst){
                    resultFz = sign * fz;
                    resultFm = fm;
                } else {
                    resultFz = resultFz * fm + sign * fz * resultFm;
                    resultFm =  resultFm * fm;
                }
                //convert param to init
                isFz = true;
                isFm = false;
                isFirst = false;
                fz = 0;
                fm = 0;
                sign = c == '+' ? 1 : -1;
            }
            //case : expression has not only one fraction,
            if (i==expression.length()-1 && !isFirst){
                resultFz = resultFz * fm + sign* fz * resultFm;
                resultFm = resultFm * fm;
            }
            //case : expression has only one fraction
            if (i==expression.length()-1 && isFirst){
                resultFz = sign * fz;
                resultFm = fm;
            }
        }
        StringBuffer sb = new StringBuffer();
        int gcd = gcd(resultFz, resultFm);
        sb.append(isNegative(resultFz ,resultFm) ? "-" : "").append(Math.abs(resultFz/gcd)).append("/").append(Math.abs(resultFm/gcd));
        return sb.toString();
    }

    private boolean isNegative(int a, int b){
        return (a>0&&b<0) || (a<0&&b>0);
    }

    /**
     * 有可能是负的
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b){
        if (b==0){
            return a;
        }
        return gcd(b, a%b);
    }
}
