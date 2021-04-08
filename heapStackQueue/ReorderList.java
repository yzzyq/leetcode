package heapStackQueue;


/**
 * 143. Reorder List
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 * 
 * @author yzzyq
 *
 */

// 对链表重新排序

public class ReorderList {
	
	// 速度和空间都很低
    public void reorderList(ListNode head) {
    	if(head == null || head.next == null) return ;
    	ListNode temp_head = head;
    	ListNode first_end = new ListNode(0);
    	int index = getMiddle(head,first_end);
    	first_end = first_end.next;
    	
    	ListNode second_node = first_end.next;
    	first_end.next = null;
    	
    	System.out.println(index);
    	while(index > 0) {
    		ListNode temp_second = second_node;
    		int temp_index = index;
    		// 这里是通过遍历找出要插入的节点，速度就会降低
    		while(temp_index > 1) {
    			temp_second = temp_second.next;
    			temp_index--;
    		}
    		index--;
    		temp_second.next = temp_head.next;
    		temp_head.next = temp_second;
    		temp_head = temp_head.next.next;
    	}
    	
    }
    
    private int getMiddle(ListNode head, ListNode first_end) {
		ListNode low = head;
		ListNode fast = head;
		int index = 0;
		while(fast.next != null && fast.next.next != null) {
			low = low.next;
			fast = fast.next.next;
			index++;
		}
		if(fast.next != null && fast.next.next == null) index++;
		first_end.next = low;
		return index;
	}
    
    // 寻找链表中点 + 链表逆序 + 合并链表
    public void reorderList_1(ListNode head) {
    	if(head == null || head.next == null) return ;
    	ListNode temp_head = head;
    	ListNode first_end = getMiddle_1(head);
    	
    	ListNode second_node = first_end.next;
    	first_end.next = null;
    	
    	second_node = reveredList(second_node);
    	
    	ListNode l1 = head;
    	mergeListNode(l1, second_node);
    	
    }
    
    // 反转链表
    private ListNode reveredList(ListNode head) {
    	ListNode pre = null;
    	ListNode curr = head;
    	
    	while(curr != null) {
    		ListNode next_node = curr.next;
    		curr.next = pre;
    		pre = curr;
    		curr = next_node;
    	}
    	
    	return pre;
    }
    
    private ListNode getMiddle_1(ListNode head) {
		ListNode low = head;
		ListNode fast = head;
		while(fast.next != null && fast.next.next != null) {
			low = low.next;
			fast = fast.next.next;
		}
		return low;
	}
    
    // 合并链表
    private void mergeListNode(ListNode l1, ListNode l2) {
    	while(l1 != null && l2 != null) {
    		ListNode l1_temp = l1.next;
    		ListNode l2_temp = l2.next;
    		
    		l1.next = l2;
    		l1 = l1_temp;
    		
    		l2.next = l1;
    		l2 = l2_temp;
    		
    	}
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode five = new ListNode(5,null);
		ListNode four = new ListNode(4,five);
		ListNode three = new ListNode(3,four);
		ListNode second = new ListNode(1,three);
		ListNode first = new ListNode(2,second);
		
		ReorderList reorder = new ReorderList();
		reorder.reorderList_1(second);
		while(second != null) {
			System.out.print(second.val + "->");
			second = second.next;
		}

	}

}
