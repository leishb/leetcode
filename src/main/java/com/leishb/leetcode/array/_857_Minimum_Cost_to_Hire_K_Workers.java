package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/11/14.
 */
public class _857_Minimum_Cost_to_Hire_K_Workers {


    /**
     * Accepted
     * @param quality
     * @param wage
     * @param K
     * @return
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2];
        for (int i=0;i<quality.length;i++){
            workers[i] = new double[]{quality[i], wage[i]};
        }
        Arrays.sort(workers, (a1, a2)->Double.compare(a1[1]/a1[0], a2[1]/a2[0]));
        double ans = Double.MAX_VALUE, qsum = 0;
        Queue<Double> queue = new PriorityQueue<>();
        for (double[] w : workers){
            double ratio = w[1]/w[0];
            qsum += w[0];
            queue.offer(-w[0]);
            if (queue.size() > K) qsum += queue.poll();
            if (queue.size()==K) ans = Math.min(ans, qsum * ratio);
        }
        return ans;
    }


    private double dfs(double[][] qw, int workers , int k, int pos, double rate, Map<String, Double> memo){
        if (k==workers){
            return 0;
        }
        String key = workers + "," + pos + ","+rate;
        if (memo.containsKey(key)) return memo.get(key);
        double min = Double.MAX_VALUE;
        for (int i = pos;i<qw.length;i++){
            if (workers==0){
                rate = qw[i][1]/qw[i][0];
            }
            if (qw.length-i>=k-workers){
                min = Math.min(min, qw[i][0] *rate +dfs(qw, workers+1,  k, i+1, rate, memo));
            }
        }
        memo.put(key, min);
        return min;
    }


    @Test
    public void test(){
        System.out.println(mincostToHireWorkers(new int[]{10,20,5}, new int[]{70,50,30}, 2));
    }
}
