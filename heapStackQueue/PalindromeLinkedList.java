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

// �ж��ǲ��ǻ����Ӵ�

public class PalindromeLinkedList {
	
	// ����ֱ�ӽ���������ת�ķ������в�ͨ�ģ���Ϊԭ����ᷢ���仯�������Ҫ������ת���˼�������Ļ���ֻ�ܽ���ջ��
	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) return true;
		
		// ������ת
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
	
	// ���������ջ�У���ͽ�����ȫ������list���е�˼��һ��
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
		
		// ֻҪ�Ƚ�һ�뼴��
		len >>= 1;
		while(len-- > 0) {
			if(head.val != deque_stack.pop()) return false;
			head = head.next;
		}
		
		return true;
	}
	
	ListNode front_node;
	
	// ��ʵҲ����ͨ���ݹ�����ת��������
	public boolean isPalindrome_3(ListNode head) {
		front_node = head;
		return isPali(head);
	}
	
	
	
	private boolean isPali(ListNode head) {
		if(head != null) {
			// ���֮ǰ�в�ͬ�ģ���ôֻ��Ҫ������������
			if(!isPali(head.next)) return false;
			if(front_node.val != head.val) return false;
			front_node = front_node.next;
		}
		
		return true;
	}

	// ֱ�ӽ�����ת���еĻ����ʹ��м�֮��Ľ��з�ת,�������ʱ����99���ռ���81
	public boolean isPalindrome_1(ListNode head) {
		if(head == null || head.next == null) return true;
		
		// ������ת
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
