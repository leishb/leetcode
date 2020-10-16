package com.iqiyi.pay.cashier.alg;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {



    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : tasks){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2)->e2.getValue() - e1.getValue());
        pq.addAll(freq.entrySet());
        int ans = 0;
        while (true){
            int excuted = 0;
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();
            while (!pq.isEmpty() && excuted < n + 1){
                Map.Entry<Character, Integer> e = pq.poll();
                e.setValue(e.getValue() - 1);
                tempList.add(e);
                excuted++;
            }
            ans += excuted;
            for (Map.Entry<Character, Integer> e : tempList){
                if (e.getValue() > 0) pq.offer(e);
            }
            if (pq.isEmpty()) break;
            ans += (n + 1) - excuted;
        }
        return ans;
    }


    public String rearrangeString(String s, int k) {
        if (k==0) return s;
        StringBuffer sb = new StringBuffer();
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>();
        list.addAll(freq.entrySet());
        Collections.sort(list, ((e1, e2)-> e1.getValue() != e2.getValue() ?
                e2.getValue() - e1.getValue() : Character.compare(e1.getKey(), e2.getKey())));
        while (!list.isEmpty()){
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();
            for (int i=0;i<list.size();i++){
                Map.Entry<Character, Integer> e = list.get(i);
                if (i<k){
                    e.setValue(e.getValue() - 1);
                    if (e.getValue() > 0)tempList.add(e);
                    sb.append(e.getKey());
                }else {
                    tempList.add(e);
                }
            }
            if (list.size() < k && tempList.size() > 0){
                return "";
            }
            list = tempList;
            Collections.sort(list, ((e1, e2)-> e1.getValue() != e2.getValue() ?
                    e2.getValue() - e1.getValue() : Character.compare(e1.getKey(), e2.getKey())));
        }
        return sb.toString();
    }


    public String rearrangeString2(String s, int k) {
        if (k == 0) return s;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c-'a']++;
        Queue<Integer> pq = new PriorityQueue<>((a, b)->freq[a]==freq[b]?a-b:freq[b]-freq[a]);
        for (int i=0;i<26;i++){
            if (freq[i] > 0) pq.offer(i);
        }
        StringBuffer sb = new StringBuffer();
        while (!pq.isEmpty()){
            int added = 0;
            List<Integer> tempList = new ArrayList<>();
            while (added < k && !pq.isEmpty()){
                int i = pq.poll();
                freq[i]--;
                added++;
                sb.append((char)('a'+ i));
                tempList.add(i);
            }
            for (int i : tempList){
                if (freq[i] > 0) pq.offer(i);
            }
            if (added < k && !pq.isEmpty()) return "";
        }
        return sb.toString();
    }


    public String reorganizeString(String S) {
        StringBuffer sb = new StringBuffer();
        int[] freq = new int[26];
        for (char c : S.toCharArray()) freq[c-'a']++;
        Queue<Integer> pq = new PriorityQueue<>((a, b)->freq[a]==freq[b]?a-b:freq[b]-freq[a]);
        for (int i=0;i<26;i++){
            if (freq[i] > 0) pq.offer(i);
        }
        while (!pq.isEmpty()){
            int added = 0;
            List<Integer> tempList = new ArrayList<>();
            while (!pq.isEmpty() && added < 2){
                int i = pq.poll();
                sb.append((char)('a'+i));
                added++;
                freq[i]--;
                tempList.add(i);
            }
            for (int i : tempList){
                if (freq[i] > 0) pq.offer(i);
            }
            if (added < 2 && !pq.isEmpty()) return "";
        }
        return sb.toString();
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuffer sb = new StringBuffer();
        int[] count = new int[]{a, b,c};
        Queue<Integer> pq = new PriorityQueue<>((i, j)->count[j] - count[i]);
        for (int i=0;i<=2;i++){
            if (count[i] > 0) pq.offer(i);
        }
        int prev = -1, prevCount = 0;
        while (!pq.isEmpty()){
            int i = pq.poll();
            List<Integer> temp = new ArrayList<>();
            temp.add(i);
            if (i != prev || prevCount < 2){
                sb.append((char)('a' + i));
                prevCount = i!= prev ? 1 : prevCount+1;
                prev = i;
                count[i]--;
            }else if (!pq.isEmpty()){
                int j = pq.poll();
                sb.append((char)('a' + j));
                prev = j;
                prevCount = 1;
                count[j]--;
                temp.add(j);
            }else {
                return sb.toString();
            }
            for (int k : temp){
                if (count[k] > 0) pq.offer(k);
            }
        }
        return sb.toString();
    }


    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n<=1) return 0;
        int[] buy = new int[n], sell = new int[n];
        buy[0] = -prices[0];
        int ans = 0;
        for(int i=1;i<n;i++){
            sell[i] = Math.max(prices[i] + buy[i-1], sell[i-1]);
            buy[i] = Math.max(buy[i-1], -prices[i]);
            if (i > 1){
                buy[i] = Math.max(buy[i], sell[i-2]-prices[i]);
            }
            ans = Math.max(ans, sell[i]);
            ans = Math.max(ans, buy[i]);
        }
        return ans;
    }


    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n<=1) return 0;
        int buy = -prices[0], sell = 0, rest = 0;
        int ans = 0;
        for(int i=1;i<n;i++){
            int prevSell = sell;
            sell = Math.max(sell, prices[i] + buy);
            buy = Math.max(buy, -prices[i]);

        }
        return ans;
    }


    @org.junit.Test
    public void test(){
//        System.out.println(rearrangeString("aabbcc", 3));


        System.out.println(longestDiverseString(1, 1, 7));


    }
}
