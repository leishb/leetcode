package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.BackTracking;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2017/11/23.
 */
@BackTracking
public class RestoreIpAddresses {


    @Test
    public void test(){
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses2("25525511135"));
        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses2("010010"));
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length()>12){
            return ans;
        }
        List<String> list = new ArrayList<>();
        backtracking(s, 0,0, "", list);
        for (String ip : list){
            if (validIp(ip, s.length()+3)){
                ans.add(ip);
            }
        }
        return ans;
    }


    private void backtracking(String s, int start, int count, String ip, List<String> ans){
        if (start==s.length() && count==4){
            ans.add(ip);
            return;
        }
        if (count==4 && start!=s.length()){
            return;
        }
        if (start==s.length() && count!=4){
            return;
        }
        for (int i=start;i<s.length();i++){
            for (int j=i+1;j<=i+3 && j<=s.length();j++){
                if (Integer.parseInt(s.substring(i, j)) >255){
                    return;
                }
                backtracking(s, j, count+1, ip.equals("") ? ip+s.substring(i,j) : ip + "." + s.substring(i,j), ans);
            }
        }
    }

    private boolean validIp(String ip, int count){
        if (ip.length()!=count){
            return false;
        }
        for (String s : ip.split("\\.")){
            if (s.startsWith("0") && s.length()!=1){
                return false;
            }
        }
        return true;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses2(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length()>12){
            return ans;
        }
        List<List<Integer>> splices =new ArrayList<>();
        helper(s.length(), new ArrayList<>(), splices);
        for (List<Integer> splice : splices){
            int sum=0;
            StringBuffer sb = new StringBuffer();
            for (Integer i: splice){
                String ss = s.substring(sum, sum+i);
                if (ss.startsWith("0") && ss.length()!=1){
                    break;
                }
                if (Integer.parseInt(ss) >255){
                    break;
                }
                sb.append(s.substring(sum, sum+i)).append(".");
                sum+=i;
            }
            if (sb.length()==s.length()+4){
                ans.add(sb.substring(0, sb.length()-1));
            }
        }
        return ans;
    }


    private void helper(int target, List<Integer> list, List<List<Integer>> ret){
        if (target==0 && list.size()==4){
            ret.add(list);
            return;
        }
        for (int i=1;i<=3;i++){
            int nt = target-i;
            if (nt >=0){
                List<Integer> copy = new ArrayList<>(list);
                copy.add(i);
                helper(nt, copy, ret);
            }else {
                break;
            }
        }
    }
}
