package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/12/13.
 */
public class _1147_Longest_Chunked_Palindrome_Decomposition {


    /**
     * Accepted
     * @param text
     * @return
     */
    public int longestDecomposition(String text) {
        int ans = 0;
        int i =0 , len= 1, rightEnd = text.length();
        while (len*2<=rightEnd-i){
            int j = i+len-1;
            String left = text.substring(i, j+1);
            String right = text.substring(rightEnd-len ,rightEnd);
            if (left.equals(right)){
                ans+=2;
                i = j+1;
                rightEnd = rightEnd-len;
                len = 1;
            }else {
                len++;
            }
        }
        if (i<=rightEnd-1)ans++;
        return ans;
    }



    public int longestDecomposition2(String text) {
        String l = "", r = "";
        int ans = 0;
        for (int i=0;i<text.length();i++){
            l = l + text.charAt(i);
            r = text.charAt(text.length()-i-1) + r;
            if (l.equals(r)) {
                ans++;
                l = "";
                r = "";
            }
        }
        return ans;
    }

    @Test
    public void test(){
//        longestDecomposition("antaprezatepzapreanta");
        longestDecomposition("elvtoelvto");
        longestDecomposition2("aaa");
    }
}
