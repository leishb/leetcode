package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/12/23.
 */
public class _681_Next_Closest_Time {


    /**
     * Accepted
     * @param time
     * @return
     */
    public String nextClosestTime(String time) {
        boolean[] seen = new boolean[10];
        for (char c : time.toCharArray()){
            if (c==':') continue;
            seen[c-'0'] = true;
        }
        List<String> list = new ArrayList<>();
        generate(list, "");
        int start = search(list, time.substring(0, 2) + time.substring(3));
        String s = find(list, start+1, seen);
        if ("".equals(s)){
            s = find(list, 0, seen);
        }
        return s.substring(0, 2) + ":" + s.substring(2);
    }

    int[] boud = new int[]{2, 3, 5, 9};

    private void generate(List<String> list, String s){
        if (s.length()==4){
            list.add(s);
            return;
        }
        int pos = s.length();
        int end = pos==1&&(s.charAt(0) < '2') ? 9 : boud[pos];
        for (int i=0;i<=end;i++){
            generate(list, s+i);
        }
    }

    private int search(List<String> list, String target){
        int low = 0, hi = list.size()-1;
        while (low<=hi){
            int m = (low+hi)/2;
            if (target.equals(list.get(m))){
                return m;
            }
            if (target.compareTo(list.get(m)) > 0){
                low = m+1;
            }else {
                hi = m-1;
            }
        }
        return 0;
    }


    private String find(List<String> list, int start, boolean[] seen){
        for (int i=start;i<list.size();i++){
            String s = list.get(i);
            int j = 0;
            for (;j<s.length();j++){
                if (!seen[s.charAt(j)-'a']) break;
            }
            if (j==s.length()) return list.get(i);
        }
        return "";
    }


    public String nextClosestTime2(String time) {
        Set<Integer> set = new HashSet<>();
        set.add(time.charAt(0)-'0');
        set.add(time.charAt(1)-'0');
        set.add(time.charAt(3)-'0');
        set.add(time.charAt(4)-'0');

        int minutes = Integer.parseInt(time.substring(3));
        int hours = Integer.parseInt(time.substring(0, 2));
        String result = "";
        while (true){
            minutes++;
            hours = (hours+minutes/60)%24;
            minutes %=60;
            if (set.contains(minutes/10) && set.contains(minutes%10)
                    && set.contains(hours/10) && set.contains(hours%10)) {
                result = (hours<10?"0"+hours:hours) + ":" + (minutes<10?"0"+minutes:minutes);
                break;
            }
        }
        return result;
    }

    @Test
    public void test(){
//        System.out.println(nextClosestTime2("19:34"));
        System.out.println(nextClosestTime2("23:34"));
    }

}
