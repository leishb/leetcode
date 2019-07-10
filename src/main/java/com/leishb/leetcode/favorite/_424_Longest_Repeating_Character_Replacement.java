package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/2.
 */
public class _424_Longest_Repeating_Character_Replacement {


    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }


    /**
     * Accepted
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement2(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0;
        int maxLen = 0;
        for (int end = 0;end<len;end++){
            count[s.charAt(end)-'A']++;
            if (countOthers(count) > k){
                count[s.charAt(start)-'A']--;
                start++;
            }
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen;
    }

    private int countOthers(int[] count){
        int max = 0;
        int sum = 0;
        for (int c : count){
            sum += c;
            max = Math.max(c, max);
        }
        return sum - max;
    }

    @Test
    public void test(){
        Assert.assertTrue(characterReplacement("XXXYZAB", 1)==4);
        Assert.assertTrue(characterReplacement("XXXCXXABBAM", 1)==6);
    }
}
