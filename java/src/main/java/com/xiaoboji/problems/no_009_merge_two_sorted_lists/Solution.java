package com.xiaoboji.problems.no_009_merge_two_sorted_lists;

import com.xiaoboji.common.ListNode;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * l1 = [1,2,4], l2 = [1,3,4]
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode pre = new ListNode();
        pre.next = l1;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val ){
                pre.next = l1;
                l1 = l1.next;
                continue;
            } else {
                pre.next = l2;
                l2 = l2.next;
                pre.next.next = l1;
                pre = pre.next;
            }
        }
        if(l1 != null){
            pre.next = l1;
        }

        if(l2 != null){
            pre.next = l2;
        }
        return pre.next;

    }

}
