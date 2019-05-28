package com.leishb.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by me on 2019/5/28.
 */
public class ValidSerialization {

    /**
     * Accepted
     * @param preorder
     * @return
     */
    //"9,3,4,#,#,1,#,#,2,#,6,#,#"
    public boolean isValidSerialization(String preorder) {
        if ("#".equals(preorder)){
            return true;
        }
        String[] ss = preorder.split(",");
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i=0;i<ss.length;i++){
            count.put(i, 2);
        }
        for (int i=0;i<ss.length;i++){
            if (ss[i].equals("#")){
                if (stack.isEmpty()){
                    return false;
                }
                int p = stack.peek();
                count.put(p, count.get(p)-1);
                while (count.get(p)==0){
                    stack.pop();
                    if (stack.isEmpty()){
                        return i==ss.length-1;
                    }
                    p = stack.peek();
                    count.put(p, count.get(p)-1);
                }
            }else {
                stack.push(i);
            }
        }
        return stack.isEmpty();
    }


    /**
     * Accepted
     * @param preorder
     * @return
     */
    public boolean isValidSerialization2(String preorder) {
        String[] ss = preorder.split(",");
        int degree = -1;
        for (int i=0;i<ss.length;i++){
            degree++;
            if (degree>0){
                return false;
            }
            if (!"#".equals(ss[i])) {
                degree -=2;
            }
        }
        return degree==0;
    }

    @Test
    public void test(){
        Assert.assertTrue(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        Assert.assertTrue(isValidSerialization("1,#,#"));
        Assert.assertFalse(isValidSerialization("1,#,#,2"));
        Assert.assertFalse(isValidSerialization("1,#"));

        Assert.assertTrue(isValidSerialization2("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        Assert.assertTrue(isValidSerialization2("1,#,#"));
        Assert.assertFalse(isValidSerialization2("1,#,#,2"));
        Assert.assertFalse(isValidSerialization2("1,#"));
    }

}
