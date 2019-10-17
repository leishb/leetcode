package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/10/16.
 */
public class _165_Compare_Version_Numbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        if (v1.length > v2.length){
            return -compareVersion(version2, version1);
        }
        int i = 0;
        for (;i<v1.length;i++){
            int k = compare(v1[i], v2[i]);
            if (k==0) continue;
            return k;
        }
        for (;i<v2.length;i++){
            if (isAllZero(v2[i])) continue;
            return -1;
        }
        return 0;
    }


    private boolean isAllZero(String s){
        int i = 0;
        while (i<s.length() && s.charAt(i)=='0') i++;
        return i==s.length();
    }

    private int compare(String s1, String s2){
        int i=0, j=0;
        while (i<s1.length() && s1.charAt(i)=='0')i++;
        while (j<s2.length() && s2.charAt(j)=='0')j++;
        if (s1.length()-i > s2.length()-j){
            return 1;
        }
        if (s1.length()-i < s2.length()-j){
            return -1;
        }
        for (;i<s1.length()&&j<s2.length();i++,j++){
            if (s1.charAt(i) < s2.charAt(j)) return -1;
            if (s1.charAt(i) > s2.charAt(j)) return 1;
        }
        if (i==s1.length() && j==s2.length()) return 0;
        return i==s1.length()?-1:1;
    }


    public int compareVersion2(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.max(v1.length, v2.length);
        for (int i=0;i<len;i++){
            int num1 = i<v1.length?Integer.parseInt(v1[i]):0;
            int num2 = i<v2.length?Integer.parseInt(v2[i]):0;
            if (num1<num2){
                return -1;
            }
            if (num1>num2){
                return 1;
            }
        }
        return 0;
    }
    @Test
    public void test(){
        Assert.assertTrue(compareVersion2("7.5.2", "7.5.2.4")==-1);
        Assert.assertTrue(compareVersion2("1.01", "1.001")==0);
        Assert.assertTrue(compareVersion2("0.1", "1.1")==-1);
    }
}
