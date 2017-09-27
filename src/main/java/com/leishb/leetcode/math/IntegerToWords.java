package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/27.
 */
public class IntegerToWords {


    @Test
    public void test(){
        Assert.assertTrue(numberToWords(1000000).equals("One Million"));
        Assert.assertTrue(numberToWords(1000001).equals("One Million One"));
    }

    public static final String[] TEN = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    public static final String[] LESS_20 = {"", "Ten", "Eleven", "Twelve", "Thirteen" ,"Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen","Nineteen"};

    public static final String[] LEN_100={"", "Twenty", "Thirty","Forty" , "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static final String[] HANDREDS = {"", "Thousand", "Million", "Billion"};

    /**
     * Accepted
     * @param num
     * @return
     */
    public String numberToWords(int num){
        if (num==0){
            return "Zero";
        }
        String result = "";
        int k = 0;
        while (num!=0){
            if (num%1000!=0){
                result = helper(num%1000).trim() + " " + HANDREDS[k] + " " + result;
            }
            k++;
            num/=1000;
        }
        return result.trim();
    }


    public String helper(int num){
        if (num< 10){
            return TEN[num];
        } else if (num < 20){
            return LESS_20[num%10+1];
        }else if (num < 100){
            return LEN_100[num/10-1] + " " +  helper(num%10);
        }else {
            return TEN[num/100] + " Hundred " + helper(num%100);
        }
    }
}
