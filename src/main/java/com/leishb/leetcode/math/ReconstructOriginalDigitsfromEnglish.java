package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/12.
 */
public class ReconstructOriginalDigitsfromEnglish {


    @Test
    public void test(){
        Assert.assertTrue(originalDigits("one").equals("1"));
        Assert.assertTrue(originalDigits("owoztneoer").equals("012"));
        Assert.assertTrue(originalDigits("fviefuro").equals("45"));
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public String originalDigits(String s) {

        String nums = "zero one two three four five six seven eight nine";
        int[] count = new int[10];
        for (int i=0;i< s.length();i++){
            char c = s.charAt(i);
            switch (c){
                case 'z':
                    count[0]++;
                    break;
                case 'w':
                    count[2]++;
                    break;
                case 'u':
                    count[4]++;
                    break;
                case 'x':
                    count[6]++;
                    break;
                case 'g':
                    count[8]++;
                    break;
                case 'o':
                    count[1]++;//1-0-2-4
                    break;
                case 'h':
                    count[3]++;//3-8
                    break;
                case 'f':
                    count[5]++;//5-4
                    break;
                case 's':
                    count[7]++;//7-6
                    break;
                case 'i':
                    count[9]++;//9-8-6-5
                    break;
            }
        }
        count[1] = count[1] - count[0] - count[2] - count[4];
        count[3] -= count[8];
        count[5] -= count[4];
        count[7] -= count[6];
        count[9] = count[9] - count[8] - count[6] - count[5];
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<count.length;i++){
            for (int j=0;j<count[i];j++){
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
