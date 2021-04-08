package heapStackQueue;


/**
 * 82. Remove Duplicates from Sorted List II
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, 
 * leaving only distinct numbers from the original list.

Return the linked list sorted as well.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
 * 
 * @author yzzyq
 *
 */

// 依旧是删除重复元素，不过只要是重复的都得删除

public class RemoveDuplicatesfromSortedListII {
	
	// 这里链表的处理可以加一个头指针。也就是变成了双指针
    public ListNode deleteDuplicates(ListNode head) {
    	if(head == null || head.next == null) return head;
    	ListNode current_node = head;
    	ListNode start = new ListNode();
    	start.next = current_node;
    	
    	ListNode before_node = start;
    	
    	while(current_node != null && current_node.next != null) {
    		// 每个都判断next是否相等
    		if(current_node.next.val == before_node.next.val) {
    			while(current_node != null && current_node.next != null && current_node.next.val == before_node.next.val) {
    				current_node = current_node.next;
    			}
    			before_node.next = current_node.next;
    			// 这里before的next已经发生了变化，因此before是不移动的
    			current_node = current_node.next;
    		}else {
    			current_node = current_node.next;
    			before_node = before_node.next;
    		}
    	}
    	return start.next;
    }
    
    
    // 依旧是双指针，大概思路与上面方法一样，只不过写法上稍微不同
    // current不再需要是next
    public ListNode deleteDuplicates_1(ListNode head) {
    	if(head == null || head.next == null) return head;
    	ListNode current_node = head.next;
    	ListNode start = new ListNode();
    	start.next = head;
    	
    	ListNode before_node = start;
    	
    	while(current_node != null) {
    		// 每个都判断next是否相等
    		if(current_node.val == before_node.next.val) {
    			while(current_node != null && current_node.val == before_node.next.val) {
    				current_node = current_node.next;
    			}
    			before_node.next = current_node;
    			current_node = current_node == null ? null:current_node.next;
    		}else {
    			current_node = current_node.next;
    			before_node = before_node.next;
    		}
    	}
    	return start.next;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode ln = new ListNode(3);
		ListNode second = new ListNode(1,ln);
		ListNode head = new ListNode(1,second);

		var redfsl = new RemoveDuplicatesfromSortedListII();
		
		ListNode result = redfsl.deleteDuplicates_1(head);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}

	}

}
