package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BackTracking;
import com.leishb.leetcode.tag.DFS;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/10/25.
 */
@DFS
@BackTracking
public class CombinationSum {

    @Test
    public void test(){
        long start = System.currentTimeMillis();
        System.out.println(combinatinSum(new int[]{2,3,5,6,7}, 7));
        System.out.println(combinatinSum(new int[]{2,3,4,5,6,7}, 7));
        System.out.println(combinatinSum(new int[]{1,2,3,4,5,6,7}, 7));
        System.out.println(combinatinSum(new int[]{1}, 1));
        System.out.println(combinatinSum(new int[]{2}, 4));
        System.out.println(combinatinSum(new int[]{2,3,5,6,7}, 7));
        System.out.println(combinatinSum(new int[]{1,2,3}, 7));
        System.out.println(combinatinSum(new int[]{1,2,2,3}, 7));
        System.out.println(combinatinSum(new int[]{2,2}, 4));
        System.out.println("cost : " + (System.currentTimeMillis()-start));


        System.out.println(combinatinSumWithUnique(new int[]{2,3,5,6,7}, 7));
        System.out.println(combinatinSumWithUnique(new int[]{2,3,4,5,6,7}, 7));
        System.out.println(combinatinSumWithUnique(new int[]{1,2,3,4,5,6,7}, 7));
        System.out.println(combinatinSumWithUnique(new int[]{1}, 1));
        System.out.println(combinatinSumWithUnique(new int[]{2}, 4));
        System.out.println(combinatinSumWithUnique(new int[]{2,3, 5,6,7}, 7));
        System.out.println(combinatinSumWithUnique(new int[]{2,2,2}, 4));
        System.out.println(combinatinSumWithUnique(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println("cost : " + (System.currentTimeMillis()-start));



        System.out.println(combinatinSum3(3,9));
    }

    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinatinSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        recurse(nums, target, 0, list, ret);
        return ret;
    }


    private void recurse(int[] nums, int target  ,int start, List<Integer> list, List<List<Integer>> ret){
        if (target==0){
            ret.add(list);
            return;
        }

        for (int i=start;i<nums.length;i++){
            if (i!=0 && nums[i]==nums[i-1]){
                continue;
            }
            int newTarget = target-nums[i];
            if (newTarget >=0){
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(nums[i]);
                recurse(nums, newTarget, i, copy, ret);
            }else {
                break;
            }
        }
    }


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> combinatinSumWithUnique(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        recurseWithUnique(nums, target, 0, list, ret);
        return ret;
    }


    private void recurseWithUnique(int[] nums, int target, int start, List<Integer> list, List<List<Integer>> ret){
        if (target==0){
            ret.add(list);
            return;
        }
        for (int i=start;i<nums.length;i++){
            /**
             * when we should skip a number? not just it's the same as previous number, but also when it's previous number haven't been added!
             i > cur means cand[i - 1] is not added to the path (you should know why if you understand the algorithm),
             so if cand[i] == cand[i-1], then we shouldn't add cand[i].

             i!=start 表示 nums[i] 在list的当前index出现过
             该循环中添加的元素，在list中的index是一样的
             */
            if (i!=start && nums[i]==nums[i-1]){//i-1个元素，没有添加到path，过滤
                continue;
            }
            int newTarget = target - nums[i];
            if (newTarget >=0){
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(nums[i]);
                recurseWithUnique(nums, newTarget, i+1, copy, ret);
            }else {
                break;
            }
        }
    }


    /**
     * Accepted
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinatinSum3(int k, int n){
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (k>9 || (19-k)*k/2 <n || n <0){
            return ret;
        }
        List<Integer> list = new ArrayList<Integer>();
        recurseSum3(k, n,1,list,ret);
        return ret;
    }


    private void recurseSum3(int k, int target,int start,  List<Integer> list , List<List<Integer>> ret){
        if (k == 0 && target == 0) {
            ret.add(list);
            return;
        }
        if (k==0 && target >0){
            return;
        }
        for (int i=start;i<=9;i++){
            int nt = target-i;
            if (nt>=0){
                List<Integer> copy = new ArrayList<Integer>(list);
                copy.add(i);
                recurseSum3(k-1,nt, i+1, copy ,ret);//attention : i+1
            }else {
                break;
            }
        }
    }
}
