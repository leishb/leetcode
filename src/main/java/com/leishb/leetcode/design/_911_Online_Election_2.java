package com.leishb.leetcode.design;

/**
 * Created by me on 2020/1/3.
 */
public class _911_Online_Election_2 {


    /**
     * Accepted
     */
    class TopVotedCandidate {


        int[] times;
        int[] candidates;

        public TopVotedCandidate(int[] persons, int[] times) {
            int maxFreq = 0, candidate = 0;
            this.times = times;
            this.candidates = new int[times.length];
            int[] votes = new int[persons.length+1];
            for (int i=0;i<times.length;i++){
                votes[persons[i]]++;
                if (votes[persons[i]] >= maxFreq){
                    maxFreq = votes[persons[i]];
                    candidate = persons[i];
                }
                candidates[i] = candidate;
            }
        }

        public int q(int t) {
            return candidates[search(times, t)];
        }


        private int search(int[] times, int t){
            int i=0, j = times.length-1;
            while (i<=j){
                int m = (i+j)/2;
                if (times[m] == t){
                    return m;
                }
                if (times[m] > t){
                    j = m-1;
                }else {
                    i = m+1;
                }
            }
            return j;
        }
    }
}
