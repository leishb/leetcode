package com.leishb.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2017/12/14.
 */
public class SwapNodeData {


    @Test
    public void test(){
        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        Assert.assertTrue(isPalindrome2(head));
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(1);
//        head2.next.next.next = new ListNode(1);
        Assert.assertTrue(isPalindrome2(head2));
    }


    public void swapNode(ListNode head, int key1, int key2){
        if (head == null){
            return;
        }
        ListNode pre1 = null;
        ListNode pre2 = null;
        ListNode cur = head;
        while (cur.next != null){
            if (cur.next.val == key1){
                pre1 = cur;
            }else if (cur.next.val == key2){
                pre2 = cur;
            }
            cur = cur.next;
        }
        if (pre1 == null || pre2 == null){
            return;
        }
        ListNode temp = pre1.next;
        pre1.next = pre2.next;
        pre2.next = pre2.next.next ;

    }



    public ListNode reverse(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null){
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }



    public boolean detectLoop(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }


    public ListNode detectLoop2(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                break;
            }
        }
        if (slow != fast || slow == null){
            return null;
        }
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**
     * Accepted
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeSortedList(ListNode l1, ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode cur;
        if (l1.val < l2.val){
            cur = l1;
            cur1 = l1.next;
        }else {
            cur = l2;
            cur2 = l2.next;
        }
        while (cur1 != null && cur2!= null){
            if (cur1.val < cur2.val){
                cur.next = cur1;
                cur1 = cur1.next ;
            }else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur.next = cur1==null ? cur2 : cur1;
        return l1.val < l2.val ? l1 : l2;
    }

    /**
     * Accepted
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (l1!=null && l2 != null){
            if (l1.val < l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1==null ? l2 : l1;
        return preHead.next;
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head){
        if (head==null){
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        ListNode cur = head;
        while (cur!=null){
            count++;
            cur = cur.next;
        }
        int temp=0;
        cur = head;
        while (temp!=count/2){
            stack.push(cur.val);
            cur = cur.next;
            temp++;
        }
        if (temp * 2 != count){
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            int k = stack.pop();
            if (k != cur.val){
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null){
            slow = slow.next;
        }
        ListNode temp = slow;
        slow = reverse(slow);
        ListNode node = head;
        while (slow!=null){
            if (slow.val!=node.val){
                return false;
            }
            slow = slow.next;
            node = node.next;
        }
        reverse(temp);
        return true;
    }


    /**
     * remove duplicate node from sorted list
     * Accepted
     * @param head
     */
    public ListNode removeDuplicate(ListNode head){
        ListNode prev = head;
        while ( prev != null && prev.next !=null){
            if (prev.val == prev.next.val){
                ListNode dup = prev.next;
                prev.next = prev.next.next;
                dup.next = null;
            }else {
                prev = prev.next;
            }
        }
        return head;
    }


    /**
     * remove duplicate node from unsorted list
     * @param head
     */
    public void removeUnSortDup(ListNode head){
        ListNode ptr1 = head;
        while (ptr1 != null){
            ListNode ptr2 = ptr1;
            while (ptr2.next!=null){
                if (ptr1.val == ptr2.next.val){
                    ListNode dup = ptr2.next;
                    ptr2.next = ptr2.next.next;
                    dup.next = null;
                }else {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }


    public void removeUnSortDup2(ListNode head){
        if (head == null){
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode cur = head;
        set.add(cur.val);
        while (cur.next != null){
            if (!set.contains(cur.next.val)){
                set.add(cur.next.val);
                cur = cur.next;
            }else {
                ListNode dup = cur.next;
                cur.next = cur.next.next;
                dup.next = null;
            }
        }
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode node = head;
        while (node!=null){
            map.put(node.val, map.getOrDefault(node.val, 0)+1);
            node = node.next;
        }
        ListNode preHead = new ListNode(-1);
        preHead.next = head;
        ListNode prev = preHead;
        while (prev.next!=null){
            if (map.get(prev.next.val) >1){
                ListNode dup = prev.next;
                prev.next = prev.next.next;
                dup.next = null;
            }else {
                prev = prev.next;
            }
        }
        return preHead.next;
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head==null){
            return null;
        }
        boolean curDup = false;
        ListNode prevHead = new ListNode(-1);
        prevHead.next = head;
        ListNode prev = prevHead;
        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null){
            if (cur.val == next.val){
                curDup = true;
                ListNode dup = next;
                cur.next = next.next;
                next = cur.next;
                dup.next = null;
            }else if (curDup){
                prev.next = next;
                cur.next = null;
                cur = prev.next;
                next = cur.next;
                curDup = false;
            }else {
                curDup = false;
                prev = cur;
                cur = next;
                next = cur.next;
            }
        }
        if (curDup && next == null){
            prev.next = next;
            cur.next = null;
        }
        return prevHead.next;
    }


    /**
     * Accepted
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode prevHead = new ListNode(-1);
        prevHead.next = head;
        ListNode cur = head;
        ListNode prev = prevHead;
        while (cur != null){
            while (cur.next != null && cur.next.val == cur.val){
                cur = cur.next;
            }
            if (prev.next == cur){
                prev = prev.next;
            }else {
                prev.next = cur.next;
            }
            cur = cur.next;
        }
        return prevHead.next;
    }
}
