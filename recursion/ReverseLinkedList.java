package recursion;

/**
 * 206. Reverse Linked List
 * 
 * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 * 
 * @author yzzyq
 *
 */

// 反转一个单链表

public class ReverseLinkedList {
	// 递归反转
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
        	return head;
        }
        
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        // 这个p就是翻转之后的头部
        return p;
    }
    
    // 回溯反转
    public ListNode reverseList_1(ListNode head) {
    	ListNode pre = null;
    	ListNode current = head;
    	
    	while(current != null) {
    		ListNode temp = current.next;
    		current.next = pre;
    		pre = current;
    		current = temp;
    	}
    	
    	return pre;
    }

	public static void main(String[] args) {
		var rll = new ReverseLinkedList();
		
		ListNode five = new ListNode(5,null);
		ListNode four = new ListNode(4,five);
		ListNode three = new ListNode(3,four);
		ListNode second = new ListNode(2,three);
		ListNode first = new ListNode(1,second);

        ListNode result = rll.reverseList_1(first);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}
	}

}
