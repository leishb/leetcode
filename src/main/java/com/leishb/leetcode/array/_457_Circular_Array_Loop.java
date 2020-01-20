package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2020/1/14.
 */
public class _457_Circular_Array_Loop {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        for (int i=0;i<nums.length;i++){
            if (!visited[i]) {
                boolean forward = nums[i] > 0;
                if (dfs(nums, i, visited, new boolean[nums.length], forward)){
                    return true;
                }
            }
        }
        return false;
    }


    private boolean dfs(int[] nums,  int cur,  boolean[] visited, boolean[] onstack,  boolean forward){
        if ((nums[cur] <0 && forward) || (nums[cur] >0 && !forward)){
            return false;
        }
        visited[cur] = true;
        onstack[cur] = true;
        int next = getNext(nums, cur);
        if (onstack[next] && next!=cur)return true;
        if (visited[next])return false;
        return dfs(nums, next, visited, onstack,  forward);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean circularArrayLoop2(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0) continue;
            int slow = i, fast = getNext(nums, slow);
            while (nums[i] * nums[fast] > 0 && nums[i] * nums[getNext(nums, fast)] > 0){
                if (slow==fast){
                    if (slow==getNext(nums, slow)) break;
                    return true;
                }
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
            }
            int sign = nums[i];
            slow = i;
            while (sign * nums[slow] > 0){
                int next =getNext(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }
        return false;
    }

    private int getNext(int[] nums, int i){
        int n = nums.length;
        int next = i+nums[i];
        if (next<0) next = next % n + n;
        if (next>=n) next%=n;
        return next;
    }

    @Test
    public void test(){
        circularArrayLoop2(new int[]{2,-1,1,2,2});
    }
}
