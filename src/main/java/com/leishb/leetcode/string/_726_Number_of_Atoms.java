package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by me on 2019/10/18.
 */
public class _726_Number_of_Atoms {

    /**
     * Accepted
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = helper(formula);
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.putAll(map);
        StringBuffer sb = new StringBuffer();
        for (String key : tm.keySet()){
            sb.append(key);
            if (tm.get(key)>1)sb.append(tm.get(key));
        }
        return sb.toString();
    }

    public Map<String, Integer> helper(String formula) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        while (i<formula.length()){
            char c = formula.charAt(i);
            if (Character.isUpperCase(c)){
                String s = ""+c;
                i++;
                while (i<formula.length() && !Character.isDigit(formula.charAt(i))
                        && !Character.isUpperCase(formula.charAt(i)) && formula.charAt(i)!='('){
                    s += formula.charAt(i);
                    i++;
                }
                if (i<formula.length() && Character.isDigit(formula.charAt(i))){
                    int k = 0;
                    while (i<formula.length() && Character.isDigit(formula.charAt(i))){
                        k = k*10 + formula.charAt(i)-'0';
                        i++;
                    }
                    map.put(s, map.getOrDefault(s, 0)+k);
                }else {
                    map.put(s, map.getOrDefault(s, 0)+1);
                }
            }else if (c=='('){
                int j = i;
                int count = 0;
                while (j<formula.length()){
                    if (formula.charAt(j)=='(')count++;
                    if (formula.charAt(j)==')')count--;
                    if (count==0)break;
                    j++;
                }
                Map<String, Integer> inner = helper(formula.substring(i+1, j));
                i = j+1;
                int k = 0;
                while (i<formula.length() && Character.isDigit(formula.charAt(i))){
                    k = k*10 + formula.charAt(i)-'0';
                    i++;
                }
                k = k==0?1:k;
                for (String key : inner.keySet()){
                    map.put(key, map.getOrDefault(key, 0)+inner.get(key)*k);
                }
            }
        }
        return map;
    }



    @Test
    public void test(){
        Assert.assertTrue(countOfAtoms("K4(ON(SO3)2)2").equals("K4N2O14S4"));
        Assert.assertTrue(countOfAtoms("Mg(OH)2").equals("H2MgO2"));
        Assert.assertTrue(countOfAtoms("H2H1").equals("H3"));
    }
}
