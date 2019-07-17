package com.leishb.leetcode.string;

import com.leishb.leetcode.design.NestedInteger;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/7/15.
 */
public class _385_Mini_Parser {


    /**
     * Accepted
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        char[] cs = s.toCharArray();
        Stack<NestedInteger> stack = new Stack<>();
        boolean neg = false;
        for (int i=0;i<cs.length;i++){
            char c = cs[i];
            if (c=='['){
                stack.push(new NestedInteger());
            }else if (c>='0' && c<='9'){
                int num = 0;
                while (i<cs.length && cs[i]>='0' && cs[i]<='9'){
                    num = num*10 +(cs[i]-'0');
                    i++;
                }
                num = neg?num*-1:num;
                if(!stack.isEmpty()){
                    stack.peek().add(new NestedInteger(num));
                }else {
                    return new NestedInteger(num);
                }
                i--;
                neg = false;
            }else if (c == ']'){
                NestedInteger ni = stack.pop();
                if (!stack.isEmpty()){
                    stack.peek().add(ni);
                }else {
                    return ni;
                }
            }else if (c=='-'){
                neg = true;
            }
        }
        return null;
    }


    @Test
    public void test(){
        deserialize("[123,[456,[789]]]");
        deserialize("[123,[-456,[-789]]]");
        deserialize("123");
        deserialize("-123");
    }
}
