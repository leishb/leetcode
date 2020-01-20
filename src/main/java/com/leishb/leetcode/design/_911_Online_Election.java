package com.leishb.leetcode.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by me on 2020/1/3.
 */
public class _911_Online_Election {


    /**
     * Accepted
     */
    class TopVotedCandidate {


        TreeMap<Integer, Integer> map;

        public TopVotedCandidate(int[] persons, int[] times) {
            int maxFreq = 0, candidate = 0;
            map = new TreeMap<>();
            Map<Integer, Integer> freq = new HashMap<>();
            for (int i=0;i<persons.length;i++){
                freq.put(persons[i], freq.getOrDefault(persons[i], 0)+1);
                if (freq.get(persons[i]) >= maxFreq){
                    maxFreq = freq.get(persons[i]);
                    candidate = persons[i];
                }
                map.put(times[i], candidate);
            }
        }

        public int q(int t) {
            return map.floorEntry(t).getValue();
        }
    }
}
