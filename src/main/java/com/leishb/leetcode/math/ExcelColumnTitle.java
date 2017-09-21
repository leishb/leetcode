package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/21.
 */
public class ExcelColumnTitle {

    @Test
    public void test(){
        Assert.assertTrue(excelColumTitle(26).equals("Z"));
        Assert.assertTrue(excelColumTitle(1).equals("A"));
        Assert.assertTrue(excelColumTitle(52).equals("AZ"));

        Assert.assertTrue(titleToNumber("FXSHRXW") == Integer.MAX_VALUE);
        Assert.assertTrue(titleToNumber("FXSHRXV") == Integer.MAX_VALUE-1);
        Assert.assertTrue(titleToNumber("A") == 1);

        Assert.assertTrue(titleToNumber("AA") == 27);
        Assert.assertTrue(titleToNumber("ZZ") == 26*26+26);
        System.out.println(excelColumTitle(Integer.MAX_VALUE));
    }


    /**
     * 这里需要注意三点：

     (一).取得的余数要转换为相应的字符，比如A代表1，B代

     表2...Z代表26等等。

     (二).习惯上我们是从0开始计数，用0~25表示一个轮回，

     每次加到26的时候才往前进位，且进位以后原来位为0.

     然而这个题目将A看做是1，那么这就从1开始计数了，

     即1~26,每次加到27的时候才进位，且进位后原来位为1.

     所以在这里我们为了统一就对每次除完26的整数进行减

     1操作，这样我们就把1~26转化为0~25了。

     (三).最后我们还需要翻转一些字符串，因为我们得到的结

     果是逆序的。

     Accepted
     * @param n
     * @return
     */
    public String excelColumTitle(int n){
        StringBuffer sb = new StringBuffer();
        while (n!=0){
            n--;
            int mode = n%26;
            char c = (char) ('A' + mode);
            sb.append(c);
            n/=26;
        }
        return sb.reverse().toString();
    }

    /**
     * Accepted
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        char[] cs = s.toCharArray();
        if (cs.length==0){
            return 0;
        }
        long result = 0;
        for (int i = 0;i< cs.length;i++){
            char c = cs[i];
            if (c < 'A' || c> 'Z'){
                return 0;
            }
            result = (long) (result + Math.pow(26, cs.length-i-1) * (c-'A'+1));
        }
        if (result>Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }

}
