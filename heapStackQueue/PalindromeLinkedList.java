package heapStackQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 234. Palindrome Linked List
 * Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
 * 
 * @author yzzyq
 *
 */

// 判断是不是回文子串

public class PalindromeLinkedList {
	
	// 这种直接将整个链表反转的方法是行不通的，因为原链表会发生变化，如果非要将链表反转这个思想来做的话，只能借助栈了
	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) return true;
		
		// 将链表反转
		ListNode reverse_node = head;
		ListNode reverse_first = reversedList(reverse_node);
		
		while(head != null) {
			if(reverse_first.val != head.val) return false;
			reverse_first = reverse_first.next;
			head = head.next;
		}

		return true;
    }

	private ListNode reversedList(ListNode reverse_node) {
		ListNode pre = null;
		
		while(reverse_node != null) {
			ListNode next_node = reverse_node.next;
			reverse_node.next = pre;
			pre = reverse_node;
			reverse_node = next_node;
		}
		
		return pre;
	}
	
	// 将链表放入栈中，这和将链表全部放入list当中的思想一样
	public boolean isPalindrome_2(ListNode head) {
		if(head == null || head.next == null) return true;
		
		Deque<Integer> deque_stack = new LinkedList<Integer>();
		int len = 0;
		
		ListNode temp = head;
		while(temp != null) {
			deque_stack.push(temp.val);
			temp = temp.next;
			len++;
		}
		
		// 只要比较一半即可
		len >>= 1;
		while(len-- > 0) {
			if(head.val != deque_stack.pop()) return false;
			head = head.next;
		}
		
		return true;
	}
	
	ListNode front_node;
	
	// 其实也可以通过递归来反转整个链表
	public boolean isPalindrome_3(ListNode head) {
		front_node = head;
		return isPali(head);
	}
	
	
	
	private boolean isPali(ListNode head) {
		if(head != null) {
			// 如果之前有不同的，那么只需要传这个结果即可
			if(!isPali(head.next)) return false;
			if(front_node.val != head.val) return false;
			front_node = front_node.next;
		}
		
		return true;
	}

	// 直接将链表反转不行的话，就从中间之后的进行反转,这个方法时间是99，空间是81
	public boolean isPalindrome_1(ListNode head) {
		if(head == null || head.next == null) return true;
		
		// 将链表反转
		ListNode middle_node = getMiddleNode(head);
		ListNode second_node = middle_node.next;
		middle_node.next = null;
		ListNode reverse_first = reversedList(second_node);
		
		while(reverse_first != null) {
			if(reverse_first.val != head.val) return false;
			reverse_first = reverse_first.next;
			head = head.next;
		}

		return true;
    }

	private ListNode getMiddleNode(ListNode head) {
		ListNode low = head;
		ListNode fast = head.next;
		
		while(fast.next != null && fast.next.next != null) {
			low = low.next;
			fast = fast.next.next;
		}
		
		if(fast.next != null && fast.next.next == null) low = low.next;
		
		return low;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode five = new ListNode(1);
		ListNode four = new ListNode(1,five);
		ListNode three = new ListNode(2,four);
		ListNode second = new ListNode(1,three);
		ListNode first = new ListNode(1,second);
		
		PalindromeLinkedList pll = new PalindromeLinkedList();
		boolean isPali = pll.isPalindrome_3(first);
		System.out.println(isPali);
	}

}
