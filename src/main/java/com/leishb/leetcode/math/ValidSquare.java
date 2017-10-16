package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/16.
 */
public class ValidSquare {

    @Test
    public void test(){
        Assert.assertTrue(validSquare(new int[]{0,0},new int[]{1,1},new int[]{1,0},new int[]{0,1}));
        Assert.assertTrue(!validSquare(new int[]{0,0},new int[]{2,0},new int[]{0,1},new int[]{2,1}));
        Assert.assertTrue(!validSquare(new int[]{0,0},new int[]{0,1},new int[]{1,0},new int[]{1,0}));
        Assert.assertTrue(validSquare(new int[]{1,5},new int[]{1,9},new int[]{5,5},new int[]{5,9}));
        Assert.assertTrue(validSquare(new int[]{1,0},new int[]{-1,0},new int[]{0,1},new int[]{0,-1}));
        Assert.assertTrue(!validSquare(new int[]{1,0},new int[]{1,0},new int[]{1,1},new int[]{1,1}));
    }

    /**
     * Accepted
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (isSquare(p1,p2,p3,p4)){
            return true;
        }
        if (isSquare(p1,p2,p4,p3)){
            return true;
        }
        if (isSquare(p1,p3,p2,p4)){
            return true;
        }
        if (isSquare(p1,p3,p4,p2)){
            return true;
        }
        if (isSquare(p1,p4,p2,p3)){
            return true;
        }
        if (isSquare(p1,p4,p3,p2)){
            return true;
        }
        return false;
    }


    private boolean isSquare(int[] p1, int[] p2, int[] p3, int[] p4){
        if (isVid(p1,p2,p3,p2) && isVid(p2,p3,p4,p3) &&  isVid(p3,p4,p1,p4) && isVid(p4,p1,p2,p1)){
            return true;
        }
        return false;
    }

    /**
     * p2-p1 cuizhi p3-p4
     * @return
     */
    private boolean isVid(int[] p1, int[] p2, int[] p3, int[] p4){
        int x0=p2[0]-p1[0];
        int y0=p2[1]-p1[1];

        int x1=p3[0]-p4[0];
        int y1=p3[1]-p4[1];

        if (x0==0&&y0==0){
            return false;
        }
        if (x1==0&&y1==0){
            return false;
        }
        return x0*x1+y0*y1==0 && x0*x0+y0*y0==x1*x1+y1*y1;
    }

    /**
     * p2-p1//p3-p4
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    private boolean paralle(int[] p1, int[] p2, int[] p3, int[] p4){
        int x0=p2[0]-p1[0];
        int y0=p2[1]-p1[1];

        int x1=p3[0]-p4[0];
        int y1=p3[1]-p4[1];

        if (x0==0 && x1==0){
            return false;
        }
        if (x1==0 && y1==0){
            return false;
        }
        return getSlopString(x0,y0).equals(getSlopString(x1,y1));
    }

    private String getSlopString(int x, int y){
        int gcd = gcd(Math.abs(x), Math.abs(y));
        String sign = getSign(x,y);
        return sign+Math.abs(x)/gcd+"/"+Math.abs(y)/gcd;
    }

    private String getSign(int x, int y){
        if((x>0&&y>0) || (x<0&&y<0)) {
            return "+";
        }
        return "-";
    }

    private int gcd(int a, int b){
        if (b==0){
            return a;
        }
        return gcd(b, a%b);
    }
}
