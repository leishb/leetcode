package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/10/17.
 */
public class _772_Basic_Calculator_III {


    /**
     *
     "1 + 1" = 2
     " 6-4 / 2 " = 4
     "2*(5+5*2)/3+(6/2+8)" = 21
     "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
     * @param s
     * @return
     */
    public int calculate(String s) {
        int i=0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        char prev = ' ';
        while (i<s.length()){
            char c = s.charAt(i);
            if (c==' '){
                i++;
                continue;
            }else if (c >= '0' && c<='9'){
                int k = 0;
                while (i<s.length() && s.charAt(i) >= '0' && s.charAt(i)<='9'){
                    k = k*10 + (s.charAt(i)-'0');
                    i++;
                }
                nums.push(k);
                if (!ops.isEmpty()){
                    if (ops.peek()=='*' || ops.peek()=='/'){
                        int second = nums.pop();
                        int first = nums.pop();
                        nums.push(ops.peek()=='*'?first*second:first/second);
                        ops.pop();
                    }
                }
            } else{
                ops.push(c);
                if (c==')'){
                    int k = 0;
                    char left = ops.pop();
                    while (left!='('){
                        int num1 = nums.pop();
                        left = ops.pop();
                        k += left=='-'?-1*num1:num1;
                    }
                    nums.push(k);
                    if (!ops.isEmpty()){
                        if (ops.peek()=='*' || ops.peek()=='/'){
                            int second = nums.pop();
                            int first = nums.pop();
                            nums.push(ops.peek()=='*'?first*second:first/second);
                            ops.pop();
                        }
                    }
                }else if ((c=='+' || c=='-') && prev == '('){
                    nums.push(0);
                }
                i++;
            }
            prev = c;
        }
        int ans = 0;
        while (!ops.isEmpty()){
            char op = ops.pop();
            int num = nums.pop();
            ans += op=='-'?-1*num:num;
        }
        ans += nums.isEmpty()?0:nums.pop();
        return ans;
    }



    public int calculate2(String s) {
        int i=0;
        char sign = '+';
        Stack<Integer> nums = new Stack<>();
        while (i<s.length()){
            char c = s.charAt(i);
            if (c==' '){
                i++;
                continue;
            }
            if (c=='('){
                int j = i;
                int count = 0;
                while (j<s.length()){
                    if (s.charAt(j)=='(') count++;
                    if (s.charAt(j)==')') count--;
                    j++;
                    if (count==0) break;
                }
                int innerValue = calculate2(s.substring(i+1, j-1));
                i = j;
                if (sign=='+'){
                    nums.push(innerValue);
                }else if (sign=='-'){
                    nums.push(-1 * innerValue);
                }else if (sign=='*'){
                    nums.push(nums.pop() * innerValue);
                }else {
                    nums.push(nums.pop()/innerValue);
                }
            }else if (Character.isDigit(c)){
                int num = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i)-'0');
                    i++;
                }
                if (sign=='+'){
                    nums.push(num);
                }else if (sign=='-'){
                    nums.push(-1 * num);
                }else if (sign=='*'){
                    nums.push(nums.pop() * num);
                }else {
                    nums.push(nums.pop()/num);
                }
            }else {
                sign = c;
                i++;
            }
        }
        int ans = 0;
        while (!nums.isEmpty()){
            ans += nums.pop();
        }
        return ans;
    }


    public int calculateII(String s) {
        int i = 0;
        char sign = '+';
        Stack<Integer> nums = new Stack<>();
        while (i<s.length()){
            char c = s.charAt(i);
            if (c==' '){
                i++;
            }else if (Character.isDigit(c)){
                int k = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))){
                    k = k*10 + (s.charAt(i)-'0');
                    i++;
                }
                if (sign=='+'){
                    nums.push(k);
                }else if (sign=='-'){
                    nums.push(-k);
                }else if (sign=='*'){
                    nums.push(nums.pop() * k);
                }else {
                    nums.push(nums.pop() / k);
                }
            }else {
                sign = c;
                i++;
            }
        }
        int ans = 0;
        while (!nums.isEmpty()){
            ans += nums.pop();
        }
        return ans;
    }


    public int calculateI(String s) {
        Stack<Integer> nums = new Stack<>();
        int i = 0;
        char sign = '+';
        while (i<s.length()){
            char c = s.charAt(i);
            if (c==' '){
                i++;
                continue;
            }else if (c=='(' ){
                int j = i;
                int count = 0;
                while (j<s.length()){
                    if (s.charAt(j)=='(')count++;
                    if (s.charAt(j)==')')count--;
                    if (count==0)break;
                    j++;
                }
                int k = calculateI(s.substring(i+1, j));
                i = j+1;
                nums.push(sign=='+'?k:-k);
            } else if (Character.isDigit(c)){
                int k = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))){
                    k =k*10 + s.charAt(i)-'0';
                    i++;
                }
                nums.push(sign=='+'?k:-k);
            } else {
                sign = c;
                i++;
            }
        }
        int ans = 0;
        while (!nums.isEmpty()){
            ans += nums.pop();
        }
        return ans;
    }
    @Test
    public void test(){
        Assert.assertTrue(calculate2("2*(5+5*2)/3+(6/2+8)")==21);
        Assert.assertTrue(calculate2("(2+6*3+5-(3*14/7+2)*5)+3")==-12);
        Assert.assertTrue(calculate2("6-4/2")==4);
        Assert.assertTrue(calculateI("1 + 1")==2);
        Assert.assertTrue(calculate2("1-(-7)")==8);
        Assert.assertTrue(calculate2("-(1+12)+(4-2)")==-11);
    }
}
