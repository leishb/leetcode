package com.leishb.leetcode.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2019/9/26.
 */
public class _71_Simplify_Path {

    char slash = '/';

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int i=0;
        while (i<path.length()){
            if (path.charAt(i)==slash){
                i++;
                continue;
            }
            if (isCurentFolder(i, path)){
                i++;
            }else if (isPrevFolder(i, path)){
                i+=2;
                if (!stack.isEmpty()) stack.pop();
            } else {
                StringBuffer name = new StringBuffer();
                while (i<path.length() && path.charAt(i)!=slash){
                    name.append(path.charAt(i));
                    i++;
                }
                stack.push(name.toString());
            }
        }
        if (stack.isEmpty()) return "/";
        String ans = "";
        while (!stack.isEmpty()){
            ans =  slash + stack.pop() + ans;
        }
        return ans;
    }

    private boolean isPrevFolder(int i, String path){
        return (i+2<path.length() && path.substring(i, i+3).equals("../")) ||
                (i+2==path.length() && path.substring(i, i+2).equals(".."));
    }

    private boolean isCurentFolder(int i, String path){
        return (i+1<path.length() && path.substring(i, i+2).equals("./") ||
                (i+1==path.length()  && path.substring(i, i+1).equals(".")));
    }


    public String simplifyPath2(String path) {
        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String dir : dirs){
            if ("".equals(dir) || ".".equals(dir)) continue;
            if ("..".equals(dir)){
                if (!stack.isEmpty())stack.pop();
            }else {
                stack.push(dir);
            }
        }
        String ans = "";
        if (stack.isEmpty())return "/";
        while (!stack.isEmpty()){
            ans = "/" + stack.pop() + ans;
        }
        return ans;
    }

    @Test
    public void test(){
        simplifyPath2("/home/");
    }
}
