package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/12.
 */
public class _791_Custom_Sort_String {


    /**
     * Accepted
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        int[] sorts = new int[26];
        int[] counts = new int[26];
        for (int i=0;i<S.length();i++){
            sorts[S.charAt(i)-'a'] = i+1;
        }
        StringBuffer other = new StringBuffer();
        for (int i=0;i<T.length();i++){
            if (sorts[T.charAt(i)-'a']==0){
                other.append(T.charAt(i));
            }else {
                counts[sorts[T.charAt(i)-'a']-1] +=1;
            }
        }
        StringBuffer ans = new StringBuffer();
        for (int i=0;i<counts.length;i++){
            for (int j=0;j<counts[i];j++){
                ans.append(S.charAt(i));
            }
        }
        ans.append(other);
        return ans.toString();
    }


    public String customSortString2(String S, String T) {
        int[] counts = new int[26];
        for (int i=0;i<T.length();i++){
            counts[T.charAt(i)-'a'] += 1;
        }
        StringBuffer ans = new StringBuffer();
        for (int i=0;i<S.length();i++){
            while (counts[S.charAt(i)-'a']-- > 0) ans.append(S.charAt(i));
        }
        for (char c = 'a';c<='z';c++){
            while (counts[c-'a']-->0) ans.append(c);
        }
        return ans.toString();
    }

    @Test
    public void test(){
        Assert.assertEquals("effbbbcaad", customSortString("efbca", "ffbcbbeaad"));
        Assert.assertEquals("effbbbcaad", customSortString2("efbca", "ffbcbbeaad"));
    }
}
