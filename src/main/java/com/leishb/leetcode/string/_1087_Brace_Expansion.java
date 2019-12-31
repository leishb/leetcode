package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2019/11/25.
 */
public class _1087_Brace_Expansion {


    /**
     * Accepted
     * @param S
     * @return
     */
    public String[] expand(String S) {
        List<String> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        boolean open = false;
        for (char c : S.toCharArray()){
            if (c==',') continue;
            if (c=='{' || c=='}'){
                open = c=='{';
                if (sb.length()!=0) list.add(sb.toString());
                sb = new StringBuffer();
                continue;
            }
            if (!open){
                if (sb.length()!=0) list.add(sb.toString());
                sb = new StringBuffer();
            }
            sb.append(c);
        }
        if (sb.length()!=0) list.add(sb.toString());
        dfs(list, ans, 0, "");
        Collections.sort(ans);
        return ans.toArray(new String[ans.size()]);
    }


    public String[] expand2(String S) {
        List<String> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        S = S.replaceAll("\\{", " ").replaceAll("}", " ");
        for (String s : S.trim().split(" ")){
            if (s.indexOf(",")!=-1){
                list.add(s.replace(",", ""));
                continue;
            }
            for (char c : s.toCharArray()){
                list.add(""+c);
            }
        }
        dfs(list, ans, 0, "");
        Collections.sort(ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(List<String> list, List<String> ans, int pos, String s){
        if (pos==list.size()){
            ans.add(s);
            return;
        }
        for (char c : list.get(pos).toCharArray()){
            dfs(list, ans, pos+1, s+c);
        }
    }


    @Test
    public void test(){
        expand("{a,b}c{d,e}f");
    }
}
