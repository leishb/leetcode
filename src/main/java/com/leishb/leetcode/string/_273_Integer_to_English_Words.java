package com.leishb.leetcode.string;

/**
 * Created by me on 2019/12/9.
 */
public class _273_Integer_to_English_Words {


    /**
     * Accepted
     * @param num
     * @return
     */
    public String numberToWords(int num) {
        if (num==0) return "Zero";
        return helper(num);
    }


    String[] tweentyDown = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tweentyUp = new String[]{"", "", "Twenty",  "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};


    private String helper(int num){
        StringBuffer sb = new StringBuffer();
        if (num>=1_000_000_000){
            sb.append(helper(num/1_000_000_000)).append(" Billion ").append(helper(num%1_000_000_000));
        }else if (num>=1_000_000){
            sb.append(helper(num/1_000_000)).append(" Million ").append(helper(num%1_000_000));
        }else if (num>=1_000){
            sb.append(helper(num/1_000)).append(" Thousand ").append(helper(num%1_000));
        }else if (num>=100){
            sb.append(helper(num/100)).append(" Hundred ").append(helper(num%100));
        }else if (num>=20){
            sb.append(tweentyUp[num/10]).append(" ").append(helper(num%10));
        }else {
            sb.append(tweentyDown[num]);
        }
        return sb.toString().trim();
    }
}
