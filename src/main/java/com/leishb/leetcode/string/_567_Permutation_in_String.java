package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/15.
 */
public class _567_Permutation_in_String {

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        int count = s1.length();
        for (int i=0;i<s1.length();i++){
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0)+1);
        }
        for (int i=0;i<s2.length();i++){
            if (map.containsKey(s2.charAt(i)) && map.get(s2.charAt(i)) > 0){
                map.put(s2.charAt(i), map.get(s2.charAt(i))-1);
                count--;
                if (count==0){
                    return true;
                }
            }else if (map.containsKey(s2.charAt(i)) && map.get(s2.charAt(i))==0){
                int j = i-(s1.length()-count);
                for (;j<i;j++){
                    if (s2.charAt(j)==s2.charAt(i)){
                        break;
                    }
                    map.put(s2.charAt(j), map.get(s2.charAt(j))+1);
                    count++;
                }
            } else if (!map.containsKey(s2.charAt(i))) {
                int j = i-(s1.length()-count);
                for (;j<i;j++){
                    map.put(s2.charAt(j), map.get(s2.charAt(j))+1);
                    count++;
                }
            }
        }
        return false;
    }


    @Test
    public void test(){
        checkInclusion("adc", "dcda");
    }
}
