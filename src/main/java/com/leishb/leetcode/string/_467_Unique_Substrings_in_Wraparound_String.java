package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/9.
 */
public class _467_Unique_Substrings_in_Wraparound_String {


    /**
     * Accepted
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        if (p==null||p.length()==0)return 0;
        int[] lens = new int[26];

        lens[p.charAt(0)-'a']=1;
        int prev = 1;
        for (int i=1;i<p.length();i++){
            if (p.charAt(i)-p.charAt(i-1)==1 || p.charAt(i)-p.charAt(i-1)==-25){
                prev = prev +1;
            }else {
                prev = 1;
            }
            lens[p.charAt(i)-'a'] = Math.max(lens[p.charAt(i)-'a'], prev);
        }
        int ans = 0;
        for (int len : lens){
            ans += len;
        }
        return ans;
    }



    @Test
    public void test(){
        Assert.assertTrue(findSubstringInWraproundString("zab")==6);
    }
}
