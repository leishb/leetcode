package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/6/28.
 */
public class _402_Remove_K_Digits {

    /**
     * Accepted
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        List<Integer> digits = new ArrayList<>();
        for (char c : num.toCharArray()){
            digits.add(c-'0');
        }
        while (k>0){
            int index = digits.size()-1;
            for (int i=1;i<digits.size();i++){
                if (digits.get(i)<digits.get(i-1)){
                    index = i-1;
                    break;
                }
            }
            digits.remove(index);
            k--;
        }
        StringBuffer sb = new StringBuffer();
        boolean leadingZero = true;
        for (int digit : digits){
            if (digit==0 && leadingZero){
                continue;
            }
            if (digit!=0){
                leadingZero = false;
            }
            sb.append(digit);
        }
        return sb.length()==0? "0" :sb.toString();
    }


    /**
     * Accepted
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits2(String num, int k) {
        if (num.length()==0){
            return num;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(num.charAt(0)-'0');
        for (int i=1;i<num.length();i++){
            int digit = num.charAt(i)-'0';
            while (k>0 && !stack.isEmpty() && digit<stack.peek()){
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        while (k-->0){
            stack.pop();
        }
        String res = "";
        while (!stack.isEmpty()){
            res = stack.pop()+res;
        }
        while (res.length()!=0 && res.startsWith("0")){
            res = res.substring(1);
        }
        return res.length()==0?"0":res;
    }

    /**
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits3(String num, int k) {
        int[] nums = new int[num.length()-k];
        int len = 0;
        for (int i=0;i<num.length();i++){
            while (len>0 && num.length()-i > nums.length-len  && nums[len-1] > num.charAt(i)-'0'){
                len--;
            }
            if (len<nums.length){
                nums[len++] = num.charAt(i)-'0';
            }
        }
        StringBuffer sb = new StringBuffer();
        int i=0;
        while (i<nums.length && nums[i]==0) i++;
        for (;i<nums.length;i++){
            sb.append(nums[i]);
        }
        return sb.length()==0?"0":sb.toString();
    }
    @Test
    public void test(){
        System.out.println(removeKdigits2("1432219", 3));
        System.out.println(removeKdigits2("100200", 1));
        System.out.println(removeKdigits2("10", 2));
        System.out.println(removeKdigits2("12", 2));
        System.out.println(removeKdigits2("12", 1));
        System.out.println(removeKdigits2("10", 1));
        System.out.println(removeKdigits2("1234567890",1));
        System.out.println(removeKdigits2("1234567890",8));
    }
}
