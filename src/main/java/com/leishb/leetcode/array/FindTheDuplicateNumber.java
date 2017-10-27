package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/27.
 */
@BinarySearch
@TwoPointers
public class FindTheDuplicateNumber {

    @Test
    public void test(){
        Assert.assertTrue(findDuplicate(new int[]{1,1})==1);
        Assert.assertTrue(findDuplicate(new int[]{1,2,2})==2);

        Assert.assertTrue(findDuplicate2(new int[]{1,1})==1);
        Assert.assertTrue(findDuplicate2(new int[]{1,2,2})==2);
        Assert.assertTrue(findDuplicate2(new int[]{1,3,4,2,2})==2);
        Assert.assertTrue(findDuplicate2(new int[]{1,5,5,6,2,8,4,7,5,5})==5);
        Assert.assertTrue(findDuplicate2(new int[]{1,3,4,2,1})==1);


        Assert.assertTrue(findDuplicate3(new int[]{1,1})==1);
        Assert.assertTrue(findDuplicate3(new int[]{1,2,2})==2);
        Assert.assertTrue(findDuplicate3(new int[]{1,3,4,2,2})==2);
        Assert.assertTrue(findDuplicate3(new int[]{1,3,4,2,1})==1);
        Assert.assertTrue(findDuplicate3(new int[]{1,5,5,6,2,8,4,7,5,5})==5);
        Assert.assertTrue(findDuplicate3(new int[]{1,3,4,2,1})==1);


        Assert.assertTrue(findDuplicate4(new int[]{0,0})==0);
        Assert.assertTrue(findDuplicate4(new int[]{0,1,1})==1);
        Assert.assertTrue(findDuplicate4(new int[]{0,2,3,1,1})==1);
        Assert.assertTrue(findDuplicate4(new int[]{0,2,3,1,0})==0);
        Assert.assertTrue(findDuplicate4(new int[]{0,4,4,5,1,7,3,6,4,4})==4);
        Assert.assertTrue(findDuplicate4(new int[]{0,2,3,1,0})==0);
    }


    /**
     * Accepted
     * https://leetcode.com/problems/find-the-duplicate-number/description/
     *
     * @see com.leishb.leetcode.math.SetMismatch#findErrorNums6
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int ret = -1;
        for (int i=0;i<nums.length;i++){
            //如果准备标记的值小于0
            if (nums[Math.abs(nums[i])]< 0){
                ret = Math.abs(nums[i]);
                break;
            }else {
                nums[Math.abs(nums[i])] *=-1;
            }
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]<0){
                nums[i] *=-1;
            }
        }
        return ret;
    }

    /**
     * Accepted
     * https://leetcode.com/problems/find-the-duplicate-number/description/
     * @param nums
     * @return
     */
    @BinarySearch
    public int findDuplicate2(int[] nums) {
        int left =1;
        int right =nums.length-1;
        while (left<=right){
            int mid = (right-left)/2 + left;
            int count =0;
            int midCount =0;
            for(int i=0;i<nums.length;i++){
                if (nums[i]==mid){
                    midCount++;
                }
                if (nums[i]<mid){
                    count++;
                }
            }
            if (midCount>1){
                return mid;
            }
            if (count>=mid){//在左侧
                right = mid-1;
            }else {//在右侧
                left = mid +1;
            }
        }
        return 0;
    }

    /**
     * 指针找环
     * <br>{1,3,4,2,1}
     * <br>{1,3,4,2,1} => 0->1->3->2->4->1，指针在3处相遇
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        int slow = nums[0];//对于从1开始的数组，指针从0开始
        int fast = nums[nums[0]];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast=0;
        while (fast!=slow){
            fast=nums[fast];
            slow=nums[slow];
        }
        return fast;
    }


    public int findDuplicate4(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        int slow = nums[nums.length-1];//对于从0开始的数组，指针从len-1开始
        int fast = nums[nums[nums.length-1]];
        while (slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast=nums.length-1;
        while (fast!=slow){
            fast=nums[fast];
            slow=nums[slow];
        }
        return fast;
    }
}
