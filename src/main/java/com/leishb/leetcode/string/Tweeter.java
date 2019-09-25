package com.leishb.leetcode.string;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2018/1/16.
 */
public class Tweeter {


    @Test
    public void test(){
        Tweeter tweeter = new Tweeter();
        tweeter.postTweet(1, 5);
        System.out.println(tweeter.getNewsFeed(1));
        tweeter.follow(1,2);
        tweeter.postTweet(2,6);
        System.out.println(tweeter.getNewsFeed(1));
    }



    Map<Integer, PriorityQueue<Integer>> map;
    Map<Integer, List<Integer>> tweets ;

    /** Initialize your data structure here. */
    public Tweeter() {
        map = new HashMap();
        tweets = new HashMap();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (map.containsKey(userId)){
            map.get(userId).offer(tweetId);
        }else {
            PriorityQueue<Integer> queue = new PriorityQueue((o1, o2) -> Integer.compare((Integer) o2, (Integer) o1));
            queue.offer(tweetId);
            map.put(userId, queue);
        }
        if (tweets.containsKey(userId)){
            tweets.get(userId).add(tweetId);
        }else {
            tweets.put(userId, new ArrayList<>());
            tweets.get(userId).add(tweetId);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList();
        PriorityQueue<Integer> queue = map.getOrDefault(userId,  new PriorityQueue((o1, o2) -> Integer.compare((Integer) o2, (Integer) o1)));
        while(ans.size() < 10 && !queue.isEmpty()){
            ans.add(queue.poll());
        }
        for(Integer twId : ans){
            queue.offer(twId);
        }
        return ans;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        List<Integer> list = tweets.getOrDefault(followerId, new ArrayList());
        for(Integer tw : list){
            if (map.containsKey(followeeId)){
                map.get(followeeId).offer(tw);
            }else {
                PriorityQueue<Integer> queue = new PriorityQueue((o1, o2) -> Integer.compare((Integer) o2, (Integer) o1));
                queue.offer(tw);
                map.put(followeeId, queue);
            }
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> list = tweets.getOrDefault(followerId, new ArrayList());
        for(Integer tw : list){
            map.getOrDefault(followeeId,  new PriorityQueue((o1, o2) -> Integer.compare((Integer) o2, (Integer) o1))).remove(tw);
        }
    }
}
