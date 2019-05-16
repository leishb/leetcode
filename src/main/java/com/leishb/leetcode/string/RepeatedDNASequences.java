package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/5/14.
 */
public class RepeatedDNASequences {


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<s.length()-9;i++){
            String sub = s.substring(i,i+10);
            int count = map.getOrDefault(sub, 0);
            map.put(sub, count+1);
        }
        for (String key : map.keySet()){
            if(map.get(key)>1){
                result.add(key);
            }
        }
        return result;
    }


    /**
     * Accepted
     * @param m
     * @param n
     * @return
     */
    public int bitAnd(int m, int n){
        if (m==0){
            return 0;
        }
        int factor = 1;
        while (m!=n){
            m>>=1;
            n>>=1;
            factor<<=1;
        }
        return factor * m;
    }




    @Test
    public void test(){
        Assert.assertTrue(bitAnd(5,7)==4);
        Assert.assertTrue(bitAnd(4,8)==0);
        Assert.assertTrue(bitAnd(7,13)==0);
        Assert.assertTrue(bitAnd(11,15)==8);
        Assert.assertTrue(bitAnd(0,1)==0);
        Assert.assertTrue(bitAnd(1,2)==0);
        Assert.assertTrue(bitAnd(7,7)==7);
        Assert.assertTrue(bitAnd(2,4)==0);
        Assert.assertTrue(bitAnd(6,8)==0);
    }
}
