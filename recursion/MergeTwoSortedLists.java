package recursion;

/**
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be 
 * made by splicing together the nodes of the first two lists.


Example 1:


Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: l1 = [], l2 = []
Output: []
Example 3:

Input: l1 = [], l2 = [0]
Output: [0]
 

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
 * 
 * @author yzzyq
 *
 */

// 合并俩个有序链表

public class MergeTwoSortedLists {
	
	// 直接合并,迭代解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if(l1 == null) return l2;
    	if(l2 == null) return l1;
        ListNode before_node = null;
        ListNode start = l1;
    	while(l1 != null && l2 != null) {
    		if(l1.val > l2.val) {
    			ListNode temp = l2.next;
    			if(before_node == null) {
    				l2.next = l1;
    				start = l2;
    			}else {
    				l2.next = before_node.next;
    				before_node.next = l2;
    			}
    			before_node = l2;
    			l2 = temp;
    		}else {
    			before_node = l1;
    			l1 = l1.next;
    		}
    	}
    	
    	if(l2 != null) before_node.next = l2;
    	
    	return start;
    }
    
    // 递归
    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2) {
    	if(l1 == null) {
    		return l2;
    	}
    	
    	if(l2 == null) {
    		return l1;
    	}
    	
    	if(l1.val < l2.val) {
    		l1.next = mergeTwoLists_1(l1.next,l2);
    		return l1;
    	}else {
    		l2.next = mergeTwoLists_1(l1,l2.next);
    	    return l2;
    	}
    }

	public static void main(String[] args) {
		var mt = new MergeTwoSortedLists();
		ListNode five = new ListNode(5,null);
		ListNode second = new ListNode(2,five);
		ListNode first = new ListNode(1,second);
		
		ListNode six = new ListNode(6,null);
		ListNode three = new ListNode(3,six);
		ListNode l2 = new ListNode(1,three);
		
        ListNode result = mt.mergeTwoLists_1(first, l2);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}
		
	}

}
