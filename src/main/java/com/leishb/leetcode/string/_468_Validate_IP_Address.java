package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by me on 2019/7/12.
 */
public class _468_Validate_IP_Address {


    /**
     * Accepted
     * @param IP
     * @return
     */
    public String validIPAddress(String IP) {
        if (isIPv4(IP)){
            return "IPv4";
        }
        if (isIPv6(IP)){
            return "IPv6";
        }
        return "Neither";
    }


    private boolean isIPv4(String IP){
        String[] dots = IP.split("\\.");
        if (dots.length!=4 || IP.endsWith(".") || IP.startsWith(".")){
            return false;
        }
        for (String s : dots){
            if (!validateIPv4(s)){
                return false;
            }
        }
        return true;
    }

    private boolean validateIPv4(String dot){
        if (dot.length()>3  || dot.length()==0){
            return false;
        }
        if (dot.startsWith("0") && dot.length()>1){
            return false;
        }
        int num = 0;
        for (int i=0;i<dot.length();i++){
            char c = dot.charAt(i);
            if (c-'0'<0 || c-'0'>9){
                return false;
            }
            num = (num * 10) + (c-'0');
        }
        return num<=255;
    }


    private boolean isIPv6(String IP){
        String[] dots = IP.split(":");
        if (dots.length!=8 || IP.endsWith(":") || IP.startsWith(":")){
            return false;
        }
        for (String s : dots){
            if (!validateIPv6(s)){
                return false;
            }
        }
        return true;
    }

    /**
     * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
     Output: "IPv6"
     * @param dot
     * @return
     */
    private boolean validateIPv6(String dot){
        if (dot.length()>4 || dot.length()==0){
            return false;
        }
        List<Character> chars = Arrays.asList('0', '1', '2', '3', '4','5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f');
        for (char c : dot.toLowerCase().toCharArray()){
            if (!chars.contains(c)){
                return false;
            }
        }
        return true;
    }



    @Test
    public void test(){
        Assert.assertTrue("IPv4".equals(validIPAddress("172.16.254.1")));
        Assert.assertTrue("IPv6".equals(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334")));
        Assert.assertTrue("Neither".equals(validIPAddress("256.256.256.256")));
    }
}
