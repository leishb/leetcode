package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/23.
 */
public class _621_Task_Scheduler {


    /**
     * https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] cout = new int[26];
        for (char c : tasks){
            cout[c-'A']+=1;
        }
        int max = 0;
        int maxCount = 0;
        for (int i=0;i<26;i++){
            if (cout[i]==max){
                maxCount+=1;
            }else if (max < cout[i]){
                max = cout[i];
                maxCount = 1;
            }
        }
        int gaps = max-1;
        int emptySlots = gaps * (n-maxCount+1);
        int avaliableTasks = tasks.length-maxCount*max;
        int idles = Math.max(0, emptySlots-avaliableTasks);
        return tasks.length+idles;
    }


    /**
     * Accepted
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks){
            freq[c-'A']+=1;
        }
        Queue<int[]> queue = new PriorityQueue<>((a,b)->b[1]-a[1]);
        for (int i=0;i<26;i++){
            if (freq[i]>0){
                queue.offer(new int[]{i, freq[i]});
            }
        }
        int count = 0;
        while (!queue.isEmpty()){
            int intvals = n+1;
            List<Integer> tempList = new ArrayList<>();
            while(intvals>0 && !queue.isEmpty()){
                int i = queue.poll()[0];
                freq[i]--;
                count++;
                intvals--;
                tempList.add(i);
            }
            for (int i : tempList){
                if (freq[i]>0){
                    queue.offer(new int[]{i, freq[i]});
                }
            }
            if (queue.isEmpty()){
                break;
            }
            count += intvals;
        }
        return count;
    }


    public int leastInterval3(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks){
            freq[c-'A']+=1;
        }
        Arrays.sort(freq);
        int count = 0;
        while (freq[25]>0){
            int i =0;
            while (i<=n){
                if (freq[25]==0){
                    break;
                }
                if (i<26 && freq[25-i] > 0){
                    freq[25-i]--;
                }
                i++;
                count++;
            }
            Arrays.sort(freq);
        }
        return count;
    }

    @Test
    public void test(){
        Assert.assertTrue(leastInterval3(new char[]{'A','A','A','B','B','B'},2)==8);
        Assert.assertTrue(leastInterval3(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'},2)==16);
    }
}
