package heapStackQueue;

/**
 * 83. Remove Duplicates from Sorted List
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 * 
 * @author yzzyq
 *
 */

// 删除链表中重复的元素

public class RemoveDuplicatesfromSortedList {
	 
	// 要特别注意11111的情况
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode current_node = head;
        
        while(current_node != null && current_node.next != null) {
        	if(current_node.val == current_node.next.val) {
        		current_node.next = current_node.next.next;
        	}else {
        		current_node = current_node.next;
        	}
        	
        }
        
        return head;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode ln = new ListNode(1);
		ListNode second = new ListNode(1,ln);
		ListNode head = new ListNode(1,second);

		var redfsl = new RemoveDuplicatesfromSortedList();
		
		ListNode result = redfsl.deleteDuplicates(head);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}
		
	}

}
