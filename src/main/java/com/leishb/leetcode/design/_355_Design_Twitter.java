package com.leishb.leetcode.design;

import java.util.*;

/**
 * Created by me on 2019/10/31.
 */
public class _355_Design_Twitter {


    Map<Integer, Set<Integer>> followees;
    Map<Integer, Queue<int[]>> tweets;

    int id = 0;

    /** Initialize your data structure here. */
    public _355_Design_Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new PriorityQueue<>((a, b)->b[1]-a[1]));
        tweets.get(userId).offer(new int[]{tweetId, id++});
        followees.putIfAbsent(userId, new HashSet<>());
        if (!followees.get(userId).contains(userId)) followees.get(userId).add(userId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Queue<int[]> queue = new PriorityQueue<>((a, b)->b[1]-a[1]);
        for (int f : followees.getOrDefault(userId, new HashSet<>())){
            Queue<int[]> q = tweets.getOrDefault(f, new PriorityQueue<>((a, b)->b[1]-a[1]));
            List<int[]> list = new ArrayList<>();
            while (!q.isEmpty() && list.size() < 10){
                list.add(q.poll());
            }
            q.addAll(list);
            queue.addAll(list);
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty() && ans.size() < 10){
            ans.add(queue.poll()[0]);
        }
        return ans;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        followees.putIfAbsent(followerId, new HashSet<>());
        followees.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId==followeeId) return;
        followees.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
    }
}
