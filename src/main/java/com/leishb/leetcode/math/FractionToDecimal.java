package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2017/9/20.
 */
public class FractionToDecimal {


    @Test
    public void test(){
        Assert.assertTrue(fractionToDecimal(2,1).equals("2"));
        Assert.assertTrue(fractionToDecimal(1,9).equals("0.(1)"));
        Assert.assertTrue(fractionToDecimal(2,15).equals("0.1(3)"));
        Assert.assertTrue(fractionToDecimal(1,7).equals("0.(142857)"));
        Assert.assertTrue(fractionToDecimal(8,7).equals("1.(142857)"));
        Assert.assertTrue(fractionToDecimal(1,5).equals("0.2"));

        Assert.assertTrue(fractionToDecimal(1,1000).equals("0.001"));

        Assert.assertTrue(fractionToDecimal(500,10).equals("50"));

        Assert.assertTrue(fractionToDecimal(-50,8).equals("-6.25"));
        Assert.assertTrue(fractionToDecimal3(-1,-2147483648).equals("0.0000000004656612873077392578125"));
    }

    /**
     * Accepted
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal2(int numerator, int denominator){
        if (denominator==0){
            return "0";
        }
        int sign = getSign(numerator, denominator);
        long d1 = Math.abs((long)numerator);
        long d2 = Math.abs((long)denominator);
        StringBuffer sb = new StringBuffer();
        if (sign<0){
            sb.append("-");
        }
        sb.append(d1/d2);
        d1%=d2;
        if (d1==0){
            return sb.toString();
        }

        sb.append(".");
        //attention
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put((int)d1, sb.length());
        while (d1!=0){
            d1*=10;
            int mod = (int)(d1%d2);
            int num = (int)(d1/d2);
            sb.append(num);
            if (map.containsKey(mod)){
                int index = map.get(mod);
                sb.insert(index, "(");
                sb.append(")");
                break;
            }else {
                map.put(mod, sb.length());
            }
            d1=mod;
        }
        return sb.toString();
    }

    /**
     * Accepted
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator){
        if (denominator==0){
            return "0";
        }
        int sign = getSign(numerator, denominator);
        long d1 = Math.abs((long)numerator);
        long d2 = Math.abs((long)denominator);
        StringBuffer recuringSb = new StringBuffer();
        if (sign<0){
            recuringSb.append("-");
        }
        //可以整除，直接返回
        if (d1%d2==0){
            recuringSb.append(d1/d2);
            return recuringSb.toString();
        }
        //不能整除，加小数点
        recuringSb.append(d1/d2).append(".");
        d1%=d2;
        //保存模数列表
        List<Long> modeList = new ArrayList();
        //保存被除结果列表
        List<Long> retList = new ArrayList();
        modeList.add(d1);
        //保存循环小数出现的位置
        int modeIndex = -1;
        while (true){
            long mode = (d1*10)%d2;
            long ret = (d1*10)/d2;
            for (int i = 0;i< modeList.size();i++){
                if (modeList.get(i)==mode){
                    modeIndex=i;
                    break;
                }
            }
            retList.add(ret);
            //开始出现小数循环了
            if (modeIndex!=-1){
                break;
            }else {
                modeList.add(mode);
            }
            //模数出现0，说明没有循环小数
            if (mode==0){
                break;
            }
            d1=mode;
        }
        for (int i = 0;i< retList.size();i++){
            if (i==modeIndex){
                recuringSb.append("(");
            }
            recuringSb.append(retList.get(i));
        }
        if (modeIndex!=-1){
            recuringSb.append(")");
        }
        return recuringSb.toString();
    }


    private int getSign(int n1, int n2){
        int sign = 1;
        if (n1>0&&n2<0){
            sign=-1;
        }
        if (n1<0&&n2>0){
            sign=-1;
        }
        return sign;
    }


    public String fractionToDecimal3(int numerator, int denominator) {
        boolean sign = sign(numerator, denominator);
        StringBuffer sb = new StringBuffer();
        if(sign) sb.append("-");
        long d1 = Math.abs((long)numerator);
        long d2 = Math.abs((long)denominator);
        sb.append(d1/d2);
        if(d1%d2==0){
            return sb.toString();
        }
        d1%=d2;
        List<Long> modeList = new ArrayList();
        List<Long> retList = new ArrayList();
        int modeIndex = -1;
        modeList.add(d1);
        while(true){
            long mode = (d1*10)%d2;
            long ret = (d1*10)/d2;
            for(int i=0;i<modeList.size();i++){
                if(mode==modeList.get(i)){
                    modeIndex = i;
                    break;
                }
            }
            retList.add(ret);
            if(modeIndex!=-1 || mode ==0){
                break;
            }
            modeList.add(mode);
            d1 = mode;
        }
        sb.append(".");
        for(int i=0;i<retList.size();i++){
            if(i==modeIndex){
                sb.append("(");
            }
            sb.append(retList.get(i));
        }
        if(modeIndex!=-1)sb.append(")");
        return sb.toString();
    }


    public String fractionToDecimal4(int numerator, int denominator) {
        boolean sign = sign(numerator, denominator);
        StringBuffer sb = new StringBuffer();
        if(sign) sb.append("-");
        long d1 = Math.abs((long)numerator);
        long d2 = Math.abs((long)denominator);
        sb.append(d1/d2);
        if(d1%d2==0){
            return sb.toString();
        }
        d1%=d2;
        Map<Long, Integer> map = new HashMap<>();
        sb.append(".");
        map.put(d1, sb.length());
        while (d1!=0){
            long mode = (d1*10)%d2;
            long ret = (d1*10)/d2;
            sb.append(ret);
            if (map.containsKey(mode)){
                sb.insert(map.get(mode), "(");
                sb.append(")");
                break;
            }
            map.put(mode, sb.length());
            d1 = mode;
        }
        return sb.toString();
    }

    private boolean sign(int x, int y){
        return ((x>0&&y<0)||(x<0&&y>0));
    }
}
