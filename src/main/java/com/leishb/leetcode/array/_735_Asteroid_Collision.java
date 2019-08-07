package com.leishb.leetcode.array;

import java.util.Stack;

/**
 * Created by me on 2019/8/6.
 */
public class _735_Asteroid_Collision {


    /**
     * Accepted
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids==null||asteroids.length<=1)return asteroids;
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i=1;i<asteroids.length;i++){
            int cur = asteroids[i];
            if (cur>=0){
                stack.push(cur);
            }else if (stack.isEmpty() || stack.peek()<=0){
                stack.push(cur);
            }else if (stack.peek() >0){
                while (!stack.isEmpty() && stack.peek()>0 && stack.peek() < -cur){
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == -cur){
                    stack.pop();
                }else if (stack.isEmpty() || stack.peek()<=0){
                    stack.push(cur);
                }
            }
        }
        int[] ans = new int[stack.size()];
        int i=stack.size()-1;
        while (!stack.isEmpty()){
            ans[i--] = stack.pop();
        }
        return ans;
    }
}
