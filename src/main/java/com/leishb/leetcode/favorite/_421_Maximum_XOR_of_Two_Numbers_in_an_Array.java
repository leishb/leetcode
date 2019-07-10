package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/2.
 * https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems
 */
public class _421_Maximum_XOR_of_Two_Numbers_in_an_Array {


    class TrieNode {
        TrieNode zero, one;
        int val;
        boolean isEnd;
    }


    class TrieTree{

        TrieNode root ;

        public TrieTree(){
            root = new TrieNode();
        }

        public void insert(int num){
            int j = 1 << 30;
            TrieNode cur = root;
            for (int i=0;i<=30;i++){
                int bit = (j & num) == 0 ? 0 : 1;
                if (bit==0 && cur.zero == null){
                    cur.zero = new TrieNode();
                }
                if (bit==1 && cur.one ==null){
                    cur.one = new TrieNode();
                }
                cur = bit==0?cur.zero:cur.one;
                j >>=1;
            }
            cur.val = num;
            cur.isEnd = true;
        }

        public void insert(int num, TrieNode cur, int level){
            if (level==0){
                cur.val = num;
                cur.isEnd = true;
                return;
            }
            int j = 1 << (level-1);
            int bit = (num&j)==0?0:1;
            if (bit==0){
                if (cur.zero==null){
                    cur.zero = new TrieNode();
                }
                insert(num, cur.zero, level-1);
            }
            if (bit==1){
                if (cur.one==null){
                    cur.one = new TrieNode();
                }
                insert(num, cur.one, level-1);
            }
        }

        public int query(int num){
            int j = 1<<30;
            TrieNode cur = root;
            for (int i=0;i<31;i++){
                int bit = (num&j)==0?0:1;
                if (bit==0){
                    cur = cur.one!=null?cur.one:cur.zero;
                }
                if (bit==1){
                    cur = cur.zero!=null?cur.zero:cur.one;
                }
                j>>=1;
            }
            return cur.val^num;
        }

        public int queryCountXORLessThanK(int num, int k){

            return 0;
        }
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        TrieTree tree = new TrieTree();
        for (int num : nums){
            tree.insert(num, tree.root, 31);
        }
        TrieNode cur = tree.root;
        while (cur.one==null || cur.zero ==null){
            cur = cur.one==null?cur.zero:cur.one;
        }
        return helper(cur.zero, cur.one);
    }

    //Given an array of integers, find the subarray with maximum XOR.
    public int findMaximumXOROfSubArray(int[] nums){
        TrieTree tree = new TrieTree();
        tree.insert(0);
        int ans = 0;
        int pre = 0;
        for (int i=0;i<nums.length;i++){
            pre = nums[i] ^ pre;
            tree.insert(pre);//利用arr(0->R) XOR arr(0->i) = arr(i+1, R)
            ans = Math.max(ans, tree.query(pre));
        }
        return ans;
    }

    private int helper(TrieNode zero, TrieNode one){
        if (zero.isEnd && one.isEnd){
            return zero.val ^ one.val;
        }
        if (zero.one==null){
            return helper(zero.zero, one.one==null?one.zero:one.one);
        }else if (zero.zero==null){
            return helper(zero.one, one.zero==null?one.one:one.zero);
        }else if (one.zero==null){
            return helper(zero.zero, one.one);
        }else if (one.one==null){
            return helper(zero.one, one.zero);
        }else {
            return Math.max(helper(zero.one, one.zero), helper(zero.zero, one.one));
        }
    }



    @Test
    public void test(){
        Assert.assertTrue(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8})==28);
        int[] nums = new int[]{3, 10, 5, 25, 2, 8};
        TrieTree tree = new TrieTree();
        for (int num : nums){
            tree.insert(num, tree.root, 31);
        }
        Assert.assertTrue(tree.query(5)==28);
    }
}
