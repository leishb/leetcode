package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/5.
 */
public class _955_Delete_Columns_to_Make_Sorted_II {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        int ans = 0;
        String[] ss = new String[A.length];
        for (int i=0;i<A.length;i++){
            ss[i] = "";
        }
        for (int i=0;i<A[0].length();i++){
            for (int j=0;j<A.length;j++){
                ss[j] += A[j].charAt(i);//add
            }
            if (!sorted(ss)){//judge
                for (int j=0;j<A.length;j++){//remove
                    ss[j] = ss[j].substring(0,ss[j].length()-1);
                }
                ans++;
            }
        }
        return ans;
    }


    private boolean sorted(String[] ss){
        for (int i=1;i<ss.length;i++){
            if (ss[i].compareTo(ss[i-1]) <0){
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){
        Assert.assertTrue(minDeletionSize(new String[]{"ca","bb","ac"})==1);
        Assert.assertTrue(minDeletionSize(new String[]{"xc","yb","za"})==0);
        Assert.assertTrue(minDeletionSize(new String[]{"zyx","wvu","tsr"})==3);
        Assert.assertTrue(minDeletionSize(new String[]{"xga","xfb","yfa"})==1);
    }
}
