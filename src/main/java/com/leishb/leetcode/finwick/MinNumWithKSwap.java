package com.iqiyi.pay.config.test.arg;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinNumWithKSwap {



    public String minNum(String num, int k){
        int n = num.length();
        char[] cs = num.toCharArray();
        int i = 0;
        while (i < n && k > 0){
            int minId = i, minC = cs[i];
            for (int j=i;j<=Math.min(k + i, n - 1); j++){
                if (cs[j] < minC){
                    minC = cs[j];
                    minId = j;
                }
            }
            char temp = cs[minId];
            k -= (minId - i);
            for (int j = minId - 1; j>=i;j --){
                cs[j + 1] = cs[j];
            }
            cs[i] = temp;
            i++;
        }
        return new String(cs);
    }


    public String minInteger(String num, int k) {
        int n = num.length();
        List<Integer>[] digits = new List[10];
        for (int i=0;i<10;i++){
            digits[i] = new ArrayList<>();
        }
        for (int i=0;i<num.length();i++){
            digits[num.charAt(i) - '0'].add(i);
        }
        SegmentTree root = buildTree(new int[n], 0, n - 1);
        StringBuffer sb = new StringBuffer();
        boolean[] used = new boolean[n];
        int[] pointers = new int[10];
        int i = 0;
        while (i < n){
            if (used[i]) {
                i++;
                continue;
            }
            int cur = num.charAt(i) - '0';
            boolean find = false;
            for (int j=0;j < cur && k > 0; j++){
                while (pointers[j] < digits[j].size() && digits[j].get(pointers[j]) < i){
                    pointers[j] ++;
                }
                if (pointers[j] == digits[j].size()){
                    continue;
                }
                int index = digits[j].get(pointers[j]);
                int swaped = querySum(root, i + 1, index - 1);
                if (index - i - swaped <= k){
                    k -= (index - i - swaped);
                    char c = num.charAt(index);
                    sb.append(c);
                    used[index] = true;
                    pointers[j]++;
                    update(root, index, 1);
                    find = true;
                    break;
                }
            }
            if (!find){
                sb.append(num.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }


    public String minInteger2(String num, int k) {
        int n = num.length();
        List<Integer>[] digits = new List[10];
        for (int i=0;i<10;i++){
            digits[i] = new ArrayList<>();
        }
        for (int i=0;i<num.length();i++){
            digits[num.charAt(i) - '0'].add(i);
        }
        FinwickTree tree = new FinwickTree(n);
        StringBuffer sb = new StringBuffer();
        boolean[] used = new boolean[n];
        int[] pointers = new int[10];
        int i = 0;
        while (i < n){
            if (used[i]) {
                i++;
                continue;
            }
            int cur = num.charAt(i) - '0';
            boolean find = false;
            for (int j=0;j < cur && k > 0; j++){
                while (pointers[j] < digits[j].size() && digits[j].get(pointers[j]) < i){
                    pointers[j] ++;
                }
                if (pointers[j] == digits[j].size()){
                    continue;
                }
                int index = digits[j].get(pointers[j]);
                int swaped = tree.prefixSum(index) - tree.prefixSum(i + 1);
                if (index - i - swaped <= k){
                    k -= (index - i - swaped);
                    char c = num.charAt(index);
                    sb.append(c);
                    used[index] = true;
                    pointers[j]++;
                    tree.update(index + 1, 1);
                    find = true;
                    break;
                }
            }
            if (!find){
                sb.append(num.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
    class FinwickTree{

        int[] sums;

        FinwickTree(int n){
            sums = new int[n+1];
        }

        public void update(int i, int delta){
            while (i<sums.length){
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        public int prefixSum(int i){
            int ans = 0;
            while (i>0){
                ans += sums[i];
                i -= lowbit(i);
            }
            return ans;
        }

        private int lowbit(int i){
            return i&(-i);
        }
    }

    class SegmentTree{
        SegmentTree left;
        SegmentTree right;
        int start;
        int end;
        int sum;
        SegmentTree(int s, int e, int sum){
            this.start = s;
            this.end = e;
            this.sum = sum;
        }
    }


    public SegmentTree buildTree(int[] nums, int start, int end){
        if (start == end) return new SegmentTree(start, end, nums[start]);
        int mid = (start + end) / 2;
        SegmentTree root = new SegmentTree(start, end, 0);
        SegmentTree left = buildTree(nums, start, mid);
        SegmentTree right = buildTree(nums, mid + 1, end);
        root.left = left;
        root.right = right;
        root.sum = left.sum + right.sum;
        return root;
    }


    private int querySum(SegmentTree root, int start, int end){
        if (end < start) return 0;
        if (root.start == start && root.end == end) return root.sum;
        int mid = (root.start + root.end) / 2;
        if (end <= mid) return querySum(root.left, start, end);
        if (start > mid) return querySum(root.right, start, end);
        return querySum(root.left, start, mid) + querySum(root.right, mid + 1, end);
    }


    private void update(SegmentTree root, int index, int val){
        if (root.start == index && root.end == index) {
            root.sum = val;
            return;
        }
        int mid = (root.start + root.end) / 2;
        if (index <= mid) update(root.left, index, val);
        else update(root.right, index, val);
        root.sum = root.left.sum + root.right.sum;
    }


    public int longestConsecutive(int[] nums){
        Set<Integer> set = new HashSet<>();
        for (int i : nums){
            set.add(i);
        }
        int ans = 0;
        for (int i : nums){
            if (!set.contains(i)) continue;
            int smaller = 0, j = i-1;
            while (set.contains(j)){
                set.remove(j);
                smaller++;
                j--;
            }
            int bigger = 0, k = i + 1;
            while (set.contains(k)){
                set.remove(k);
                bigger++;
                k++;
            }
            ans = Math.max(ans, smaller + bigger + 1);
        }
        return ans;
    }


    private boolean reverseSame(String s){
        int i = 0, j = s.length() - 1;
        while (i <= j){
            if (s.charAt(i) != s.charAt(j)){
                return help(s, i + 1, j) || help(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean help(String s, int i, int j){
        while (i<=j){
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void test(){
//        System.out.println(minNum("7124", 3));
//        System.out.println(minInteger("7124", 3));
        System.out.println(minInteger("4321", 4));
        System.out.println(minInteger("9438957234785635408", 23));
        Assert.assertTrue(longestConsecutive(new int[]{100, 4,200,3,1,2}) == 4);
    }

}
