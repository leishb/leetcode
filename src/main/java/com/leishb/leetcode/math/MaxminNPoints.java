package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/9/19.
 */
public class MaxminNPoints {

    class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }


    @Test
    public void testNPoints(){
        Point[] points = new Point[]{new Point(0,0), new Point(0,0)};
        Point[] p2 = new Point[]{new Point(0,0),new Point(-1,-1), new Point(2,2)};
        Assert.assertTrue(nPointsInOneLine(points)==2);
        Assert.assertTrue(nPointsInOneLine(p2)==3);
    }



    public int nPointsInOneLine(Point[] points){
        int n = points.length;
        if (n== 0||n==1){
            return n;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0;i<n;i++){
            Map<String, Integer> slopMap = new HashMap<String, Integer>();
            int howManyCur = 1;
            int maxUnCur = 0;
            for (int j=i+1;j<n;j++){
                if (points[i].x==points[j].x &&points[i].y==points[j].y){
                    howManyCur+=1;
                }else {
                    String slop = getSlopString(points[i], points[j]);
                    if (slopMap.containsKey(slop)) {
                        slopMap.put(slop, slopMap.get(slop) + 1);
                    } else {
                        slopMap.put(slop, 1);
                    }
                    maxUnCur = Math.max(maxUnCur, slopMap.get(slop));
                }
            }
            result = Math.max(result, maxUnCur+howManyCur);
        }
        return result;
    }


    private String getSlopString(Point p1, Point p2){
        int dividend = p1.y-p2.y;
        int divisor = p1.x-p2.x;
        //dividend 和 divisor 不会同时为0
        int gcd = gcd(Math.abs(dividend), Math.abs(divisor));
        String sign = getSign(dividend,divisor);
        return sign+Math.abs(dividend)/gcd+"/"+Math.abs(divisor)/gcd;
    }

    private int gcd(int a, int b){
        if (b==0){
            return a;
        }
        return gcd(b, a%b);
    }


    private String getSign(int x, int y){
        if((x>0&&y>0) || (x<0&&y<0)) {
            return "+";
        }
        return "-";
    }
}
