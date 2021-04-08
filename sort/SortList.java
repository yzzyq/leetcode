package sort;

/*
 * 148. Sort List
 * 
 * Given the head of a linked list, return the list after sorting it in ascending order.

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

 

Example 1:


Input: head = [4,2,1,3]
Output: [1,2,3,4]
Example 2:


Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]
Example 3:

Input: head = []
Output: []
 

Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105
 * 
 * 
 */

// 对链表进行排序

public class SortList {
	
	// 这个超时，无法运行
    public ListNode sortList(ListNode head) {
    	if(head == null || head.next == null) return head;
        ListNode sort_list = new ListNode(Integer.MIN_VALUE);
        
        while(head != null) {
        	ListNode current_list = sort_list;
        	while(head.val > current_list.val && 
        		current_list.next !=null && 
        		head.val > current_list.next.val) {
        		current_list = current_list.next;
        	}
        	
        	ListNode temp = new ListNode(head.val);
        	temp.next = current_list.next;
        	current_list.next = temp;
        	head = head.next;
        }
        
        
        return sort_list.next;
    }
    
    // 归并排序
    public ListNode sortList_1(ListNode head) {
    	if(head == null || head.next == null) return head;
    	
    	// 根据快慢指针找到中间节点
    	ListNode middle_node = getMiddle(head);
    	
    	
    	ListNode right = sortList_1(middle_node.next);
    	middle_node.next = null;
    	ListNode left = sortList_1(head);
    	
    	// 合并节点
    	ListNode merge_node = mergeNode(left, right);
    	
    	return merge_node;
    }

    // 合并节点
	private ListNode mergeNode(ListNode left, ListNode right) {
		ListNode first = new ListNode(0);
		ListNode merge_first = first;
		
		
		while(left != null && right != null) {
			if(left.val < right.val) {
				merge_first.next = left;
				left = left.next;
			}else {
				merge_first.next = right;
				right = right.next;
			}
			merge_first = merge_first.next;
		}
		
		if(left != null) merge_first.next = left;
		if(right != null) merge_first.next = right;
		
		return first.next;
	}

	// 得出中间节点
	private ListNode getMiddle(ListNode head) {
		ListNode low = head;
		ListNode fast = head.next;
		
		while(fast.next != null && fast.next.next != null) {
			low = low.next;
			fast = fast.next.next;
		}
		
		return low;
	}

	public static void main(String[] args) {
		ListNode five = new ListNode(5,null);
		ListNode four = new ListNode(4,five);
		ListNode three = new ListNode(3,four);
		ListNode second = new ListNode(1,three);
		ListNode first = new ListNode(2,second);
		
		SortList sl = new SortList();
		ListNode result = sl.sortList_1(first);
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}

	}

}
