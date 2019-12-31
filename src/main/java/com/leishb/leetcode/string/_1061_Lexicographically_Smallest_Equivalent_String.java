package com.leishb.leetcode.string;

/**
 * Created by me on 2019/11/21.
 */
public class _1061_Lexicographically_Smallest_Equivalent_String {


    /**
     * Accepted
     * @param A
     * @param B
     * @param S
     * @return
     */
    public String smallestEquivalentString(String A, String B, String S) {
        int[] parents = new int[26];
        int[] min = new int[26];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
            min[i] = i;
        }
        for (int i=0;i<A.length();i++){
            int rx = find(A.charAt(i)-'a', parents);
            int ry = find(B.charAt(i)-'a', parents);
            if (rx!=ry) parents[rx] = ry;
        }
        for (int i=0;i<26;i++){
            int r = find(i, parents);
            min[r] = Math.min(min[r], i);
        }
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<S.length();i++){
            int p = find(S.charAt(i)-'a', parents);
            sb.append((char)(min[p]+'a'));
        }
        return sb.toString();
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
